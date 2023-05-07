package com.company;

import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server started. Listening on port 1234...");

        while (true) {
            Socket clientSocket = serverSocket.accept(); // waiting on this line... till some1 connects
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            // Read the file from the server's directory
            File fileToSend = new File("src/servermap/map1");
            FileInputStream fis = new FileInputStream(fileToSend);
            BufferedInputStream bis = new BufferedInputStream(fis);

            // Get the output stream of the client socket
            OutputStream os = clientSocket.getOutputStream();

            // Write the file to the output stream
            byte[] buffer = new byte[1024];
            int count;
            while ((count = bis.read(buffer)) > 0) {
                os.write(buffer, 0, count);
            }

            // Flush the output stream and close the socket
            os.flush();
            bis.close();
            clientSocket.close();
            System.out.println("File sent to the client.");
        }
    }
}
