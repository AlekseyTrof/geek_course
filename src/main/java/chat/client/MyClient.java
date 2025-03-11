package chat.client;

import chat.constants.Constants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;

public class MyClient extends JFrame {

    private JTextField textField;
    private JTextArea textArea;

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private JTextField loginField;
    private JTextField passField;
    private JButton button;
    private String nameLog;
    private final Calendar time = new GregorianCalendar();
    private BufferedWriter writer;

    public MyClient() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prepareUI();
    }

    private void openConnection() throws IOException {
        socket = new Socket(Constants.SERVER_ADDRESS, Constants.SERVER_PORT);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                while (true) {
                    String messageFromServer = dataInputStream.readUTF();
                    writeLog(messageFromServer);
                    if (messageFromServer.startsWith(Constants.AUTH_COMMAND)) {
                        displayMessagesFromArchive();
                    }
                    if (messageFromServer.contains("/end")) {
                        break;
                    }
                    textArea.append(messageFromServer);
                    textArea.append("\n");
                }
                textArea.append("Соединение разорвано");
                textField.setDisabledTextColor(Color.RED);
                textField.setText("Сервер отключился");
                textField.setEnabled(false);
                button.setEnabled(false);
                closeConnection();
            } catch (Exception ex) {
                textField.setEnabled(false);
                textField.setDisabledTextColor(Color.RED);
                textField.setText("Вы отключились от чат-канала");
                button.setEnabled(false);
                throw new RuntimeException("Соединение потеряно");
            }
        }).start();
    }


    private void closeConnection() {
        try {
            dataOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            dataInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendMessage() {
        if (textField.getText().trim().isEmpty()) {
            return;
        }
        try {
            dataOutputStream.writeUTF(textField.getText());
            textField.setText("");
            textField.grabFocus();
        }catch (Exception ex) {
            ex.printStackTrace();
            textField.setEnabled(false);
            textField.setDisabledTextColor(Color.RED);
            textField.setText("Чат-канал заблокирован");
            button.setEnabled(false);
        }
    }

    private void prepareUI() {
        setBounds(200, 200, 500, 500);
        setTitle("Client chat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        button = new JButton("Отправить");
        panel.add(button, BorderLayout.EAST);
        textField = new JTextField();
        panel.add(textField, BorderLayout.CENTER);

        add(panel, BorderLayout.SOUTH);

        JPanel loginPanel = new JPanel(new GridLayout());
        loginField = new JTextField("Логин");
        loginPanel.add(loginField);
        passField = new JTextField("Пароль");
        loginPanel.add(passField);
        JButton authButton = new JButton("Авторизоваться");
        loginPanel.add(authButton);
        add(loginPanel, BorderLayout.NORTH);

        loginField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Если текстовое поле содержит начальный текст, очищаем его
                if (loginField.getText().trim().equals("Логин")) {
                    loginField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Если текстовое поле пустое, восстанавливаем начальный текст
                if (loginField.getText().trim().isEmpty()) {
                    loginField.setText("Логин");
                }
            }
        });

        //Устанавливаем курсор сразу в нужное нам поле для ввода
        SwingUtilities.invokeLater(() -> textField.requestFocusInWindow());

        passField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (passField.getText().trim().equals("Пароль")) {
                    passField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (passField.getText().trim().isEmpty()) {
                    passField.setText("Пароль");
                }
            }
        });

        passField.addActionListener(e -> {
            try {
                dataOutputStream.writeUTF(Constants.AUTH_COMMAND + " " + loginField.getText() + " " + passField.getText());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        authButton.addActionListener(e -> {
            try {
                dataOutputStream.writeUTF(Constants.AUTH_COMMAND + " " + loginField.getText() + " " + passField.getText());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        setVisible(true);
    }

    public void writeLog(String log) throws IOException {
        nameLog ="history_" +  loginField.getText() + ".txt";
        writer = new BufferedWriter(new FileWriter(nameLog, true));
        writer.write(time.getTime() + ": " + log + "\n");
        writer.flush();
    }

    public List<String> loadLastMessages(int numberOfMessages) {
        List<String> messages = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nameLog))) {
            String line;
            while ((line = reader.readLine()) != null) {
                messages.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Проверяем, сколько сообщений у нас есть, и берем последние сообщения в правильном порядке
        // Math.max(0, ...): Эта функция используется для гарантии того,
        // что startIndex не будет отрицательным.
        // Если бы количество сообщений было меньше,
        // чем numberOfMessages (например, у вас 3 сообщения, а вы хотите получить 10),
        // это выражение вернет 0, что позволит избежать отрицательного индекса.
        int startIndex = Math.max(0, messages.size() - numberOfMessages);
        return messages.subList(startIndex, messages.size());
    }

    // Метод для отображения сообщений в поле
    public void displayMessagesFromArchive() {
        List<String> lastMessages = loadLastMessages(10);
        for (String message : lastMessages) {
            textArea.append(message + "\n"); // Здесь вы можете заменить вывод на отображение в интерфейсе
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyClient::new);
    }

}



