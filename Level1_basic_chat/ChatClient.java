package Level1_basic_chat;

import java.io.*;
import java.net.*;

// main client class
public class ChatClient {

    public static void main(String[] args) throws IOException {
        // check command line arguments for hostname
        if (args.length != 1) {
             System.out.println("Usage: java ChatClient <hostname>");
             return;
        }

        String host = args[0];
        int port = 43221;

        try (
            Socket socket = new Socket(host, port);
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream())); // read messages from server
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true); // send messages to server
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in)); // read user input from console
        ) {
            System.out.println("Connected securely to chat server at " + host + ":" + port);

            // username prompt
            String prompt = serverIn.readLine();
            if ("ENTER_USERNAME".equals(prompt)) {
                System.out.print("Enter username: ");
                String username = userIn.readLine();
                if (username == null || username.trim().isEmpty()) username = "Guest";
                serverOut.println(username);
            } 

            // start a separate thread to continuously read messages from server
            Thread reader = new Thread(() -> {
                try {
                    String s;
                    while ((s = serverIn.readLine()) != null) { // read until server closes connection
                        System.out.println(s); // print server message to client console
                    }
                } catch (IOException e) {
                    System.out.println("Exception in reader thread: " + e.getMessage());
                }
            });
            reader.start();

             // allow user to send messages to server
            String input;
            while ((input = userIn.readLine()) != null) {
                serverOut.println(input); // print client message to server console
                if ("/quit".equalsIgnoreCase(input.trim())) {
                    System.out.println("Exiting chat...");
                    break;
                }
            }
            
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
