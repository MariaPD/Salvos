package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository repoPlayer, GameRepository repoGame, GamePlayerRepository repoGamePlayer) {
		return (args) -> {
			//Players
			Player player1 = new Player("Jack", "Bauer", "j.bauer@ctu.gov");
			Player player2 = new Player("Chloe", "O'Brian", "c.obrian@ctu.gov");
			Player player3 = new Player("Kim", "Bauer", "kim_bauer@gmail.com");
			Player player4 = new Player("Tony", "Almeida", "t.almeida@ctu.gov");
			//Games
			Game game1 = new Game(LocalDateTime.now());
			Game game2 = new Game(LocalDateTime.now().plusHours(1));
			Game game3 = new Game(LocalDateTime.now().plusHours(2));
			//GamePlayer
			GamePlayer gamePlayer1 = new GamePlayer(game1, player1);
			GamePlayer gamePlayer2 = new GamePlayer(game1, player2);
			GamePlayer gamePlayer3 = new GamePlayer(game2, player2);
			GamePlayer gamePlayer4 = new GamePlayer(game3, player3);

			//Functions
			game1.addGamePlayers(gamePlayer1);
			game1.addGamePlayers(gamePlayer2);
			game2.addGamePlayers(gamePlayer3);
			game3.addGamePlayers(gamePlayer4);

			player1.addGamePlayer(gamePlayer1);
			player2.addGamePlayer(gamePlayer2);
			player2.addGamePlayer(gamePlayer3);
			player3.addGamePlayer(gamePlayer4);


			//Saved data
			repoPlayer.save(player1);
			repoPlayer.save(player2);
			repoPlayer.save(player3);
			repoPlayer.save(player4);

			repoGame.save(game1);
			repoGame.save(game2);
			repoGame.save(game3);

			repoGamePlayer.save(gamePlayer1);
			repoGamePlayer.save(gamePlayer2);
			repoGamePlayer.save(gamePlayer3);
			repoGamePlayer.save(gamePlayer4);

		};
	}

}


