package com.example.lab11.controller;

import com.example.lab11.domain.Player;
import com.example.lab11.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping ("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String addPlayer(@RequestBody Player player) {
        playerService.addPlayer(player);
        return "Saved.....";
    }

    @PutMapping("/update/{id}")
    public String updatePlayerName(@PathVariable Long id, @RequestParam String name) {
        playerService.updatePlayerName(id, name);
        return "Updated.....";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return "Deleted.....";
    }
}
