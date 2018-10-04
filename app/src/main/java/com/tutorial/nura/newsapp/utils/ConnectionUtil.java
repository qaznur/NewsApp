package com.tutorial.nura.newsapp.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ConnectionUtil {

    private static boolean state = false;

    public static boolean isOnline(){
        Thread thread = new Thread(() -> {
            try {
                int timeOutMillis = 1500;
                Socket socket = new Socket();
                SocketAddress address = new InetSocketAddress("8.8.8.8", 53);

                socket.connect(address, timeOutMillis);
                socket.close();

                state = true;
            } catch (IOException e) {
                state = false;
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return state;
    }
}
