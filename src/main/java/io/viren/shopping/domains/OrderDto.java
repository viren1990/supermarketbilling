package io.viren.shopping.domains;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class OrderDto {

  private List<OrderEntryDto> orderEntries;

  private CustomerDto user;

  private long id;

  private Double totalPrice;
  
  private String currency;
  private Double totalDiscount;

  private Double subTotal;
  
  private Double subTotalWithoutDiscounts;
}
