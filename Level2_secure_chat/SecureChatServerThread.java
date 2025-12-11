package Level2_secure_chat;

import javax.net.ssl.SSLSocket;
import java.io.*;


// thread class to handle communication with a connected client
public class SecureChatServerThread extends Thread {

    private final SSLSocket socket; // client SSL socket
    private PrintWriter out; // to this client
    private BufferedReader in; // from this client

    // constructor
    public SecureChatServerThread(SSLSocket socket) {
        this.socket = socket;
    }

    // main thread method
    public void run() {

        try {
            socket.startHandshake(); //TLS Handshake
            
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        
            // prompt client for username
            out.println("ENTER_USERNAME");
            String username = in.readLine();

            // announce client joining
            SecureChatServer.broadcastMessage("SERVER: " + username + " has joined the chat. Type /quit to exit.", this);


            // read and broadcast messages from client
            String line;
            // continuously read messages from client
            while ((line = in.readLine()) != null) {
                // check for quit command
                if ("/quit".equalsIgnoreCase(line.trim())) {
                    SecureChatServer.broadcastMessage(username + " has left the chat!", this);
                    SecureChatServer.removeClient(this);
                    break;
                }
                // broadcast message to other clients
                String message = username + ": " + line;
                SecureChatServer.broadcastMessage(message, this);

            }

        } catch (IOException e) {
            System.out.println("I/OException: " + socket.getRemoteSocketAddress() + ": " + e.getMessage());
            return;
        }
    }

    // method to send a message to the client
    public void sendMessage(String message) {
            out.println(message);
        
    }
}
