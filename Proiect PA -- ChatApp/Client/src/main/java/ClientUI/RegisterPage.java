package ClientUI;

import Connection.ServerCom;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import Connection.ClientConnection;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class RegisterPage {
    private final ClientConnection con;
    private final Stage primaryStage;
    private TextField usernameField ;

    private TextField passwordField;

    public RegisterPage(Stage stage, ClientConnection con) {
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

        Text companyText = new Text("Company");
        companyText.setFill(Color.WHITE);
        companyText.setLayoutX(24);
        companyText.setLayoutY(184);
        companyText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        companyText.setStrokeWidth(0);
        companyText.setFont(Font.font("System Bold", 54));

        Text sloganText = new Text("Company Slogan");
        sloganText.setFill(Color.WHITE);
        sloganText.setLayoutX(82);
        sloganText.setLayoutY(220);
        sloganText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        sloganText.setStrokeWidth(0);
        sloganText.setFont(Font.font(18));

        leftPane.getChildren().addAll(companyText, sloganText);

        root.setLeft(leftPane);

        // Right side
        AnchorPane rightPane = new AnchorPane();
        rightPane.setPrefSize(300, 400);

        Text registerText = new Text("User Registration");
        registerText.setFill(Color.web("#404040"));
        registerText.setLayoutX(47);
        registerText.setLayoutY(53);
        registerText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        registerText.setStrokeWidth(0);
        registerText.setFont(Font.font("Wingdings 3", 27));

        TextField usernameField = new TextField();
        this.usernameField=usernameField;
        usernameField.setLayoutX(49);
        usernameField.setLayoutY(183);
        usernameField.setPrefSize(202, 25);
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-background-color: transparent; -fx-border-color: #404040; -fx-border-width: 0px 0px 2px 0px;");

        PasswordField passwordField = new PasswordField();
        this.passwordField=passwordField;
        passwordField.setLayoutX(49);
        passwordField.setLayoutY(219);
        passwordField.setPrefSize(202, 25);
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: transparent; -fx-border-color: #404040; -fx-border-width: 0px 0px 2px 0px;");


        Button registerButton = new Button("Submit");
        registerButton.setLayoutX(112);
        registerButton.setLayoutY(302);
        registerButton.setPrefSize(76, 32);
        registerButton.setStyle("-fx-background-color: #404040; -fx-background-radius: 0px;");
        registerButton.setTextFill(Color.WHITE);
        registerButton.setCursor(Cursor.HAND);
        registerButton.setOnAction(event -> register());

        Text loginText = new Text("Already have an account? Login");
        loginText.setFill(Color.web("#404040"));
        loginText.setLayoutX(66);
        loginText.setLayoutY(358);
        loginText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        loginText.setStrokeWidth(0);
        loginText.setCursor(Cursor.HAND);
        loginText.setOnMouseClicked(event -> showLoginStage());

        rightPane.getChildren().addAll(registerText, usernameField, passwordField, registerButton, loginText);

        root.setRight(rightPane);

        // Set the root as the scene content
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void register() {

        // Call the server's login request method
        String loginResult = new ServerCom(con).sendSignupRequest(usernameField.getText(), passwordField.getText());
        if(loginResult.contains("successful"))
        {
            new LoginPage(this.primaryStage, this.con);
            showSuccessMessage();
        }
        else {
            showFailureMessage();
        }
    }

    private void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Successful.");
        alert.setHeaderText(null);
        alert.setContentText("Your registration was successful!Please login.");
        alert.showAndWait();
    }

    private void showFailureMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Registration Failed");
        alert.setHeaderText(null);
        alert.setContentText("Registration failed. Please try again.");
        alert.showAndWait();
    }
    private void showLoginStage() {
        new LoginPage(this.primaryStage, this.con);
    }
}

