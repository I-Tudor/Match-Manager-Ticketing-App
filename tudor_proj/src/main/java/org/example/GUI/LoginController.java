package org.example.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.service.UserService;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final UserService userService = new UserService();

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (userService.authenticate(username, password)) {
            openMainWindow(event);
        } else {
            errorLabel.setText("Invalid credentials!");
        }
    }

    private void openMainWindow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Main Window");
            stage.setScene(new Scene(root));
            stage.show();

            // Close the login window
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
