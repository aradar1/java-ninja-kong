/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstracts;

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
public abstract class LevelCreator {
    protected List<Platform> platforms = new ArrayList();
    protected List<Ladder> ladders = new ArrayList();
    protected List<Kunai> kunais  = new ArrayList();
    protected List<Flag> flags = new ArrayList();
    
    
    public List<Platform> getPlatforms() {
        return platforms;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public List<Kunai> getKunais() {
        return kunais;
    }

    public List<Flag> getFlags() {
        return flags;
    }
    
    public abstract void createObjects();
    public abstract void drawObjects(Graphics g, ImageObserver ob);
    public abstract void drawPlayer(Player p, Graphics g, ImageObserver ob);
}
