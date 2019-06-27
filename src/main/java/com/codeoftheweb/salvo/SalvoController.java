package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import static java.util.stream.Collectors.toList;

@RequestMapping("/api")
@RestController
public class SalvoController {

    @Autowired
    private GameRepository repoGame;

    @RequestMapping("/games")
    public List<Map<String,Object>> getAll() {
        return repoGame.findAll().stream().map(game -> game.makeGameDTO()).collect(toList());
    }

    @Autowired
    private GamePlayerRepository repoGamePlayer;

    @RequestMapping("/game_view/{gameplayerID}")
    public Map<String,Object> findGamePlayer(@PathVariable Long gameplayerID) {
       Optional<GamePlayer> optionalGamePlayer = repoGamePlayer.findById(gameplayerID);
       if (optionalGamePlayer.isPresent()){
           Game game = optionalGamePlayer.get().getGame();
           GamePlayer gamePlayer = optionalGamePlayer.get();
           return new LinkedHashMap<String, Object>(){{
               put("id", game.getID());
               put("fecha", game.getFecha());
               put("gameplayer", game.getGamePlayers().stream().map(gamePlayer -> gamePlayer.makeGamePlayerDTO()).collect(toList()));
               put("ships", gamePlayer.getShips().stream().map(ship -> ship.makeShipDTO()).collect(toList()));
               put("salvos", game.getGamePlayers().stream().flatMap(gameplayer -> gameplayer.getSalvos().stream())
                       .map(salvo -> salvo.makeSalvoDTO()));
           }};
       }
       else {
           return null;
       }


    }

}
