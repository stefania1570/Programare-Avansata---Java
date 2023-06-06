package ClientUI;

import Connection.ServerCom;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import Connection.ClientConnection;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class LoginPage {
    private final ClientConnection con;
    private final Stage primaryStage;
    private TextField usernameField ;

    private TextField passwordField;

    public LoginPage(Stage stage, ClientConnection con) {
        this.con = con;
        this.primaryStage = stage;
        loginSetup();
    }

    public void loginSetup(){
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 400);

        // Left side
        AnchorPane leftPane = new AnchorPane();
        leftPane.setStyle("-fx-background-color: #404040;");
        leftPane.setPrefSize(300, 400);

        Text titleText = new Text("ChatApp");
        titleText.setFill(Color.WHITE);
        titleText.setLayoutX(24);
        titleText.setLayoutY(184);
        titleText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        titleText.setStrokeWidth(0);
        titleText.setFont(Font.font("System Bold", 54));


        leftPane.getChildren().add(titleText);

        root.setLeft(leftPane);

        // Right side
        AnchorPane rightPane = new AnchorPane();
        rightPane.setPrefSize(300, 400);

        TextField usernameField = new TextField();
        this.usernameField=usernameField;
        usernameField.setLayoutX(49);
        usernameField.setLayoutY(163);
        usernameField.setPrefSize(202, 25);
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-background-color: transparent; -fx-border-color: #404040; -fx-border-width: 0px 0px 2px 0px;");

        PasswordField passwordField = new PasswordField();
        this.passwordField=passwordField;
        passwordField.setLayoutX(49);
        passwordField.setLayoutY(202);
        passwordField.setPrefSize(202, 25);
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: transparent; -fx-border-color: #404040; -fx-border-width: 0px 0px 2px 0px;");

        Button loginButton = new Button("Login");
        loginButton.setLayoutX(112);
        loginButton.setLayoutY(255);
        loginButton.setPrefSize(76, 32);
        loginButton.setStyle("-fx-background-color: #404040; -fx-background-radius: 0px;");
        loginButton.setTextFill(Color.WHITE);
        loginButton.setOnAction(event -> login());

        loginButton.setCursor(Cursor.OPEN_HAND);

        Text userLoginText = new Text("User Login");
        userLoginText.setFill(Color.web("#404040"));
        userLoginText.setLayoutX(81);
        userLoginText.setLayoutY(143);
        userLoginText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        userLoginText.setStrokeWidth(0);
        userLoginText.setFont(Font.font("Wingdings 3", 27));

        Text registerText = new Text("Don't have an account? Register");
        registerText.setFill(Color.web("#404040"));
        registerText.setLayoutX(65);
        registerText.setLayoutY(313);
        registerText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        registerText.setStrokeWidth(0);
        registerText.setOnMouseClicked(event -> showRegisterStage());
        registerText.setCursor(Cursor.HAND);

        rightPane.getChildren().addAll(usernameField, passwordField, loginButton, userLoginText, registerText);

        root.setRight(rightPane);

        // Sets the root as the scene content
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Action performed when the login button is pressed
     */
    private void login() {

        String loginResult = new ServerCom(con).sendLoginRequest(usernameField.getText(), passwordField.getText());
        if(loginResult.contains("successful"))
        {
             new ChatView(this.primaryStage,this.con, this.usernameField.getText());
        }
    }

    /**
     * Shows the register page. - action listener
     */
    private void showRegisterStage() {
        new RegisterPage(this.primaryStage,this.con);
    }
}
