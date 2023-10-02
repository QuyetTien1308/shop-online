package com.example.shoponline.entities;

import com.example.shoponline.utils.OrderDetailId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_details")
@IdClass(OrderDetailId.class)
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "order_id", length = 10, nullable = false)
	private String orderId;

	@Id
	@Column(name = "product_id",  length = 36, nullable = false)
	private String productId;
	
	@Column(name = "quantity", length = 11, nullable = false)
	private int quantity;

	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "order_id") private Order order;
	 * 
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "product_id") private Product product;
	 */
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/*
	 * public Order getOrder() { return order; }
	 * 
	 * public void setOrder(Order order) { this.order = order; }
	 * 
	 * public Product getProduct() { return product; }
	 * 
	 * public void setProduct(Product product) { this.product = product; }
	 */
}
