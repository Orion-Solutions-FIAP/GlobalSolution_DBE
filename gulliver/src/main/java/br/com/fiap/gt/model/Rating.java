package br.com.fiap.gt.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@IdClass(RatingPK.class)

@NamedQueries({
	@NamedQuery(name = "Rating.CountByRentalCompany", query = "SELECT COUNT(r.id) FROM Rating r WHERE r.rentalCompany = :rc"),
	@NamedQuery(name = "Rating.FindByUser", query = "SELECT r FROM Rating r WHERE r.user = :u"),
	@NamedQuery(name = "Rating.FindByRentalCompanyId", query = "SELECT r FROM Rating r WHERE r.rentalCompany.id = :id")
})

@Entity
@Table(name = "T_GT_RATING")
@SequenceGenerator(name = "rating", sequenceName = "SQ_T_GT_RATING", allocationSize = 1)
public class Rating {

	@Id
	@Column(name = "id_rating")
	@GeneratedValue(generator = "rating", strategy = GenerationType.SEQUENCE)
	private int id;

	@JsonBackReference
	@Id
	@ManyToOne(cascade = CascadeType.MERGE )
	@JoinColumn(name = "id_rental_comp")
	private RentalCompany rentalCompany;

	@JsonBackReference(value="user")
	@Id
	@ManyToOne(cascade = CascadeType.MERGE )
	@JoinColumn(name = "id_user")
	private User user;

	@Column(name = "ds_title", nullable = false, length = 60)
	private String title;

	@Column(name = "ds_rating", nullable = false, length = 200)
	private String description;

	@Column(name = "nr_grade", nullable = false, length = 1)
	private int grade;

	public Rating() {

	}

	public Rating(RentalCompany rentalCompany, User user, String title, String description, int grade) {
		super();
		this.rentalCompany = rentalCompany;
		this.user = user;
		this.title = title;
		this.description = description;
		this.grade = grade;
	}

	public Rating(int id, RentalCompany rentalCompany, User user, String title, String description, int grade) {
		super();
		this.id = id;
		this.rentalCompany = rentalCompany;
		this.user = user;
		this.title = title;
		this.description = description;
		this.grade = grade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RentalCompany getRentalCompany() {
		return rentalCompany;
	}

	public void setRentalCompany(RentalCompany rentalCompany) {
		this.rentalCompany = rentalCompany;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Rating [Id=" + id + ", Rental Company Name=" + rentalCompany.getName() + ", User Name=" + user.getName()
				+ ", Title=" + title + ", Description=" + description + ", Grade=" + grade + "]";
	}

}
