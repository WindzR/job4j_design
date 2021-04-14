package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    String message = " ";
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("?msg=Exit")) {
                            message = "Server is closed!";
                            System.out.println(message);
                            server.close();
                        } else if (str.contains("?msg=Hello")) {
                            message = "Hello!";
                            System.out.println(message);
                        } else if (str.contains("?msg=")) {
                            message = "What";
                            System.out.println("What");
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\"".getBytes());
                    out.write((message + "\r\n").getBytes());
                }
            }
        }
    }
}
