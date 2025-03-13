package chat.server;

import chat.constants.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class MyServer {
    private static final Logger LOGGER = LogManager.getLogger(MyServer.class);
    private AuthService authService;
    private static Scanner scanner = new Scanner(System.in);
    private List<ClientHandler> clients;
    private DbAuthServer dbAuth;

    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(Constants.SERVER_PORT)) {
            authService = new DbAuthServer();
            if (authService instanceof DbAuthServer) {
                dbAuth = (DbAuthServer) authService;
            }

            clients = new ArrayList<>();
            sendMessageFromConsole();

            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                LOGGER.info(socket);
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
                    if (message.startsWith(Constants.CLIENTS_LIST_COMMAND)) {
                        System.out.println(getActiveClients());
                    }
                    if (message.startsWith(Constants.SEND_USER)) {
                        String[] toNick = message.split("\\s+");
                        sendMessageToClient(toNick[1], "Cервер", message);
                    }
                    if (message.startsWith(Constants.ADD_CLIENT_TO_BASE)) {
                        String[] msg = message.split("\\s+");
                        dbAuth.insertOneClient(msg[1],msg[2]);
                    }
                    if (message.startsWith(Constants.DEL_CLIENT_FROM_BASE)) {
                        String[] msg = message.split("\\s+");
                        dbAuth.deleteClientFromBase(msg[1]);
                    }
                    if (message.startsWith(Constants.SET_LOGIN)) {
                        String[] msg = message.split("\\s+");
                        dbAuth.updateLoginOfClient(msg[1], msg[2]);
                    }
                    if (message.startsWith(Constants.DEL_CLIENT_FROM_BASE)) {
                        String[] msg = message.split("\\s+");
                        dbAuth.deleteClientFromBase(msg[1]);
                    }
                    if (message.startsWith(Constants.CLEAR_BASE)) {
                        dbAuth.clearTableOfClients();
                    }
                    if (message.startsWith(Constants.DROP_BASE)) {
                        dbAuth.dropTableClients();
                    }
                    if (message.startsWith(Constants.CREATE_TABLE)) {
                        dbAuth.createTable();
                    }
                    if (message.equalsIgnoreCase(Constants.END_COMMAND)) {
                        System.out.println("Соединение разорвано");
                        broadcastMessage(Constants.END_COMMAND);
                        scanner.close();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void setLoginOfClient(String loginOld, String loginNew) {
        try {
            dbAuth.updateLoginOfClient(loginOld, loginNew);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при изменении логина пользователя");
        }
    }
}
