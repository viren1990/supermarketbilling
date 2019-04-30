package io.viren.shopping.utils;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import io.viren.shopping.domains.CustomerDto;
import io.viren.shopping.domains.OrderDto;
import io.viren.shopping.domains.OrderEntryDto;
import io.viren.shopping.domains.ProductDto;
import io.viren.shopping.exception.ProductNotFoundException;
import io.viren.shopping.models.Order;
import io.viren.shopping.models.OrderEntry;
import io.viren.shopping.models.Price;
import io.viren.shopping.models.Product;
import io.viren.shopping.repositories.CustomerRepository;
import io.viren.shopping.repositories.OrderRepository;
import io.viren.shopping.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ResourceUtils {

  @Resource private ProductRepository productRepository;

  @Resource private CustomerRepository customerRepository;

  @Resource private OrderRepository orderRepository;

  public Order createOrderFromDto(final OrderDto order) {
    final var orderModel = new Order();

    if (Objects.nonNull(order.getOrderEntries())) {
      if (Objects.nonNull(order.getUser())) {

        orderModel.setPlacedBy(
            customerRepository
                .findByUid(order.getUser().getUid())
                .orElseThrow(() -> new RuntimeException("User not found")));
      }

      orderModel.setEntries(
          order
              .getOrderEntries()
              .stream()
              .map(entry -> this.createEntry(entry, orderModel))
              .collect(Collectors.toList()));

      final Double currentTotal =
          orderModel
              .getEntries()
              .stream()
              .mapToDouble(OrderEntry::getTotalPrice)
              .reduce(0, Double::sum);

      orderModel.setTotalPrice(currentTotal);
      orderModel.setSubTotal(currentTotal);

      final Price anyPrice =
          orderModel
              .getEntries()
              .stream()
              .map(OrderEntry::getProduct)
              .flatMap(p -> p.getPrices().stream())
              .findAny()
              .orElseThrow();
      // Assume all products have prices in same currency as of now.
      orderModel.setCurrency(anyPrice.getCurrency());

      orderRepository.save(orderModel);
      return orderModel;
    }
    log.warn("Possibly no entries present in order, hence returning null");
    return null;
  }

  private OrderEntry createEntry(final OrderEntryDto entryDto, final Order order) {
    final OrderEntry entry = new OrderEntry();

    final Product product =
        productRepository
            .findByCode(entryDto.getProduct().getCode())
            .orElseThrow(() -> new ProductNotFoundException(entryDto.getProduct().getCode()));
    entry.setProduct(product);
    entry.setOrder(order);
    entry.setQuantity(entryDto.getQuantity());
    entry.setBasePrice(product.getPrices().iterator().next().getValue());
    entry.setQuantity(entryDto.getQuantity());
    entry.setTotalPrice(entry.getBasePrice() * entryDto.getQuantity());
    entry.setEntry(entryDto.getEntry());

    Optional.ofNullable(product.getUnit())
        .ifPresent(
            unit -> {
              entry.setUnit(unit.getCode());
            });
    return entry; 
  }

  public OrderDto createOrderDto(final Order order, CustomerDto customer) {

    final var orderDto = new OrderDto();

    Optional.ofNullable(order.getCurrency())
        .ifPresent(
            currency -> {
              orderDto.setCurrency(currency.getIsoCode());
            });

    final var updatedCustInfo = customer;

    final var customerModel = order.getPlacedBy();

    Optional.ofNullable(customerModel)
        .ifPresent(
            u -> {
              updatedCustInfo.setName(u.getName());
            });
    orderDto.setUser(updatedCustInfo);
    orderDto.setOrderEntries(
        order.getEntries().stream().map(this::createEntryDto).collect(Collectors.toList()));
    orderDto.setId(order.getId());
    orderDto.setTotalPrice(order.getTotalPrice());
    orderDto.setSubTotal(order.getSubTotal());

    orderDto.setSubTotalWithoutDiscounts(
        orderDto
            .getOrderEntries()
            .stream()
            .mapToDouble(entry -> entry.getBasePrice() * entry.getQuantity())
            .reduce(0, Double::sum));

    orderDto.setTotalDiscount(order.getTotalDiscount());
    return orderDto;
  }

  private OrderEntryDto createEntryDto(final OrderEntry entry) {

    final var dto = new OrderEntryDto();
    dto.setEntry(entry.getEntry());
    final var qualtity = entry.getQuantity();
    dto.setQuantity(qualtity);
    dto.setBasePrice(entry.getBasePrice());
    dto.setTotalPrice(entry.getTotalPrice());
    dto.setDiscountedValue(entry.getDiscountedPrice());
    dto.setProduct(Optional.ofNullable(entry.getProduct()).map(this::createProduct).orElse(null));
    dto.setQuantityString(
        new StringBuilder().append(qualtity).append(" ").append(entry.getUnit()).toString());
    dto.setId(entry.getId());
    return dto;
  }

  private ProductDto createProduct(Product product) {
    final var pr = new ProductDto();
    pr.setCode(product.getCode());
    pr.setName(product.getCode());
    return pr;
  }
}
