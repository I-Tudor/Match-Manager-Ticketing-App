package org.example.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private final int port;
    private final List<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        new Thread(() -> {
            try(ServerSocket serverSocket = new ServerSocket(port)) {
                while(true) {
                    Socket clientSocket = serverSocket.accept();
                    ClientHandler handler = new ClientHandler(clientSocket, this);
                    clients.add(handler);
                    handler.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void broadcast(String msg) {
        for(ClientHandler handler : clients) {
            handler.send(msg);
        }
    }
}
