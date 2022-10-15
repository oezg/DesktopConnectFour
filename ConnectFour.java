package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectFour extends JFrame implements ActionListener {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    private int turn;
    private final String[] turns;
    public ConnectFour() {
        this.turn = 0;
        this.turns = new String[]{"X", "O"};
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setTitle("Connect Four");

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JButton button = new JButton();
                char column = (char) ('A' + j);
                String name = column + String.valueOf(ROWS - i);
                button.setText(" ");
                button.setName("Button" + name);
                button.setFocusPainted(false);
                button.addActionListener(this);
                add(button);
            }
        }
        setLayout(new GridLayout(6, 7));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String label = this.turns[this.turn % this.turns.length];
        ((JButton) actionEvent.getSource()).setText(label);
        this.turn++;
    }
}