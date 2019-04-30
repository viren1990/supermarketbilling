package io.viren.shopping.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class StandardItem {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "IsoCode")
	private String isoCode;
	
	@Column(name = "active")
	private boolean active;
	
}
