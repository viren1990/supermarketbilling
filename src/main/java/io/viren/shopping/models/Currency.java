package io.viren.shopping.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CURRENCY")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Currency extends StandardItem{
	
	@Column(name = "symbol")
	private String symbol;

}
