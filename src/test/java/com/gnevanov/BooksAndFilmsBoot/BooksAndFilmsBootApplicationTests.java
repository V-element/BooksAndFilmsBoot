package com.gnevanov.BooksAndFilmsBoot;

import com.gnevanov.BooksAndFilmsBoot.controllers.AppController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BooksAndFilmsBootApplicationTests {

	private AppController appController;

	@Autowired
	public void setAppController(AppController appController) {
		this.appController = appController;
	}

	@Test
	void contextLoads() {
		Assertions.assertThat(appController).isNotNull();
	}

}
