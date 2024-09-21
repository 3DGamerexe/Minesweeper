
package com.mycompany.minesweeper;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.*;

public class GameOverPanel extends JFrame{
    
    private JFrame panel;
    
    public GameOverPanel() {
        setTitle("Minesweeper Mania!!!!");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
    }
}
