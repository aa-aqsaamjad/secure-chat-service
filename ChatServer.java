import java.io.*;
import java.net.*;
import java.util.ArrayList;

// main server class
public class ChatServer {

    public static final int PORT = 43221;
    // list to keep track of connected client threads
    public static ArrayList<ChatServerThread> clientThreads = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println("Starting chat server on port " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)){ // create server socket
            System.out.println("Chat server started. Waiting for clients to connect...");
            
            // continuously loop to accept new client connections
            while (true) {
                Socket clientSocket = serverSocket.accept(); // wait for a client to connect
                System.out.println("New client connected:" + clientSocket.getRemoteSocketAddress());

                // create and start a new thread to handle the client
                ChatServerThread thread = new ChatServerThread(clientSocket);
                thread.start();
                // add thread to list of client threads
                clientThreads.add(thread);
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        } 
    }

    // broadcast message to all connected clients
        public static void broadcastMessage(String message, ChatServerThread sender) {
            for (ChatServerThread clientThread : clientThreads) {
                if (clientThread != sender) { // don't send the message back to the sender
                    clientThread.sendMessage(message);
                }
            }
        }

    // remove a client thread from the list
    public static void removeClient(ChatServerThread thread) {
        clientThreads.remove(thread);
        System.out.println("Client disconnected. Current clients: " + clientThreads.size());
    }
}
