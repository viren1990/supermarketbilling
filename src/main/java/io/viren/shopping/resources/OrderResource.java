package io.viren.shopping.resources;

import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import io.viren.shopping.domains.OrderDto;
import io.viren.shopping.promotions.services.PromotionEngineService;
import io.viren.shopping.repositories.OrderRepository;
import io.viren.shopping.utils.ResourceUtils;
import lombok.extern.slf4j.Slf4j;

@RepositoryRestController
@RequestMapping("/orders")
@Slf4j
public class OrderResource {

  @Resource private OrderRepository orderRepository;

  @Resource private PromotionEngineService promotionEngineService;

  @Resource private ResourceUtils resourceUtils;

  @PostMapping
  public ResponseEntity<OrderDto> submitOrder(
      final HttpServletRequest request,
      final HttpServletResponse response,
      @Valid @RequestBody final OrderDto order) {

    final var gson = new Gson();
    log.info("Input order is {}", gson.toJson(order));

    final var orderModel = resourceUtils.createOrderFromDto(order);

    if (Objects.nonNull(orderModel)) {
      promotionEngineService.updateOrderPromotions(orderModel);

      final var updated = orderRepository.findById(orderModel.getId()).orElseThrow();

      log.info("Updated Order total is {}", updated.getSubTotal());
      log.info("Updated total saving is {}", updated.getTotalDiscount());

      return new ResponseEntity<OrderDto>(
          resourceUtils.createOrderDto(updated, order.getUser()), HttpStatus.OK);
    }
    return new ResponseEntity<OrderDto>(HttpStatus.BAD_REQUEST);
  }
}
