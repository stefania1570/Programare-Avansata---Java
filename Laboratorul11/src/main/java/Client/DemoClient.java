package Client;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoClient {

    public static void main(String[] args) {
        System.out.println("All players: ");
        PlayerClient.GetPlayersRequest();

        PlayerClient.PostRequest();
        PlayerClient.GetPlayersRequest();

        PlayerClient.PutRequest();
        PlayerClient.GetPlayersRequest();

        PlayerClient.DeleteRequest();
        PlayerClient.GetPlayersRequest();

        PlayerClient.GetGamesRequest();

    }

}