package course2.lesson7.client;

import course2.lesson7.constants.Constants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EchoClient extends JFrame {

    private JTextField textField;
    private JTextArea textArea;

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public EchoClient() {
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
                    if (messageFromServer.contains("/end")) {
                        break;
                    }
                    textArea.append(messageFromServer);
                    textArea.append("\n");
                }
                textArea.append("Соединение разорвано");
                textField.setEnabled(false);
                closeConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }


    private void closeConnection() {
        try {
            dataOutputStream.close();
        } catch (Exception ex) {

        }
        try {
            dataInputStream.close();
        } catch (Exception ex) {

        }
        try {
            socket.close();
        } catch (Exception ex) {

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
        }
    }

    private void prepareUI() {
        setBounds(200, 200, 500, 500);
        setTitle("EchoClient");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);


        JPanel panel = new JPanel(new BorderLayout());
        JButton button = new JButton("Отправить");
        panel.add(button, BorderLayout.EAST);
        textField = new JTextField();
        panel.add(textField, BorderLayout.CENTER);

        add(panel, BorderLayout.SOUTH);

        JPanel loginPanel = new JPanel(new GridLayout());
        JTextField loginField = new JTextField("Логин");
        loginPanel.add(loginField);
        JTextField passField = new JTextField("Пароль");
        loginPanel.add(passField);
        JButton authButton = new JButton("Авторизоваться");
        loginPanel.add(authButton);
        add(loginPanel, BorderLayout.NORTH);

        loginField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Если текстовое поле содержит начальный текст, очищаем его
                if (loginField.getText().equals("Логин")) {
                    loginField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Если текстовое поле пустое, восстанавливаем начальный текст
                if (loginField.getText().isEmpty()) {
                    loginField.setText("Логин");
                }
            }
        });

        //Устанавливаем курсор сразу в нужное нам поле для ввода
        SwingUtilities.invokeLater(() -> textField.requestFocusInWindow());

        passField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passField.getText().equals("Пароль")) {
                    passField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passField.getText().isEmpty()) {
                    passField.setText("Пароль");
                }
            }
        });

        authButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dataOutputStream.writeUTF(Constants.AUTH_COMMAND +" " + loginField.getText() + " " + passField.getText());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EchoClient::new);
    }

}



