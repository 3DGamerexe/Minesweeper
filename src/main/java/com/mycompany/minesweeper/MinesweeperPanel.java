
package com.mycompany.minesweeper;

import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Minesweeper Panel
 * 
 * @author Aaliyah M
 */
public class MinesweeperPanel extends JPanel {
    
    private MinesweeperFrame mineF;
    private GridLayout layout = new GridLayout(15,15);
    private MineModel model = new MineModel(layout); 
    private JMenuItem currentLevel;
    int numMines;    
    private boolean currentEasy, currentMedium, currentHard = false;
    Random rand = new Random(); 
    
  
    public MinesweeperPanel(MinesweeperFrame mf) {       
        mineF = mf;     
        setLayout(layout);          
        GridSetup();   
        JMenuItem defaultLevel = new JMenuItem("Intermediate");
        currentLevel = defaultLevel;
        //numberofMines(defaultLevel);
        setDifficulty(defaultLevel);
        //model.placeMines(numMines);
        mouse();
        
    } 
    
    public void GridSetup() {
        
        for (int i=0;i<layout.getRows();i++) {
            for (int j=0;j<layout.getColumns();j++) {
                model.labels[i][j] = new JLabel("?"); 
                add(model.labels[i][j]);
            }
        }
        
    }
    
    int numberofMines(JMenuItem level) {
               
        if (level.getText().equals("Novice")){
            currentEasy = true;
            numMines = ThreadLocalRandom.current().nextInt(5,9);
            System.out.println("Novice " + numMines);
        }
        
        else if (level.getText().equals("Intermediate")) {
            currentMedium =  true; 
            numMines = rand.nextInt(11) + 15;
        }
        else if (level.getText().equals("Nightmare")) {
            currentHard = true;
            numMines = rand.nextInt(16) + 30;
        }
        System.out.println(numMines);
        
        return numMines;
    }
   
    
    public void setDifficulty(JMenuItem level) {
        currentLevel = level;
        if (level.getText().equals("Novice")) {
            layout = new GridLayout(7,7);  
            setLayout(layout);         
            removeAll();
            GridSetup();  
            numMines = numberofMines(level);                 
        }
        else if (level.getText().equals("Intermediate")) {
            layout = new GridLayout(10,10);  
            setLayout(layout);         
            removeAll();
            GridSetup();  
            numMines = numberofMines(level);                 
        }
        else if (level.getText().equals("Nightmare")) {
            layout = new GridLayout(15,15);  
            setLayout(layout);         
            removeAll();
            GridSetup();  
            numMines = numberofMines(level);                 
        }
        //System.out.println(numMines);
        model.placeMines(numMines);
        mouse();       
        revalidate();
        repaint();
        //mineF.mineCount.setText("Number of Mines: " + numMines);
    }
    
    private void mouse() {
        
        for (int i=0;i<layout.getRows();i++) {
            for (int j=0;j<layout.getColumns();j++) {
                final int finalI = i;
                final int finalJ = j;
                
                model.labels[i][j].addMouseListener(new MouseAdapter() {
                   @Override
                   public void mouseClicked(MouseEvent e) {                     
                       if (model.mineFound[finalI][finalJ] == true) {
                           model.labels[finalI][finalJ].setText("BOOM!");                 
                           model.revealAllMines();
                                                
                       }
                       else {
                           int adjMines = model.getAdjacentMines(finalI, finalJ);
                           model.labels[finalI][finalJ].setText(Integer.toString(adjMines));
                       }
                   }
                });
            }
        }
    }
    
    public void reset() {
        for (int i=0; i < layout.getRows(); i++) {
            for (int j=0; j < layout.getColumns(); j++) {
                if (!(model.labels[i][j].getText() == "?")) {
                    model.labels[i][j].setText("?");
                    model.clearMines();
//                    numMines = numberofMines(currentLevel);
//                    numMines = rand.nextInt(5,20);                  
                }
            }
        }
        model.placeMines(numberofMines(currentLevel));
        mineF.mineCount.setText("Number of Mines: " + numMines);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    }
}
