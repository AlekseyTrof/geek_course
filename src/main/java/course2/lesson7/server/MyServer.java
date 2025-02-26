package course2.lesson7.server;

import course2.lesson7.client.EchoClient;
import course2.lesson7.constants.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MyServer {
    /**
     * Сервис аутентификации.
     */
    private AuthService authService;

    private static Scanner scanner = new Scanner(System.in);
    /**
     * Активные клиента.
     */
    private List<ClientHandler> clients;


    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(Constants.SERVER_PORT)) {
            authService = new BaseAuthService();
            authService.start();

            clients = new ArrayList<>();
            sendMessageFromConsole();

            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }

        } catch (IOException ex) {
            System.out.println("Ошибка в работе сервера.");
            ex.printStackTrace();
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized void broadcastMessage(String message) {

        clients.forEach(client -> client.sendMessage(message));
//        for (ClientHandler client : clients) {
//            client.sendMessage(message);
//        }
    }

    public synchronized void sendOnlyOneUser(String nick, String message) {
        for (ClientHandler client : clients) {
            if (client.getName().equals(nick)) {
                String mes = message.replace("/w ", "");
                mes = mes.replace("nick1 ", "");
                mes = mes.replace("nick2 ", "");
                mes = mes.replace("nick3 ", "");
                client.sendMessage(mes);
            }
        }
    }

    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void sendMessageFromConsole() {
        new Thread(() -> {
            try {
                while (true) {
                    String message = scanner.nextLine();
                    if (!message.trim().isEmpty()) {
                        broadcastMessage("Сервер: " + message);
                    }
                    if (message.equalsIgnoreCase(Constants.END_COMMAND)) {
                        System.out.println("Соединение разорвано");
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

}
