package br.com.fiap.gt.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@NamedQuery(name = "RentalCompany.FindByName", query = "SELECT new br.com.fiap.gt.model.RentalCompany(rc.id, rc.name, rc.urlImage, "
		+ "rc.description, rc.link) FROM RentalCompany rc WHERE LOWER(rc.name) LIKE :n")

@Entity
@Table(name = "T_GT_RENTAL_COMPANY")
@SequenceGenerator(name = "rental", sequenceName = "SQ_T_GT_RENTAL_COMPANY", allocationSize = 1)
public class RentalCompany {

	@Id
	@Column(name = "id_rental_comp")
	@GeneratedValue(generator = "rental", strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "nm_rental_company", nullable = false, length = 60)
	private String name;

	@Column(name = "ds_url", nullable = false, length = 200)
	private String urlImage;

	@Column(name = "ds_long", nullable = false, length = 50)
	private String longitude;

	@Column(name = "ds_lat", nullable = false, length = 50)
	private String latitude;

	@Column(name = "ds_rental_company", nullable = false, length = 300)
	private String description;

	@Column(name = "ds_link", nullable = false, length = 300)
	private String link;

	@JsonManagedReference
	@JsonInclude(value = Include.NON_NULL)
	@OneToMany(mappedBy = "rentalCompany", cascade = CascadeType.ALL)
	private List<Car> cars;

	@JsonManagedReference
	@JsonInclude(value = Include.NON_NULL)
	@OneToMany(mappedBy = "rentalCompany", cascade = CascadeType.ALL)
	private List<Schedule> schedules;

	@JsonManagedReference
	@JsonInclude(value = Include.NON_NULL)
	@OneToMany(mappedBy = "rentalCompany", cascade = CascadeType.ALL)
	private List<Rating> ratings;

	public RentalCompany() {
	}

	public RentalCompany(String name, String urlImage, String longitude, String latitude, String description,
			String link) {
		super();
		this.name = name;
		this.urlImage = urlImage;
		this.longitude = longitude;
		this.latitude = latitude;
		this.description = description;
		this.link = link;
	}

	public RentalCompany(int id, String name, String urlImage, String longitude, String latitude, String description,
			String link) {
		super();
		this.id = id;
		this.name = name;
		this.urlImage = urlImage;
		this.longitude = longitude;
		this.latitude = latitude;
		this.description = description;
		this.link = link;
	}

	public RentalCompany(int id, String name, String urlImage, String description, String link) {
		super();
		this.id = id;
		this.name = name;
		this.urlImage = urlImage;
		this.description = description;
		this.link = link;
	}

	public void addCar(Car car) {
		if (this.cars == null)
			this.cars = new ArrayList<Car>();
		this.cars.add(car);
		car.setRentalCompany(this);
	}

	public void addSchedule(Schedule schedule) {
		if (this.schedules == null)
			this.schedules = new ArrayList<Schedule>();
		this.schedules.add(schedule);
		schedule.setRentalCompany(this);
	}

	public void addRating(Rating rating) {
		if (this.getRatings() == null)
			this.setRatings(new ArrayList<Rating>());
		this.getRatings().add(rating);
		rating.setRentalCompany(this);
	}
	
	public int getRatingsAvg() {
        int media = 0;
        for (Rating r : this.ratings) {
            media += r.getGrade();
        }
        media = media/this.ratings.size();
        return media;
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

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	@Override
	public String toString() {
		return "RentalCompany [Id=" + id + ", Name=" + name + ", Url Image=" + urlImage + ", Description=" + description
				+ ", Link=" + link + "]";
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
}
