package io.viren.shopping.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories",uniqueConstraints = {@UniqueConstraint(name = "UK_CATEGORY_CODE" , columnNames = {"CATEGORY_CODE"})})
public class Category {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "CATEGORY_CODE")
	@NonNull
	private String code;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "cat2prodrel", joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
	private Set<Product> products;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "cat2catrel", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "subCategory_id"))
	private Set<Category> categories;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "cat2catrel", joinColumns = @JoinColumn(name = "subCategory_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> superCategories;
	
	
}
