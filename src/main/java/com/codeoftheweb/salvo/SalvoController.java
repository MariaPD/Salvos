package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.*;
import static java.util.stream.Collectors.toList;

@RequestMapping("/api")
@RestController
public class SalvoController {

    //Creates JSON api/games
    @Autowired
    private GameRepository repoGame;

    /*    @RequestMapping("/games")
    public List<Map<String,Object>> getAll() {
        return repoGame.findAll().stream().map(game -> game.makeGameDTO()).collect(toList());
    }*/

    // getAll() es el nombre de mi función / findAll() es una función propia de repositorio
    @RequestMapping("/games")
    public Map<String,Object> getAll(Authentication auth) {
        Optional<Player> optionalPlayer = getPlayers(auth);
        return new LinkedHashMap<String, Object>(){{
            put("games", repoGame.findAll().stream().map(game -> game.makeGameDTO()).collect(toList()));
            put("player", optionalPlayer.map(player -> player.makePlayerAuthenticatedDTO()).orElse(null));
        }};
    }

    @RequestMapping(path = "/games", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> register(Authentication auth) {
        Optional<Player> optionalPlayer = getPlayers(auth);

        if (!optionalPlayer.isPresent()) {
            return new ResponseEntity<>(new LinkedHashMap<String, Object>(){{
                put("error", "You are not logged in");
            }} ,HttpStatus.UNAUTHORIZED);
        };

        Game game = new Game(LocalDateTime.now());
        GamePlayer gameplayer = new GamePlayer(game, optionalPlayer.get());

        repoGame.save(game);
        repoGamePlayer.save(gameplayer);

        return new ResponseEntity<>(new LinkedHashMap<String, Object>(){{
            put("gpid", gameplayer.getID() );
        }} ,HttpStatus.CREATED);
    }

    @RequestMapping(path ="game/{gameID}/players", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findGame(@PathVariable Long gameID, Authentication auth) {
        Optional<Player> optionalPlayer = getPlayers(auth);
        Optional<Game> optionalGame = repoGame.findById(gameID);

        if (!optionalPlayer.isPresent()) {
            return new ResponseEntity<>(new LinkedHashMap<String, Object>(){{
                put("error", "You are not logged in");
            }} ,HttpStatus.UNAUTHORIZED);
        };
        if (!optionalGame.isPresent()) {
            return new ResponseEntity<>(new LinkedHashMap<String, Object>(){{
                put("error", "No such game");
            }} ,HttpStatus.FORBIDDEN);
        }
        if (optionalGame.get().getGamePlayers().size() > 1) {
            return new ResponseEntity<>(new LinkedHashMap<String, Object>(){{
                put("error", "Game is full");
            }} ,HttpStatus.FORBIDDEN);
        }

        GamePlayer gameplayer = new GamePlayer(optionalGame.get(), optionalPlayer.get());

        repoGamePlayer.save(gameplayer);

        return new ResponseEntity<>(new LinkedHashMap<String, Object>(){{
            put("gpid", gameplayer.getID());
        }}, HttpStatus.CREATED);

    }

    //Player es el tipo de dato que voy a devolver. Player es el tipo del optional. Hacemos el optional porque sabemos que no queremos devolver un string o un list
    //Cuando creo una clase defino los objetos y son del tipo del nombre de la clase

    public Optional<Player> getPlayers(Authentication auth){
        if(auth == null) {
            return Optional.empty();
        }
        else {
            return repoPlayer.findByUserName(auth.getName());
        }
    };


    //Creates JSON api/game_view
    @Autowired
    private GamePlayerRepository repoGamePlayer;

    //Envíamos la información de un juego de un gamplayer solamente
    @RequestMapping("/game_view/{gameplayerID}")
    public ResponseEntity<Map<String,Object>> findGamePlayer(@PathVariable Long gameplayerID, Authentication auth) {
        //Buscamos sólo el que corresponda con el ID de la url, usamos un optional porque si el id no existe no se puede ejecutar el resto de la función
       Optional<GamePlayer> optionalGamePlayer = repoGamePlayer.findById(gameplayerID);
       Optional<Player> optionalPlayer = getPlayers(auth);

        if (!optionalPlayer.isPresent()) {
            return new ResponseEntity<>(new LinkedHashMap<String, Object>(){{
                put("error", "You are not logged in");
            }} ,HttpStatus.UNAUTHORIZED);
        };
        if (!optionalGamePlayer.isPresent()) {
            return new ResponseEntity<>(new LinkedHashMap<String, Object>(){{
                put("error", "The gameplayer does not exist");
            }} ,HttpStatus.UNAUTHORIZED);
        };
        if (optionalPlayer.get().getID() != optionalGamePlayer.get().getPlayer().getID()){
            return new ResponseEntity<>(new LinkedHashMap<String, Object>(){{
                put("error", "We caught you cheating!!!");
            }} ,HttpStatus.UNAUTHORIZED);
        };

        Game game = optionalGamePlayer.get().getGame();
        GamePlayer gamePlayer = optionalGamePlayer.get();

        return new ResponseEntity<>(new LinkedHashMap<String, Object>(){{
            put("id", game.getID());
            put("fecha", game.getFecha());
            put("gameplayer", game.getGamePlayers().stream().map(gamePlayer -> gamePlayer.makeGamePlayerDTO()).collect(toList()));
            put("ships", gamePlayer.getShips().stream().map(ship -> ship.makeShipDTO()).collect(toList()));
            put("salvos", game.getGamePlayers().stream().flatMap(gameplayer -> gameplayer.getSalvos().stream())
                    .map(salvo -> salvo.makeSalvoDTO()));

        }}, HttpStatus.OK);

/*       if (optionalGamePlayer.isPresent()){
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
       }*/
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Creates new registered players
    @Autowired
    private PlayerRepository repoPlayer;

    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestBody Player player) {

        if (player.getFirstName().isEmpty() || player.getLastName().isEmpty() || player.getUserName().isEmpty() || player.getPassword().isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (repoPlayer.findByUserName(player.getUserName()).isPresent()) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        repoPlayer.save(new Player(player.getFirstName(), player.getLastName(), player.getUserName(), passwordEncoder.encode(player.getPassword())));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
