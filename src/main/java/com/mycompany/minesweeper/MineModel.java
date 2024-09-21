
package com.mycompany.minesweeper;

import java.awt.GridLayout;
import java.util.Random;
import javax.swing.*;

/**
 * 
 * @author Aaliyah M
 * 
 * Holds get/retrieval methods
 */
public class MineModel {
    
    private int ROWS = 15;
    private int COLUMNS = 15;
    boolean[][] mineFound = new boolean[ROWS][COLUMNS];
    JLabel[][] labels = new JLabel[ROWS][COLUMNS];
    private GridLayout grid;
    
    public MineModel(GridLayout gl) {
        grid = gl;
        ROWS = grid.getRows();
        COLUMNS = grid.getColumns();
    }
    
    void placeMines(int mineCount) {
        Random rand1 = new Random();
        Random rand2 = new Random();
        
        while (mineCount >0) { //more than 0 mines
            int row = rand1.nextInt(grid.getRows());
            int col = rand2.nextInt(grid.getColumns());          
            mineFound[row][col] = true;
            
            mineCount--;
        }
        
    }
    
    void clearMines() {
        for (int i=0;i<ROWS;i++) {
            for (int j=0;j<COLUMNS;j++) {
                if (mineFound[i][j] == true) {
                    mineFound[i][j] = false;
                }
            }
        }
    }
    
    int getAdjacentMines(int r, int c) {
        int mines = 0;
        
        for (int i = r-1; i <= r + 1;i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                if (i >= 0 && i < ROWS && j >= 0 && j < COLUMNS && mineFound[i][j]) {
                    mines++;
                }
            }
        }
        return mines;
    }
    
    void revealAllMines() {
        for (int i=0;i<ROWS; i++) {
            for (int j=0; j< COLUMNS; j++) {
                if (mineFound[i][j] == true) {
                    labels[i][j].setText("BOOM!"); 
                    
                }
            }
        }
    }
    
}
