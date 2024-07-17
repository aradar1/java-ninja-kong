/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstracts;

import java.awt.Rectangle;

/**
 *
 * @author CJ
 */
public abstract class Sprite{
    protected int virtualX;
    protected int virtualY;


    public Sprite(int virtualX, int virtualY, String fileLocation) {
        this.virtualX = virtualX;
        this.virtualY = virtualY;

    }

    public int getVirtualX() {
        return virtualX;
    }

    public void setVirtualX(int virtualX) {
        this.virtualX = virtualX;
    }

    public int getVirtualY() {
        return virtualY;
    }

    public void setVirtualY(int virtualY) {
        this.virtualY = virtualY;
    }
    
    public abstract int getWidth();
    public abstract int getHeight();
    public abstract Rectangle getBounds();
}