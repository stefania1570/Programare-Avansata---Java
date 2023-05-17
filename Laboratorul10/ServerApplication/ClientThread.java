import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.stream.Collectors;

class ClientThread extends Thread {
    private Socket clientSocket = null;
    private GameServer gameServer;
    private Game game;
    private Player player; // Add a player reference

    public ClientThread(Socket socket, GameServer gameServer) throws SocketException {
        clientSocket = socket;
        this.gameServer = gameServer;
        game = null;
        player = null;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                String[] tokens = inputLine.split(" ");
                switch ( tokens[0] ) {
                    case "stop":
                        out.println("Server stopped");
                        sleep(2000);
                        gameServer.stop();
                        break;
                    case "create":
                        if( tokens.length == 2 ) {
                            if( game != null ){
                                out.println("Game already in progress!"); //+TODO DACA EXISTA DEJA JOCUL CU ID-UL RESPECTIV
                            } else {
                                game = createGame(clientSocket);
                                //this.clientSocket.setSoTimeout(3000); // 3 sec
                                out.println("Game created. Game ID: " + game.getId() + " Remember: you have only 10 seconds to make a move!");
                            }
                        }
                        break;
//                    case "show":
//                        for (Game game : gameServer.getGames()){
//                            out.print(game.getId()+" : ");
//                            for (Player player : game.getPlayers()){
//                                out.print("Player"+player.getId()+", ");
//                            }
//                            out.print("\n");
//                        }
//                        break;
                    case "join":
                        if (tokens.length < 2) {
                            out.println("Invalid command format: join <gameId>");
                            break;
                        }
                        int gameId = Integer.parseInt(tokens[1]);
                        boolean joined = joinGame(clientSocket, gameId);
                        if (joined){
                            out.println("Server received the request: Joined game with ID " + gameId);
                            readAllLines(in); //TODO IDK? nu citeste toate liniile de la board
                        } else
                            out.println("Failed to join game with ID " + gameId); //TODO PREA MULTI JUCATORI SAU NU EXISTA ID-UL RESPECTIV
                        break;
                    case "submit":
                        if( game == null ) {
                            out.println("No game available.First create or join a game!");
                            break;
                        }
                        Player currentPlayer = game.getCurrentPlayer();
                        if (currentPlayer != this.player) {
                            out.println("It's not your turn.");
                            break;
                        }
                        if( tokens.length == 2 ) {
                            if (!game.getBoard().isValidMove(tokens[1])) {
                                out.println("Invalid move. Please try again, example: submit A2");
                                break;
                            } else { //Changes turn
                                game.getBoard().makeMove(tokens[1],player.getSymbol());
                                int indexAux;
                                if(game.getCurrentPlayerIndex()==0){
                                    indexAux=1;
                                }else indexAux=0;
                                game.setCurrentPlayerIndex(indexAux);
                            }
                        }
                        break;
                    default:
                        out.println("Unknown command: " + inputLine);
                        break;
                }
            }

            out.close();
            in.close();
            clientSocket.close();
            gameServer.removeClientThread(this);
        } catch (IOException e) {
            System.out.println("Error handling client request: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    private Game createGame(Socket clientSocket) {
        this.player = new Player(clientSocket); //Player1
        this.player.setSymbol('#');
        Game g = new Game();
        g.addPlayer(this.player);
        gameServer.addGame(g);
        return g;
    }

    private boolean joinGame(Socket clientSocket, int gameId) {
        game = findGameById(gameId);
        if (game != null) {
            player = new Player(clientSocket); //Player2
            player.setSymbol('x');
            game.addPlayer(player);
            game.startGame(); //The game starts when the second player enters
            return true;
        }
        return false;
    }

    private Game findGameById(int gameId) {
        for (Game game : gameServer.getGames()) {
            if (game.getId() == gameId) {
                return game;
            }
        }
        return null;
    }
    public String readAllLines(BufferedReader reader) {
        return reader.lines()
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
