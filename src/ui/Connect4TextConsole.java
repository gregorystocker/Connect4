package ui;
import core.Connect4;
import test.Tester;

import java.lang.reflect.Array;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Displays the text based console display of a vertical board with 42 windows distributed in 6 rows and 7 columns,
 * and handles I.O. methods. S
 * @author Greg Stocker
 *  * @version 1
 */
public class Connect4TextConsole {

    Scanner in = new Scanner(System.in);

    /***
     *Displays the game board and some spacing afterwords
     * @param board: a 2d character array that holds information about our game board
     */
    public void display(char[][] board){
        int ROWS = Connect4.ROWS;
        int COLS = Connect4.COLS;
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                System.out.print(Connect4.getBoard()[i][j] + " ");
            }
            System.out.println();

        }
        //System.out.println("\n\n");
    }


    /***
     *gives the player instruction about what to do
     * @param player:a char that holds a character 'O' or 'X' to represent who is playing
     */
    public void  instruct(char player){
        System.out.println("Player" + player + "- your turn. Choose a column number from 1 - 7: ");
    }

    /***
     *outputs a message when a player wins the game
     * @param player: a char that gives info about which player just won
     */
    public void outputWin(char player){
        System.out.println("Player " + player + " Won the Game");
    }

    /***
     *Gets the user's input on which column they want, then translates that to the array by subtracting 1, since we're
     *  going from 1-7 instead of 0-6. As a side effect this also updates the currentColumn variable in Connect4.
     * @return column: an int that represents which column the player wants to drop his/her "coin"
     *
     */
    public int getInput()throws InputMismatchException, ArrayIndexOutOfBoundsException{
        int answer = in.nextInt();
    if (answer < 1 || answer > 7 || Connect4.getAvailable()[answer - 1] < 0) {
        while (answer < 1 || answer > 7 || Connect4.getAvailable()[answer - 1] < 0) {
            System.out.println("That is not an availible choice:");
            answer = Integer.parseInt(in.next());
        }
    }
    Connect4.setCurrentColumn(answer - 1);
        return answer - 1;
    }

    /***
     * @param args  String[]
     *
     */
    public static void main(String[] args) {
        char winner;
        boolean hasWinner = false;
        //Tester t = new Tester();
        Connect4TextConsole ui = new Connect4TextConsole();
        Connect4.createDefaultBoard();
        Connect4.setPlayer('X');
        ui.display(Connect4.getBoard());
        System.out.println("Begin Game");
        //these are tester functions
        //t.hTest(); //tests horizontally
        //t.LtoRdiagPrint(); //tests different diagonals based on changing a line of code in this function
        while(!hasWinner){
            ui.instruct(Connect4.getPlayer());//tells the current player that it is their turn and what to do.
            int answer =  ui.getInput(); // corrects for 0-based indexing in arrays
            Connect4.update(answer);// updates the board

            ui.display(Connect4.getBoard());//displays the board
            if(Connect4.checkWin()){
                ui.outputWin(Connect4.getPlayer());//if we have a winner, output the message and
                // set the condition to exit the loop at the end
                hasWinner = true;
            }else if(Connect4.isDraw()){
                System.out.println("The game is a draw.");
                hasWinner = true;
            }
            Connect4.changePlayer(); // sets the game to be the next player's turn.
        }//ends main game loop
    }
}
