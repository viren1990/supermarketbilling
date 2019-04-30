package io.viren.shopping.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMERS")
public class Customer {
	
	@Id
	@GeneratedValue
	@Column(name = "CUSTOMER_ID")
	private long id;
	
	@Column(name = "CUSTOMER_UID",unique = true)
	private String uid;
	
	private String name;
	
	private int age;
	
	@OneToMany(mappedBy ="placedBy",cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	private List<Order> orders;
	
}
