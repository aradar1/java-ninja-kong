/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Canvas;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author R312-Lab
 */
public class Game_Frame extends JFrame{
    
    private JLabel title;
    private Game_Canvas gameCanvas;
    private int x, y;

    public Game_Frame(int gameMode) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Container Pane;
        Pane = getContentPane();
        Pane.setLayout(null);
        x = this.getWidth() / 2;
        y = this.getHeight() / 2;
        title = new JLabel("The JLabel is in the SOUTH area");
        gameCanvas = new Game_Canvas(gameMode);
        gameCanvas.setBounds(x, y, 500, 500);
        Pane.add(gameCanvas);
        setSize(500, 500);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        
    }
}
