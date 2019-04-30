package io.viren.shopping.domains;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class OrderEntryDto {

  private ProductDto product;

  private long id;

  private int quantity;
  
  private String quantityString;
  
  private int entry;
  
  private Double basePrice;
  
  private Double totalPrice;
  
  private Double discountedValue;
}
