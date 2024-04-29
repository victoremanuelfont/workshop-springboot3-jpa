package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.Objects;

import com.educandoweb.course.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Primeiro atributo é o identificador que corresponde a chave primária. Vai ser
	 * do tipo OrderItemPK. O tipo do mapeamento é @EmbeddedId. É necessário
	 * instanciar para que o valor do id não seja nulo.
	 */
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();

	private Integer quantity;
	private Double price;

	public OrderItem() {
	}

	/*
	 * Adicionados o Order e o public manualmente no construtor, assim como a
	 * criação dos get e set
	 */

	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}

	/*
	 * Devido a ssociação de mão dupla entre orderItem, e o order, precisa por um
	 * jsonignore para evitar o loop. Nesse caso, foi colado no get, já que no
	 * OrderItem, não tem o atributo direto, ele tem o id e o id que tem a
	 * associação com o Order. Mas o que vale é o metodo Get, pois o GetOrder que
	 * chama o GetOrderItem, e o getOrderItem chama de novo o getOrder, e então
	 * entra no loop. 
	 */
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}

	public void setOrder(Order order) {
		id.setOrder(order);
	}

	
	public Product getProduct() {
		return id.getProduct();
	}

	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}

}
