/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;


public class MinesweeperController implements ActionListener {
    
    private MinesweeperPanel minePanel;
    private MinesweeperFrame fr;
    private JMenuItem it = new JMenuItem();
    private JMenuItem easy = new JMenuItem("Novice");
    private JMenuItem medium = new JMenuItem("Intermediate");
    private JMenuItem hard = new JMenuItem("Nightmare");
    private JPopupMenu difficulty = new JPopupMenu();
    private JFileChooser file = new JFileChooser();
    
    public MinesweeperController(MinesweeperPanel panel, MinesweeperFrame fr, JMenuItem item) {
        minePanel = panel;
        this.fr = fr;
        it = item;
        difficulty.add(easy);
        difficulty.add(medium);
        difficulty.add(hard);   
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {    
        difficulty.show(minePanel, 0, 0); 
        
        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                minePanel.setDifficulty(easy);
                
            }
        });
        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                minePanel.setDifficulty(medium);
            }
        });
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent events) {
                minePanel.setDifficulty(hard);
            }
        });
        
        if (it.getText().equals("Save")) {
            int value = file.showSaveDialog(null);
            
            if (value == JFileChooser.APPROVE_OPTION) {
                File saveFile = file.getSelectedFile();
                try {
                    ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(saveFile));
                    output.writeObject(fr);
                    output.close();
                }catch(Exception exc) {
                    System.out.println("Could not Save file");
                }
            }
        }
        
        if (it.getText().equals("Load")) {
            int returnValue = file.showOpenDialog(fr);
            
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = file.getSelectedFile();
                try {
                    ObjectInputStream o = new ObjectInputStream(new FileInputStream(selectedFile));
                    JFrame frame = (JFrame) o.readObject();
                    o.close();
                    
                    frame.setVisible(true);
                    fr.dispose();
                    
                }catch(Exception ex) {
                    System.out.println("Could not retrive save file.");
                }
            }
        }
    }
    
    
    
    
}
