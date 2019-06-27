package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Salvo {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private int turn;

    @ElementCollection
    @Column(name="location")
    private List<String> locations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name="gameplayer_id")
    private GamePlayer gamePlayer;

    //Constructors
    public Salvo () { }

    public Salvo (int turn, List<String> locations) {
        this.turn = turn;
        this.locations = locations;
    }

    //Methods

    public Long getID() {
        return id;
    }

    public int getTurn() { return turn; }

    public void setTurn(int shipType) { this.turn = turn; }

    public List<String> getLocations() { return locations; }

    public void setLocations(List<String> locations) { this.locations = locations; }

    public GamePlayer getGamePlayer() { return gamePlayer; }

    public void setGamePlayer(GamePlayer gamePlayer) { this.gamePlayer = gamePlayer; }

    public Map<String, Object> makeSalvoDTO() {
        return new LinkedHashMap<String, Object>(){{
            put("turn", turn);
            put("player", gamePlayer.getPlayer().getID());
            put("locations", locations);
        }};
    }

}
