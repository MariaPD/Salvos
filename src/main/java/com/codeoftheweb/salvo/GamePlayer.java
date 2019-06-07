package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

        private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    public GamePlayer() {}

    public GamePlayer(Game game, Player player, LocalDateTime fecha) {
        this.game = game;
        this.player = player;
        this.fecha = fecha;
    }

    public long getId() {return id;}

    public Game getGame() {return game;}

    public void setGame(Game game) {this.game = game;}

    public Player getPlayer() {return player;}

    public void setPlayer(Player player) {this.player = player;}

    public LocalDateTime getFecha() {return fecha;}
}
