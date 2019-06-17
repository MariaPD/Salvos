package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String shipType;

    @ElementCollection
    @Column(name="location")
    private List<String> locations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name="gameplayer_id")
    private GamePlayer gamePlayer;

    //Constructors
    public Ship () { }

    public Ship (String shipType, List<String> locations) {
        this.shipType = shipType;
        this.locations = locations;
    }

    //Methods
    public Long getID() {
        return id;
    }

    public String getShipType() { return shipType; }

    public void setShipType(String shipType) { this.shipType = shipType; }

    public List<String> getLocations() { return locations; }

    public void setLocations(List<String> locations) { this.locations = locations; }

    public GamePlayer getGamePlayer() { return gamePlayer; }

    public void setGamePlayer(GamePlayer gamePlayer) { this.gamePlayer = gamePlayer; }
}
