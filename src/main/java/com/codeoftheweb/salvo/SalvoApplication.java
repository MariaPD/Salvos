package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

import static java.util.Calendar.PM;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository repoPlayer, GameRepository repoGame, GamePlayerRepository repoGamePlayer) {
		return (args) -> {
			repoPlayer.save(new Player("Jack", "Bauer", "j.bauer@ctu.gov"));
			repoPlayer.save(new Player("Chloe", "O'Brian", "c.obrian@ctu.gov"));
			repoPlayer.save(new Player("Kim", "Bauer", "kim_bauer@gmail.com"));
			repoPlayer.save(new Player("Tony", "Almeida", "t.almeida@ctu.gov"));
			repoGame.save(new Game(LocalDateTime.now()));
			repoGame.save(new Game(LocalDateTime.now().plusHours(1)));
			repoGame.save(new Game(LocalDateTime.now().plusHours(2)));
			repoGamePlayer.save(new GamePlayer());

		};
	}

}


