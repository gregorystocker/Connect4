
package core;
import test.Tester;

import java.io.IOException;

/**
 * contains all game logic place your Connect4.java in here. Both players have a set of 21 thin pieces (like coins);
 * each of them uses a different color. The board is empty at the start of the game.
 * The aim for both players is to make a straight line of four own pieces; the line can be vertical,
 * horizontal or diagonal.
 * Main Game Logic
 * @author Greg Stocker
 * @version 1
 */
public class Connect4 {
    //private member variables
    private static char player;
    private static char[][] board = new char[6][7];
    private static int currentColumn;
    private static int currentRow; //didn't end up really using these, this was when I was planning on just checking around
    //the input, but just checking the whole array each time ended up being alot easier to think through.
    private static int[] available = {5,5,5,5,5,5,5};
    //public member variables
    public static final int ROWS = 6;
    public static final int COLS = 7;


    /***
     *Updates the board and the internal state to reflect the user's choice.
     * @param column gets the column from the user (make sure you subtract 1 from it) and adds that player's coin to
     * the next availible slot.
     */
    public static  void update(int column){
        board[available[column]][column] = player;
        currentColumn = column;
        currentRow = available[column];
        decrementAvailable(column);

    }

    /**
     * returns true if the game is a draw otherwise returns false.
     * @return boolean value
     */
    public static boolean isDraw(){
        int count = 0;
        for(int i = 0; i < ROWS; i++){
           for(int j = 0; j < COLS; j++){
                if(board[i][j] == '|')
                    count++;
           }
        }
        if(count == 0){
            return true;
        }
        else{
            return false;
        }
    }


