package com.example.lab11.services;

import com.example.lab11.domain.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private List<Game> games = new ArrayList<>();

    public GameService() {
        games.add(new Game(1L, "JOC1"));
        games.add(new Game(2L, "JOC2"));
    }

    public List<Game> getAllGames() {
        return games;
    }
}
