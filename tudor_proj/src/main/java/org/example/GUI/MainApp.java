package org.example.GUI;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.grpc.RefreshServiceGrpc;
//import org.example.network.Server;
import org.example.grpc.Refresh;
import org.example.grpc.RefreshRequest;
import org.example.grpc.RefreshReply;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import static javafx.application.Application.launch;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        openClientWindow(primaryStage);
        openClientWindow(new Stage());
    }

    private void openClientWindow(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Scene scene = new Scene(loader.load());

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        //Server server = new Server(12345);
        //server.start();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 12345)
                .usePlaintext()
                .build();

//        RefreshServiceGrpc.RefreshServiceBlockingStub stub = RefreshServiceGrpc.newBlockingStub(channel);
//
//        RefreshRequest request = RefreshRequest.newBuilder()
//                .setMessage("REFRESH")
//                .build();
//
//        RefreshReply response = stub.sendRefresh(request);
//
//        System.out.println("Response from server: " + response.getStatus());
//
        channel.shutdown();

        launch(args);
    }
}
