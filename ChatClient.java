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
        int port = 43221; // fixed port to match server

        try (
            Socket socket = new Socket(host, port);
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream())); // read messages from server
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true); // send messages to server
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in)); // read user input from console
        ) {
            System.out.println("Connected to chat server at " + args[0] + ": 43221");
            
            // read and print welcome messages from server
            for (int i = 0; i < 2; i++) {
                String serverMessage = serverIn.readLine();
                if (serverMessage == null) {
                    break;
                }
                System.out.println("Server: " + serverMessage);
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
                    break;
                }
            }
            
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
