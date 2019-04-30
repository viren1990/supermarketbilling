package io.viren.shopping.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Price {
	
	@Id
	@GeneratedValue
	@Column(name = "PRICE_ID")
	private long id;
	
	@Column(name = "PRICE_VALUE")
	private double value;
	
	@OneToOne(fetch = FetchType.LAZY)
	@NotNull
	private Currency currency;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date endDate;
	
	@OneToOne(fetch = FetchType.LAZY)
	@NotNull
	private Unit unit;
	
	private Double unitFactor;
	
	@ManyToOne(optional = false , fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID" , nullable = false)
	private Product product;

}
