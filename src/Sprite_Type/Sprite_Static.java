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
public class Sprite_Static extends Sprite{
    protected Image img;

    public Sprite_Static(int virtualX, int virtualY, String fileLocation) {
        super(virtualX, virtualY, fileLocation);
        this.img = new ImageIcon(fileLocation + ".png").getImage();
    }

    public Image getImg() {
        return img;
    }

    @Override
    public int getWidth() {
        return this.img.getWidth(null);
    }

    @Override
    public int getHeight() {
        return this.img.getHeight(null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(virtualX,virtualY, getWidth(),getHeight());
    }
}
