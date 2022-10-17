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
        Cell cell;
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLS; j++) {
                this.board[i][j].reset();
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
        int row = 0;
        for (; row < ROWS; row++) {
            if (this.board[row][column].isEmpty()) {
                selectedCell = this.board[row][column];
                selectedCell.setContent(content);
                break;
            }
        }

        if (row < ROWS && isGameOver(selectedCell)) {
            setGameOver(true);
        }
    }

    private boolean isGameOver(Cell cell) {
        int i = cell.getRow();
        int j = cell.getColumn();
        Content content = cell.getContent();
        List<Cell> cells = null;
        cells = isVerticalConnectedFour(i, j, content);
        if (cells != null) {
            cells.add(cell);
            cells.forEach(connectedCell -> connectedCell.highlight());
            return true;
        }
        cells = isHorizontalConnectedFour(i, j, content);
        if (cells != null) {
            cells.add(cell);
            cells.forEach(connectedCell -> connectedCell.highlight());
            return true;
        }
        cells = isDescendingDiagonalConnectedFour(i, j, content);
        if (cells != null) {
            cells.add(cell);
            cells.forEach(connectedCell -> connectedCell.highlight());
            return true;
        }
        cells = isAscendingDiagonalConnectedFour(i, j, content);
        if (cells != null) {
            cells.add(cell);
            cells.forEach(connectedCell -> connectedCell.highlight());
            return true;
        }
        return false;
    }

    private List<Cell> isVerticalConnectedFour(int i, int j, Content content) {
        if (i < 3) {
            return null;
        }
        for (int k = 1; k < 4; k++) {
            Content other = this.board[i - k][j].getContent();
            if (content != other) {
                return null;
            }
        }
        List<Cell> cells = new ArrayList<>();
        for (int k = 0; k < 4; k++) {
            cells.add(this.board[i - k][j]);
        }
        return cells;
    }

    private List<Cell> isHorizontalConnectedFour(int i, int j, Content content) {
        List<Cell> cells = new ArrayList<>();
        for (int k = 1; k < 4 && k <= j; k++) {
            Cell left = this.board[i][j - k];
            if (left.getContent() == content) {
                cells.add(left);
            } else {
                break;
            }
        }
        for (int k = 1; k < 4 && k + j < COLS; k++) {
            Cell right = this.board[i][j + k];
            if (right.getContent() == content) {
                cells.add(right);
            } else {
                break;
            }
        }
        return cells.size() < 3 ? null : cells;
    }

    private List<Cell> isAscendingDiagonalConnectedFour(int i, int j, Content content) {
        List<Cell> cells = new ArrayList<>();
        for (int k = 1; k < 4 && k <= j && k <= i; k++) {
            Cell leftDown = this.board[i - k][j - k];
            if (leftDown.getContent() == content) {
                cells.add(leftDown);
            } else {
                break;
            }
        }
        for (int k = 1; k < 4 && k + j < COLS && k + i < ROWS; k++) {
            Cell rightUp = this.board[i + k][j + k];
            if (rightUp.getContent() == content) {
                cells.add(rightUp);
            } else {
                break;
            }
        }
        return cells.size() < 3 ? null : cells;
    }

    private List<Cell> isDescendingDiagonalConnectedFour(int i, int j, Content content) {
        List<Cell> cells = new ArrayList<>();
        for (int k = 1; k < 4 && k <= j && i + k < ROWS; k++) {
            Cell leftUp = this.board[i + k][j - k];
            if (leftUp.getContent() == content) {
                cells.add(leftUp);
            } else {
                break;
            }
        }
        for (int k = 1; k < 4 && k + j < COLS && k <= i; k++) {
            Cell rightDown = this.board[i - k][j + k];
            if (rightDown.getContent() == content) {
                cells.add(rightDown);
            } else {
                break;
            }
        }
        return cells.size() < 3 ? null : cells;
    }
}