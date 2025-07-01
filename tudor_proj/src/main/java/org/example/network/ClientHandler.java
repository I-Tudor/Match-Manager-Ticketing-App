package org.example.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Server server;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String msg;
        try {
            while((msg = in.readLine()) != null) {
                if(msg.equals("REFRESH")) {
                    server.broadcast("REFRESH");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String msg) {
        out.println(msg);
    }
}
