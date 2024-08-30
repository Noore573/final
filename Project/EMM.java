import java.util.ArrayList;
import java.util.List;

public class EMM {
    boolean check = false;

    public Gameboard findBestMove(Gameboard currentGameboard, int depth) {
        List<Gameboard> possibleGameboards = generateLegalGameboardsForMaximizer(currentGameboard);
        double bestValue = Double.NEGATIVE_INFINITY;
        Gameboard bestGameboard = null;

        for (Gameboard gameboard : possibleGameboards) {
            double gameboardValue = expectedMinimax(gameboard, false, depth);

            if (gameboardValue > bestValue) {
                bestValue = gameboardValue;
                bestGameboard = gameboard;

            }
        }

        return bestGameboard;
    }

    private double expectedMinimax(Gameboard gameboard, boolean isMaximizingPlayer, int depth) {
        if (depth == 0 || gameboard.IsFinal()) {
            return calculateUtility(gameboard, 1);
        }

        List<Gameboard> possibleGameboards;
        if (isMaximizingPlayer) {
            possibleGameboards = generateLegalGameboardsForMaximizer(gameboard);
        } else {
            possibleGameboards = generateLegalGameboardsForMinimizer(gameboard);
        }

        double expectedValue = 0;

        for (Gameboard nextGameboard : possibleGameboards) {
            double outcomeProbability = calculateOutcomeProbability(nextGameboard);

            double gameboardValue = expectedMinimax(nextGameboard, !isMaximizingPlayer, depth - 1);

            expectedValue += outcomeProbability * gameboardValue;
        }

        return expectedValue;
    }

    private double calculateUtility(Gameboard gm, int turn) {
        // Implement your utility function
        // ...
        int pstones = gm.p.playernumofstones - gm.p.playerwinninstones;
        int cstones = gm.com.comnumofstones - gm.com.comwinninstones;
        if (turn == 0) {
            return pstones * 10 + cstones * (-10);
        } else if (turn == 1) {
            return pstones * (-10) + cstones * 10;
        } else {
            return 0;
        }
    }

    public List<Gameboard> generateLegalGameboardsForMaximizer(Gameboard gm) {
        List<Gameboard> testlist = new ArrayList<>();
        // for (int i = 0; i < gm.com.comstones.length; i++) {
        //     if (gm.com.comstones[i].stonepos > 0)
        //         check = true;
        // }

        if (check == false) {
            testlist=gm.firstgetnextstate(gm);
            check=true;
            // List<Gameboard> test;
            // test = gm.firstgetnextstate(gm);
            return testlist;
        } else {
            testlist = gm.getNextStates2(0);
            return testlist;
        }
    }

    public List<Gameboard> generateLegalGameboardsForMinimizer(Gameboard gm) {
        List<Gameboard> testlist = new ArrayList<>();
        testlist = gm.getNextStates(0);
        return testlist;
    }

    private float calculateOutcomeProbability(Gameboard gameboard) {
        return gameboard.probability;
        // return 0.25;
    }
}
