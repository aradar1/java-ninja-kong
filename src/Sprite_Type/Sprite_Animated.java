/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprite_Type;

import Abstracts.Sprite;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author CJ
 */
public class Sprite_Animated extends Sprite{
    protected int imageArrayIndex;
    protected Image[] img;

    public Sprite_Animated(int virtualX, int virtualY, String fileLocation, int imageArraySize) {
        super(virtualX, virtualY, fileLocation);
        
        img = new Image[imageArraySize];
        for(int i=0; i<imageArraySize; i++)
            this.img[i] = new ImageIcon(fileLocation + "/" +(i+1)+ ".png").getImage();
    }
    
    public Image[] getImg() {
        return img;
    }

    public int getImageArrayIndex() {
        return imageArrayIndex;
    }

    public void setImageArrayIndex(int imageArrayIndex) {
        this.imageArrayIndex = imageArrayIndex;
    }

    @Override
    public int getWidth(){
        return this.img[imageArrayIndex].getWidth(null);
    }


    @Override
    public int getHeight() {
        return this.img[imageArrayIndex].getHeight(null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(virtualX,virtualY, getWidth(),getHeight());
    }
}
