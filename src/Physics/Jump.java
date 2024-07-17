/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics;

import Database.DBCaller;
import Database.PlayerRepository;
import Models.Player;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CJ
 */
public class Jump implements Runnable{

    private int vJump;
    private int gJump;
    private int currentPos;
    private int initialPos;
    private int MAX_HEIGHT;
    private int JUMP_SPEED;
    private boolean stopJump;
    
    private Player p;
    
    public Jump(Player pl, int gameMode){
        try {
            p = pl;
            if(gameMode == 2){
                initialPos = new DBCaller().getPlayer(new PlayerRepository(), p).getInitialPos();
                MAX_HEIGHT = 11;
                JUMP_SPEED = 50;
            }
            else{
                MAX_HEIGHT = 9;
                JUMP_SPEED = 50;
                initialPos = p.getInitialPos();
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Jump.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
            vJump = -MAX_HEIGHT; //jump value
            gJump = 1;  //gravitation value
            System.out.println(initialPos);
            do{
                p.setCanJump(false);

                if(!stopJump){
                    try {
                        Thread.sleep(JUMP_SPEED);
                        p.setVirtualY(p.getVirtualY()+vJump);
                        vJump += gJump;
                        currentPos = p.getVirtualY();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Jump.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }     

                if(currentPos >= initialPos){
                    p.setInitialPos(initialPos);
                    p.setVirtualY(initialPos);
                    stopJump = true;                    
                }
                p.setJumping(true);
            }while(!stopJump);
            p.setJumping(false);
            //  END JUMP
    }
    
}
