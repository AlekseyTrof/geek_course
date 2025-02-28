package course2.lesson7.server;

import course2.lesson7.constants.Constants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Optional;

public class ClientHandler {
    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private boolean auth = false;

    public ClientHandler(MyServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    authentication();
                    readMessage();
                } catch (IOException ex) {
                    throw new RuntimeException("Соединение разорвано с пользователем");
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException ex) {
            throw new RuntimeException("Проблемы при создании обработчика");
        }
    }

    //auth login pass
    private void authentication() throws IOException {
        while (true) {
            checkAuth();
            String str = in.readUTF();
            if (str.startsWith(Constants.AUTH_COMMAND)) {
                String[] tokens = str.split("\\s+");
                Optional<String> nick = server.getAuthService().getNickByLoginAndPass(tokens[1], tokens[2]);
                if (nick.isPresent()) {
                    if (!server.isNickBusy(nick.orElse(null))) {
                        sendMessage("/authok " + nick.get());
                        auth = true;
                        name = nick.get();
                        server.broadcastMessage(name + " зашел в чат");
                        server.subscribe(this);
                        return;
                    } else {
                        sendMessage("Учетная запись уже используется");
                    }
                } else {
                    sendMessage("Неверные логин/пароль");
                }
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessage() throws IOException {
        while (true) {
            String messageFromClient = in.readUTF();
            System.out.println("Сообщение от " + name + ": " + messageFromClient);
            if (messageFromClient.equals(Constants.END_COMMAND)) {
                return;
            }
            if (messageFromClient.startsWith(Constants.CLIENTS_LIST_COMMAND)) {
                sendMessage(server.getActiveClients());
            } else if (messageFromClient.contains(Constants.SEND_USER)) {
                String[] stringsFromUser = messageFromClient.split("\\s+");
                String nameWhoMessage = stringsFromUser[1];
                server.sendMessageToClient(nameWhoMessage, this, messageFromClient);
            } else {
                server.broadcastMessage(name + ": " + messageFromClient);
            }
        }
    }

    /*
    Отключение неавторизованных пользователей по тайм-ауту (120 секунд ждём после
    подключения клиента и, если он не авторизовался за это время, закрываем соединение).
     */
    public void checkAuth() {
        int start = LocalTime.now().toSecondOfDay();
        Thread thread = new Thread(() -> {
            while (true) {
                int now = LocalTime.now().toSecondOfDay();
                if (now - start > 120 && !auth) {
                    try {
                        socket.close();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace(); // Логируем ошибку
                    }
                }
                try {
                    Thread.sleep(1000); // Пауза в 1 секунду
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Восстанавливаем состояние прерывания
                }
            }
        });
        thread.start(); // Запускаем поток
    }

    private void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(name + " вышел из чата");
        try {
            in.close();
        } catch (IOException ex) {
            //ignore
        }
        try {
            out.close();
        } catch (IOException ex) {
            //ignore
        }
        try {
            socket.close();
        } catch (IOException ex) {
            //ignore
        }
    }

    public String getName() {
        return name;
    }
}
