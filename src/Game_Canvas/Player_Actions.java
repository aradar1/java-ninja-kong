/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Canvas;

// This class will implement all the actions will be done by the player

import Models.Player;
import Interfaces.IAction;
import Physics.Jump;

public class Player_Actions implements IAction<Player>{
    
    public Player_Actions(){
    }

    @Override
    public void moveUp(Player p){
        
        if(p.canClimb()){
            if ((p.getImageArrayIndex() >= 0) && (p.getImageArrayIndex() <= 3)){
                p.setImageArrayIndex(p.getImageArrayIndex()+1);
                if(p.getImageArrayIndex() == 4)
                    p.setImageArrayIndex(0);
            }
            else
                p.setImageArrayIndex(0);
            p.setVirtualY(p.getVirtualY()-5);
            p.setInitialPos(p.getVirtualY());
        }
        
        // Clear all collision detection after a single player action
        p.clearCollision();
        p.setCanClimb(false);    
       
    }
    
    @Override
    public void moveDown(Player p){
        if(p.isClimbing()){
            if ((p.getImageArrayIndex() >= 4) && (p.getImageArrayIndex() <= 7)){
                p.setImageArrayIndex(p.getImageArrayIndex()+1);
                if(p.getImageArrayIndex() == 8)
                    p.setImageArrayIndex(4);
            }
            else
                p.setImageArrayIndex(4);
            p.setVirtualY(p.getVirtualY()+5);
        }
        // Clear all collision detection after a single player action
        p.clearCollision();
        p.setCanClimb(false);        
    }
    
    @Override
    public void moveLeft(Player p){
        if(!p.isClimbing()){
            if ((p.getImageArrayIndex() >= 8) && (p.getImageArrayIndex() <= 11)){
                p.setImageArrayIndex(p.getImageArrayIndex()+1);
                if(p.getImageArrayIndex() == 12)
                    p.setImageArrayIndex(8);
            }
            else
                p.setImageArrayIndex(8);
            p.setVirtualX(p.getVirtualX()-5);
            p.setFacingDirection("left");
        }
        // Clear all collision detection after a single player action
        p.clearCollision();
        p.setCanClimb(false);
    }
    
    @Override
    public void moveRight(Player p){
        if(!p.isClimbing()){
            if ((p.getImageArrayIndex() >= 12) && (p.getImageArrayIndex() <= 15)){
                p.setImageArrayIndex(p.getImageArrayIndex()+1);
                if(p.getImageArrayIndex() == 16)
                    p.setImageArrayIndex(12);
            }
            else
                p.setImageArrayIndex(12);
            p.setVirtualX(p.getVirtualX()+5);
            p.setFacingDirection("right");
        }
        // Clear all collision detection after a single player action
        p.clearCollision();
        p.setCanClimb(false);
    }

    @Override
    public void jump(Player p, int gameMode) {
        if(p.canJump() && !p.canClimb() && !p.isClimbing())
            new Thread(new Jump(p, gameMode)).start();
    }
}
