import java.io.*;
import java.net.*;

// main server class
public class ChatServer {

    public static void main(String[] args) throws IOException {

        // port number
        int port = 43221;

    
        try (ServerSocket serverSocket = new ServerSocket(port)){ // create server socket
            System.out.println("Server is listening on port " + port);

            // continuously loop to accept new client connections
            while (true) {
                Socket clientSocket = serverSocket.accept(); // wait for a client to connect
                System.out.println("New client connected:" + clientSocket.getRemoteSocketAddress());

                // create and start a new thread to handle the client
                ChatServerThread t = new ChatServerThread(clientSocket);
                t.start();
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        } 
    }

}
