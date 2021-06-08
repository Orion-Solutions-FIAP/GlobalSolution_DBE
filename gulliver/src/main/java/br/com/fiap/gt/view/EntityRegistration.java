package br.com.fiap.gt.view;

import java.math.BigDecimal;
import java.time.LocalTime;

import javax.persistence.EntityManager;
import br.com.fiap.gt.model.Car;
import br.com.fiap.gt.model.Day;
import br.com.fiap.gt.model.Rating;
import br.com.fiap.gt.model.RentalCompany;
import br.com.fiap.gt.model.Schedule;
import br.com.fiap.gt.model.User;
import br.com.fiap.gt.dao.RentalCompanyDao;
import br.com.fiap.gt.dao.impl.RentalCompanyDaoImpl;
import br.com.fiap.gt.exception.CommitException;
import br.com.fiap.gt.singleton.EntityManagerFactorySingleton;

public class EntityRegistration {

	public static void main(String[] args) {

		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		RentalCompanyDao rentalCompanyDao = new RentalCompanyDaoImpl(em);

		// Criando uma locadora de carro
		RentalCompany rentalCompany = new RentalCompany("Localiza",
				"https://upload.wikimedia.org/wikipedia/pt/c/c8/Logotipo_da_Localiza_em_2014.png", "-46.645992",
				"-23.546838",
				"Localiza Rent a Car, conhecida apenas como Localiza, � uma rede brasileira de lojas especializadas em aluguel de carros",
				"https://www.localiza.com/brasil/pt-br");

		// Adicionando hor�rios da locadora de carro
		LocalTime openingHour = LocalTime.of(8, 30);
		LocalTime closingHour = LocalTime.of(18, 30);
		
		
		rentalCompany.addSchedule(new Schedule(Day.MONDAY, openingHour, closingHour));
		rentalCompany.addSchedule(new Schedule(Day.TUESDAY, openingHour, closingHour));
		rentalCompany.addSchedule(new Schedule(Day.WEDNESDAY, openingHour, closingHour));
		rentalCompany.addSchedule(new Schedule(Day.THURSDAY, openingHour, closingHour));
		rentalCompany.addSchedule(new Schedule(Day.FRIDAY, openingHour, closingHour));
		rentalCompany.addSchedule(new Schedule(Day.SATURDAY, openingHour, closingHour));
		rentalCompany.addSchedule(new Schedule(Day.SUNDAY, openingHour, closingHour));

		// Adicionando carros na locadora de carro
		rentalCompany.addCar(new Car("Kwid 1.0", true, new BigDecimal(135.66), "Renault"));
		rentalCompany.addCar(new Car("HB20 1.0", true, new BigDecimal(135.66), "Hyundai"));
		rentalCompany.addCar(new Car("Corolla 1.8", true, new BigDecimal(135.66), "Toyota"));

		// Criando um usu�rio
		User user = new User("Kelly", "kelly.test@gmail.com", "teste123", "11999999999");

		//Criando avalia��o
		Rating rating = new Rating(rentalCompany, user, "TOP!!", "Muito Bom :D", 5);
		
		// Adicionando a avalia��o � locadora
		rentalCompany.addRating(rating);
		
		try {
			rentalCompanyDao.create(rentalCompany);
			rentalCompanyDao.commit();
			System.out.println("Success!!");
		} catch (CommitException e) {
			System.out.println(e.getMessage());
		}

		em.close();
		EntityManagerFactorySingleton.getInstance().close();
	}
}
