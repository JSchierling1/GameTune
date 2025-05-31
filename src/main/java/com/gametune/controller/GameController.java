package com.gametune.controller;

import org.springframework.web.bind.annotation.*;
import com.gametune.model.Game;
import com.gametune.repository.GameRepository;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @PostMapping
    public Game addGame(@RequestBody Game game){
        return gameRepository.save(game);
    }
}