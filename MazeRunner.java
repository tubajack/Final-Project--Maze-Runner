import java.util.Scanner;
import java.util.*;

public class MazeRunner {

    public static void main(String[] args) {
        Maze myMap = new Maze();
        intro(myMap);
    }

    //Introduction to the maze and printing out the map
    public static void intro(Maze myMap) {
        System.out.println("Welcome to Maze Runner!");
        System.out.println("Here is your current position: ");
        myMap.printMap();
        movesMessage(myMap);
        //navigatePit(myMap);
        //usersMove(myMap);
    }

    //What direction do you want your user to be moving in?
    public static char usersMove(Maze myMap) {

        Scanner input = new Scanner(System.in);
        System.out.println("Where would you like to move? (R,L,U,D)");
        char choice = input.nextLine().charAt(0);


        if (choice == 'R' || choice == 'r' && myMap.canIMoveRight()) {
            myMap.moveRight();
            myMap.printMap();
            System.out.println("You move Right!");
        } else if (choice == 'L' || choice == 'l' && myMap.canIMoveLeft()) {
            myMap.moveLeft();
            myMap.printMap();
            System.out.println("You move Left!");
        } else if (choice == 'U' || choice == 'u' && myMap.canIMoveUp()) {
            myMap.moveUp();
            myMap.printMap();
            System.out.println("You move Up!");
        } else if (choice == 'D' || choice == 'd' && myMap.canIMoveDown()) {
            myMap.moveDown();
            myMap.printMap();
            System.out.println("You move Down!");
        } else {
            System.out.println("Sorry, you’ve hit a wall.");
        }
        navigatePit(myMap);
        return choice;
    }

    //Let us account for 100 moves
    public static int movesMessage(Maze myMap) {
        int moves = 0;
        for (int i = 0; i < 100; i++) {
            moves++;
            usersMove(myMap);
            System.out.println("Move: " + moves);

            if (moves == 50) {
                System.out.print("Warning: You have made 50 moves,");
                System.out.println(" you have 50 remaining before the maze exit closes");
            } else if (moves == 75) {
                System.out.print("Alert! You have made 75 moves,");
                System.out.println(" you only have 25 moves left to escape.");
            } else if (moves == 90) {
                System.out.print("DANGER! You have made 90 moves,");
                System.out.println(" you only have 10 moves left to escape!!");
            } else if (moves == 100) {
                System.out.print("Oh no! You took too long to escape,");
                System.out.println(" and now the maze exit is closed FOREVER >:[");

                if (!myMap.didIWin()) {
                    System.out.println("Sorry, but you didn't escape in time- you lose!");
                }

            }
            if (myMap.didIWin()) {
                System.out.println("Congratulations, you won. You solved the maze in " + moves + " moves");
                break;
            }
        }
        return moves;
    }

    //To add to the difficulty level, there will also be pits in this maze.
    public static boolean navigatePit(Maze myMap) {
        Scanner input = new Scanner(System.in);
        String decisions;
        boolean jumpIt = false;

        if(myMap.isThereAPit("R")){
            System.out.println("Watch out! There is a pit ahead. Jump it?");
            decisions = input.next();
            if(decisions.toUpperCase().charAt(0) == 'Y'){
                myMap.jumpOverPit("R");
            }
            jumpIt = true;
        }
        else if(myMap.isThereAPit("L")){
            System.out.println("Watch out! There is a pit ahead. Jump it?");
            decisions = input.next();
            if(decisions.toUpperCase().charAt(0) == 'Y'){
                myMap.jumpOverPit("L");
            }
            jumpIt = true;
        }
        else if(myMap.isThereAPit("U")){
            System.out.println("Watch out! There is a pit ahead. Jump it?");
            decisions = input.next();
            if(decisions.toUpperCase().charAt(0) == 'Y'){
                myMap.jumpOverPit("U");
            }
            jumpIt = true;
        }
        else if(myMap.isThereAPit("D")){
            System.out.println("Watch out! There is a pit ahead. Jump it?");
            decisions = input.next();
            if(decisions.toUpperCase().charAt(0) == 'Y'){
                myMap.jumpOverPit("D");
            }
            jumpIt = true;
        }

        return jumpIt;
    }
}