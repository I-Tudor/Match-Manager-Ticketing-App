package org.example.GUI;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.domain.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.network.Client;
import org.example.repository.MatchRepository;
import org.example.repository.TicketORMRepo;
import org.example.repository.TicketRepository;
import org.example.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private Client client;

    @FXML private ListView<String> matchListView = new ListView<>();
    @FXML private TextField customerNameField;
    @FXML private TextField seatsField;
    @FXML private Button sellTicketButton;
    @FXML private Button logoutButton;

    private TicketService ticketService;

    private boolean isAuthenticated = false;

    public MainController() {
        try {
            client = new Client("localhost", 12345, (msg -> {
                if ("REFRESH".equals(msg)) {
                    Platform.runLater(this::loadMatchList);
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
        MatchRepository matchRepo = new MatchRepository();
        System.out.println(matchRepo.getAll());
        TicketORMRepo ticketRepo = new TicketORMRepo();
        ticketService = new TicketService(matchRepo, ticketRepo);
        loadMatchList();
    }

    public void initialize() {
        loadMatchList();

        sellTicketButton.setOnAction(this::sellTicket);

        logoutButton.setOnAction(this::logout);
    }

    private void loadMatchList() {
        List<Match> matches = ticketService.getMatches();
        matchListView.getItems().clear();

        for (Match match : matches) {
            String matchDisplay = match.getTeamA() + " vs " + match.getTeamB() +
                    " | Price: " + match.getTicketPrice();

            if (match.getAvailableSeats() == 0) {
                matchDisplay += " | SOLD OUT ";
            } else {
                matchDisplay += " | Available Seats: " + match.getAvailableSeats();
            }

            matchListView.getItems().add(matchDisplay);
        }
        styleSoldOutMatches();
    }

    private void sellTicket(ActionEvent event) {
        String customerName = customerNameField.getText();
        int seats = Integer.parseInt(seatsField.getText());

        int selectedIndex = matchListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Match selectedMatch = ticketService.getMatches().get(selectedIndex);

            boolean success = ticketService.sellTicket(selectedMatch.getId(), customerName, seats);
            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Tickets Sold Successfully!", ButtonType.OK);
                alert.show();
                client.sendMessage("REFRESH");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Not enough available seats.", ButtonType.OK);
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a match to sell tickets.", ButtonType.OK);
            alert.show();
        }
    }

    private void logout(ActionEvent event) {
        logger.info("User logged out.");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();

            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

        } catch (IOException e) {
            logger.error("Error loading login window: {}", e.getMessage());
        }
    }

    private void styleSoldOutMatches() {
        matchListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    if (item.contains("SOLD OUT")) {
                        setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                        setDisable(true);
                    } else {
                        setStyle("");
                        setDisable(false);
                    }
                }
            }
        });
    }


}
