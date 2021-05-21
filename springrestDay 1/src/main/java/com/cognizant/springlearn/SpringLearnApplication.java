package com.cognizant.springlearn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.springlearn.model.Country;

@SpringBootApplication
public class SpringLearnApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(SpringLearnApplication.class, args);
		
		
		LOGGER.info("inside main");
		
		displayDate();
		displayCountry();
		displayCountries();
		
	}
	
	
	private static void displayDate() throws ParseException {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
		SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
		String dateStr = "31/12/2018";
		LOGGER.debug(dateStr);
		
		Date date = format.parse(dateStr);
		LOGGER.debug(String.valueOf(date));
		
		LOGGER.info("End");
	}
	
	private static void displayCountry() {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		Country country = context.getBean("country", Country.class);
		Country anotherCountry = context.getBean("country", Country.class);		
		LOGGER.debug("Country : {}", country.toString());
		LOGGER.info("END");
	}
	
	private static void displayCountries() {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		List<Country> country = (List<Country>) context.getBean("countryList");
		
		LOGGER.debug("Country : {}", country);
		LOGGER.info("END");
	}

}
