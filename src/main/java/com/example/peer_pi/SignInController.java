package com.example.peer_pi;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignInController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/peer_pie_data";
    private final String username = "root";
    private final String password = "arafath221158";

    public SignInController() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    @FXML
    void signUp(ActionEvent event) throws SQLException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
        } else {
            int participantNo = generateParticipantNo();
            String signUpDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String query = "INSERT INTO users (participant_no, first_name, last_name, phone, email, password, sign_up_datetime) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, participantNo);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, phone);
            statement.setString(5, email);
            statement.setString(6, password);
            statement.setString(7, signUpDateTime);
            statement.executeUpdate();
            statusLabel.setText("Sign up successful! Your participant no. is " + participantNo);
        }
    }

    private int generateParticipantNo() {
        Random random = new Random();
        return random.nextInt(1000000);
    }

    @FXML
    void goToLogin(ActionEvent event) {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
}
