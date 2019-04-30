package io.viren.shopping.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderentries")
@Entity
public class OrderEntry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDER_ENTRY_ID")
  private long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="product")
  @NotNull
  private Product product;

  @ManyToOne(optional = false, fetch = FetchType.LAZY , cascade=CascadeType.ALL)
  @JoinColumn(name = "ORDER_ID", nullable = false)
  @NotNull
  private Order order;

  @Column(name = "ORDER_ENTRY_QUANTITY")
  private int quantity;

  private Double basePrice;

  private Double totalPrice;
  
  private Double discountedPrice;
  
  private int entry;
  
  private String unit;
}
