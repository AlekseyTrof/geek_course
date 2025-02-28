package course2.lesson7.server;

import course2.lesson7.constants.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


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
            System.out.println("Ошибка в работе сервера");
            ex.printStackTrace();
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized void broadcastMessage(String message) {
        clients.forEach(client -> client.sendMessage(message));
    }

    public synchronized void broadcastClientsList() {
        StringBuilder sb = new StringBuilder(Constants.CLIENTS_LIST_COMMAND).append(" ");
        for (ClientHandler o : clients) {
            sb.append(o.getName()).append(" ");
        }
        broadcastMessage(sb.toString());
    }

    public synchronized String getActiveClients() {
        StringBuilder sb = new StringBuilder(Constants.CLIENTS_LIST_COMMAND).append(" ");
        sb.append(clients.stream()
                .map(c -> c.getName())
                .collect(Collectors.joining(" ")));

//        for (ClientHandler o : clients) {
//            sb.append(o.getName()).append(" ");
//        }
        return sb.toString();
    }

    public synchronized void sendMessageToClient(String toNick, ClientHandler fromNick, String message) {
        for (ClientHandler client : clients) {
            if (client.getName().equals(toNick)) {
                String mes = message.replace("/w ", "").substring(5);
                client.sendMessage("От " + fromNick.getName() + ": " + mes);
                fromNick.sendMessage("Клиенту " + toNick + ": " + mes);
                return;
            }
        }
        fromNick.sendMessage("Участника с ником " + toNick + " нет в чат-комнате");
    }

    public synchronized void sendMessageToClient(String toNick, String fromNick, String message) {
        for (ClientHandler client : clients) {
            if (client.getName().equals(toNick)) {
                String mes = message.replace("/w ", "").substring(5);
                client.sendMessage("От " + fromNick + ": " + mes);
                return;
            }
        }
        System.out.println("Участника с ником " + toNick + " нет в чат-комнате");
    }

    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
        broadcastClientsList();
    }

    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
        broadcastClientsList();
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
                    if (!message.trim().isEmpty() && !message.startsWith("/")) {
                        broadcastMessage("Сервер: " + message);
                    }
                    if (message.startsWith("/clients")) {
                        System.out.println(getActiveClients());
                    }
                    if (message.startsWith("/w ")) {
                        String[] toNick = message.split("\\s+");
                        sendMessageToClient(toNick[1], "Cервер", message);
                    }
                    if (message.equalsIgnoreCase(Constants.END_COMMAND)) {
                        System.out.println("Соединение разорвано");
                        broadcastMessage("/end");
                        scanner.close();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
