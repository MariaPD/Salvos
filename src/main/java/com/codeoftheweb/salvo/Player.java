package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class Player {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String firstName;
    private String lastName;
    private String userName;

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers = new HashSet<>();

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<Score> scores = new HashSet<>();

    //Constructors
    public Player() { }

    public Player(String first, String last, String user) {
        this.firstName = first;
        this.lastName = last;
        this.userName = user;
    }

    //Methods
    public Long getID() {
        return id;
    }

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String toString() {
        return firstName + " " + lastName;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setPlayer(this);
        gamePlayers.add(gamePlayer);
    }

    public Set<Score> getScore() {
        return scores;
    }

    public void addScore(Score score) {
        score.setPlayer(this);
        scores.add(score);
    }

    public Map<String, Object> makePlayerDTO() {

        return new LinkedHashMap<String, Object>(){{
            put("id", id);
            put("email", userName);
        }};

/*        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", getID());
        dto.put("email", getUserName());
        return dto;*/
    }
}
