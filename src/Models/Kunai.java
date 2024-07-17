/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Sprite_Type.Sprite_Static;

/**
 *
 * @author Acer
 */
public class Kunai extends Sprite_Static{
    
    private final int speed;    
    private String spawnArea;

    public Kunai(int virtualX, int virtualY, String fileLocation, String spawn_Direction, int speed) {
        super(virtualX, virtualY, fileLocation);
        this.spawnArea = spawn_Direction;
        this.speed = speed;
    }
    
    public void move(){
        
        if (spawnArea.equals("left")){
            if(this.virtualX >= 500)
                this.virtualX = 0; // Reset the position of kunai
            this.virtualX+=speed;
        }
        
        else if (spawnArea.equals("right")){
            if(this.virtualX <= 0)
                this.virtualX = 500; // Reset the position of kunai
            this.virtualX-=speed;
        }
    }
}