    /***
     * This function holds most of the game's logic. It checks for 4 in a row horizontally, vertically and on both
     * diagonals and anti-diagonals.
     * @return returns a boolean that represents whether or not 4 in a row was found anywhere on the board.
     */
    public static boolean checkWin(){

        int count = 0;
        //check horizontally
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if(count == 4){//check 4 in a row
                    return true;
                }
                if(j == 0){//set count to 0 at a new row
                    count = 0;
                }
                if(board[i][j] == player){
                    count++;
                }

                else{count = 0;}
            }
        }

        //check vertically
        count = 0;
        for(int i = 0; i < COLS; i++){
            for(int j = ROWS-1; j > 0; j--){
                if(count == 4){//check 4 in a col
                    return true;
                }
                if(j == ROWS - 1){//set count to 0 at a new col
                    count = 0;
                }
                if(board[j][i] == player){
                    count++;
                }
                else{count = 0;}
            }
        }
        boolean diag, anti;
        diag = diagonalCheck();//these were long so I broke them up into different helper functions
        anti = antiDiagonalCheck();
        if(diag || anti){
            return true;
        }
        return false;
    }

    /**
     *  used as a helper function by checkWin to check for 4 in a row along a diagonal.
     * @return returns a boolean representing weather or not a connection of 4 was found
     */
    public static boolean diagonalCheck() {
        //check diagonally

        //algorithm
        //for an m x n matrix m = rows; n = cols
        //go around outside
        //starts at first index of rows and last index of columns
        //i = i - 1
        //j = j + 1
        int i,j;
        int count = 0;
        for(int k = 0; k < ROWS ; k++){//goes from row 0 to row 5

            i = k;//so we don't lose our place with the outer loop with k
            j = 0;

            while( i >= 0){
                //System.out.print(board[i][j] + " "); //for testing purposes
                //diag
                if(count == 4){//check 4 in a row
                    return true;
                }
                if(j == 0){
                    count = 0; //reset if we're at the far left hand side = new diagonal
                }

                if(board[i][j] == player){
                    count++;
                }
                else{count = 0;}

                //end diag

                i = i - 1;
                j = j + 1;
            }//ends while loop
           // System.out.println(); //for testing purposes
        }//ends outer for loop
        //up to the middle of the matrix

        count = 0;
        for(int k = 1; k < COLS ; k++){//starting at 1 as to not double count the long diagonal
            i = ROWS-1;
            j = k;
            while(j < COLS ){
                //System.out.print(board[i][j] + " ");
                //diag
                if(count == 4){//check 4 in a row
                    return true;
                }
                if(i == ROWS - 1){
                    count = 0; //reset if we're at the bottom of the matrix
                }

                if(board[i][j] == player){
                    count++;
                }
                else{count = 0;}

                //end diag

                i = i - 1;
                j = j + 1;
            }
            //System.out.println();

        }
        return false;
    }//ends diagonal check

    /**
     *  used as a helper function by checkWin to check for 4 in a row along a anti-diagonal.
     * @return returns a boolean representing weather or not a connection of 4 was found
     */

    public static boolean antiDiagonalCheck() {
        //check antiDiagonals

        //check AntiDiagonally
        int count = 0;
        int i, j;
        for (int k = 0; k < ROWS; k++){//goes from row 0 to row 5
            i = k;//so we don't lose our place with the outer loop with k
            j = COLS - 1; // starts at the last column index
            while (i >= 0) {

                //anti-diag check
                //System.out.print(board[i][j] + " ");//for testing purposes
                if(count == 4){//check 4 in a row
                    return true;
                }
                if(j == COLS - 1){
                    count = 0; //reset if we're at the far right of the matrix
                }
                if(board[i][j] == player){
                    count++;
                }
                else{count = 0;}
                //ends anti-diag check

                i = i - 1;
                j = j - 1; //for anti-diag this should be subtracting 1 as well
            } //ends while loop
            //System.out.println();
        } //ends outer for
         //up to the middle of the matrix

        for (int  k = COLS - 2; k >= 0; k--) {//starting at cols-2 as to not double count the long diagonal
            i = ROWS - 1;
            j = k;
            while (j > 0){

                //anti-diag check
                if(count == 4){//check 4 in a row
                    return true;
                }
                if(i == ROWS - 1){
                    count = 0; //reset if we're at the far right of the matrix
                }
                if(board[i][j] == player){
                    count++;
                }
                else{count = 0;}
                //ends anti-diag check
                //System.out.print(board[i][j] + " ");
                i = i - 1;
                j = j - 1;
            }
           // System.out.println();

        }
        return false;// if it gets to the end and hasn't returned true yet, there was no win
    }//ends anti-diagonal check

    /**
     *  Used for testing purposes to print out along the diagonals.
     */
    public static void diag() { //function just for testing purposes
        //check diagonally

        //algorithm
        //for an m x n matrix m = rows; n = cols
        //go around outside
        //starts at first index of rows and last index of columns
        //i = i - 1
        //j = j + 1
        int i,j;
        for(int k = 0; k < ROWS ; k++){//goes from row 0 to row 5

            i = k;//so we don't lose our place with the outer loop with k
            j = 0;
            while( i >= 0){
                System.out.print(board[i][j] + " ");
                i = i - 1;
                j = j + 1;
            }//ends while loop
            System.out.println();
        }//ends outer for loop
        //up to the middle of the matrix

        for(int k = 1; k < COLS ; k++){//starting at 1 as to not double count the long diagonal
            i = ROWS-1;
            j = k;
            while(j < COLS ){
                System.out.print(board[i][j] + " ");
                i = i - 1;
                j = j + 1;
            }
            System.out.println();

        }
    }//ends diagonal check

    /**
     * sets the member vaiable currentColumn
     * @param currentColumn
     */
    public static void setCurrentColumn(int currentColumn) {
        Connect4.currentColumn = currentColumn;
    }
    /**
     * decrements the index of available[] given by column. This represents a space getting taken by a token.
     * @param column
     */
    public static void decrementAvailable(int column){
        available[column]--;
    }

    /**
     * gets player
     * @return  player
     */
    public static char getPlayer() {
        return player;
    }

    /**
     * sets this.player to player
     * @param player
     */
    public static void setPlayer(char player){
        Connect4.player = player;
    }

    /**
     * Returns the 2D array that represents the game board
     * @return  board
     */
    public static char[][] getBoard(){
        return board;
    }

    /**
     * sets this.board to board
     * @param board
     */
    public static void setBoard(char[][] board){
        Connect4.board = board;
    }

    /**
     * sets the default blank board with all indexes initialized with '|'
     */
    public static void createDefaultBoard(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
               board[i][j] = '|';
            }
        }
    }

    /**
     * changes the player state from 'X' to 'O' or vice versa
     */
    public static void changePlayer(){
        if(player == 'X'){
            player = 'O';
        }else{
            player = 'X';
        }
    }

    /**
     *gets a copy of the array representing the next available space in each column.
     * @return  int[]
     */
    public static int[] getAvailable() {
        return available.clone();
    }

}//ends Connect4
