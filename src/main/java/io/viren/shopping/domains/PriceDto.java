package io.viren.shopping.domains;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class PriceDto {
	
	@NotEmpty
	private Double value;
	
	private String unit;

}
