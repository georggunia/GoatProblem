
import java.util.Scanner;

public class Ziegenproblem {
    private static int readDoor(String doorName) { //Read door
        int door = 0;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter "+doorName + ":");
            if (!(sc.hasNextInt())) {
                System.out.println("Error: Please enter integer");
                sc.nextLine();
            } else {
                door = sc.nextInt();
                if (door >= 0 && door < 3) { //condition for valid input
                    break; // end loop, if valid input was found
                }

                System.out.println("The given Integer is invalid! (0 =< x < 3)");
                sc.nextLine();
            }
        }
        return door;
    }

    private static int getRemainingDoor(int door1, int door2) {
        if (door1 != door2) {
            if (door1 == 0 && door2 == 1 || door1 == 1 && door2 == 0) {              // 0,1 or 1,0
                return 2;
            } else if (door1 == 0 && door2 == 2 || door1 == 2 && door2 == 0) {      // 0,2 or 2,0
                return 1;
            } else if (door1 == 1 && door2 == 2 || door1 == 2 && door2 == 1) {      // 1,2 or 2,1
                return 0;
            }
        }
        return -1; //Error! doors are the same!
    }


    public static void main(String[] args){
            // Initialize Variablies
            float winsA = 0, winsB = 0; //Counter for Strategies a and b
            int goatDoor; //the door which is being opened by the game master

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Test Remaining door method? (y/n)");

                if (!(sc.hasNext())) {
                    System.out.println("Invalid Input");
                    sc.nextLine();
                    continue;
                }

                String input = sc.next();

                switch (input) {
                    case "y":
                        while (true) {
                            int x = readDoor("door 1");
                            int y = readDoor("door 2");
                            if (x == y) {
                                System.out.println("The given doors are the same!");
                                continue;
                            }
                            System.out.println("The remaining door is: " + getRemainingDoor(x, y) + "!\n");
                            break;
                        }
                        break;

                    case "n":
                        break;

                    default:
                        System.out.println("Invalid Input");
                        continue;
                }
                break;
            }





        //goat is always behind door 0 in our case
        for (int i = 0; i < 10000000; i++) {
            int candidateDoor = (int) (Math.random() * 3); //candidate chooses random door
            //chooosing the door and opnening the door with no goat behind
            if (candidateDoor == 0) { //candidate chooses door 0
                winsA++; //Strategy A gets a win (strat a is staying with the door you choose)
                if (Math.random() < 0.5) { //50-50 chance
                    goatDoor = 1; //door 1 is being shown
                } else {
                    goatDoor = 2; //doore 2 is being shown
                }
            } else {
                goatDoor = getRemainingDoor(0, candidateDoor); //Closed: 0 and choosen door. Remaining loser door is being opened by gamemaster
            }
            if (getRemainingDoor(candidateDoor, goatDoor) == 0) { //candidate decided to switch to door 0 (winner door)
                winsB++; //Strategy B gets a win (strat B is switching the door after the gamemaster opens the loser door)
            }
        }
        System.out.printf("Stategy A: %f Wins <%.2f%%>\n", winsA, (winsA / (winsB + winsA))*100);
        System.out.printf("Stategy B: %f Wins <%.2f%%>\n", winsB, (winsB / (winsB + winsA))*100); //Has a higher change of winning overall


    }
}


