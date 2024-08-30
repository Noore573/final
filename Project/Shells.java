public class Shells {
    boolean sh[] = new boolean[6];
    int closed = 0;
    int opened = 0;
    shellsResult result;

    public Shells(boolean[] sh) {
        this.sh = sh;
        ClosedOpened();
        result = determineMovesAndState(closed);
    }
    public void ClosedOpened() {
        for (int i = 0; i < sh.length; i++) {
            if (sh[i] == false)
                closed++;
            if (sh[i] == true)
                opened++;

        }
    }
    static class shellsResult {
        int moveCount;
        String stateName;
        boolean khal;

        shellsResult(int moveCount, String stateName, boolean Khal) {
            this.moveCount = moveCount;
            this.stateName = stateName;
            this.khal = Khal;
        }
    }
    private static shellsResult determineMovesAndState(int closedShells) {
        String stateName;
        int moveCount;
        boolean Khal;
        

        switch (closedShells) {
            case 0:
                stateName = "shaka";
                moveCount = 6;
                Khal = isKhal(closedShells);

                break;
            case 1:
                stateName = "dst";
                moveCount = 10;
                Khal = isKhal(closedShells);

                break;
            case 2:
                stateName = "dwaq";
                moveCount = 2;
                Khal = isKhal(closedShells);

                break;
            case 3:
                stateName = "3";
                moveCount = 3;
                Khal = isKhal(closedShells);

                break;
            case 4:
                stateName = "4";
                moveCount = 4;
                Khal = isKhal(closedShells);

                break;
            case 5:
                stateName = "bng";
                moveCount = 25;
                Khal = isKhal(closedShells);

                break;
            case 6:
                stateName = "bara";
                moveCount = 12;
                Khal = isKhal(closedShells);

                break;
            default:
                stateName = "Unknown";
                moveCount = 0;
                Khal = isKhal(closedShells);

        }

        return new shellsResult(moveCount, stateName, Khal);
    }

    private static boolean isKhal(int closedShells) {
        return closedShells == 1 || closedShells == 5;
    }
}
