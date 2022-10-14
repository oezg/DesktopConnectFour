package four;

import javax.swing.*;
import java.awt.*;

public class ConnectFour extends JFrame {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setTitle("Connect Four");

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JButton button = new JButton();
                char column = (char) ('A' + j);
                String name = column + String.valueOf(ROWS - i);
                button.setText(name);
                button.setName("Button" + name);
                button.setFocusPainted(false);
                add(button);
            }
        }
        setLayout(new GridLayout(6, 7));
        setVisible(true);
    }
}