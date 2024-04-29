package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;

	/*
	 * ManyToMany com JoinTable. 1) Escolhe uma das classes, tanto faz, poderia ser
	 * Product ou Category. Faz o mapeamento @ManyToMany, para que as Coleções Set
	 * se tornem tabelas no banco de dados. 2) Usa o JoinTable para dizer qual o
	 * nome da tabela e qual vai ser a chave estrangeira que vai associar a tabela
	 * de product com a tabela de category. joinColumns = nome da chave estrangeira
	 * referente a tabela de Product. inverseJoinColumns = Definir a chave
	 * estrangeira da outra entidade, que nesse caso, como estou na classe Product,
	 * então é a outra entidade é a tabela da classe Category.
	 * 
	 * 
	 */
	@ManyToMany
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> items = new HashSet<>();

	public Product() {

	}

	// Não coloca coleções no Construtor. Pq ela já está sendo instanciada.
	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	/*
	 * getOrders() = mesmo nome projetado no diagrama (- orders). É necessário
	 * varrer os orderItem, e para cada orderItem será pego o order associado ao
	 * orderItem.
	 */
	@JsonIgnore
	public Set<Order> getOrders() {
		Set<Order> set = new HashSet<>();
		for (OrderItem x : items) { // Percorrendo cada objeto do tipo OrderItem contido na lista de "items"
			set.add(x.getOrder());// Vai ser adicionado no set
		}
		return set; // Apos fazer isso com todos, será retornado o set
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

}
