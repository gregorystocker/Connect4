package test;

import core.Connect4;
import ui.Connect4TextConsole;

/**
 * contains code to run tests on the connect 4 project
 * @author Greg Stocker
 * @version 1
 */


public class Tester {

    /***
     * tests the checkWin method for accuracy horizontally. Note: you need to comment out the vertical check in
     * checkWin for this to work accurately. The different checks are labeled using comments.
     * be accurate.
     */
    public void hTest() {

        char[][] testBoard =
                {{'X', 'O', 'X', 'X', 'X', 'X', 'X'},
                        {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                        {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                        {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                        {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                        {'X', 'O', 'X', 'O', 'X', 'O', 'X'}

                };
        char[][] testBoard2=
                        {{'O', 'O', 'X', 'O', 'X', 'X', 'X'},
                        {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                        {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                        {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                        {'X', 'O', 'X', 'O', 'X', 'O', 'X'},
                        {'X', 'O', 'X', 'O', 'X', 'O', 'X'}

                };
        Connect4TextConsole c = new Connect4TextConsole();
        System.out.println("Should output true to catch 4 Xs in a row horizontally(only checking for Xs here):");
        Connect4.setPlayer('X');
        Connect4.setBoard(testBoard);
        c.display(Connect4.getBoard());
        System.out.println(Connect4.checkWin());

        System.out.println("Should output false to avoid false positive from X's spilling into another row:");
        Connect4.setPlayer('X');
        Connect4.setBoard(testBoard2);
        c.display(Connect4.getBoard());
        System.out.println(Connect4.checkWin());


    }

    /***
     * Tests different diagonal function calls. Need to uncomment the System.out.println() comments in the functions
     * diagonalCheck() and antiDiagonalCheck() in the Connect4 class to see the output. Otherwise you can still
     * compare the result to the array you are passing in to setBoard().
     */
    public void LtoRdiagPrint(){
        char[][] testBoard =
                        {{'a', 'b', 'c', '4', 'e', 'f', 'g'},
                        {'h', 'i', 'j', 'X', '3', 'l', 'm' },
                        {'n', 'q', 'p', 'q', '3', '2', 't' },
                        {'p', 'v', 'w', 'x', 'y', 'X', '1' },
                        {'1', '2', '3', '4', '6', '7', '0' },
                        {'9', 'A', 'B', 'C', 'D', 'E', 'F' }
                };
        Connect4.setPlayer('X');
        System.out.println("Should print true to recognize the 4 Xs in the 5th anti-diagonal and return." +
                " tracing anti-diagonals through the matrix: ");
        Connect4.setBoard(testBoard);
        //change this line to check different pieces
        System.out.println(Connect4.antiDiagonalCheck());






    }
}
