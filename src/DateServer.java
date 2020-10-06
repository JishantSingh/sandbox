import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DateServer {
    public static void main(String[] args) {
        try {
            ServerSocket sock = new ServerSocket(6017);
            System.out.println("Server running");
            while (true){
                Socket client = sock.accept();
                PrintWriter pout = new PrintWriter(client.getOutputStream(),true);
                pout.println("Hello World!");
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
