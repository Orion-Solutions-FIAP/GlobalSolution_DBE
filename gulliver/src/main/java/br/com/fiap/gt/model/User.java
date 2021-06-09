package br.com.fiap.gt.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@NamedQuery(name = "User.FindByEmail", query = "SELECT new br.com.fiap.gt.model.User(u.id, u.name, u.email, u.phoneNumber) FROM User u WHERE LOWER(u.email) = :e")

@Entity
@Table(name = "T_GT_USER")
@SequenceGenerator(name = "user", sequenceName = "SQ_T_GT_USER", allocationSize = 1)
public class User {

	@Id
	@Column(name = "id_user")
	@GeneratedValue(generator = "user", strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "nm_user", nullable = false, length = 60)
	private String name;

	@Column(name = "ds_email", nullable = false, length = 100, unique = true)
	private String email;

	@Column(name = "ds_password", nullable = false, length = 40)
	private String password;

	@Column(name = "nr_phone", nullable = false, length = 11)
	private String phoneNumber;

	@JsonManagedReference(value = "user")
	@JsonInclude(value = Include.NON_NULL)
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Rating> ratings;

	public User() {

	}

	public User(String name, String email, String password, String phoneNumber) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public User(int id, String name, String email, String password, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public User(int id, String name, String email, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "User [Id=" + id + ", Name=" + name + ", Email=" + email + ", Phone Number=" + phoneNumber + "]";
	}
}
