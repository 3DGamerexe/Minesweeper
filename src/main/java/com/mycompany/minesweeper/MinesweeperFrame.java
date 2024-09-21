
package com.mycompany.minesweeper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class MinesweeperFrame extends JFrame{
    
    private MinesweeperPanel panel;
    private JButton restartButton;
    private JMenuItem m1, m2, m3, m4;
    JMenuBar mb = new JMenuBar();
    private JMenu file = new JMenu("File");
    JMenu mineCount;
    
    public MinesweeperFrame() {
        
        setTitle("Minesweeper Mania!!!!");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new MinesweeperPanel(this);
        add(panel);      
      
        setJMenuBar(mb);
       
        mb.add(file);
        
        m1 = new JMenuItem("New");
        m2 = new JMenuItem("Save");
        m3 = new JMenuItem("Load");
        m4 = new JMenuItem("Quit");
        
        file.add(m1);
        file.add(m2);
        file.add(m3);
        file.add(m4);
        
        mineCount = new JMenu("Number of Mines: " + panel.numMines);
        mb.add(mineCount);
        
        MinesweeperController controller = new MinesweeperController(panel, this, m1);
        m1.addActionListener(controller);  
        MinesweeperController saveControl = new MinesweeperController(panel, this, m2);
        m2.addActionListener(saveControl);
        MinesweeperController loadControl = new MinesweeperController(panel, this, m3);
        m3.addActionListener(loadControl);
         
        restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.reset();
                mb.removeAll();
                mb.add(file);
                mineCount.setText("Number of Mines: " + panel.numMines);
                mb.add(mineCount);
            }
        });
        add(restartButton, BorderLayout.SOUTH);
      
        m4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent close) {
                setVisible(false);
                System.exit(0);
            }
        });
        
        setVisible(true);
        
    }   
    
}
