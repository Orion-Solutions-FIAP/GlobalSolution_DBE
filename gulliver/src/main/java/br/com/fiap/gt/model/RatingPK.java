package br.com.fiap.gt.model;

import java.io.Serializable;

public class RatingPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int rentalCompany;
	private int user;

	public RatingPK() {

	}

	public RatingPK(int id, int rentalCompany, int user) {
		super();
		this.id = id;
		this.rentalCompany = rentalCompany;
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + rentalCompany;
		result = prime * result + user;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RatingPK other = (RatingPK) obj;
		if (id != other.id)
			return false;
		if (rentalCompany != other.rentalCompany)
			return false;
		if (user != other.user)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRentalCompany() {
		return rentalCompany;
	}

	public void setRentalCompany(int rentalCompany) {
		this.rentalCompany = rentalCompany;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

}
