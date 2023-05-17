package com.example.lab11.services;

import com.example.lab11.domain.Player;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.ArrayList;

@Service
public class PlayerService {
    private List<Player> players = new ArrayList<>();

    public PlayerService() {
        players.add(new Player(1L, "John", 100));
        players.add(new Player(2L, "Jane", 150));
    }

    public List<Player> getAllPlayers() {
        return players;
    }
}