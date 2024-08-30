import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GamePlay {
    Gameboard gm;
    Scanner s = new Scanner(System.in);
    int turns = 0; // 0>player 1>computer 2>noone

    public GamePlay() {
        gm = new Gameboard("Noore");

    }

    public void FirstPlay() throws InterruptedException {
        FirstThrowShells();
        FirstThrowShells();
        FirstThrowShells();
        FirstComThrowShells();
        FirstComThrowShells();
        FirstComThrowShells();
        // if(gm.com.comkhal)
        // gm.com.comnumofstones++;
        // if(gm.p.playerkhal)
        // gm.p.playernumofstones++;
        System.out.print("----------");
        Thread.sleep(500);
        System.out.print("-------");
        Thread.sleep(500);
        System.out.print("------------");
        Thread.sleep(500);
        System.out.print("------");
        Thread.sleep(300);
        System.out.print("----------------- ");
        System.out.println("Done calculating");
        System.out.println("Player: " + gm.p.playernumofmovements + " - Player khal:" + gm.p.playerkhal);
        System.out.println("Computer: " + gm.com.comnumofmovements + " - Compuer khal: " + gm.com.comkhal);

        if (gm.p.playerkhal == false && gm.com.comkhal == true) {

            gm.com.comnumofstones++;
            gm.p.playernumofmovements = 0;

            System.out.println("test 1");
            System.out.println("com number of stones at test 1: " + gm.com.comnumofstones);

            // gm.com.comnumofstones++;
            turns = 1;
        } else if (gm.p.playerkhal == false && gm.com.comkhal == false) {
            System.out.println("test 2");
            turns = 2;
        } else if (gm.com.comkhal == false && gm.p.playerkhal == true) {
            gm.p.playernumofstones++;
            gm.com.comnumofmovements = 0;
            System.out.println("test 3");
            turns = 0;
        }
        // true true
        else {

            System.out.println("test 4");
            if (gm.p.playernumofmovements >= gm.com.comnumofmovements) {
                gm.p.playernumofstones++;
                System.out.println("test 5");
                turns = 0;
            } else {
                gm.com.comnumofstones++;
                System.out.println("test 6");
                turns = 1;
            }
        }
        System.out.println("Turn :" + turns);

        switch (turns) {

            case 0:
                System.out.println("playerfirstplay " + gm.p.playerstones[0].stonepos);
                gm.p.playerstones[0].stonepos++;
                if (gm.p.playerkhal) {
                    gm.p.playernumofmovements++;
                }
                gm.makemove2(1, 0, gm.p.playernumofmovements);
                gm.com.comnumofmovements = 0;
                gm.p.playernumofmovements = 0;
                gm.p.playerkhal = false;
                gm.printarray(gm.board);
                turns = 1;

                break;
            case 1:
                System.out.println("compter first play " + gm.com.comstones[0].stonepos);
                gm.com.comstones[0].stonepos++;
                if (gm.com.comkhal) {
                    gm.com.comnumofmovements++;
                }
                gm.commakemove2(1, 0, gm.com.comnumofmovements);
                gm.com.comnumofmovements = 0;
                gm.p.playernumofmovements = 0;
                gm.com.comkhal = false;
                gm.printarray(gm.board);
                turns = 0;
                break;
            case 2:
                System.out.println("No one will play :(\ntype r to replay");
                gm.p.playernumofmovements = 0;
                gm.com.comnumofmovements = 0;
                String r = s.nextLine();
                // if (r == "r") {
                turns = 0;
                FirstPlay();
                // }
                break;

        }

    }

    public void play() throws InterruptedException {

        int in = 1;
        while (in != 0) {
            if (IsFinal()) {
                DisplayWinningAnimation();
                in = 0;
            } else {
                System.out.print("Enter 1> Play, 2> Print 3> stats: 4>Freeplay");
                System.out.println();
                if (turns == 0) {
                    System.out.println("Player Turn");
                } else if (turns == 1) {
                    System.out.println("Computer Turn");
                }
                in = s.nextInt();
                int sn = 0;
                System.out.println("turns" + turns);
                switch (in) {

                    case 1:
                        if (turns == 0) {
                            if (gm.p.playernumofstones == 0
                                    || (gm.p.playerstones[0].stonepos == 0 && gm.p.playerstones[1].stonepos == 0
                                            && gm.p.playerstones[2].stonepos == 0
                                            && gm.p.playerstones[3].stonepos == 0)) {
                                gm.p.playernumofmovements = 0;

                                FirstThrowShells();
                                if (gm.p.playerkhal == true) {
                                    System.out.println("# of stone before: " + gm.p.playernumofstones);
                                    gm.p.playernumofstones++;
                                    System.out.println("# of stone after: " + gm.p.playernumofstones);
                                    // PlayerTurn(sn);
                                    if (gm.p.playerstones[sn].stonepos == 0) {
                                        System.out.println("Spe test 1");
                                        // gm.p.playerstones[sn].stonepos++;
                                        gm.p.playernumofmovements++;
                                    }
                                    gm.makemove2(1, gm.p.playernumofstones - 1, gm.p.playernumofmovements );
                                    gm.p.playernumofmovements = 0;
                                    gm.p.playerkhal = false;
                                    gm.printarray(gm.board);
                                    turns = 1;
                                }
                                if (gm.p.playernumofstones == 0
                                        || (gm.p.playerstones[0].stonepos == 0 && gm.p.playerstones[1].stonepos == 0
                                                && gm.p.playerstones[2].stonepos == 0
                                                && gm.p.playerstones[3].stonepos == 0)) {
                                    System.out.println("Still nothing for player!");
                                    turns = 1;
                                    // return;
                                }
                            } else {
                                if (turns == 0) {
                                    System.out.println("Player current number of stones: " + gm.p.playernumofstones);
                                    PlayerTurn(sn);
                                }
                            }
                        }

                        else if (turns == 1) {

                            if (gm.com.comnumofstones == 0
                                    || (gm.com.comstones[0].stonepos == 0 && gm.com.comstones[1].stonepos == 0
                                            && gm.com.comstones[2].stonepos == 0
                                            && gm.com.comstones[3].stonepos == 0)) {
                                gm.com.comnumofmovements = 0;

                                FirstComThrowShells();
                                if (gm.com.comkhal == true) {

                                    System.out.println("# of stone before: " + gm.com.comnumofstones);
                                    gm.com.comnumofstones++;
                                    System.out.println("# of stone after: " + gm.com.comnumofstones);
                                    // PlayerTurn(sn);
                                    if (gm.com.comstones[sn].stonepos == 0) {
                                        System.out.println("Spe test 2");
                                        // gm.com.comstones[sn].stonepos++;
                                        gm.com.comnumofmovements++;
                                    }
                                    gm.commakemove2(1, gm.com.comnumofstones - 1, gm.com.comnumofmovements );
                                    gm.com.comnumofmovements = 0;
                                    gm.com.comkhal = false;
                                    gm.printarray(gm.board);
                                    turns = 0;

                                }

                            }
                            if (gm.com.comnumofstones == 0
                                    || (gm.com.comstones[0].stonepos == 0 && gm.com.comstones[1].stonepos == 0
                                            && gm.com.comstones[2].stonepos == 0
                                            && gm.com.comstones[3].stonepos == 0)) {
                                System.out.println("Still nothing for the computer !");
                                turns = 0;
                                // return;
                            } else {
                                if (turns == 1) {
                                    System.out.println("Computer current number of stones: " + gm.com.comnumofstones);
                                    Computerturn(sn);
                                }
                            }
                        }

                        break;
                    case 2:
                        gm.printarray(gm.board);
                        break;
                    case 3:
                        System.out.println("Player stats: " + gm.p.getName() + " -playernumberofstones: "
                                + gm.p.playernumofstones + " -player winningstones" + gm.p.playerwinninstones
                                + " - playerstonespos" + gm.p.playerstones[0].stonepos + "-"
                                + gm.p.playerstones[1].stonepos
                                + "-" + gm.p.playerstones[2].stonepos + "-" + gm.p.playerstones[3].stonepos);
                        System.out.println("com stats: " + gm.com.getName() + " -comnumberofstones: "
                                + gm.com.comnumofstones + " -com winningstones" + gm.com.comwinninstones
                                + " - comstonespos"
                                + gm.com.comstones[0].stonepos + "-" + gm.com.comstones[1].stonepos + "-"
                                + gm.com.comstones[2].stonepos + "-" + gm.com.comstones[3].stonepos);
                        break;
                    case 4:
                        if (turns == 0) {
                            if (gm.p.playernumofstones == 0
                                    || (gm.p.playerstones[0].stonepos == 0 && gm.p.playerstones[1].stonepos == 0
                                            && gm.p.playerstones[2].stonepos == 0
                                            && gm.p.playerstones[3].stonepos == 0)) {
                                gm.p.playernumofmovements = 0;

                                FirstThrowShells();
                                if (gm.p.playerkhal == true) {
                                    System.out.println("# of stone before: " + gm.p.playernumofstones);
                                    gm.p.playernumofstones++;
                                    System.out.println("# of stone after: " + gm.p.playernumofstones);
                                    // PlayerTurn(sn);
                                    if (gm.p.playerstones[sn].stonepos == 0) {
                                        System.out.println("Spe test 1");
                                        // gm.p.playerstones[sn].stonepos++;
                                        gm.p.playernumofmovements++;
                                    }

                                    gm.makemove2(1, 0, gm.p.playernumofmovements + 1);
                                    gm.p.playernumofmovements = 0;
                                    gm.p.playerkhal = false;
                                    gm.printarray(gm.board);
                                    turns = 1;
                                }
                                if (gm.p.playernumofstones == 0
                                        || (gm.p.playerstones[0].stonepos == 0 && gm.p.playerstones[1].stonepos == 0
                                                && gm.p.playerstones[2].stonepos == 0
                                                && gm.p.playerstones[3].stonepos == 0)) {
                                    System.out.println("Still nothing for player!");
                                    turns = 1;
                                    // return;
                                }
                            } else {
                                System.out.println("Player current number of stones: " + gm.p.playernumofstones);
                                System.out.println("Enter the ammount of moves: ");
                                int moves = s.nextInt();
                                if (turns == 0) {
                                    FPPlayerTurn(sn, moves);
                                }
                            }
                        }

                        else if (turns == 1) {

                            if (gm.com.comnumofstones == 0
                                    || (gm.com.comstones[0].stonepos == 0 && gm.com.comstones[1].stonepos == 0
                                            && gm.com.comstones[2].stonepos == 0
                                            && gm.com.comstones[3].stonepos == 0)) {
                                System.out.println("com stone are 0");
                                gm.com.comnumofmovements = 0;

                                FirstComThrowShells();
                                if (gm.com.comkhal == true) {
                                    System.out.println("# of stone before: " + gm.com.comnumofstones);
                                    gm.com.comnumofstones++;
                                    System.out.println("# of stone after: " + gm.com.comnumofstones);
                                    // PlayerTurn(sn);
                                    if (gm.com.comstones[sn].stonepos == 0) {
                                        System.out.println("Spe test 2");
                                        // gm.com.comstones[sn].stonepos++;
                                        gm.com.comnumofmovements++;
                                    }
                                    gm.commakemove2(1, 0, gm.com.comnumofmovements + 1);
                                    gm.com.comnumofmovements = 0;
                                    gm.com.comkhal = false;
                                    gm.printarray(gm.board);
                                    turns = 0;
                                }
                                if (gm.com.comnumofstones == 0
                                        || (gm.com.comstones[0].stonepos == 0 && gm.com.comstones[1].stonepos == 0
                                                && gm.com.comstones[2].stonepos == 0
                                                && gm.com.comstones[3].stonepos == 0)) {
                                    System.out.println("Still nothing for the computer !");
                                    turns = 0;
                                    // return;
                                }
                            } else {
                                System.out.println("Computer current number of stones: " + gm.com.comnumofstones);
                                System.out.println("Enter the ammount of moves: ");
                                int moves = s.nextInt();
                                if (turns == 1) {
                                    FPComputerturn(sn, moves);
                                }
                            }
                        }
                }
            }
        }
    }

    public void PlayerTurn(int sn) throws InterruptedException {

        gm.p.numofplayerthrows = 0;
        gm.p.numofplayerthrows++;
        System.out.println("Player turn!" + gm.p.playernumofstones);
        gm.p.playernumofmovements = 0;
        ThrowShells();

        System.out.println(
                "player movecount: " + gm.p.playernumofmovements + " player khal? " + gm.p.playerkhal);
        if (gm.p.playernumofstones > 1 && ((gm.p.playerstones[0].stonepos > 0 || gm.p.playerstones[1].stonepos > 0
                || gm.p.playerstones[2].stonepos > 0 || gm.p.playerstones[3].stonepos > 0))) {
            boolean rightchoice = false;
            while (rightchoice == false) {

                int avs[] = new int[4];
                for (int i = 0; i < 4; i++) {
                    if (gm.p.playerstones[i].stonepos != 0) {
                        if (gm.p.playerstones[i].stoneboardpos == 219 && gm.p.playerstones[i].stonepos > 5
                                && gm.p.playerkhal == false)
                            continue;
                        System.out.println("Available stones: " + gm.p.playerstones[i].stoneid);
                        avs[i] = gm.p.playerstones[i].stoneid;
                    }
                }
                System.out.println("Enter the stone you want to move");
                sn = s.nextInt();
                sn--;
                // if (sn > gm.com.comnumofstones) {
                for (int i = 0; i < avs.length; i++) {
                    if (sn + 1 == avs[i]) {
                        rightchoice = true;
                        break;
                    }
                }
                if (rightchoice == false) {
                    System.out.println("Wrong choice!");
                }
            }

        }
        if (IsFinalMove('p', sn)) {
            if (gm.p.playerkhal == true) {
                EnterKitchen('p', sn, gm.p.playerstones[sn].stonepos);
                System.out.println("Player stone have entered the kitchen!");
                gm.printarray(gm.board);
                turns = 1;
            } else {
                turns = 1;
            }
        } else {
            if (gm.p.playerkhal) {
                if (gm.p.playernumofstones < 4) {
                    System.out.println("Choose:\n1> add stone\n2>make extra move");
                    int khalchoice = s.nextInt();
                    if (khalchoice == 1) {
                        khalmove('p', gm.p.firstthrowstatename);
                    } else {
                        System.out.println("Before:" + gm.p.playernumofmovements);
                        gm.p.playernumofmovements++;
                        System.out.println("After:" + gm.p.playernumofmovements);
                    }
                } else {
                    System.out.println("Before:" + gm.p.playernumofmovements);
                    gm.p.playernumofmovements++;
                    System.out.println("After:" + gm.p.playernumofmovements);
                }
            }
            if (gm.p.playerstones[sn].stonepos == 0) {
                System.out.println("Spe test 3");
                // gm.p.playerstones[sn].stonepos++;
                gm.p.playernumofmovements++;
            }
            gm.makemove2(1, sn, gm.p.playernumofmovements);
            // gm.makemove2(1, sn, 14);
            gm.p.playernumofmovements = 0;
            gm.p.playerkhal = false;
            gm.printarray(gm.board);
            turns = 1;
        }
    }

    public void Computerturn(int sn) throws InterruptedException {
        System.out.println("Computer turn! " + gm.com.comnumofstones);
        gm.com.comnumofmovements = 0;
        ComThrowShells();
        
        // List<Gameboard>test=gm.firstgetnextstate(gm);
        // System.out.println(test.size()+"<Size");
        // for (int i = 0; i < test.size(); i++) {
        //     gm.printarray(test.get(i).board);

        // }
        // System.out.println(
        //         "com movecount: " + gm.com.comnumofmovements + " com khal? " + gm.com.comkhal);
        // System.out.println("com number of stones : " + gm.com.comnumofstones);
        // System.out.println("player number of stones : " + gm.p.playernumofstones);
        // if (gm.com.comnumofstones > 1 && (gm.com.comstones[0].stonepos > 0 || gm.com.comstones[1].stonepos > 0
        //         || gm.com.comstones[2].stonepos > 0 || gm.com.comstones[3].stonepos > 0)) {
        //     boolean rightchoice = false;
        //     while (rightchoice == false) {
        //         int avs[] = new int[4];
        //         for (int i = 0; i < 4; i++) {
        //             if (gm.com.comstones[i].stonepos != 0) {
        //                 if (gm.com.comstones[i].stoneboardpos == 143 && gm.com.comstones[i].stonepos > 10
        //                         && gm.com.comkhal == false)
        //                     continue;
        //                 System.out.println("Available stones: " + gm.com.comstones[i].stoneid);
        //                 avs[i] = gm.com.comstones[i].stoneid;
        //             }
        //         }
        //         System.out.print("Enter the stone you want to move: ");
        //         sn = s.nextInt();
        //         sn--;
        //         // if (sn > gm.com.comnumofstones) {
        //         for (int i = 0; i < avs.length; i++) {
        //             if (sn + 1 == avs[i]) {
        //                 rightchoice = true;
        //                 break;
        //             }
        //         }
        //         if (rightchoice == false) {
        //             System.out.println("Wrong choice!");
        //         }
        //     }

        // }
        // if (IsFinalMove('c', sn)) {
        //     if (gm.com.comkhal == true) {
        //         EnterKitchen('c', sn, gm.com.comstones[sn].stonepos);
        //         System.out.println("Computer stone have entered the kitchen >:(");
        //         turns = 0;
        //     } else {
        //         turns = 0;
        //     }
        // } else {
        //     // if (gm.p.playernumofstones < 4) {
        //     // System.out.println("Choose:\n1> add stone\n2>make extra move");
        //     // int khalchoice = s.nextInt();
        //     // if (khalchoice == 1) {
        //     // khalmove('p');
        //     // } else {
        //     // System.out.println("Before:" + gm.p.playernumofmovements);
        //     // gm.p.playernumofmovements++;
        //     // System.out.println("After:" + gm.p.playernumofmovements);
        //     // }
        //     // }
        //     if (gm.com.comkhal) {
        //         if (gm.com.comnumofstones < 4) {
        //             System.out.println("Choose:\n1> add stone\n2>make extra move");
        //             int khalchoice = s.nextInt();
        //             if (khalchoice == 1) {
        //                 khalmove('c', gm.com.comfirstthrowname);
        //             } else {
        //                 System.out.println("Before:" + gm.com.comnumofmovements);
        //                 gm.com.comnumofmovements++;
        //                 System.out.println("After:" + gm.com.comnumofmovements);
        //             }
        //         } else {
        //             System.out.println("Before:" + gm.com.comnumofmovements);
        //             gm.com.comnumofmovements++;
        //             System.out.println("After:" + gm.com.comnumofmovements);
        //         }
        //     }
        //     if (gm.com.comstones[sn].stonepos == 0) {
        //         System.out.println("Spe test 4");
        //         // gm.com.comstones[sn].stonepos++;
        //         gm.com.comnumofmovements++;
        //     }
        //     gm.commakemove2(1, sn, gm.com.comnumofmovements);
        //     // gm.commakemove2(1, sn, 12);
        //     gm.com.comnumofmovements = 0;
        //     gm.com.comkhal = false;
        //     gm.printarray(gm.board);
        //     turns = 0;
        // }
        
        EMM em=new EMM();
        gm=em.findBestMove(gm,3);
        gm.printarray(gm.board);
        turns=1;
    }

    public Boolean IsFinal() {
        if (gm.p.playerwinninstones == 4) {
            System.out.println("Player " + gm.p.getName() + " have won!!!");
            return true;
        } else if (gm.com.comwinninstones == 4) {
            System.out.println("Computer " + gm.com.getName() + " have won!!!");
            return true;
        }
        return false;
    }

    public void khalmove(char turn, String throwname) {
        if (turn == 'p') {
            boolean done = false;
            // if there is a dead stone it takes the priority
            for (int i = 0; i < 4; i++) {
                if (gm.p.playerstones[i].killed) {
                    if (throwname == "dst") {
                        gm.makemove2(1, i, 11);
                    } else {
                        gm.makemove2(1, i, 26);
                    }
                    done = true;
                    System.out.println("Resurecting...");
                }
            }
            if (done) {
                return;
            } else {
                System.out.println("adding stone..." + throwname);
                gm.p.playernumofstones++;
                System.out.println("before khalmove " + gm.p.playerstones[gm.p.playernumofstones - 1].stonepos);
                // gm.makemove2(1, gm.p.playernumofstones - 1, 1);
                if (throwname == "dst") {
                    gm.makemove2(1, gm.p.playernumofstones - 1, 11);
                } else {
                    gm.makemove2(1, gm.p.playernumofstones - 1, 26);
                }
                System.out.println("after khalmove " + gm.p.playerstones[gm.p.playernumofstones - 1].stonepos);
                // gm.p.playerstones[gm.p.playernumofstones - 1].stonepos++;
                System.out
                        .println("after khalmove with increace "
                                + gm.p.playerstones[gm.p.playernumofstones - 1].stonepos);
            }
        }
        if (turn == 'c') {
            boolean done = false;
            // if there is a dead stone it takes the priority
            for (int i = 0; i < 4; i++) {
                if (gm.com.comstones[i].killed) {
                    // gm.commakemove2(1, i, 1);
                    if (throwname == "dst") {
                        gm.commakemove2(1, i, 11);
                    } else {
                        gm.commakemove2(1, i, 26);
                    }
                    System.out.println("Resurecting...");
                    done = true;
                }
            }
            if (done) {
                return;
            } else {
                System.out.println("adding stone..." + throwname);
                gm.com.comnumofstones++;
                System.out.println("before khalmove " + gm.com.comstones[gm.com.comnumofstones - 1].stonepos);
                // gm.commakemove2(1, gm.com.comnumofstones - 1, 1);
                if (throwname == "dst") {
                    gm.commakemove2(1, gm.com.comnumofstones - 1, 11);
                } else {
                    gm.commakemove2(1, gm.com.comnumofstones - 1, 26);
                }
                System.out.println("after khalmove " + gm.com.comstones[gm.com.comnumofstones - 1].stonepos);
                // gm.com.comstones[gm.com.comnumofstones - 1].stonepos++;
                System.out
                        .println("after khalmove with increamnet "
                                + gm.com.comstones[gm.com.comnumofstones - 1].stonepos);
            }
        }
    }

    public Boolean IsFinalMove(char turn, int sn) {
        if (turn == 'p') {
            if (gm.p.playerstones[sn].stonepos == 83) {
                System.out.println("final move player");
                return true;
            }
            return false;
        } else {
            if (gm.com.comstones[sn].stonepos == 84) {
                System.out.println("final move computer");
                return true;
            }
            return false;
        }
    }

    public void EnterKitchen(char turn, int sn, int oldidex) {
        if (turn == 'p') {
            System.out.println("oldindex for player in kitchen:" + oldidex);
            gm.p.playerstones[sn].stoneboardpos = 0;
            gm.p.playerstones[sn].stonepos = 0;
            // gm.p.playernumofstones--;
            gm.p.playerstones[sn].kitchened = true;
            gm.p.playerwinninstones++;
            gm.DeletedFromBoard(oldidex, sn);
        } else if (turn == 'c') {
            System.out.println("oldindex for com in kitchen:" + oldidex);
            gm.com.comstones[sn].stoneboardpos = 0;
            gm.com.comstones[sn].stonepos = 0;
            gm.com.comstones[sn].kitchened = true;
            // gm.com.comnumofstones--;
            gm.com.comwinninstones++;
            gm.ComDeleteFromBoard(oldidex, sn);
        }

    }

    private static boolean[] generateRandomshellarray(int size) {
        boolean[] array = new boolean[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextBoolean();
        }

        return array;
    }

    public void ThrowShells() throws InterruptedException {
        System.out.println("Throwing the shells:..");
        // Thread.sleep(300);
        System.out.println("*");
        // Thread.sleep(500);
        System.out.println("* * *");
        // Thread.sleep(500);
        System.out.println("* * * * * *");
        // Thread.sleep(500);
        boolean[] shellarray = generateRandomshellarray(6);
        // boolean[] shellarray = new boolean[] { false, true, true, true, true, true };

        for (boolean value : shellarray) {
            if (value == false) {
                System.out.print(" O ");
            } else {
                System.out.print(" V ");
            }
        }
        System.out.println();
        Shells sh = new Shells(shellarray);
        System.out.println("Shell movecount: " + sh.result.moveCount + " - Shell statename: "
                + sh.result.stateName + " - Shell khal? " + sh.result.khal);
        gm.p.playernumofmovements += sh.result.moveCount;
        gm.p.playerkhal = sh.result.khal;
        gm.p.firstthrowstatename = sh.result.stateName;
        if ((sh.result.stateName == "bng" || sh.result.stateName == "dst" || sh.result.stateName == "bara"
                || sh.result.stateName == "shaka") && gm.p.numofplayerthrows <= 10) {
            while (true) {

                System.out.println("Throwing new shell for the player:..");
                // Thread.sleep(300);
                System.out.println("*");
                // Thread.sleep(500);
                System.out.println("* * *");
                // Thread.sleep(500);
                System.out.println("* * * * * *");
                // Thread.sleep(500);
                boolean[] shellarray2 = generateRandomshellarray(6);
                Shells shnew = new Shells(shellarray2);
                for (boolean value : shellarray2) {
                    if (value == false) {
                        System.out.print(" O ");
                    } else {
                        System.out.print(" V ");
                    }
                }
                gm.p.playernumofmovements += shnew.result.moveCount;
                System.out.println("Shell movecount: " + shnew.result.moveCount + " - shnewell statename: "
                        + shnew.result.stateName + " - shnewell khal? " + shnew.result.khal);
                if (shnew.result.khal == true)
                    gm.p.playerkhal = true;
                if (shnew.result.stateName != "bng" || shnew.result.stateName != "dst"
                        || shnew.result.stateName != "bara" || shnew.result.stateName != "shaka")
                    break;
            }
        }
    }

    public void FirstThrowShells() throws InterruptedException {
        System.out.println("Throwing the shells for the player:..");
        boolean[] shellarray = generateRandomshellarray(6);
        // boolean[] shellarray = new boolean[] { false, true, true, true, true, true };
        // boolean[] shellarray = new boolean[] { false, false, true, true, true, true
        // };

        for (boolean value : shellarray) {
            if (value == false) {
                System.out.print(" O ");
            } else {
                System.out.print(" V ");
            }
        }
        System.out.println();
        Shells sh = new Shells(shellarray);
        System.out.println("Shell movecount: " + sh.result.moveCount + " - Shell statename: "
                + sh.result.stateName + " - Shell khal? " + sh.result.khal);
        gm.p.playernumofmovements += sh.result.moveCount;
        if (sh.result.khal) {
            gm.p.playerkhal = true;
        }

    }

    public void ComThrowShells() throws InterruptedException {
        gm.com.numofcomthrows = 0;
        gm.com.numofcomthrows++;
        System.out.println("Throwing the shells for the computer :..");
        // Thread.sleep(300);
        System.out.println("*");
        // Thread.sleep(500);
        System.out.println("* * *");
        // Thread.sleep(500);
        System.out.println("* * * * * *");
        // Thread.sleep(500);
        boolean[] shellarray = generateRandomshellarray(6);
        // boolean[] shellarray = new boolean[] { false, true, true, true, true, true };

        for (boolean value : shellarray) {
            if (value == false) {
                System.out.print(" O ");
            } else {
                System.out.print(" V ");
            }
        }
        System.out.println();
        Shells sh = new Shells(shellarray);
        System.out.println("Shell movecount: " + sh.result.moveCount + " - Shell statename: "
                + sh.result.stateName + " - Shell khal? " + sh.result.khal);
        gm.com.comnumofmovements += sh.result.moveCount;
        gm.com.comkhal = sh.result.khal;
        gm.com.comfirstthrowname = sh.result.stateName;
        if ((sh.result.stateName == "bng" || sh.result.stateName == "dst" || sh.result.stateName == "bara"
                || sh.result.stateName == "shaka") && gm.com.numofcomthrows <= 10) {
            while (true) {
                System.out.println("Throwing new shell:..");
                Thread.sleep(300);
                System.out.println("*");
                Thread.sleep(500);
                System.out.println("* * *");
                Thread.sleep(500);
                System.out.println("* * * * * *");
                Thread.sleep(500);
                boolean[] shellarray2 = generateRandomshellarray(6);
                Shells shnew = new Shells(shellarray2);
                for (boolean value : shellarray2) {
                    if (value == false) {
                        System.out.print(" O ");
                    } else {
                        System.out.print(" V ");
                    }
                }
                gm.com.comnumofmovements += shnew.result.moveCount;
                System.out.println("Shell movecount: " + shnew.result.moveCount + " - shnewell statename: "
                        + shnew.result.stateName + " - shnewell khal? " + shnew.result.khal);
                if (shnew.result.khal == true)
                    gm.com.comkhal = true;
                if (shnew.result.stateName != "bng" || shnew.result.stateName != "dst"
                        || shnew.result.stateName != "bara" || shnew.result.stateName != "shaka")
                    break;
            }
        }
    }

    public void FirstComThrowShells() throws InterruptedException {
        System.out.println("Throwing the shells for the computer :..");
        boolean[] shellarray = generateRandomshellarray(6);
        // boolean[] shellarray = new boolean[] { false, true, true, true, true, true };
        // boolean[] shellarray = new boolean[] { false, false, false, false, false,
        // true };
        // boolean[] shellarray = new boolean[] { false, false, true, true, true, true
        // };

        for (boolean value : shellarray) {
            if (value == false) {
                System.out.print(" O ");
            } else {
                System.out.print(" V ");
            }
        }
        System.out.println();
        Shells sh = new Shells(shellarray);
        System.out.println("Shell movecount: " + sh.result.moveCount + " - Shell statename: "
                + sh.result.stateName + " - Shell khal? " + sh.result.khal);
        gm.com.comnumofmovements += sh.result.moveCount;
        if (sh.result.khal == true) {
            gm.com.comkhal = true;
        }

        // gm.com.comkhal = sh.result.khal;
    }

    // freeplay
    public void FPPlayerTurn(int sn, int move) throws InterruptedException {
        System.out.println("Player turn!" + gm.p.playernumofstones);
        gm.p.playernumofmovements = 0;
        ThrowShells();

        System.out.println(
                "player movecount: " + move + " player khal? " + gm.p.playerkhal);
        System.out.println("You want khal?");
        int wantkhal = s.nextInt();
        if (wantkhal == 1) {
            gm.p.playerkhal = true;
        } else {
            gm.p.playerkhal = false;
        }
        if (gm.p.playernumofstones > 1) {
            boolean rightchoice = false;
            while (rightchoice == false) {

                int avs[] = new int[4];
                for (int i = 0; i < 4; i++) {
                    if (gm.p.playerstones[i].stonepos != 0) {
                        if (gm.p.playerstones[i].stoneboardpos == 219 && gm.p.playerstones[i].stonepos > 5
                                && gm.p.playerkhal == false)
                            continue;
                        System.out.println("Available stones: " + gm.p.playerstones[i].stoneid);
                        avs[i] = gm.p.playerstones[i].stoneid;
                    }
                }

                System.out.println("Enter the stone you want to move");
                sn = s.nextInt();
                sn--;
                // if (sn > gm.com.comnumofstones) {
                for (int i = 0; i < avs.length; i++) {
                    if (sn + 1 == avs[i]) {
                        rightchoice = true;
                        break;
                    }
                }
                if (rightchoice == false) {
                    System.out.println("Wrong choice!");
                }
            }

        }
        if (IsFinalMove('p', sn)) {
            if (gm.p.playerkhal == true) {
                EnterKitchen('p', sn, gm.p.playerstones[sn].stonepos);
                System.out.println("Player stone have entered the kitchen!");
                turns = 1;
            } else {
                turns = 1;
            }
        } else {
            if (gm.p.playerkhal) {
                if (gm.p.playernumofstones < 4) {
                    System.out.println("Choose:\n1> add stone\n2>make extra move");
                    int khalchoice = s.nextInt();
                    if (khalchoice == 1) {
                        khalmove('p', gm.p.firstthrowstatename);
                    } else {
                        // System.out.println("Before:" + move);
                        // move++;
                        // System.out.println("After:" + move);
                    }
                } else {
                    // System.out.println("Before:" + move);
                    // move++;
                    // System.out.println("After:" + move);
                }
            }
            if (gm.p.playerstones[sn].stonepos == 0) {
                System.out.println("Spe test 3");
                // gm.p.playerstones[sn].stonepos++;
                // move++;
            }
            gm.makemove2(1, sn, move);
            // gm.makemove2(1, sn, 14);
            // gm.Updategameboard(oldidex, sn, move);
            move = 0;
            gm.p.playerkhal = false;
            gm.printarray(gm.board);
            turns = 1;
        }
    }

    public void FPComputerturn(int sn, int move) throws InterruptedException {
        System.out.println("Computer turn! " + gm.com.comnumofstones);
        gm.com.comnumofmovements = 0;
        ComThrowShells();
        System.out.println(
                "com movecount: " + move + " com khal? " + gm.com.comkhal);
        System.out.println("com number of stones : " + gm.com.comnumofstones);
        System.out.println("player number of stones : " + gm.p.playernumofstones);
        System.out.println("You want khal?");
        int wantkhal = s.nextInt();
        if (wantkhal == 1) {
            gm.com.comkhal = true;
        } else {
            gm.com.comkhal = false;
        }
        if (gm.com.comnumofstones > 1) {
            boolean rightchoice = false;
            while (rightchoice == false) {
                int avs[] = new int[4];
                for (int i = 0; i < 4; i++) {
                    if (gm.com.comstones[i].stonepos != 0) {
                        if (gm.com.comstones[i].stoneboardpos == 143 && gm.com.comstones[i].stonepos > 5
                                && gm.com.comkhal == false)
                            continue;
                        System.out.println("Available stones: " + gm.com.comstones[i].stoneid);
                        avs[i] = gm.com.comstones[i].stoneid;
                    }
                }

                System.out.print("Enter the stone you want to move: ");
                sn = s.nextInt();
                sn--;
                // if (sn > gm.com.comnumofstones) {
                for (int i = 0; i < avs.length; i++) {
                    if (sn + 1 == avs[i]) {
                        rightchoice = true;
                        break;
                    }
                }
                if (rightchoice == false) {
                    System.out.println("Wrong choice!");
                }
            }

        }
        if (IsFinalMove('c', sn)) {
            if (gm.com.comkhal == true) {
                EnterKitchen('c', sn, gm.com.comstones[sn].stonepos);
                System.out.println("Computer stone have entered the kitchen >:(");
                gm.printarray(gm.board);
                turns = 0;
            } else {
                turns = 0;
            }
        } else {
            if (gm.com.comkhal) {
                if (gm.com.comnumofstones < 4) {
                    System.out.println("Choose:\n1> add stone\n2>make extra move");
                    int khalchoice = s.nextInt();
                    if (khalchoice == 1) {
                        khalmove('c', gm.com.comfirstthrowname);
                    } else {
                        // System.out.println("Before:" + move);
                        // move++;
                        // System.out.println("After:" + move);
                    }
                } else {
                    // System.out.println("Before:" + move);
                    // move++;
                    // System.out.println("After:" + move);
                }
            }
            if (gm.com.comstones[sn].stonepos == 0) {
                System.out.println("Spe test 4");
                // gm.com.comstones[sn].stonepos++;
                move++;
            }
            gm.commakemove2(1, sn, move);
            // gm.commakemove2(1, sn, 12);
            move = 0;
            gm.com.comkhal = false;
            gm.printarray(gm.board);
            turns = 0;
        }
    }

    public void DisplayWinningAnimation() throws InterruptedException {
        System.out.println("-------------------------------------");
        Thread.sleep(500);
        System.out.println("-------------------------------------");
        Thread.sleep(500);
        System.out.println("----------------BERJIS---------------");
        Thread.sleep(500);
        System.out.println("-------------------------------------");
        Thread.sleep(500);
        System.out.println("-------------------------------------");
    }

    public void gns() {
        List<Gameboard>testlist=new ArrayList<>();
        testlist=gm.getNextStates(0);

        System.out.println("Size:"+testlist.size());
        System.out.println(testlist.get(10).probability+"<>>>>><");
        // System.out.println(testlist.get(50).com.comnumofstones);
    }
    public void gns2() {
        List<Gameboard>testlist=new ArrayList<>();
        testlist=gm.firstgetnextstate(gm);
        System.out.println("Size:"+testlist.size());
        // System.out.println(testlist.get(50).com.comnumofstones);
    }
    public void ThrowShells2() throws InterruptedException {
        System.out.println("Throwing the shells2:..");
        // Thread.sleep(300);
        System.out.println("*");
        // Thread.sleep(500);
        System.out.println("* * *");
        // Thread.sleep(500);
        System.out.println("* * * * * *");
        // Thread.sleep(500);
        boolean[] shellarray = generateRandomshellarray(6);
        // boolean[] shellarray = new boolean[] { false, true, true, true, true, true };

        for (boolean value : shellarray) {
            if (value == false) {
                System.out.print(" O ");
            } else {
                System.out.print(" V ");
            }
        }
        System.out.println();
        Shells sh = new Shells(shellarray);
        System.out.println("Shell movecount: " + sh.result.moveCount + " - Shell statename: "
                + sh.result.stateName + " - Shell khal? " + sh.result.khal);
        // gm.p.playernumofmovements += sh.result.moveCount;
        gm.p.playerkhal = sh.result.khal;
        gm.p.firstthrowstatename = sh.result.stateName;
        
        if ((sh.result.stateName == "bng" || sh.result.stateName == "dst" || sh.result.stateName == "bara"
                || sh.result.stateName == "shaka") && gm.p.numofplayerthrows <= 10) {
                    System.out.println("Choose the stone you want to move first");
                    int choosenstone=s.nextInt();
                    gm.com.comstones[choosenstone-1].stonenumofmoves+=sh.result.moveCount;

            while (true) {
                System.out.println("Throwing new shell for the player:..");
                // Thread.sleep(300);
                System.out.println("*");
                // Thread.sleep(500);
                System.out.println("* * *");
                // Thread.sleep(500);
                System.out.println("* * * * * *");
                // Thread.sleep(500);
                
                boolean[] shellarray2 = generateRandomshellarray(6);
                Shells shnew = new Shells(shellarray2);
                for (boolean value : shellarray2) {
                    if (value == false) {
                        System.out.print(" O ");
                    } else {
                        System.out.print(" V ");
                    }
                }
                // gm.p.playernumofmovements += shnew.result.moveCount;
                System.out.println("Choose the stone you want to move next");
                int nextchoosenstone=s.nextInt();
                gm.com.comstones[nextchoosenstone-1].stonenumofmoves+=sh.result.moveCount;
                System.out.println("Shell movecount: " + shnew.result.moveCount + " - shnewell statename: "
                        + shnew.result.stateName + " - shnewell khal? " + shnew.result.khal);
                if (shnew.result.khal == true)
                    gm.p.playerkhal = true;
                if (shnew.result.stateName != "bng" || shnew.result.stateName != "dst"
                        || shnew.result.stateName != "bara" || shnew.result.stateName != "shaka")
                    break;
            }
        }
    }
    public void moveallstones(){
        for (int i = 0; i < gm.com.comstones.length; i++) {
            
        }
    }
}
