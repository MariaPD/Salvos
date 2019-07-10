package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PlayerRepository repoPlayer;

    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String last,
            @RequestParam String user, @RequestParam String password) {

        if (firstName.isEmpty() || last.isEmpty() || user.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (repoPlayer.findByUserName(user) !=  null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        repoPlayer.save(new Player(firstName, last, user, passwordEncoder.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/players")
    public Player getAll(Authentication authentication) {
        return repoPlayer.findByUserName(authentication.getName());
    }
}
