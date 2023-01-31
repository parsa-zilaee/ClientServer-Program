import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class server {

    public static void main(String args[]) throws IOException {

        System.out.println("[SERVER] waiting for client connection...");

        // Creating the Socket and connecting them
        ServerSocket welcomeSocket = new ServerSocket(444);
        Socket connectionSocket = welcomeSocket.accept();

        System.out.println("[SERVER] client : connected...");

        // Run the operation from the connection socket
        DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

        String date = (new Date()).toString();
        System.out.println("[SERVER] client connected on " + date);

        while (true) {

            double result = 0;
            String input = inFromClient.readUTF();

            if(input.equals("END"))
                break;

            int optionNum = Integer.parseInt(input);
            String sentence = inFromClient.readUTF();

            String numbers[] = sentence.split(" ");

            int x;
            int y;

            // addition operation
            if (optionNum==1) {

                x = Integer.parseInt(numbers[0]);
                y = Integer.parseInt(numbers[1]);
                result = x + y;
            }

            // subtraction operation
            else if (optionNum==2) {

                x = Integer.parseInt(numbers[0]);
                y = Integer.parseInt(numbers[1]);
                result = x - y;
            }

            // multiplication operation
            else if (optionNum==3) {

                x = Integer.parseInt(numbers[0]);
                y = Integer.parseInt(numbers[1]);
                result = x * y;
            }

            // division operation
            else if(optionNum==4) {

                x = Integer.parseInt(numbers[0]);
                y = Integer.parseInt(numbers[1]);
                result = x / y;
            }

            System.out.println("Sending the result...");

            // send the result back to the client.
            outToClient.writeUTF(Integer.toString((int) result));
        }
    }
}