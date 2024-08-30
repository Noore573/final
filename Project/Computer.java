import java.util.*;

public class Computer implements Cloneable {
    private String name;
    int comnumofstones = 0;
    int comnumofmovements = 0;
    boolean comkhal = false;
    int numofcomthrows = 0;
    int[] positions = new int[] { 0, 143, 124, 105, 86, 67, 48, 29, 10, 9, 28, 47, 66, 85, 104, 123, 142, 160, 159, 158,
            157, 156, 155, 154, 153, 172, 191, 192, 193, 194, 195, 196, 196, 197, 198, 218, 237, 256, 275, 294, 313,
            332, 351, 352, 353, 334, 315, 296, 277, 258, 239, 220, 202, 203, 204, 205, 206, 207, 208, 209, 190, 171,
            170, 169, 168, 167, 166, 165, 164, 144, 125, 106, 87, 68, 49, 30, 11, 10, 29, 48, 67, 86, 105, 124, 143,
            360, 361, 333 };

    Stones comstones1 = new Stones(0, 0, 1);
    Stones comstones2 = new Stones(0, 0, 2);
    Stones comstones3 = new Stones(0, 0, 3);
    Stones comstones4 = new Stones(0, 0, 4);
    // Stones comstones4 = new Stones(315, 12);
    Stones comstones[] = new Stones[] { comstones1, comstones2, comstones3, comstones4 };
    int comwinninstones;
    String comfirstthrowname = "";

    public Computer() {
    }

    public Computer(String name, int comnumofstones, int comnumofmovements) {
        this.name = name;
        this.comnumofmovements = comnumofmovements;
        this.comnumofstones = comnumofstones;

    }

    public String getName() {
        return name;
    }

    public int getstoneindex(int index) {
        return comstones[index].stonepos;
    }

    public void PlaceStone(int sn) {
        comstones[sn].stonepos++;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Computer clonedComputer = (Computer) super.clone();

        // Create new instances for mutable fields
        clonedComputer.positions = Arrays.copyOf(this.positions, this.positions.length);

        // Deep copy Stones
        clonedComputer.comstones1 = (Stones) this.comstones1.clone();
        clonedComputer.comstones2 = (Stones) this.comstones2.clone();
        clonedComputer.comstones3 = (Stones) this.comstones3.clone();
        clonedComputer.comstones4 = (Stones) this.comstones4.clone();
        clonedComputer.comstones = new Stones[] {
                clonedComputer.comstones1, clonedComputer.comstones2,
                clonedComputer.comstones3, clonedComputer.comstones4
        };

        return clonedComputer;
    }

}
