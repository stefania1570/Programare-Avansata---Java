package Client;

import com.example.lab11.domain.Game;
import com.example.lab11.domain.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
public class PlayerClient {
    static RestTemplate restTemplate = new RestTemplate();
    public static void GetPlayersRequest() {
        ResponseEntity<Player[]> response = restTemplate.getForEntity("http://localhost:8092/players", Player[].class);
        Player[] players = response.getBody();
        // Print the players
        for (Player player : players) {
            System.out.println("ID: " + player.getId() + ", Name: " + player.getName() + ", Score: " + player.getScore());
        }
    }
    public static void GetGamesRequest() {
        ResponseEntity<Game[]> response = restTemplate.getForEntity("http://localhost:8092/games", Game[].class);
        Game[] games = response.getBody();
        // Print the players
        for (Game game : games) {
            System.out.println("ID: " + game.getId() + ", Name: " + game.getName() );
        }
    }
    public static void PostRequest() {
        System.out.println("Adding a player: ");
        // Create a new player
        Player newPlayer = new Player(5L, "PLAYER ADAUGAT", 200);
        HttpEntity<Player> request = new HttpEntity<>(newPlayer);

        // Invoke the addPlayer service
        ResponseEntity<String> addResponse = restTemplate.postForEntity("http://localhost:8092/players/save", request, String.class);
        System.out.println(addResponse.getBody());
    }

    public static void PutRequest() {
        // Update a player's name
        Long playerIdToUpdate = 1L;
        String newName = "UPDATED NAME";

        // Invoke the updatePlayerName service
        restTemplate.put("http://localhost:8092/players/update/{id}?name={name}", null, playerIdToUpdate, newName);
        System.out.println("Player name updated.");
    }

    public static void DeleteRequest() {
        // Delete a player
        Long playerIdToDelete = 2L;

        // Invoke the deletePlayer service
        restTemplate.delete("http://localhost:8092/players/delete/{id}", playerIdToDelete);
        System.out.println("Player deleted.");
    }

}
