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
import java.awt.Graphics;
import java.awt.image.ImageObserver;

/**
 *
 * @author CJ
 */
public class Level3 extends LevelCreator{

    @Override
    public void createObjects() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
