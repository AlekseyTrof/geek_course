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
                if (message.equalsIgnoreCase("/end")) {
                    break;
                } else {
                    System.out.println("Клиент написал: " + message);
                }
            }
            System.out.println("Соединение разорвано");
            out.writeUTF("/end");
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessageFromConsole() {
        new Thread(() -> {
            try {
                while (true) {
                    String message = scanner.nextLine();
                    if (!message.trim().isEmpty()) {
                        out.writeUTF(message);
                    }
                    if (message.equalsIgnoreCase("/end")) {
                        System.out.println("Соединение разорвано");
                        break;
                    }
                }
                closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
