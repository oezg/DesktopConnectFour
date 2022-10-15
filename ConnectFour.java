package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectFour extends JFrame implements ActionListener {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    private int turn;
    private JButton[][] board;
    private final String[] turns;
    public ConnectFour() {
        this.turn = 0;
        this.turns = new String[]{"X", "O"};
        this.board = new JButton[ROWS][COLS];
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setTitle("Connect Four");

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JButton button = new JButton();
                String name = (char) ('A' + j) + String.valueOf(ROWS - i);
                button.setText(" ");
                button.setName("Button" + name);
                button.setFocusPainted(false);
                button.addActionListener(this);
                add(button);
                this.board[ROWS - 1 - i][j] = button;
            }
        }

        setLayout(new GridLayout(6, 7));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String label = this.turns[this.turn % this.turns.length];
        int column = ((JButton) actionEvent.getSource()).getName().charAt("Button".length()) - 'A';
        for (int i = 0; i < ROWS; i++) {
            if (this.board[i][column].getText().equals(" ")) {
                this.board[i][column].setText(label);
                break;
            }
        }
        this.turn++;
    }
}