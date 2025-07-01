package org.example.test;

import java.net.http.*;
import java.net.URI;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;


public class MatchTestJava {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/matches"))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        System.out.println("Response: " + response.body());
    }
}
