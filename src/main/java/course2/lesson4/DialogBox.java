package course2.lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;

public class DialogBox extends JFrame{

    public DialogBox() throws HeadlessException {
        setTitle("Диалоговое окно");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setLayout(null);


        JTextArea textArea = new JTextArea();
        textArea.setBounds(5, 5, 375, 325);
        add(textArea);
        textArea.setEditable(false);

        JTextField textFieldForMessage = new JTextField("Поле ввода сообщения");
        textFieldForMessage.setBounds(5, 335, 280, 20);
        add(textFieldForMessage);

        textFieldForMessage.addActionListener(e -> {
            String text = textFieldForMessage.getText();
            textArea.append(text + "\n");
            textFieldForMessage.setText("");
            textFieldForMessage.requestFocus();

        });

        textFieldForMessage.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textFieldForMessage.getText().equals("Поле ввода сообщения")) {
                    textFieldForMessage.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textFieldForMessage.getText().isEmpty()) {
                    textFieldForMessage.setText("Поле ввода сообщения");
                }
            }
        });


        JButton buttonSend = new JButton("Отправить");
        buttonSend.setBounds(280,335,100,20);
        add(buttonSend);
        buttonSend.addActionListener(e -> {
            String text = textFieldForMessage.getText();
            if (textFieldForMessage.getText().isEmpty() ||
                    textFieldForMessage.getText().equals("Поле ввода сообщения")) {

                textFieldForMessage.requestFocus();
            } else {
                textArea.append(text + "\n");
                textFieldForMessage.setText("");
                textFieldForMessage.requestFocus();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new DialogBox();
    }

}
