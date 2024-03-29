package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class GamePlayer {
    //Attributes
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

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    Set<Ship> ships = new HashSet<>();

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    Set<Salvo> salvos = new HashSet<>();

    //Constructors
    public GamePlayer() {}

    public GamePlayer(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.fecha = LocalDateTime.now();
    }

    //Methods
    public long getID() {return id;}

    public Game getGame() {return game;}

    public void setGame(Game game) {this.game = game;}

    public Player getPlayer() {return player;}

    public void setPlayer(Player player) {this.player = player;}

    public LocalDateTime getFecha() {return fecha;}

    public Set<Ship> getShips() {
        return ships;
    }

    public void addShip (Ship ship) {
        ship.setGamePlayer(this);
        ships.add(ship);
    }

    public Set<Salvo> getSalvos() {
        return salvos;
    }

    public void addSalvo (Salvo salvo) {
        salvo.setGamePlayer(this);
        salvos.add(salvo);
    }

    public Map<String, Object> makeGamePlayerDTO() {

         return new LinkedHashMap<String,Object>(){{
            put("id", id);
            put("player", player.makePlayerDTO());
        }};

    }
}
