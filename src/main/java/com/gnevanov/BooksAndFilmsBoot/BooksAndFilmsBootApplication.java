package com.gnevanov.BooksAndFilmsBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class BooksAndFilmsBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksAndFilmsBootApplication.class, args);
	}

}
