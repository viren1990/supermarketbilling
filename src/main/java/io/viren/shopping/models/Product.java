package io.viren.shopping.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "products", uniqueConstraints = {
		@UniqueConstraint(name = "UK_PRODUCT_CODE", columnNames = { "PRODUCT_CODE" }) })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "PRODUCT_CODE")
	@NonNull
	private String code;

	@Column(name = "PRODUCT_DESCRIPTION")
	private String description;
	
	@OneToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "unit")
	private Unit unit;

	@ManyToMany(mappedBy = "products",cascade = CascadeType.ALL)
	@NotEmpty
	private Set<Category> superCategories;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@NotEmpty
	private List<Price> prices;

}
