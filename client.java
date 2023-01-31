import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

    public class client {

        public static void main(String[] args) throws IOException {

            InetAddress ipNum = InetAddress.getLocalHost();
            int portNum = 444;

            Scanner read = new Scanner(System.in);
            Socket sock = new Socket(ipNum, portNum);

            DataInputStream outToServer = new DataInputStream(sock.getInputStream());
            DataOutputStream inFromServer = new DataOutputStream(sock.getOutputStream());

            while (true) {

                // Menu created and responds based on number of choice
                System.out.println("Choose an operation for calculation (Choose #)\n");
                System.out.println("1. Add");
                System.out.println("2. Subtract");
                System.out.println("3. Multiply");
                System.out.println("4. Divide");
                String choice = read.nextLine();

                // Numbers separated by a space will be split into separate strings to use as integers.
                System.out.println("Choose two numbers (separate with a space)");
                String num = read.nextLine();

                if (choice.equals("END"))
                    break;

                // sending the operation to server.java
                inFromServer.writeUTF(choice);
                inFromServer.writeUTF(num);

                // Displaying result after getting information from server
                String result = outToServer.readUTF();
                System.out.println("Result = " + result + "\n");
            }
        }
    }