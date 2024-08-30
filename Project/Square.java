public class Square {
    public int pos = 0;
    public String value = "   ";

    public Square(int pos, String value) {
        this.pos = pos;
        this.value = value;
    }

    public Square() {
    }
    public Square(Square other) {
        this.pos = other.pos;
        this.value = other.value;
    }

}
