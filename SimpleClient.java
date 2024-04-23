import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class SimpleClient {

    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 8080;
        Socket socket = null;
        BufferedReader reader = null;

        try {
            socket = new Socket(hostname, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read the message from the server and print it
            String serverMessage = reader.readLine();
            System.out.println("Server says: " + serverMessage);

        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        } finally {
            // Close the reader and socket after use
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Could not close reader: " + e.getMessage());
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Could not close socket: " + e.getMessage());
                }
            }
        }
    }
}
