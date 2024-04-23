import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;

public class SimpleServer {

    public static void main(String[] args) {
        int port = 8080;
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        PrintWriter writer = null;
        
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started. Listening on Port " + port);

            while (true) {
                try {
                    clientSocket = serverSocket.accept();
                    OutputStream out = clientSocket.getOutputStream();
                    writer = new PrintWriter(out, true);

                    // Send a welcome message to the client
                    writer.println("Hello, client! You are connected to the server.");
                } catch (IOException e) {
                    System.err.println("I/O error: " + e.getMessage());
                } finally {
                    // Close the writer if it's open
                    if (writer != null) {
                        writer.close();
                    }
                    // Close the client socket if it's open
                    if (clientSocket != null && !clientSocket.isClosed()) {
                        try {
                            clientSocket.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            e.printStackTrace();
        } finally {
            // Close the server socket if it's open
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
