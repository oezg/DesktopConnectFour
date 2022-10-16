package four;

public enum Content {
    X("X"), O("O"), EMPTY(" ");
    private String text;
    Content(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}