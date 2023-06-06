package ClientUI;

import Connection.ClientConnection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * The class which creates the first page of the application.
 */
public class TitlePage {
    ClientConnection con;
    Stage stage;
    Text pageTitle = new Text("ChatApp");
    Button logIn_button = new Button("LOGIN");
    Button signup_button = new Button("SIGNUP");
    VBox vBox = new VBox();
    HBox hBox = new HBox();

    TitlePage(Stage stage, ClientConnection con)
    {
        this.stage = stage;
        this.con = con;
        //title
        pageTitle.setStyle("-fx-font: 50px Verdana;" +
                "-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, #7161ef 0%, #dec0f1 50%);" +
                "-fx-stroke: #231f20;" +
                "-fx-stroke-width: 1;");
        //buttons
        String buttonStyle = "-fx-font: 30px Verdana;" +
                "-fx-text-fill: #7161ef;" +
                "-fx-border-color: #7161ef;" +
                "-fx-background-color: #11151c";
        logIn_button.setStyle(buttonStyle);
        signup_button.setStyle(buttonStyle);
        logIn_button.setMinWidth(300);
        signup_button.setMinWidth(300);
        //border pane settings
        BorderPane.setAlignment(hBox, Pos.CENTER);
        BorderPane.setAlignment(vBox, Pos.CENTER);
        //HBox
        hBox.getChildren().add(pageTitle);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color: #11151c;");
        //vbox
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10, 50, 50, 50));
        vBox.setSpacing(30);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBox);
        vBox.getChildren().add(logIn_button);
        vBox.getChildren().add(signup_button);
        vBox.setStyle("-fx-background-color: #11151c");
        borderPane.setCenter(vBox);
        this.stage.setScene(new Scene(borderPane, 500, 500));
        this.stage.show();

        logIn_button.setOnAction(t -> new FormPage("Login", this.stage, con));
        signup_button.setOnAction(t -> new FormPage("Signup", this.stage, con));


    }

}
