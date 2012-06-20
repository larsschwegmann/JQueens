/**
 *
 * @author Lars Schwegmann
 * Site: http://larsschwegmann.com
 * Twitter: @larsschwegmann
 * 
 * LICENSE
 * -------
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/3.0/
 * or send a letter to:
 * 
 * Creative Commons
 * 444 Castro Street
 * Suite 900
 * Mountain View
 * California, 94041
 * USA.
 * 
 */

package com.larsschwegmann;

//Imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class JQueens {

    //Variables
    private int boardsize; //schachbrettsize = 8 -> 8x8 = 32 Felder
    private int[][] board; //Virtuelles Schachbrett (Zweidimensionaler Array). 1 = Dame , 2 = leer
    private static int solutions = 0; //Anzahl möglicher Lösungen
    
    //Initialization
    public JQueens (int size) {
        this.boardsize = size;
        this.board = new int[size][size];
    }
    
    //==========================================================================
    
    //Main Method
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("How many rows/collums has the chessboard you want to use?");
        int value = readInt();
        JQueens queen = new JQueens(value); //init the queen object
        
        //start the timer
        long before;
        long after;
        before = System.currentTimeMillis();
        
        queen.placeQueen(0); //Start with row zero. The recursion with backtracking does the rest for us.
        
        //stop the timer
        after = System.currentTimeMillis();
        long millis = after - before;
        //print a few stats
        System.out.println("Solutions found: " + queen.solutions);
        System.out.println(String.format("Computing time: %d minutes, %d seconds", TimeUnit.MILLISECONDS.toMinutes(millis), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))));
        System.out.println();
        System.out.println("Copyright 2012 by Lars Schwegmann");
        System.out.println("http://larsschwegmann.com");
        
    }
    
    //==========================================================================
    
    /*
     * Methods
     */
    
    //Print the content of board[][] to the console
    private void printResults() {
        for(int a=0; a<this.boardsize; a++){
            for(int b = 0; b<boardsize; b++)
                System.out.print(board[a][b]+" ");
            System.out.println("");
        }
    }
    
    //Checks if all queens are set correctly
    private boolean placedCorrectly() {
        boolean queenFound = false;
        
        //Only 1 queen is allowed per row:
        for(int a=0; a<this.boardsize; a++){
            queenFound = false;
            for(int b=0; b<this.boardsize; b++){
                if(this.board[a][b] == 1){
                    if(queenFound == false)
                        queenFound = true;
                    else
                        return false;
                }
            }
        }
        
        //Only 1 queen is allowed per collumn
        for(int b=0; b<this.boardsize; b++){
            queenFound = false;
            for (int a = 0; a<this.boardsize; a++){
                if(this.board[a][b] == 1){
                    if(queenFound == false)
                        queenFound = true;
                    else
                        return false;
                }
            }
        }
        
        //Now let's check the diagonals!
        
        //From upper left to bottom right:
        for (int row=0; row<this.boardsize; row++){
            queenFound = false;
            int collumn = 0; 
            while(row+collumn < this.boardsize){
                if(this.board[row+collumn][collumn] == 1){
                    if(queenFound == false)
                        queenFound = true;
                    else
                        return false;
                }
                collumn++;
            }
        }
        for(int collumn=1; collumn<this.boardsize; collumn++){
            queenFound = false;
            int row = 0;
            while(collumn+row < this.boardsize){
                if(this.board[row][collumn+row] == 1){
                    if(queenFound == false)
                        queenFound = true;
                    else
                        return false;
                }
                row++;
            }
        }
        
        //From bottom left to upper right:
        for(int row=this.boardsize-1; row>=0; row--){
            queenFound = false;
            int collumn = 0;
            while(row-collumn >= 0){
                if(this.board[row-collumn][collumn] == 1){
                    if(queenFound == false)
                        queenFound = true;
                    else
                        return false;
                }
                collumn++;
            }
        }
        for(int collumn=0; collumn<this.boardsize; collumn++){
            queenFound = false;
            int row = 0;
            while(collumn+row < this.boardsize){
                if(this.board[this.boardsize-row-1][collumn+row] == 1){
                    if(queenFound == false)
                        queenFound = true;
                    else
                        return false;
                }
                row++;
            }
        }
        
        return true;
    }
    
    public void placeQueen(int row){
        for(int collumn=0; collumn<this.boardsize; collumn++){
            this.board[row][collumn] = 1;
            if(this.placedCorrectly()){
                if(row == this.boardsize - 1){
                    this.printResults();
                    System.out.println("-------");
                    JQueens.solutions++;
                    System.out.println("Solutions already found: " + JQueens.solutions);
                    System.out.println("-------");
                }else{
                    placeQueen(row+1); //recursion
                }
            }
            //Setze Spalte zurück
            this.board[row][collumn] = 0;
        }
    }
    
    
    //Helper/Third-Party Methods
    
    /*
     * readInt()
     * Found here:
     * http://www.java-forum.org/java-basics-anfaenger-themen/14238-readint-funktioniert.html
     */
    public static int readInt()
    {
        int i=0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            i= Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e)
        {
           System.out.println("Wrong input format! Only decimal numbers are allowed!");
           return readInt();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return i;
        
    }
}
