import java.io.*;
import java.net.*;

// thread class to handle communication with a connected client
public class ChatServerThread extends Thread {

    private final Socket socket; // client socket
    private PrintWriter out; // output stream to client
    private BufferedReader in; // input stream from client

    // constructor
    public ChatServerThread(Socket socket) {
        this.socket = socket;
    }

    // main thread method
    public void run() {
        System.out.println("ServerThread started for client " + socket.getRemoteSocketAddress());

        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            // send welcome messages to client
            out.println("You are connected to the chat server!");
            out.println("Type /quit to disconnect.");

            String line;
            // continuously read messages from client
            while ((line = in.readLine()) != null) {
                System.out.println("Received from " + socket.getRemoteSocketAddress() + ": " + line);

                // check for quit command
                if ("/quit".equalsIgnoreCase(line.trim())) {
                    System.out.println("Client " + socket.getRemoteSocketAddress() + " disconnected.");
                    break;
                }

                // TODO: Broadcast message to other clients
            }

        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return;
        }
    }
}
