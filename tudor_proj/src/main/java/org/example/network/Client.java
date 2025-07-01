package org.example.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.function.Consumer;

public class Client {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Thread listenerThread;

    private Consumer<String> onMessageReceived;

    public Client(String host, int port, Consumer<String> onMessageReceived) {
        this.onMessageReceived = onMessageReceived;
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            startListening();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startListening() {
        listenerThread = new Thread(() -> {
            try {
                String msg;
                while((msg = in.readLine()) != null) {
                    onMessageReceived.accept(msg);
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        });
        listenerThread.setDaemon(true);
        listenerThread.start();
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }

    private void close() {
        try {
            if(socket != null) socket.close();
            if(listenerThread != null) listenerThread.interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
