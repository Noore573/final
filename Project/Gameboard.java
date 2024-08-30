import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gameboard {
    // private Square[] squares;
    Square[] squares = new Square[361];
    public Square[][] board = new Square[19][19];
    Player p;
    Computer com;
    float probability;
    // int[][] positionsplayer = new int[][] {
    // { 0, 219, 238, 257, 276, 295, 314, 333, 352, 353, 334 },
    // };

    public Gameboard(String playername) {

        initsquares();
        addtoboard();
        p = new Player(playername, 0, 0);
        com = new Computer("R2D2", 0, 0);
    }

    public Gameboard() {
    }

    public void initsquares() {
        for (int i = 0; i < 361; i++) {
            // Square s = new Square(i + 1, (i + 1));
            Square s = new Square(i + 1, "   ");

            if (s.pos == 161 || s.pos == 162 || s.pos == 163 ||
                    s.pos == 180 || s.pos == 181 || s.pos == 182 ||
                    s.pos == 199 || s.pos == 200 || s.pos == 201) {
                s.value = " * ";
            } else if (s.pos == 47 || s.pos == 49 |
                    s.pos == 155 || s.pos == 193 ||
                    s.pos == 169 || s.pos == 207 ||
                    s.pos == 313 || s.pos == 315) {
                s.value = " X ";
            } else if (s.pos == 171 || s.pos == 191) {
                s.value = " / ";
            } else if (s.pos == 3) {
                s.value = "noore";
            }

            // else if (s.pos == 47) {
            // s.value = "1 ";
            // } else if (s.pos == 315) {

            // s.value = " O ";
            // }
            squares[i] = s;
        }

    }

    public void addtoboard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = squares[(i * 19) + j];
            }
        }
    }

    public void printarray(Square[][] array) {
        System.out.println(
                "_______________________________________________________________________________________________");
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (i < 8 && j < 8) {
                    System.out.print("       ");
                } else if (i > 10 && j < 8) {
                    System.out.print("       ");
                } else if (j > 10 && i < 8) {
                    System.out.print("       ");
                } else if (j > 10 && i > 10) {
                    System.out.print("       ");
                } else {
                    System.out.print("| " + array[i][j].value + " |");
                }
            }
            System.out.println();
        }
        System.out.println(
                "_______________________________________________________________________________________________");
    }

    public void makemove2(int num, int sn, int mn) {
        int temp = 0;
        int oldidex = p.getstoneindex(sn);
        boolean done = false;
        while (mn > 0) {
            int s1index = p.getstoneindex(sn);
            s1index += num;
            p.playerstones[sn].stonepos = s1index;
            mn--;
            if (IsFinalMove('p', sn) && mn > 0) {
                // System.out.println("Its in the final mode entering the kitchen...");
                EnterKitchen('p', sn, oldidex);
                mn = 0;
                done = true;
            }
            temp = s1index;
        }
        if (done) {
            // Updategameboard(oldidex, sn, 1);
        } else {
            if (validmove(p.positions[temp] - 1, 'p') == false) {
                p.playerstones[sn].stonepos = oldidex;
            } else {
                p.playerstones[sn].stoneboardpos = squares[p.positions[temp] - 1].pos;
                if (squares[p.positions[temp] - 1].value.charAt(0) == 'O'
                        || squares[p.positions[temp] - 1].value.charAt(1) == 'O') {
                    // System.out.println("Kill!!" + p.positions[temp]);
                    Kill('p', p.positions[temp]);
                }
                Updategameboard(oldidex, sn, temp);
            }
        }
    }

    public void commakemove2(int num, int sn, int mn) {
        int temp = 0;
        int oldidex = com.getstoneindex(sn);
        boolean done = false;
        while (mn > 0) {
            int s1index = com.getstoneindex(sn);
            s1index += num;
            com.comstones[sn].stonepos = s1index;
            mn--;
            if (IsFinalMove('c', sn) && mn > 0) {
                // System.out.println("Its in the final mode entering the kitchen...");
                EnterKitchen('c', sn, oldidex);

                mn = 0;
                done = true;
            }

            temp = s1index;

        }
        if (done) {
            // System.out.println("Done!");
            // Updategameboard(oldidex, sn, 1);

        } else {
            if (validmove(com.positions[temp] - 1, 'c') == false) {
                com.comstones[sn].stonepos = oldidex;
            } else {
                com.comstones[sn].stoneboardpos = squares[com.positions[temp] - 1].pos;
                if (squares[com.positions[temp] - 1].value.charAt(0) == '1'
                        || squares[com.positions[temp] - 1].value.charAt(1) == '1') {
                    // System.out.println("Kill !!");
                    Kill('c', com.positions[temp]);

                }
                ComUpdategameboard(oldidex, sn, temp);
            }
        }
    }

    public boolean validmove(int pos, char turn) {
        if (turn == 'p') {
            if ((squares[pos].value.charAt(0) == 'X' || squares[pos].value.charAt(0) == '/')
                    && squares[pos].value.charAt(2) == 'O') {
                // System.out.println("Not valid!!");
                return false;
            }

            else {
                // System.out.println("valid ;)");
                return true;
            }
        } else if (turn == 'c') {
            if ((squares[pos].value.charAt(0) == 'X' || squares[pos].value.charAt(0) == '/')
                    && squares[pos].value.charAt(2) == '1') {
                // System.out.println("Not valid!!");
                return false;
            }

            else {
                // System.out.println("valid ;)");
                return true;
            }
        }
        return true;
    }

    public void Kill(char turn, int pos) {
        if (turn == 'p') {
            // com.comnumofstones--;
            for (int i = 0; i < 4; i++) {
                if (com.comstones[i].stoneboardpos == pos) {
                    com.comstones[i].stoneboardpos = 0;
                    com.comstones[i].stonepos = 0;
                    com.comstones[i].killed = true;
                    // try
                    // com.comstones[i].stonepos = com.positions.length-1;
                    squares[pos - 1].value = "   ";
                    // System.out.println("found a computer!");
                }
            }

        } else if (turn == 'c') {
            // p.playernumofstones--;
            for (int i = 0; i < 4; i++) {
                if (p.playerstones[i].stoneboardpos == pos) {
                    p.playerstones[i].stoneboardpos = 0;
                    p.playerstones[i].stonepos = 0;
                    p.playerstones[i].killed = true;
                    // try
                    // p.playerstones[i].stonepos = p.positions.length-1;
                    squares[pos - 1].value = "   ";
                    // System.out.println("found a player");
                }
            }
        }

    }

    public void Updategameboard(int oldidex, int sn, int temp) {
        if (p.getstoneindex(sn) >= 1) {
            if (squares[p.positions[temp] - 1].value != "   ") {
                if (squares[p.positions[temp] - 1].value.charAt(2) == '1') {
                    squares[p.positions[temp] - 1].value += "1";
                } else {
                    squares[p.positions[temp] - 1].value = squares[p.positions[temp] - 1].value.charAt(1)
                            + "-1";
                }
            } else {

                squares[p.positions[temp] - 1].value = " 1 ";

            }

        }
        // System.out.println("Old index: " + oldidex);
        if (oldidex >= 1) {
            if (squares[p.positions[oldidex] - 1].value.charAt(0) == 'X') {
                squares[p.positions[oldidex] - 1].value = " X ";
            } else if (squares[p.positions[oldidex] - 1].value.charAt(0) == '/') {
                squares[p.positions[oldidex] - 1].value = " / ";
            } else if (squares[p.positions[oldidex] - 1].value.charAt(2) == '1') {
                squares[p.positions[oldidex] - 1].value = squares[p.positions[oldidex] - 1].value.substring(0,
                        squares[p.positions[oldidex] - 1].value.length() - 1);
                if (squares[p.positions[oldidex] - 1].value
                        .charAt(squares[p.positions[oldidex] - 1].value.length() - 1) == '-') {
                    squares[p.positions[oldidex] - 1].value = squares[p.positions[oldidex] - 1].value
                            .substring(0, squares[p.positions[oldidex] - 1].value.length() - 1);
                    squares[p.positions[oldidex] - 1].value = " " + squares[p.positions[oldidex] - 1].value;
                    squares[p.positions[oldidex] - 1].value += " ";
                }
            } else {
                squares[p.positions[oldidex] - 1].value = "   ";
            }
        }
    }

    public void ComUpdategameboard(int oldidex, int sn, int temp) {
        // System.out.println("Before ComUpdategameboard: oldidex=" + oldidex + ", sn="
        // + sn + ", temp=" + temp);

        if (com.getstoneindex(sn) >= 1) {

            if (squares[com.positions[temp] - 1].value != "   ") {
                if (squares[com.positions[temp] - 1].value.charAt(2) == 'O') {
                    squares[com.positions[temp] - 1].value += "O";
                }

                else {
                    squares[com.positions[temp] - 1].value = squares[com.positions[temp] - 1].value.charAt(1)
                            + "-O";
                }
            } else {

                squares[com.positions[temp] - 1].value = " O ";
            }

        }
        // System.out.println("Old index: " + oldidex);
        if (oldidex >= 1) {
            // System.out.println("Testing..");
            // System.out.println(squares[com.positions[oldidex] - 1].value + " <");
            if (squares[com.positions[oldidex] - 1].value.charAt(0) == 'X') {
                // System.out.println("test1");
                squares[com.positions[oldidex] - 1].value = " X ";
            } else if (squares[com.positions[oldidex] - 1].value.charAt(0) == '/') {
                // System.out.println("test2");
                squares[com.positions[oldidex] - 1].value = " / ";
            } else if (squares[com.positions[oldidex] - 1].value.charAt(2) == 'O') {
                squares[com.positions[oldidex] - 1].value = squares[com.positions[oldidex] - 1].value
                        .substring(0, squares[com.positions[oldidex] - 1].value.length() - 1);
                if (squares[com.positions[oldidex] - 1].value
                        .charAt(squares[com.positions[oldidex] - 1].value.length() - 1) == '-') {
                    squares[com.positions[oldidex] - 1].value = squares[com.positions[oldidex] - 1].value
                            .substring(0, squares[com.positions[oldidex] - 1].value.length() - 1);
                    squares[com.positions[oldidex] - 1].value = " " + squares[com.positions[oldidex] - 1].value;
                    squares[com.positions[oldidex] - 1].value += " ";
                }
            } else {
                // System.out.println("test3");
                squares[com.positions[oldidex] - 1].value = "   ";
            }
        }
    }

    public Boolean IsFinalMove(char turn, int sn) {
        // check this agian
        if (turn == 'p') {
            // its 83 i think test
            if (p.playerstones[sn].stonepos == 84) {
                // System.out.println("final pos for player in gb");
                return true;
            }
            return false;
        } else {
            if (com.comstones[sn].stonepos == 84) {
                // System.out.println("final pos for computerin gb");
                return true;
            }
            return false;
        }
    }

    public void EnterKitchen(char turn, int sn, int oldidex) {
        // System.out.println("Entered the kichen!");

        if (turn == 'p') {
            // Updategameboard(oldidex, sn, 86);
            DeletedFromBoard(oldidex, sn);
            p.playerstones[sn].stoneboardpos = 0;
            p.playerstones[sn].stonepos = 0;
            p.playerstones[sn].kitchened = true;
            // p.playernumofstones--;
            p.playerwinninstones++;
        } else if (turn == 'c') {
            // ComUpdategameboard(oldidex, sn, 86);
            ComDeleteFromBoard(oldidex, sn);
            com.comstones[sn].stoneboardpos = 0;
            com.comstones[sn].stonepos = 0;
            com.comstones[sn].kitchened = true;
            // com.comnumofstones--;
            com.comwinninstones++;
        }

    }

    public void DeletedFromBoard(int oldidex, int sn) {
        // System.out.println("Deleting.......");
        squares[360].value = " 1 ";
        // System.out.println("Old index: " + oldidex);
        if (oldidex >= 1) {
            if (squares[p.positions[oldidex] - 1].value.charAt(0) == 'X') {
                squares[p.positions[oldidex] - 1].value = " X ";
            } else if (squares[p.positions[oldidex] - 1].value.charAt(0) == '/') {
                squares[p.positions[oldidex] - 1].value = " / ";
            } else if (squares[p.positions[oldidex] - 1].value.charAt(2) == '1') {
                squares[p.positions[oldidex] - 1].value = squares[p.positions[oldidex] - 1].value.substring(0,
                        squares[p.positions[oldidex] - 1].value.length() - 1);
                if (squares[p.positions[oldidex] - 1].value
                        .charAt(squares[p.positions[oldidex] - 1].value.length() - 1) == '-') {
                    squares[p.positions[oldidex] - 1].value = squares[p.positions[oldidex] - 1].value
                            .substring(0, squares[p.positions[oldidex] - 1].value.length() - 1);
                    squares[p.positions[oldidex] - 1].value = " " + squares[p.positions[oldidex] - 1].value;
                    squares[p.positions[oldidex] - 1].value += " ";
                }
            } else {
                squares[p.positions[oldidex] - 1].value = "   ";
            }
        }
    }

    public void ComDeleteFromBoard(int oldidex, int sn) {
        squares[359].value = " O ";
        // System.out.println("Old index: " + oldidex);
        if (oldidex >= 1) {
            // System.out.println(squares[com.positions[oldidex] - 1].value + " <");
            if (squares[com.positions[oldidex] - 1].value.charAt(0) == 'X') {
                squares[com.positions[oldidex] - 1].value = " X ";
            } else if (squares[com.positions[oldidex] - 1].value.charAt(0) == '/') {
                squares[com.positions[oldidex] - 1].value = " / ";
            } else if (squares[com.positions[oldidex] - 1].value.charAt(2) == 'O') {
                squares[com.positions[oldidex] - 1].value = squares[com.positions[oldidex] - 1].value
                        .substring(0, squares[com.positions[oldidex] - 1].value.length() - 1);
                if (squares[com.positions[oldidex] - 1].value
                        .charAt(squares[com.positions[oldidex] - 1].value.length() - 1) == '-') {
                    squares[com.positions[oldidex] - 1].value = squares[com.positions[oldidex] - 1].value
                            .substring(0, squares[com.positions[oldidex] - 1].value.length() - 1);
                    squares[com.positions[oldidex] - 1].value = " " + squares[com.positions[oldidex] - 1].value;
                    squares[com.positions[oldidex] - 1].value += " ";
                }
            } else {
                // System.out.println("test3");
                squares[com.positions[oldidex] - 1].value = "   ";
            }
        }
    }

    Gameboard Deepcopy() {
        Gameboard cgb = new Gameboard();

        // Deep copy player
        cgb.p = new Player();
        cgb.p.playernumofmovements = this.p.playernumofmovements;
        cgb.p.playerkhal = this.p.playerkhal;
        cgb.p.playernumofstones = this.p.playernumofstones;
        cgb.p.playerwinninstones = this.p.playerwinninstones;

        // Deep copy computer
        cgb.com = new Computer();
        cgb.com.comnumofstones = this.com.comnumofstones;
        cgb.com.comkhal = this.com.comkhal;
        cgb.com.comnumofmovements = this.com.comnumofmovements;
        cgb.com.comwinninstones = this.com.comwinninstones;

        // Deep copy comstones array
        cgb.com.comstones = new Stones[this.com.comstones.length];
        for (int i = 0; i < this.com.comstones.length; i++) {
            cgb.com.comstones[i] = new Stones(
                    this.com.comstones[i].stoneboardpos,
                    this.com.comstones[i].stonepos,
                    this.com.comstones[i].stoneid);
        }

        // Deep copy playerstones array
        cgb.p.playerstones = new Stones[this.p.playerstones.length];
        for (int i = 0; i < this.p.playerstones.length; i++) {
            cgb.p.playerstones[i] = new Stones(
                    this.p.playerstones[i].stoneboardpos,
                    this.p.playerstones[i].stonepos,
                    this.p.playerstones[i].stoneid);
        }

        // Deep copy board array

        // for (int i = 0; i < this.squares.length; i++) {
        // cgb.squares[i]=this.squares[i];
        // }
        cgb.squares = new Square[this.squares.length];
        for (int i = 0; i < this.squares.length; i++) {
            cgb.squares[i] = new Square(
                    this.squares[i].pos,
                    this.squares[i].value
            // "333"
            );
        }
        cgb.board = new Square[this.board.length][this.board[0].length];
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                // cgb.board[i][j] = new Square(
                // this.board[i][j].pos,
                // this.board[i][j].value
                // );
                cgb.board[i][j] = cgb.squares[(i * 19) + j];
            }
        }

     
        return cgb;
    }

    

    public List<Gameboard> getNextStates(int sn) {
        List<Gameboard> nextStates = new ArrayList<>();
        int shakacount = 1;
        int baracount = 1;
        int dstcount = 1;
        int bngcount = 1;

        // shaka
        for (int i = 0; i < 13; i++) {
            Gameboard nextState = Deepcopy();
            nextState.p.playernumofmovements += (6 * shakacount);
            String last = "2";
            if (shakacount <= 5) {
                if (i == 0 || i == 3 || i == 6 || i == 9 || i == 12) {
                    nextState.p.playernumofmovements += 2;
                    last = "2";
                } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13) {
                    nextState.p.playernumofmovements += 3;
                    last = "3";
                } else if (i == 2 || i == 5 || i == 8 || i == 11 || i == 14) {
                    nextState.p.playernumofmovements += 4;
                    last = "4";
                }

                if (i == 2 || i == 5 || i == 8 || i == 11) {
                    shakacount++;
                }
            }

            nextState.probability = (float) Math.pow(0.015625, shakacount);
            if (last.equals("2")) {
                nextState.probability *= (0.234375);
            } else if (last.equals("3")) {
                nextState.probability *= (0.3125);
            } else if (last.equals("4")) {
                nextState.probability *= (0.234375);
            }
            nextState.makemove2(1, sn, nextState.p.playernumofmovements);
            nextStates.add(nextState);
        }

        // // bara
        for (int i = 0; i < 13; i++) {
            Gameboard nextState = Deepcopy();
            nextState.p.playernumofmovements += (6 * baracount);
            String last = "2";
            if (baracount < 5) {
                if (i == 0 || i == 3 || i == 6 || i == 9 || i == 12) {
                    nextState.p.playernumofmovements += 2;
                    last = "2";
                } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13) {
                    nextState.p.playernumofmovements += 3;
                    last = "3";
                } else if (i == 2 || i == 5 || i == 8 || i == 11 || i == 14) {
                    nextState.p.playernumofmovements += 4;
                    last = "4";
                }
                if (i == 2 || i == 5 || i == 8 || i == 11) {
                    baracount++;
                }
            }

            nextState.probability = (float) Math.pow(0.015625, baracount);
            if (last.equals("2")) {
                nextState.probability *= (0.234375);
            } else if (last.equals("3")) {
                nextState.probability *= (0.3125);
            } else if (last.equals("4")) {
                nextState.probability *= (0.234375);
            }
            nextState.makemove2(1, sn, nextState.p.playernumofmovements);
            nextStates.add(nextState);
        }

        // dst extra move
        for (int i = 0; i < 13; i++) {
            Gameboard nextState = Deepcopy();
            nextState.p.playernumofmovements += (6 * dstcount);
            String last = "2";
            if (dstcount < 5) {
                if (i == 0 || i == 3 || i == 6 || i == 9 || i == 12) {
                    nextState.p.playernumofmovements += 2;
                    nextState.p.playernumofmovements += 1;
                    last = "2";
                } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13) {
                    nextState.p.playernumofmovements += 3;
                    nextState.p.playernumofmovements += 1;
                    last = "3";
                } else if (i == 2 || i == 5 || i == 8 || i == 11 || i == 14) {
                    nextState.p.playernumofmovements += 4;
                    nextState.p.playernumofmovements += 1;
                    last = "4";
                }
                if (i == 2 || i == 5 || i == 8 || i == 11) {
                    dstcount++;
                }
            }

            nextState.probability = (float) Math.pow(6.0 / 64.0, dstcount);
            if (last.equals("2")) {
                nextState.probability *= (0.234375);
            } else if (last.equals("3")) {
                nextState.probability *= (0.3125);
            } else if (last.equals("4")) {
                nextState.probability *= (0.234375);
            }
            nextState.makemove2(1, sn, nextState.p.playernumofmovements);
            nextStates.add(nextState);
            // System.out.println("dst test " + i + " :" +
            // nextState.p.playernumofmovements);
        }

        // // dst add stone
        for (int i = 0; i < 13; i++) {
            Gameboard nextState = Deepcopy();
            nextState.p.playernumofmovements += (6 * dstcount);
            khalmoveForNextState('p', nextState, "dst", i);

            String last = "2";
            if (dstcount < 5) {
                if (i == 0 || i == 3 || i == 6 || i == 9 || i == 12) {
                    nextState.p.playernumofmovements += 2;
                } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13) {
                    nextState.p.playernumofmovements += 3;
                } else if (i == 2 || i == 5 || i == 8 || i == 11 || i == 14) {
                    nextState.p.playernumofmovements += 4;
                }
                if (i == 2 || i == 5 || i == 8 || i == 11) {
                    dstcount++;
                }
                last = "4";
            }

            nextState.probability = (float) Math.pow(6.0 / 64.0, dstcount);
            if (last.equals("2")) {
                nextState.probability *= (0.234375);
            } else if (last.equals("3")) {
                nextState.probability *= (0.3125);
            } else if (last.equals("4")) {
                nextState.probability *= (0.234375);
            }

            nextStates.add(nextState);
            // printarray(board);
        }

        // bng extra move
        for (int i = 0; i < 13; i++) {
            Gameboard nextState = Deepcopy();
            nextState.p.playernumofmovements += (6 * bngcount);
            String last = "2";
            if (dstcount < 5) {
                if (i == 0 || i == 3 || i == 6 || i == 9 || i == 12) {
                    nextState.p.playernumofmovements += 2;
                    nextState.p.playernumofmovements += 1;
                    last = "2";
                } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13) {
                    nextState.p.playernumofmovements += 3;
                    nextState.p.playernumofmovements += 1;
                    last = "3";
                } else if (i == 2 || i == 5 || i == 8 || i == 11 || i == 14) {
                    nextState.p.playernumofmovements += 4;
                    nextState.p.playernumofmovements += 1;
                    last = "4";
                }
                if (i == 2 || i == 5 || i == 8 || i == 11) {
                    dstcount++;
                }
            }

            nextState.probability = (float) Math.pow(6.0 / 64.0, dstcount);
            if (last.equals("2")) {
                nextState.probability *= (0.234375);
            } else if (last.equals("3")) {
                nextState.probability *= (0.3125);
            } else if (last.equals("4")) {
                nextState.probability *= (0.234375);
            }
            nextState.makemove2(1, sn, nextState.p.playernumofmovements);
            nextStates.add(nextState);
        }

        // bng add stone
        for (int i = 0; i < 13; i++) {
            Gameboard nextState = Deepcopy();
            nextState.p.playernumofmovements += (6 * bngcount);
            String last = "2";
            khalmoveForNextState('p', nextState, "bng", i);
            if (dstcount < 5) {
                if (i == 0 || i == 3 || i == 6 || i == 9 || i == 12) {
                    nextState.p.playernumofmovements += 2;
                    last = "2";
                } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13) {
                    nextState.p.playernumofmovements += 3;
                    last = "3";
                } else if (i == 2 || i == 5 || i == 8 || i == 11 || i == 14) {
                    nextState.p.playernumofmovements += 4;
                    last = "4";
                }
                if (i == 2 || i == 5 || i == 8 || i == 11) {
                    bngcount++;
                }
            }

            nextState.probability = (float) Math.pow(6.0 / 64.0, dstcount);
            if (last.equals("2")) {
                nextState.probability *= (0.234375);
            } else if (last.equals("3")) {
                nextState.probability *= (0.3125);
            } else if (last.equals("4")) {
                nextState.probability *= (0.234375);
            }
            nextState.makemove2(1, sn, nextState.p.playernumofmovements);
            nextStates.add(nextState);
            // printarray(board);
        }

        return nextStates;
    }

    // public void gns() {
    // List<Gameboard> testlist = new ArrayList<>();
    // testlist = getNextStates();
    // }

    public void khalmoveForNextState(char turn, Gameboard gb, String type, int x) {
        if (turn == 'p') {
            boolean done = false;
            // if there is a dead stone it takes the priority
            for (int i = 0; i < 4; i++) {
                if (gb.p.playerstones[i].killed) {
                    if (type == "dst") {

                        makemove2(1, i, 11);
                    } else {
                        makemove2(1, i, 26);
                    }
                    done = true;
                }
            }
            if (done) {
                return;
            } else {
                gb.p.playernumofstones++;
                if (type == "dst") {
                    gb.makemove2(1, gb.p.playernumofstones - 1, 11);
                } else {
                    gb.makemove2(1, gb.p.playernumofstones - 1, 26);
                }
            }
        }
        if (turn == 'c') {
            boolean done = false;
            // if there is a dead stone it takes the priority
            for (int i = 0; i < 4; i++) {
                if (gb.com.comstones[i].killed) {
                    if (type == "dst") {
                        commakemove2(1, i, 11);
                    } else {

                        commakemove2(1, i, 26);
                    }

                    done = true;
                }
            }

            if (done) {
                return;
            } else {
                gb.com.comnumofstones++;

                // System.out.println(gb.com.comnumofstones);
                // System.out.println(gb.com.comstones[1].stoneboardpos + " < pos");
                if (type == "dst") {
                    gb.commakemove2(1, gb.com.comnumofstones - 1, 11);
                } else {
                    gb.commakemove2(1, gb.com.comnumofstones - 1, 26);
                }
                // System.out.println(gb.com.comstones[1].stoneboardpos + " < pos");
            }
        }
    }

    public Boolean IsFinal() {
        if (p.playerwinninstones == 4) {
            System.out.println("Player " + p.getName() + " have won!!!");
            return true;
        } else if (com.comwinninstones == 4) {
            System.out.println("Computer " + com.getName() + " have won!!!");
            return true;
        }
        return false;
    }

    public List<Gameboard> getNextStates2(int sn) {
        List<Gameboard> nextStates = new ArrayList<>();
        int shakacount = 1;
        int baracount = 1;
        int dstcount = 1;
        int bngcount = 1;

        // shaka
        for (int i = 0; i < 13; i++) {
            Gameboard nextState = Deepcopy();
            nextState.com.comnumofmovements += (6 * shakacount);
            String last = "2";
            if (shakacount <= 5) {
                if (i == 0 || i == 3 || i == 6 || i == 9 || i == 12) {
                    nextState.com.comnumofmovements += 2;
                    last = "2";
                } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13) {
                    nextState.com.comnumofmovements += 3;
                    last = "3";
                } else if (i == 2 || i == 5 || i == 8 || i == 11 || i == 14) {
                    nextState.com.comnumofmovements += 4;
                    last = "4";
                }

                if (i == 2 || i == 5 || i == 8 || i == 11) {

                    shakacount++;
                }
            }
            if (last == "2") {
                nextState.probability = (float) Math.pow(0.015625, shakacount);
                nextState.probability *= (0.234375);

            }
            if (last == "3") {
                nextState.probability = (float) Math.pow(0.015625, shakacount);
                nextState.probability *= (0.3125);

            }
            if (last == "3") {
                nextState.probability = (float) Math.pow(0.015625, shakacount);
                nextState.probability *= (0.234375);

            }
            // nextState.probability=1;
            nextState.commakemove2(1, sn, nextState.com.comnumofmovements);
            nextStates.add(nextState);
        }

        // bara
        for (int i = 0; i < 13; i++) {
            Gameboard nextState = Deepcopy();
            nextState.com.comnumofmovements += (6 * baracount);
            String last = "2";
            if (baracount < 5) {
                if (i == 0 || i == 3 || i == 6 || i == 9 || i == 12) {
                    nextState.com.comnumofmovements += 2;
                    last = "2";
                } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13) {
                    nextState.com.comnumofmovements += 3;
                    last = "3";
                } else if (i == 2 || i == 5 || i == 8 || i == 11 || i == 14) {
                    nextState.com.comnumofmovements += 4;
                    last = "4";
                }
                if (i == 2 || i == 5 || i == 8 || i == 11) {
                    baracount++;
                }
            }
            if (last == "2") {
                nextState.probability = (float) Math.pow(0.015625, baracount);
                nextState.probability *= (0.234375);

            }
            if (last == "3") {
                nextState.probability = (float) Math.pow(0.015625, baracount);
                nextState.probability *= (0.3125);

            }
            if (last == "3") {
                nextState.probability = (float) Math.pow(0.015625, baracount);
                nextState.probability *= (0.234375);

            }
            nextState.commakemove2(1, sn, nextState.com.comnumofmovements);
            nextStates.add(nextState);
        }

        // dst extra move
        for (int i = 0; i < 13; i++) {
            Gameboard nextState = Deepcopy();
            nextState.com.comnumofmovements += (6 * dstcount);
            String last = "2";
            if (dstcount < 5) {
                if (i == 0 || i == 3 || i == 6 || i == 9 || i == 12) {
                    nextState.com.comnumofmovements += 2;
                    nextState.com.comnumofmovements += 1;
                    last = "2";
                } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13) {
                    nextState.com.comnumofmovements += 3;
                    nextState.com.comnumofmovements += 1;
                    last = "3";
                } else if (i == 2 || i == 5 || i == 8 || i == 11 || i == 14) {
                    nextState.com.comnumofmovements += 4;
                    nextState.com.comnumofmovements += 1;
                    last = "4";
                }
                if (i == 2 || i == 5 || i == 8 || i == 11) {
                    dstcount++;
                }
                if (last == "2") {
                    nextState.probability = (float) Math.pow(6.0 / 64.0, dstcount);
                    nextState.probability *= (0.234375);

                }
                if (last == "3") {
                    nextState.probability = (float) Math.pow(6.0 / 64.0, dstcount);
                    nextState.probability *= (0.3125);

                }
                if (last == "3") {
                    nextState.probability = (float) Math.pow(6.0 / 64.0, dstcount);
                    nextState.probability *= (0.234375);

                }
            }
            nextState.commakemove2(1, sn, nextState.com.comnumofmovements);
            nextStates.add(nextState);
        }

        // dst add stone
        for (int i = 0; i < 13; i++) {
            Gameboard nextState = Deepcopy();
            nextState.com.comnumofmovements += (6 * dstcount);
            khalmoveForNextState('c', nextState, "dst", i);
            String last = "2";
            if (dstcount < 5) {
                if (i == 0 || i == 3 || i == 6 || i == 9 || i == 12) {
                    nextState.com.comnumofmovements += 2;
                } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13) {
                    nextState.com.comnumofmovements += 3;
                } else if (i == 2 || i == 5 || i == 8 || i == 11 || i == 14) {
                    nextState.com.comnumofmovements += 4;
                }
                if (i == 2 || i == 5 || i == 8 || i == 11) {
                    dstcount++;
                }
            }
            if (last == "2") {
                nextState.probability = (float) Math.pow(6.0 / 64.0, dstcount);
                nextState.probability *= (0.234375);

            }
            if (last == "3") {
                nextState.probability = (float) Math.pow(6.0 / 64.0, dstcount);
                nextState.probability *= (0.3125);

            }
            if (last == "3") {
                nextState.probability = (float) Math.pow(6.0 / 64.0, dstcount);
                nextState.probability *= (0.234375);

            }
            nextState.commakemove2(1, sn, nextState.com.comnumofmovements);
            nextStates.add(nextState);
        }

        // bng extra move
        for (int i = 0; i < 13; i++) {
            Gameboard nextState = Deepcopy();
            nextState.com.comnumofmovements += (6 * bngcount);
            String last = "2";
            if (dstcount < 5) {
                if (i == 0 || i == 3 || i == 6 || i == 9 || i == 12) {
                    nextState.com.comnumofmovements += 2;
                    nextState.com.comnumofmovements += 1;
                    last = "2";
                } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13) {
                    nextState.com.comnumofmovements += 3;
                    nextState.com.comnumofmovements += 1;
                    last = "3";
                } else if (i == 2 || i == 5 || i == 8 || i == 11 || i == 14) {
                    nextState.com.comnumofmovements += 4;
                    nextState.com.comnumofmovements += 1;
                    last = "4";
                }
                if (i == 2 || i == 5 || i == 8 || i == 11) {
                    dstcount++;
                }
            }
            if (last == "2") {
                nextState.probability = (float) Math.pow(0.015625, dstcount);
                nextState.probability *= (0.234375);

            }
            if (last == "3") {
                nextState.probability = (float) Math.pow(0.015625, dstcount);
                nextState.probability *= (0.3125);

            }
            if (last == "3") {
                nextState.probability = (float) Math.pow(0.015625, dstcount);
                nextState.probability *= (0.234375);

            }
            nextState.commakemove2(1, sn, nextState.com.comnumofmovements);
            nextStates.add(nextState);
        }

        // bng add stone
        for (int i = 0; i < 13; i++) {
            Gameboard nextState = Deepcopy();
            nextState.com.comnumofmovements += (6 * bngcount);
            String last = "2";
            khalmoveForNextState('c', nextState, "bng", i);
            if (dstcount < 5) {
                if (i == 0 || i == 3 || i == 6 || i == 9 || i == 12) {
                    nextState.com.comnumofmovements += 2;
                    last = "2";
                } else if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13) {
                    nextState.com.comnumofmovements += 3;
                    last = "3";
                } else if (i == 2 || i == 5 || i == 8 || i == 11 || i == 14) {
                    nextState.com.comnumofmovements += 4;
                    last = "4";
                }
                if (i == 2 || i == 5 || i == 8 || i == 11) {
                    bngcount++;
                }
            }
            if (last == "2") {
                nextState.probability = (float) Math.pow(0.015625, bngcount);
                nextState.probability *= (0.234375);

            }
            if (last == "3") {
                nextState.probability = (float) Math.pow(0.015625, bngcount);
                nextState.probability *= (0.3125);

            }
            if (last == "3") {
                nextState.probability = (float) Math.pow(0.015625, bngcount);
                nextState.probability *= (0.234375);

            }
            nextState.commakemove2(1, sn, nextState.com.comnumofmovements);
            nextStates.add(nextState);
        }

        return nextStates;
    }

 
    public List<Gameboard> firstgetnextstate(Gameboard currentboard) {
        List<Gameboard> nextStates = new ArrayList<>();
        System.out.println("Computer moves: "+currentboard.com.comnumofmovements);
        // if(currentboard.com.com)
      
        for (int j = 0; j < 4; j++) {
            if (currentboard.com.comstones[j].stonepos > 0) {

             if(currentboard.com.comkhal==true){//add stone
                Gameboard nextState = Deepcopy();
                if(currentboard.com.comnumofmovements<26){
                nextState.khalmoveForNextState('c', nextState, "dst", j);
                nextState.commakemove2(1, j, currentboard.com.comnumofmovements);
                nextStates.add(nextState);
                }else{
                    nextState.khalmoveForNextState('c', nextState, "bng", j);
                    nextState.commakemove2(1, j, currentboard.com.comnumofmovements);
                    nextStates.add(nextState); 
                }
                Gameboard nextState2 = Deepcopy();
                nextState2.commakemove2(1, j, currentboard.com.comnumofmovements+1);
                nextStates.add(nextState2);
            }else{
                Gameboard nextState = Deepcopy();
                nextState.commakemove2(1, j, currentboard.com.comnumofmovements);
                nextStates.add(nextState);
            }
        }
        }
       
        return nextStates;
    }
}
