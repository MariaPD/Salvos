package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class Game {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private LocalDateTime fecha;

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers = new HashSet<>();

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    Set<Score> scores = new HashSet<>();

    //Constructors
    public Game() { }

    public Game(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    //Methods
    public Long getID() {
        return id;
    }

    public LocalDateTime getFecha () {return fecha;}

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void addGamePlayers(GamePlayer gamePlayer) {
        gamePlayer.setGame(this);
        gamePlayers.add(gamePlayer);
    }

    public Set<Score> getScore() {
        return scores;
    }

    public void addScore(Score score) {
        score.setGame (this);
        scores.add(score);
    }

    public Map<String, Object> makeGameDTO() {

        return new LinkedHashMap<String, Object>(){{
            put("id", id);
            put("created", fecha);
            put("gameplayer", gamePlayers.stream().map(GamePlayer::makeGamePlayerDTO));
        }};

/*        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", getID());
        dto.put("created", getFecha());
        dto.put("gameplayer", getGamePlayers().stream().map(GamePlayer::makeGamePlayerDTO));
        return dto;*/
    }

}
