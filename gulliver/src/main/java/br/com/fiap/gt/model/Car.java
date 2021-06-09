package br.com.fiap.gt.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@NamedQueries({
	@NamedQuery(name = "Car.FindByBrandAndIsActive", query = "SELECT c FROM Car c WHERE LOWER(c.brand) LIKE :b AND c.isActive = :is"),
	@NamedQuery(name = "Car.FindByRentalCompanyId", query = "SELECT c FROM Car c WHERE c.rentalCompany.id = :id") 
})

@Entity
@Table(name = "T_GT_CAR")
@SequenceGenerator(name = "car", sequenceName = "SQ_T_GT_CAR", allocationSize = 1)
public class Car {

	@Id
	@Column(name = "id_car")
	@GeneratedValue(generator = "car", strategy = GenerationType.SEQUENCE)
	private int id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_rental_comp", nullable = false)
	private RentalCompany rentalCompany;

	@Column(name = "ds_model", nullable = false, length = 60)
	private String model;

	@Column(name = "st_isActive", nullable = false)
	private boolean isActive;

	@Column(name = "vl_daily", nullable = false, precision = 11, scale = 2)
	private BigDecimal dailyValue;

	@Column(name = "ds_brand", nullable = false, length = 60)
	private String brand;

	public Car() {

	}

	public Car(RentalCompany rentalCompany, String model, boolean isActive, BigDecimal dailyValue, String brand) {
		super();
		this.rentalCompany = rentalCompany;
		this.model = model;
		this.isActive = isActive;
		this.dailyValue = dailyValue;
		this.brand = brand;
	}

	public Car(int id, RentalCompany rentalCompany, String model, boolean isActive, BigDecimal dailyValue,
			String brand) {
		super();
		this.id = id;
		this.rentalCompany = rentalCompany;
		this.model = model;
		this.isActive = isActive;
		this.dailyValue = dailyValue;
		this.brand = brand;
	}

	public Car(String model, boolean isActive, BigDecimal dailyValue, String brand) {
		super();
		this.model = model;
		this.isActive = isActive;
		this.dailyValue = dailyValue;
		this.brand = brand;
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public BigDecimal getDailyValue() {
		return dailyValue;
	}

	public void setDailyValue(BigDecimal dailyValue) {
		this.dailyValue = dailyValue;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Car [Id=" + id + ", Model=" + model + ", Is Active=" + isActive + ", Daily Value=" + dailyValue
				+ ", Brand=" + brand + "]";
	}
}
