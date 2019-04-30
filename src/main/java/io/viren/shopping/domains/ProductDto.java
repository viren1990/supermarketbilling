package io.viren.shopping.domains;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ProductDto {	

  @NotEmpty private String code;
  private String name;
  private String description;
  @NotEmpty private List<PriceDto> prices;

  @NotEmpty private List<CategoryDto> categories;
}
