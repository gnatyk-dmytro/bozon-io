package org.demo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Listening for Connections: 8080 port...");
        while (true) {
            try (Socket socket = serverSocket.accept()){
                Scanner scanner = new Scanner(System.in);

                System.out.print("Type numA:");
                int a = scanner.nextInt();

                System.out.print("Type numB:");
                int b = scanner.nextInt();

                Date date = new Date();
                String httpResponse = "HTTPS/1.1 200 OK\r\n\r\n" + " Date:" + date;
                socket.getOutputStream()
                        .write(httpResponse.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}