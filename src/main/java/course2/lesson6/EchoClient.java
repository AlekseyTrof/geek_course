package course2.lesson6;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EchoClient extends JFrame {
    private final String SERVER_ADDRESS = "localhost";
    private final int SERVER_PORT = 8080;
    private JTextField msgInputField;
    private JTextArea chatArea;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public EchoClient() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        prepareGUI();
    }

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                while (true) {
                    String strFromServer = in.readUTF();
                    if (strFromServer.equalsIgnoreCase("/end")) {
                        break;
                    }
                    chatArea.append("Сервер: " + strFromServer);
                    chatArea.append("\n");
                }
                chatArea.append("Соединение разорвано");
                msgInputField.setEnabled(false);
                closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void closeConnection() {
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMessage() {
        if (!msgInputField.getText().trim().isEmpty()) {
            try {
                chatArea.append("Клиент: " + msgInputField.getText() + "\n");
                out.writeUTF(msgInputField.getText());
                msgInputField.setText("");
                msgInputField.grabFocus();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения!");
            }
        }
    }

    public void prepareGUI() {
        //param window
        setBounds(600, 300, 500, 500);
        setTitle("Клиент");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // text field for out message
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        //Нижняя панель с полем для вывода сообщений и кнопкой отправки сообщений
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JButton btnSendMessage = new JButton("Отправить");

        msgInputField = new JTextField();

        buttonPanel.add(msgInputField, BorderLayout.CENTER);
        buttonPanel.add(btnSendMessage, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);

        btnSendMessage.addActionListener(e -> sendMessage());

        msgInputField.addActionListener(e -> sendMessage());

        //Настраиваем действие на закрытие окна
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    out.writeUTF("/end");
                    closeConnection();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EchoClient());
    }
}
