package ClientUI;

import Connection.ClientConnection;
import Connection.ServerCom;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class ChatView {
    private final ClientConnection con;
    private final Stage primaryStage;
    private final String username;
    private BorderPane borderPane;
    private VBox centerVBox;
    private ScrollPane scrollPane;
    private VBox chatPane;
    private HBox messageBoxHBox;
    private TextArea messageBox;
    private Button buttonSend;
    private VBox leftVBox;
    private HBox onlineUsersHbox;
    private ListView<String> userList;
    private BorderPane topBorderPane;
    private HBox leftHBox;
    private VBox userVBox;
    private Label usernameLabel;
    private String friendToSend;
    private Timeline refreshTimeline; // Timeline for auto-refreshing


    public ChatView(Stage stage, ClientConnection con, String username) {
        this.con = con;
        this.primaryStage = stage;
        this.username = username;
        chatSetup();
        setupAutoRefresh(); // Setup auto-refresh
    }

    private void setupAutoRefresh() {
        refreshTimeline = new Timeline(
                new KeyFrame(Duration.seconds(3), event -> {
                    if(friendToSend!=null) {
                        showMessages(friendToSend); // Refresh the chat
                    }
                })
        );
        refreshTimeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        refreshTimeline.play(); // Start the auto-refresh timeline
    }

    private void chatSetup() {
        borderPane = new BorderPane();
        borderPane.setMinHeight(500);
        centerVBox = new VBox();
        chatPane = new VBox(10);
        messageBoxHBox = new HBox();
        messageBox = new TextArea();
        buttonSend = new Button();
        leftVBox = new VBox(); //leftVBox
        onlineUsersHbox = new HBox();
        userList = new ListView<>();
        topBorderPane = new BorderPane();
        leftHBox = new HBox();
        userVBox = new VBox();
        usernameLabel = new Label(username);

        scrollPane = new ScrollPane(chatPane);
        scrollPane.setFitToWidth(true);

        scrollPane.setContent(scrollPane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        chatPane.setMinHeight(300);
        chatPane.setMinWidth(400);
        chatPane.setPadding(new Insets(25, 25, 25, 25));
        chatPane.setStyle("-fx-background-color: transparent;");
        chatPane.setFillWidth(true);



        messageBoxHBox.setMinHeight(Region.USE_PREF_SIZE);
        messageBoxHBox.setMinWidth(100.0);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        messageBox.setMaxHeight(Double.MAX_VALUE);
        messageBox.setMaxWidth(Double.MAX_VALUE);
        messageBox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        messageBox.setPrefHeight(16.0);
        messageBox.setPrefWidth(698.0);
        messageBox.setPromptText("Enter Message...");
        messageBox.setFont(new Font("SansSerif Regular", 14.0));
        HBox.setHgrow(messageBox, Priority.ALWAYS);
        buttonSend.setAlignment(Pos.CENTER);
        buttonSend.setMaxHeight(Double.MAX_VALUE);
        buttonSend.setMaxWidth(Double.MAX_VALUE);
        buttonSend.setMinHeight(Region.USE_PREF_SIZE);
        buttonSend.setMinWidth(Region.USE_PREF_SIZE);
        buttonSend.setOnAction(e -> sendButtonAction());
        buttonSend.setText("Send");
        buttonSend.setTextAlignment(TextAlignment.CENTER);
        HBox.setHgrow(buttonSend, Priority.NEVER);
        onlineUsersHbox.setMinHeight(Region.USE_PREF_SIZE);
        onlineUsersHbox.setMinWidth(Region.USE_PREF_SIZE);
        onlineUsersHbox.setPrefHeight(50.0);
        onlineUsersHbox.setPrefWidth(217.0);
        userList.setFocusTraversable(false);
        userList.setMaxHeight(Double.MAX_VALUE);
        userList.setPrefHeight(495.0);
        userList.setPrefWidth(217.0);
        userList.setStyle("-fx-border-image-width: 0;");

        topBorderPane.setMaxHeight(Double.MAX_VALUE);
        topBorderPane.setMaxWidth(Double.MAX_VALUE);
        topBorderPane.setStyle("-fx-background-color: #031c30;");


        leftHBox.setPrefWidth(259.0);
        leftHBox.setAlignment(Pos.CENTER_LEFT);
        userVBox.setPrefWidth(Region.USE_PREF_SIZE);
        userVBox.setPadding(new Insets(0, 0, 0, 10.0));
        usernameLabel.setText("Connected user:    " + this.username);
        usernameLabel.setTextFill(Paint.valueOf("#fa6648"));
        usernameLabel.setFont(new Font("HelveticaNeue Medium", 14.0));

        ScrollBar scrollBar = new ScrollBar();
        scrollBar.setTranslateX(205);

        leftVBox.setPrefWidth(200); // Set a fixed width for the leftVBox (pt lista de useri)
        onlineUsersHbox.setPrefHeight(Region.USE_PREF_SIZE);
        userList.setPrefWidth(200);

        onlineUsersHbox.getChildren().add(scrollBar);

        // Add components to their parent containers
        messageBoxHBox.getChildren().addAll(messageBox, buttonSend);
        centerVBox.getChildren().addAll(scrollPane, messageBoxHBox);
        scrollPane.setContent(chatPane);

        //pt scroll sa fie down mereu la ultimul mesaj
        chatPane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                scrollPane.setVvalue((Double) newValue);
            }
        });

        // The username of the client
        leftHBox.getChildren().add(userVBox); //todo
        userVBox.getChildren().add(usernameLabel);
        topBorderPane.setLeft(leftHBox);
        usernameLabel.setPadding(new Insets(20,20,20,30));
        HBox topHBox = new HBox();
        TextArea textArea = new TextArea();
        textArea.setPromptText("Search for a friend...");
        textArea.setFont(new Font("SansSerif Regular", 14.0));
        textArea.setPrefHeight(12.0);
        textArea.setPrefWidth(300.0); // Increase the width as desired
        HBox.setHgrow(textArea, Priority.ALWAYS); // Allow the text area to grow horizontally
        HBox.setMargin(textArea, new Insets(0, 10, 0, 0)); // Add margin if needed
        topHBox.getChildren().add(textArea);
        Button friends_btn = new Button("ADD FRIEND");
        friends_btn.setOnAction(t -> addFriendAction(textArea.getText()));

        topHBox.getChildren().add(friends_btn);
        Insets buttonMargin =  new Insets(17,20,20,30); // Set the desired margin values
        HBox.setMargin(friends_btn,buttonMargin);

        topHBox.getChildren().add(usernameLabel);

        //+TODO ADD "ADD FRIENDS" BUTTON
        topBorderPane.setTop(topHBox);

        onlineUsersHbox.getChildren().add(userList);
        leftVBox.getChildren().addAll(onlineUsersHbox, userList);
        borderPane.setCenter(centerVBox);
        borderPane.setLeft(leftVBox);
        borderPane.setTop(topBorderPane);
        setUsersList(new ServerCom(con).getUserFriends(username));

        // Set the root of the scene to the BorderPane
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void setUsersList(List<String> users) {
       leftVBox.getChildren().clear(); // Clear existing user HBoxes
        int i=1;
        for (String user : users) {
            i++;
            HBox userHBox = createUserHBox(user,i); // Create an HBox for each user
            leftVBox.getChildren().add(userHBox); // Add the user HBox to the userVBox
        }
    }

    public void addFriendAction(String friendUsername){
        new ServerCom(con).addFriendRequest(this.username, friendUsername);
        new ChatView(this.primaryStage,this.con, this.username);
    }

    private HBox createUserHBox(String user, int index) {
        HBox userHBox = new HBox();
        Label usernameLabel = new Label(user);
        userHBox.getChildren().add(usernameLabel);
        userHBox.setMinHeight(40);


        // Change the background color to dark grey when the mouse hovers
        userHBox.setOnMouseEntered(event -> {
            userHBox.setStyle("-fx-background-color: #dbdbdb;");
            userHBox.setCursor(Cursor.HAND);
        });

        // Revert the background color to the original color when the mouse exits
        userHBox.setOnMouseExited(event -> {
            userHBox.setStyle("-fx-background-color: transparent;" );
            userHBox.setCursor(Cursor.DEFAULT);
        });

        //If i click on the user it will redirect me to the conversation
        userHBox.setOnMouseClicked(event -> showMessages(usernameLabel.getText()));

        return userHBox;
    }

    /**
     * Requests the messages from the server
     * @return String
     */
    private String getMessage() {
        return (new ServerCom(this.con).getMessages(this.username,this.friendToSend));
    }

    /**
     * Shows the messages with that friend
     * @param friend username
     */
    private void showMessages(String friend) {
        this.friendToSend = friend;
        String[] messages = getMessage().split(";");
        chatPane.getChildren().clear(); // Clear existing messages

        for (String message : messages) {
            VBox messageBox = createMessageBox(message);
            chatPane.getChildren().add(messageBox);
        }


    }

    /**
     * Creates a message box
     * @param message
     * @return VBox
     */
    private VBox createMessageBox(String message) {
        VBox messageBox = new VBox();
        Label nameLabel = new Label();
        Label messageLabel = new Label();

        if (isUser(message, username)) {
            nameLabel.setText(username);
            nameLabel.setStyle("-fx-text-fill: blue;");
            messageBox.setAlignment(Pos.CENTER_RIGHT);
            messageLabel.setStyle("-fx-text-fill: blue;" +
                    "-fx-font: 20px Verdana;" +
                    "-fx-border-width: 2px;" +
                    "-fx-border-radius: 10px;" +
                    "-fx-border-color: blue");
        } else {
            nameLabel.setText(friendToSend);
            nameLabel.setStyle("-fx-text-fill: green;");
            messageBox.setAlignment(Pos.CENTER_LEFT);
            messageLabel.setStyle("-fx-text-fill: green;" +
                    "-fx-font: 20px Verdana;" +
                    "-fx-border-width: 2px;" +
                    "-fx-border-radius: 10px;" +
                    "-fx-border-color: green");
        }
        messageLabel.setText(getTextFromMessage(message));
        messageLabel.setPadding(new Insets(10, 10, 10, 10));

        messageBox.getChildren().addAll(nameLabel, messageLabel);

        return messageBox;
    }

    /**
     * Checks if the first part of the input is the current user or the friend.
     * @param input string
     * @param name username
     * @return true/false
     */
    public static boolean isUser(String input, String name) {
        int delimiterIndex = input.indexOf(":");
        if (delimiterIndex != -1) {
            String firstPart = input.substring(0, delimiterIndex);
            return firstPart.equals(name);
        }
        return false;
    }

    /**
     * Gets the text message from the string received
     * @param input string
     * @return string
     */
    public static String getTextFromMessage(String input) {
        int delimiterIndex = input.indexOf(":");
        String lastPart = "";
        if (delimiterIndex != -1) {
            lastPart = input.substring(delimiterIndex+1);
            return lastPart;
        }
        return lastPart;
    }

    /**
     *  Event handler for the send button action
     */

    private void sendButtonAction() {
        String messageText = messageBox.getText().trim();

        if (!messageText.isEmpty()) {

            // Send the message to the server
            ServerCom serverCom = new ServerCom(con);
            serverCom.sendMessage(username, friendToSend, messageText);

            // Clear the message box
            messageBox.clear();

            // Refresh the messages
            showMessages(friendToSend);
        }
    }


}
