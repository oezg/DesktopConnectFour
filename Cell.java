package four;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {

    public static final Color BACKGROUND = Color.LIGHT_GRAY;
    public static final Color HIGHLIGHT = Color.CYAN;
    private int row;
    private int column;
    private Content content;
    Cell(int i, int j) {
        super();
        this.row = i;
        this.column = j;
        this.content = Content.EMPTY;
        setName("Button" + (char) ('A' + j) + (i + 1));
        setText();
        setFocusPainted(false);
        setBackground();
    }

    private void setText() {
        setText(this.content.getText());
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    Content getContent() {
        return this.content;
    }

    void setContent(Content content) {
        this.content = content;
        setText();
    }

    boolean isEmpty() {
        return this.content.equals(Content.EMPTY);
    }

    void highlight() {
        setBackground(HIGHLIGHT);
    }

    void setBackground() {
        setBackground(BACKGROUND);
    }
    void reset() {
        setContent(Content.EMPTY);
        setBackground();
    }
}