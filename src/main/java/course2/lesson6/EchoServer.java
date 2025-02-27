package course2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            socket = null;
            System.out.println("Сервер запущен, ожидает подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            sendMessageFromConsole();

            while (true) {
                String message = in.readUTF();
                if (message.contains("/end")) {
                    break;
                } else {
                    System.out.println("Клиент написал: " + message);
                    sendMessage("Клиент: " + message);
                }
            }
            System.out.println("Соединение разорвано");
            out.writeUTF("/end");
            scanner.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Ошибка при подключении клиента: " + e.getMessage());
        }
    }

    public static void sendMessageFromConsole() {
        new Thread(() -> {
            try {
                while (true) {
                    String message = scanner.nextLine();
                    if (!message.trim().isEmpty()) {
                        sendMessage("Сервер: " + message);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Соединение разорвано");
            }
        }).start();
    }
    public static void sendMessage(String message) throws IOException {
        if (!message.trim().isEmpty()) {
            out.writeUTF(message);
        }
    }
}
