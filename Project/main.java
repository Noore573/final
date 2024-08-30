import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class main {
    public static void main(String[] args) throws InterruptedException {
        // Gameboard gameboard = new Gameboard(12, 8);
        GamePlay gm = new GamePlay();
        System.out.println("-------------------------------------");
        Thread.sleep(500);
        System.out.println("-------------------------------------");
        Thread.sleep(500);
        System.out.println("----------------BERJIS---------------");
        Thread.sleep(500); 
        System.out.println("-------------------------------------");
        Thread.sleep(500);
        System.out.println("-------------------------------------");
        gm.FirstPlay();
        gm.play();
        System.out.println();
        // gm.gns();
        // gm.gm.printarray(gm.gm.board);
        // gm.gm.gns();
        // System.out.println(gm.gm.com.comnumofmovements+" <<<<<<");
        

    }
}
