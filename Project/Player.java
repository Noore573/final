import java.util.*;

public class Player implements Cloneable {
    private String name;
    int playernumofstones = 0;
    int playernumofmovements = 0;
    boolean playerkhal = false;
    int numofplayerthrows = 0;
    int[] positions = new int[] { 0, 219, 238, 257, 276, 295, 314, 333, 352, 353, 334, 315, 296, 277, 258, 239,
            220, 202, 203, 204, 205, 206, 207, 208, 209, 190, 171, 170, 169, 168, 167, 166, 165, 164, 144, 125, 106, 87,
            68, 49, 30, 11, 10, 9, 28, 47, 66, 85, 104, 123, 142, 160, 159, 158, 157, 156, 155, 154, 153, 172, 191, 192,
            193, 194, 195, 196, 197, 198, 218, 237, 256, 275, 294, 313, 332, 351, 352, 333, 314, 295, 276, 257, 238,
            219, 359, 333 };

    Stones playerstones1 = new Stones(0, 0, 1);
    Stones playerstones2 = new Stones(0, 0, 2);
    Stones playerstones3 = new Stones(0, 0, 3);
    Stones playerstones4 = new Stones(0, 0, 4);
    // Stones playerstones4 = new Stones(47, 0);
    Stones playerstones[] = new Stones[] { playerstones1, playerstones2, playerstones3, playerstones4 };
    int playerwinninstones = 0;
        String firstthrowstatename="";
    public Player() {
    }

    public Player(String name, int playernumofstones, int playernumofmovements) {
        this.name = name;
        this.playernumofmovements = playernumofmovements;
        this.playernumofstones = playernumofstones;

    }

    public String getName() {
        return name;
    }

    public int getstoneindex(int index) {
        return playerstones[index].stonepos;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        Player clonedPlayer = (Player) super.clone();

        // Create new instances for mutable fields
        clonedPlayer.positions = Arrays.copyOf(this.positions, this.positions.length);

        // Deep copy Stones
        clonedPlayer.playerstones1 = (Stones) this.playerstones1.clone();
        clonedPlayer.playerstones2 = (Stones) this.playerstones2.clone();
        clonedPlayer.playerstones3 = (Stones) this.playerstones3.clone();
        clonedPlayer.playerstones4 = (Stones) this.playerstones4.clone();
        clonedPlayer.playerstones = new Stones[] {
                clonedPlayer.playerstones1, clonedPlayer.playerstones2,
                clonedPlayer.playerstones3, clonedPlayer.playerstones4
        };

        return clonedPlayer;
    }

}
