import java.util.List;

public class Stones implements Cloneable {
    // String stoneType;
    int stoneboardpos = 0;
    int stonepos = 0;
    int stoneid = 0;
    int stonenumofmoves = 0;
    boolean killed = false;
    boolean kitchened = false;

    public Stones(int stoneboardpos, int stonepos, int stoneid) {
        // this.stoneType = stoneType;
        this.stonepos = stonepos;
        this.stoneboardpos = stoneboardpos;
        this.stoneid = stoneid;
        this.stonenumofmoves = 0;
    }

    public Stones() {
    }

    public int getstoneboardpos() {
        return stoneboardpos;
    }

    public int getStonepos() {
        return stonepos;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // Assuming stoneboardpos, stonepos, stoneid are immutable or simple types
    }

}