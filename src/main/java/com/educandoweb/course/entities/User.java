package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // Para instruir o JPA, passar de POO para relacional, ao conectar ao banco
@Table(name = "tb_user") // especificando o nome da tabela
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id // especificando que o atributo "id" é a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremento, gerado pelo banco
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;

	/*
	 * Mapeamento de OneToMany é opcional: É para acessar um obj do tipo usuário e
	 * acessar automaticamente os pedidos (ORDER) feitos por esse usuário. Mas é
	 * preciso colocar o nome do atributo que ta do outro lado da associação,
	 * nesse caso, o outro lado da associação é ORDER, e o nome do atributo é
	 * "client". mappedby = mapeado por "cliente".
	 */
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>(); // É uma associaçaõ e uma coleção, devido isso faz-se soemnte os
													// get.

	/*
	 * Após o mapeamento "@Entity...@Id..." Pode-se acessar
	 * localhost:8080/h2-console que poderá ver a tabela criada com o nome tb_user,
	 * referente a classe User. h2-console foi o caminho definido em
	 * application-tes.properties
	 */

	public User() {
	}

	public User(Long id, String name, String email, String phone, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrders() {
		return orders;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
