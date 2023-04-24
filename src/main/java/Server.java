import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8080;

    public static void main(String[] args) {


        try (ServerSocket serverSocket = new ServerSocket(PORT);) { // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
            System.out.println("Сервер запущен.");
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    System.out.println("New connection accepted " + clientSocket.getPort());
                    out.println(String.format("Hi Client! your port is %s.", clientSocket.getPort()));
                    System.out.println(in.readLine());
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

