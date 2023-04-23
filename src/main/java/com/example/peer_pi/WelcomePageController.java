package com.example.peer_pi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomePageController {

    @FXML
    private Button LogInBtn1;

    @FXML
    private Button signUp1;

    @FXML

    protected void switchToLogIn(ActionEvent event) throws IOException, InterruptedException {
        Parent scene2 = FXMLLoader.load(getClass().getResource("Log in.fxml"));
        Scene displayScene = new Scene(scene2);
        Stage window = (Stage)((Node)(event.getSource())).getScene().getWindow();
        window.setScene(displayScene);
        window.show();
    }
    protected void switchToSignUp(ActionEvent event) throws IOException, InterruptedException {
        Parent scene2 = FXMLLoader.load(getClass().getResource("Sign up.fxml"));
        Scene displayScene = new Scene(scene2);
        Stage window = (Stage)((Node)(event.getSource())).getScene().getWindow();
        window.setScene(displayScene);
        window.show();
    }
}
