package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ConnectFour extends JFrame implements ActionListener {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    private int turn;
    private Cell[][] board;
    private final Content[] contents;
    private boolean gameOver;


    public ConnectFour() {
        this.gameOver = false;
        this.turn = 0;
        this.contents = Content.values();
        this.board = new Cell[ROWS][COLS];
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setTitle("Connect Four");
        setLayout(new BorderLayout());

        JPanel boardPane = new JPanel();
        boardPane.setLayout(new GridLayout(6, 7));
        add(boardPane, BorderLayout.CENTER);

        JPanel resetPane = new JPanel();
        resetPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton resetButton = new JButton("Reset");
        resetButton.setName("ButtonReset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                resetBoard();
                setGameOver(false);
            }
        });
        resetPane.add(resetButton);
        add(resetPane, BorderLayout.SOUTH);

        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLS; j++) {
                Cell cell = new Cell(i, j);
                cell.addActionListener(this);
                boardPane.add(cell);
                this.board[i][j] = cell;
            }
        }

        setVisible(true);
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        this.turn = 0;
    }
    private void resetBoard() {
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLS; j++) {
                this.board[i][j].setContent(Content.EMPTY);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (this.gameOver) {
            return;
        }
        Cell selectedCell = (Cell) actionEvent.getSource();

        if (!selectedCell.isEmpty()) {
            return;
        }
        Content content = this.contents[this.turn++ % 2];
        int column = selectedCell.getColumn();
        for (int row = 0; row < ROWS; row++) {
            if (this.board[row][column].isEmpty()) {
                this.board[row][column].setContent(content);
                break;
            }
        }

        if (isGameOver(selectedCell)) {
            changeColorWinningCells(selectedCell);
            setGameOver(true);
        }
    }

    private boolean isGameOver(Cell cell) {
        boolean vertical = isVerticalConnectedFour(cell);
        boolean horizontal = isHorizontalConnectedFour(cell);
        boolean diagonal = isDiagonalConnectedFour(cell);
        return vertical || horizontal || diagonal;
    }

    private boolean isVerticalConnectedFour(Cell cell) {
//        TODO
        return false;
    }

    private boolean isHorizontalConnectedFour(Cell cell) {
//        TODO
        return false;
    }

    private boolean isDiagonalConnectedFour(Cell cell) {
//        TODO
        return false;
    }

    private List<Cell> connectedCells(Cell cell) {
        List<Cell> cells = new ArrayList<>();
        cells.add(cell);
        if (isVerticalConnectedFour(cell)) {
//            TODO
        } else if (isHorizontalConnectedFour(cell)) {
//            TODO
        } else {
//            TODO
        }
        return cells;
    }

    private void changeColorWinningCells(Cell cell) {
        connectedCells(cell).forEach(winningCell -> winningCell.setBackground(Color.cyan));
    }
}