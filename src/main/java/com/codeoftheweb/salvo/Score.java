package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Score {
    //Atributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Double score;

    private LocalDateTime finishDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    //Constructors

    public Score () {}

    public Score (Game game, Player player, Double score) {
        this.game = game;
        this.player = player;
        this.score = score;
        this.finishDate = LocalDateTime.now();
    }

    //Methods

    public Long getID() {
        return id;
    }

    public Double getScore() {return score; }

    public void setScore(Double score) { this.score = score; }

    public LocalDateTime getFinishDate () {return finishDate;}

    public Game getGame() {return game;}

    public void setGame(Game game) {this.game = game;}

    public Player getPlayer() {return player;}

    public void setPlayer(Player player) {this.player = player;}

    public Map<String, Object> makeScoreDTO() {
        return new LinkedHashMap<String, Object>() {{
            put("score", score);
            put("finishDate", finishDate);
        }};
    }
}
