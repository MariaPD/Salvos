package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(PlayerRepository repoPlayer, GameRepository repoGame, GamePlayerRepository repoGamePlayer, ShipRepository repoShip, SalvoRepository repoSalvo, ScoreRepository repoScore) {
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
			Game game4 = new Game(LocalDateTime.now().plusHours(3));
			Game game5 = new Game(LocalDateTime.now().plusHours(4));
			Game game6 = new Game(LocalDateTime.now().plusHours(5));
			Game game7 = new Game(LocalDateTime.now().plusHours(6));
			Game game8 = new Game(LocalDateTime.now().plusHours(7));
			//GamePlayer
			GamePlayer gamePlayer1 = new GamePlayer(game1, player1);
			GamePlayer gamePlayer2 = new GamePlayer(game1, player2);
			GamePlayer gamePlayer3 = new GamePlayer(game2, player1);
			GamePlayer gamePlayer4 = new GamePlayer(game2, player2);
			GamePlayer gamePlayer5 = new GamePlayer(game3, player2);
			GamePlayer gamePlayer6 = new GamePlayer(game3, player4);
			GamePlayer gamePlayer7 = new GamePlayer(game4, player2);
			GamePlayer gamePlayer8 = new GamePlayer(game4, player1);
			GamePlayer gamePlayer9 = new GamePlayer(game5, player4);
			GamePlayer gamePlayer10 = new GamePlayer(game5, player1);
			GamePlayer gamePlayer11 = new GamePlayer(game6, player3);
			GamePlayer gamePlayer12 = new GamePlayer(game7, player4);
			GamePlayer gamePlayer13 = new GamePlayer(game8, player3);
			GamePlayer gamePlayer14 = new GamePlayer(game8, player4);
			//Ships
			Ship ship1 = new Ship("Destroyer", Arrays.asList("H2","H3","H4"));
			Ship ship2 = new Ship("Submarine", Arrays.asList("E1","F1","G1"));
			Ship ship3 = new Ship("Patrol Boat", Arrays.asList("B4","B5"));
			Ship ship4 = new Ship("Destroyer", Arrays.asList("B5","C5","D5"));
			Ship ship5 = new Ship("Patrol Boat", Arrays.asList("F1","F2"));
			Ship ship6 = new Ship("Destroyer", Arrays.asList("B5","C5","D5"));
			Ship ship7 = new Ship("Patrol Boat", Arrays.asList("C6","C7"));
			Ship ship8 = new Ship("Submarine", Arrays.asList("A2","A3","A4"));
			Ship ship9 = new Ship("Patrol Boat", Arrays.asList("G6","H6"));
			Ship ship10 = new Ship("Destroyer", Arrays.asList("B5","C5","D5"));
			Ship ship11 = new Ship("Patrol Boat", Arrays.asList("C6","C7"));
			Ship ship12 = new Ship("Submarine", Arrays.asList("A2","A3","A4"));
			Ship ship13 = new Ship("Patrol Boat", Arrays.asList("G6","H6"));
			Ship ship14 = new Ship("Destroyer", Arrays.asList("B5","C5","D5"));
			Ship ship15 = new Ship("Patrol Boat", Arrays.asList("C6","C7"));
			Ship ship16 = new Ship("Submarine", Arrays.asList("A2","A3","A4"));
			Ship ship17 = new Ship("Patrol Boat", Arrays.asList("G6","H6"));
			Ship ship18 = new Ship("Destroyer", Arrays.asList("B5","C5","D5"));
			Ship ship19 = new Ship("Patrol Boat", Arrays.asList("C6","C7"));
			Ship ship20 = new Ship("Submarine", Arrays.asList("A2","A3","A4"));
			Ship ship21 = new Ship("Patrol Boat", Arrays.asList("G6","H6"));
			Ship ship22 = new Ship("Destroyer", Arrays.asList("B5","C5","D5"));
			Ship ship23 = new Ship("Patrol Boat", Arrays.asList("C6","C7"));
			Ship ship24 = new Ship("Destroyer", Arrays.asList("B5","C5","D5"));
			Ship ship25 = new Ship("Patrol Boat", Arrays.asList("C6","C7"));
			Ship ship26 = new Ship("Submarine", Arrays.asList("A2","A3","A4"));
			Ship ship27 = new Ship("Patrol Boat", Arrays.asList("G6","H6"));
			//Salvo
			Salvo salvo1 = new Salvo(1, Arrays.asList("B5", "C5", "F1"));
			Salvo salvo2 = new Salvo(1, Arrays.asList("B4", "B5", "B6"));
			Salvo salvo3 = new Salvo(2, Arrays.asList("F2", "D5"));
			Salvo salvo4 = new Salvo(2, Arrays.asList("E1", "H3", "A2"));
			Salvo salvo5 = new Salvo(1, Arrays.asList("A2", "A4", "G6"));
			Salvo salvo6 = new Salvo(1, Arrays.asList("B5", "D5", "C7"));
			Salvo salvo7 = new Salvo(2, Arrays.asList("A3", "H6"));
			Salvo salvo8 = new Salvo(2, Arrays.asList("C5", "C6"));
			Salvo salvo9 = new Salvo(1, Arrays.asList("G6", "H6", "A4"));
			Salvo salvo10 = new Salvo(1, Arrays.asList("H1", "H2", "H3"));
			Salvo salvo11 = new Salvo(2, Arrays.asList("A2", "A3", "D8"));
			Salvo salvo12 = new Salvo(2, Arrays.asList("E1", "F2", "G3"));
			Salvo salvo13 = new Salvo(1, Arrays.asList("A3", "A4", "F7"));
			Salvo salvo14 = new Salvo(1, Arrays.asList("B5", "C6", "H1"));
			Salvo salvo15 = new Salvo(2, Arrays.asList("A2", "G6", "H6"));
			Salvo salvo16 = new Salvo(2, Arrays.asList("C5", "C7", "D5"));
			Salvo salvo17 = new Salvo(1, Arrays.asList("A1", "A2", "A3"));
			Salvo salvo18 = new Salvo(1, Arrays.asList("B5", "B6", "C7"));
			Salvo salvo19 = new Salvo(2, Arrays.asList("G6", "G7", "G8"));
			Salvo salvo20 = new Salvo(2, Arrays.asList("C6", "D6", "E6"));
			Salvo salvo21 = new Salvo(3, Arrays.asList("H1", "H8"));

			Score score1 = new Score (game1, player1, 1.0);
			Score score2 = new Score (game1, player2, 0.0);
			Score score3 = new Score (game2, player1, 0.5);
			Score score4 = new Score (game2, player2, 0.5);
			Score score5 = new Score (game3, player2, 1.0);
			Score score6 = new Score (game3, player4, 0.0);
			Score score7 = new Score (game4, player2, 0.5);
			Score score8 = new Score (game4, player1, 0.5);

			//Functions
			game1.addGamePlayers(gamePlayer1);
			game1.addGamePlayers(gamePlayer2);
			game2.addGamePlayers(gamePlayer3);
			game2.addGamePlayers(gamePlayer4);
			game3.addGamePlayers(gamePlayer5);
			game3.addGamePlayers(gamePlayer6);
			game4.addGamePlayers(gamePlayer7);
			game4.addGamePlayers(gamePlayer8);
			game5.addGamePlayers(gamePlayer9);
			game5.addGamePlayers(gamePlayer10);
			game6.addGamePlayers(gamePlayer11);
			game7.addGamePlayers(gamePlayer12);
			game8.addGamePlayers(gamePlayer13);
			game8.addGamePlayers(gamePlayer14);

			player1.addGamePlayer(gamePlayer1);
			player2.addGamePlayer(gamePlayer2);
			player1.addGamePlayer(gamePlayer3);
			player2.addGamePlayer(gamePlayer4);
			player2.addGamePlayer(gamePlayer5);
			player4.addGamePlayer(gamePlayer6);
			player2.addGamePlayer(gamePlayer7);
			player1.addGamePlayer(gamePlayer8);
			player4.addGamePlayer(gamePlayer9);
			player1.addGamePlayer(gamePlayer10);
			player3.addGamePlayer(gamePlayer11);
			player4.addGamePlayer(gamePlayer12);
			player3.addGamePlayer(gamePlayer13);
			player4.addGamePlayer(gamePlayer14);

			gamePlayer1.addShip(ship1);
			gamePlayer1.addShip(ship2);
			gamePlayer1.addShip(ship3);
			gamePlayer2.addShip(ship4);
			gamePlayer2.addShip(ship5);
			gamePlayer3.addShip(ship6);
			gamePlayer3.addShip(ship7);
			gamePlayer4.addShip(ship8);
			gamePlayer4.addShip(ship9);
			gamePlayer5.addShip(ship10);
			gamePlayer5.addShip(ship11);
			gamePlayer6.addShip(ship12);
			gamePlayer6.addShip(ship13);
			gamePlayer7.addShip(ship14);
			gamePlayer7.addShip(ship15);
			gamePlayer8.addShip(ship16);
			gamePlayer8.addShip(ship17);
			gamePlayer9.addShip(ship18);
			gamePlayer9.addShip(ship19);
			gamePlayer10.addShip(ship20);
			gamePlayer10.addShip(ship21);
			gamePlayer11.addShip(ship22);
			gamePlayer11.addShip(ship23);
			gamePlayer12.addShip(ship24);
			gamePlayer12.addShip(ship25);
			gamePlayer14.addShip(ship26);
			gamePlayer14.addShip(ship27);

			gamePlayer1.addSalvo(salvo1);
			gamePlayer2.addSalvo(salvo2);
			gamePlayer1.addSalvo(salvo3);
			gamePlayer2.addSalvo(salvo4);
			gamePlayer3.addSalvo(salvo5);
			gamePlayer4.addSalvo(salvo6);
			gamePlayer3.addSalvo(salvo7);
			gamePlayer4.addSalvo(salvo8);
			gamePlayer5.addSalvo(salvo9);
			gamePlayer6.addSalvo(salvo10);
			gamePlayer5.addSalvo(salvo11);
			gamePlayer6.addSalvo(salvo12);
			gamePlayer7.addSalvo(salvo13);
			gamePlayer8.addSalvo(salvo14);
			gamePlayer7.addSalvo(salvo15);
			gamePlayer8.addSalvo(salvo16);
			gamePlayer9.addSalvo(salvo17);
			gamePlayer10.addSalvo(salvo18);
			gamePlayer9.addSalvo(salvo19);
			gamePlayer10.addSalvo(salvo20);
			gamePlayer10.addSalvo(salvo21);

			game1.addScore(score1);
			game1.addScore(score2);
			game2.addScore(score3);
			game2.addScore(score4);
			game3.addScore(score5);
			game3.addScore(score6);
			game4.addScore(score7);
			game4.addScore(score8);

			player1.addScore(score1);
			player2.addScore(score2);
			player1.addScore(score3);
			player2.addScore(score4);
			player2.addScore(score5);
			player4.addScore(score6);
			player2.addScore(score7);
			player1.addScore(score8);


			//Saved data
			repoPlayer.save(player1);
			repoPlayer.save(player2);
			repoPlayer.save(player3);
			repoPlayer.save(player4);

			repoGame.save(game1);
			repoGame.save(game2);
			repoGame.save(game3);
			repoGame.save(game4);
			repoGame.save(game5);
			repoGame.save(game6);
			repoGame.save(game7);
			repoGame.save(game8);

			repoGamePlayer.save(gamePlayer1);
			repoGamePlayer.save(gamePlayer2);
			repoGamePlayer.save(gamePlayer3);
			repoGamePlayer.save(gamePlayer4);
			repoGamePlayer.save(gamePlayer5);
			repoGamePlayer.save(gamePlayer6);
			repoGamePlayer.save(gamePlayer7);
			repoGamePlayer.save(gamePlayer8);
			repoGamePlayer.save(gamePlayer9);
			repoGamePlayer.save(gamePlayer10);
			repoGamePlayer.save(gamePlayer11);
			repoGamePlayer.save(gamePlayer12);
			repoGamePlayer.save(gamePlayer13);
			repoGamePlayer.save(gamePlayer14);

			repoShip.save(ship1);
			repoShip.save(ship2);
			repoShip.save(ship3);
			repoShip.save(ship4);
			repoShip.save(ship5);
			repoShip.save(ship6);
			repoShip.save(ship7);
			repoShip.save(ship8);
			repoShip.save(ship9);
			repoShip.save(ship10);
			repoShip.save(ship11);
			repoShip.save(ship12);
			repoShip.save(ship13);
			repoShip.save(ship14);
			repoShip.save(ship15);
			repoShip.save(ship16);
			repoShip.save(ship17);
			repoShip.save(ship18);
			repoShip.save(ship19);
			repoShip.save(ship20);
			repoShip.save(ship21);
			repoShip.save(ship22);
			repoShip.save(ship23);
			repoShip.save(ship24);
			repoShip.save(ship25);
			repoShip.save(ship26);
			repoShip.save(ship27);

			repoSalvo.save(salvo1);
			repoSalvo.save(salvo2);
			repoSalvo.save(salvo3);
			repoSalvo.save(salvo4);
			repoSalvo.save(salvo5);
			repoSalvo.save(salvo6);
			repoSalvo.save(salvo7);
			repoSalvo.save(salvo8);
			repoSalvo.save(salvo9);
			repoSalvo.save(salvo10);
			repoSalvo.save(salvo11);
			repoSalvo.save(salvo12);
			repoSalvo.save(salvo13);
			repoSalvo.save(salvo14);
			repoSalvo.save(salvo15);
			repoSalvo.save(salvo16);
			repoSalvo.save(salvo17);
			repoSalvo.save(salvo18);
			repoSalvo.save(salvo19);
			repoSalvo.save(salvo20);
			repoSalvo.save(salvo21);

			repoScore.save(score1);
			repoScore.save(score2);
			repoScore.save(score3);
			repoScore.save(score4);
			repoScore.save(score5);
			repoScore.save(score6);
			repoScore.save(score7);
			repoScore.save(score8);


		};
	}

}


