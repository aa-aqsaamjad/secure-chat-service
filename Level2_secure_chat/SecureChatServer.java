package Level2_secure_chat;

import javax.net.ssl.*; // required for SSL
import java.io.*;
import java.util.ArrayList;


public class SecureChatServer {

    public static final int PORT = 43221;
    public static ArrayList<SecureChatServerThread> clientThreads = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // integrates generated key
        System.setProperty("javax.net.ssl.keyStore", "keys/serverkeystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "password123");

        try {
        SSLServerSocket serverSocket = (SSLServerSocket) // create SSL server socket instead of basic server socket in level 1
        SSLServerSocketFactory.getDefault().createServerSocket(PORT); //creates socket on specified port

        System.out.println("Chat server started. Waiting for clients to connect...");
        
        // continuously loop to accept new client connections
        while (true) {
            SSLSocket clientSocket = (SSLSocket) serverSocket.accept(); // wait for a client to connect
            System.out.println("New client connected:" + clientSocket.getRemoteSocketAddress());
            
            // create and start a new thread to handle the client
            SecureChatServerThread thread = new SecureChatServerThread(clientSocket);
            thread.start();
            // add thread to list of client threads
            clientThreads.add(thread);
        }

    } catch (IOException e) {
        System.out.println("Error starting SSL server: " + e.getMessage());
    }
}
    
    // broadcast message to all connected clients
    public static void broadcastMessage(String message, SecureChatServerThread sender) {
        for (SecureChatServerThread clientThread : clientThreads) {
            if (clientThread != sender) { // don't send the message back to the sender
                clientThread.sendMessage(message);
            }
        }
    }

    public static void removeClient(SecureChatServerThread thread) {
        clientThreads.remove(thread);
        System.out.println("Client disconnected. Current clients: " + clientThreads.size());
    }
}
