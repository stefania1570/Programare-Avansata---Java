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
        players.add(new Player(3L, "Marcela", 900));
        players.add(new Player(4L, "Kabana", 345));
    }

    public List<Player> getAllPlayers() {
        return players;
    }
    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getPlayerById(Long id) {
        for (Player player : players) {
            if (player.getId().equals(id)) {
                return player;
            }
        }
        return null;
    }

    public void updatePlayerName(Long id, String name) {
        for (Player player : players) {
            if (player.getId().equals(id)) {
                player.setName(name);
                return;
            }
        }
    }

    public void deletePlayer(Long id) {
        players.removeIf(player -> player.getId().equals(id));
    }
}