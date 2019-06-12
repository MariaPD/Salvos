package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class SalvoController {

    @Autowired
    private GameRepository repoGame;

    @RequestMapping("/games")
    public List<Map<String,Object>> getAll() {
        return repoGame.findAll().stream().map(game -> game.makeGameDTO()).collect(Collectors.toList());
    }

}
