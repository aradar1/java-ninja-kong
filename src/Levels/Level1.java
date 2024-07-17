/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Levels;

import Abstracts.LevelCreator;
import Models.Flag;
import Models.Kunai;
import Models.Ladder;
import Models.Platform;
import Models.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CJ
 */
public class Level1 extends LevelCreator{
    private int[][] platformLocation = {
        {0,400},
        {0,288},
        {0,176}
    };
    private int[][] ladderLocation = {
        {400,289},
        {60,177},
    };
    private int[][] flagLocation = {
        {350, 362},
        {90, 250},
        {400, 138},
    };

    @Override
    public void createObjects() {        
        // Create walls
        for(int[] location : platformLocation)
            platforms.add(new Platform(location[0], location[1], "Images/Wall/1"));               
        
        // Create ladders
        for(int[] location : ladderLocation)
            ladders.add(new Ladder(location[0], location[1], "Images/Ladder/1"));
        
        // Create flags
        for(int[] location : flagLocation)
            flags.add(new Flag(location[0], location[1], "Images/Flag/1"));
        
        // Create kunais
        kunais.add(new Kunai(500, 385, "Images/Kunai/2", "right", 9));
        kunais.add(new Kunai(0, 275, "Images/Kunai/1", "left", 10));
        kunais.add(new Kunai(190, 160, "Images/Kunai/2", "right", 11));
        kunais.add(new Kunai(500, 160, "Images/Kunai/2", "right", 11));
    }

    @Override
    public void drawObjects(Graphics g, ImageObserver ob) {
        for(Platform platform : platforms)
            g.drawImage(platform.getImg(), platform.getVirtualX(), platform.getVirtualY(), ob);
        
        for(Ladder ladder : ladders)
            g.drawImage(ladder.getImg(), ladder.getVirtualX(), ladder.getVirtualY(), ob);
        
        for(Kunai kunai : kunais)
            g.drawImage(kunai.getImg(), kunai.getVirtualX(), kunai.getVirtualY(), ob);
        
        for(Flag flag : flags)
            g.drawImage(flag.getImg(), flag.getVirtualX(), flag.getVirtualY(), ob);
    }

    @Override
    public void drawPlayer(Player p, Graphics g, ImageObserver ob) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("P" +p.getId() , p.getVirtualX()+7, p.getVirtualY());
        if(!p.isDead())
            g.drawImage(p.getImg()[p.getImageArrayIndex()], p.getVirtualX(), p.getVirtualY(), ob);
        
        else
            g.drawImage(p.getImg()[p.getImg().length-1], p.getVirtualX(), p.getVirtualY()+15, ob);
    }
}
