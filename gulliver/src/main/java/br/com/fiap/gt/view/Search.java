package br.com.fiap.gt.view;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.gt.model.Car;
import br.com.fiap.gt.model.Rating;
import br.com.fiap.gt.model.RentalCompany;
import br.com.fiap.gt.model.Schedule;
import br.com.fiap.gt.model.User;
import br.com.fiap.gt.dao.CarDao;
import br.com.fiap.gt.dao.RatingDao;
import br.com.fiap.gt.dao.RentalCompanyDao;
import br.com.fiap.gt.dao.ScheduleDao;
import br.com.fiap.gt.dao.UserDao;
import br.com.fiap.gt.dao.impl.CarDaoImpl;
import br.com.fiap.gt.dao.impl.RatingDaoImpl;
import br.com.fiap.gt.dao.impl.RentalCompanyDaoImpl;
import br.com.fiap.gt.dao.impl.ScheduleDaoImpl;
import br.com.fiap.gt.dao.impl.UserDaoImpl;
import br.com.fiap.gt.exception.EntityNotFoundException;
import br.com.fiap.gt.singleton.EntityManagerFactorySingleton;

public class Search {

	public static void main(String[] args) {

		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();

		UserDao userDao = new UserDaoImpl(em);
		RentalCompanyDao rentalCompanyDao = new RentalCompanyDaoImpl(em);
		RatingDao ratingDao = new RatingDaoImpl(em);
		ScheduleDao scheduleDao = new ScheduleDaoImpl(em);
		CarDao carDao = new CarDaoImpl(em);

		RentalCompany rentalCompany = null;

		System.out.println("Find User By E-mail");
		User user = userDao.findByEmail("Kelly.test@gmail.com");

		if (user == null)
			System.out.println("User not found");
		else
			System.out.println(user);

		System.out.println("*".repeat(60));

		try {

			System.out.println("Count Rating By Rental Company");
			rentalCompany = rentalCompanyDao.search(1);

			if (rentalCompany == null)
				throw new EntityNotFoundException();

			Long qttRating = ratingDao.countByRentalCompany(rentalCompany);
			System.out.println("Quantity Rating: " + qttRating);
			System.out.println("*".repeat(60));

		} catch (EntityNotFoundException e) {
			System.out.println(e.getMessage());
		}

		if (user != null) {
			System.out.println("Find Rating By User");
			List<Rating> ratings = ratingDao.findByUser(user);

			if (!ratings.isEmpty())
				ratings.forEach(r -> System.out.println(r));
			else
				System.out.println("No rating found");

			System.out.println("*".repeat(60));
		}

		System.out.println("Find Rental Companies By Name");
		List<RentalCompany> rentalCompanies = rentalCompanyDao.findByName("loca");
		if (!rentalCompanies.isEmpty())
			rentalCompanies.forEach(rc -> System.out.println(rc));
		else
			System.out.println("No car rental companies were found.");
		System.out.println("*".repeat(60));

		System.out.println("Find Schedules By Rental Company");
		if (rentalCompany != null) {
			List<Schedule> schedules = scheduleDao.findByRentalCompany(rentalCompany);
			System.out.println("Rental Company Name: " + rentalCompany.getName());
			schedules.forEach(s -> System.out.println(s));
		} else
			System.out.println("No car rental companies were found to get schedules.");
		System.out.println("*".repeat(60));

		System.out.println("Find By Brand And Is Active");
		List<Car> cars = carDao.findByBrandAndIsActive("Rena", true);
		if (!cars.isEmpty())
			cars.forEach(c -> System.out.println(c));
		else
			System.out.println("No car of the brand was found");
		System.out.println("*".repeat(60));

		em.close();
		EntityManagerFactorySingleton.getInstance().close();

	}
}
