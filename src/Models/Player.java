/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Sprite_Type.Sprite_Animated;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author CJ
 */
public class Player extends Sprite_Animated{
    private List<String> collisions = new ArrayList();
    private int id;
    private int initialPos;
    private boolean dead;
    private boolean climbing;
    private boolean canClimb;
    private boolean canJump;
    private boolean jumping;
    private boolean update;
    private String facingDirection = "";
    private int score;
    
    private String[] directions = {"U", "D", "L", "R"};
    // Attributes (probably states, scores, name)

    public Player(int id, int virtualX, int virtualY, String fileLocation, int imageArraySize) {
        super(virtualX, virtualY, fileLocation, imageArraySize);
        this.id = id;
        int index = 0;
        for (String direction : directions) {
            for (int f = 1; f <= 4; f++)
                img[index++] = new ImageIcon(fileLocation + "/" + direction + f + ".png").getImage();
            img[index]= new ImageIcon(fileLocation+"/dead.png").getImage();
        }
    }
    
    public void addCollision(String collide){
        if(!collisions.contains(collide))
            collisions.add(collide);
    }
    
    public boolean checkCollision(String collide){
        return collisions.contains(collide);
    }
    
    public void clearCollision(){
        collisions.clear();
    }

    public boolean isClimbing() {
        return climbing;
    }

    public void setClimbing(boolean climbing) {
        this.climbing = climbing;
    }

    public boolean canClimb() {
        return canClimb;
    }

    public void setCanClimb(boolean canClimb) {
        this.canClimb = canClimb;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
    
    public boolean canJump() {
        return canJump;
    }
    
    public boolean isJumping() {
        return jumping;
    }
    
    public void setCanJump(boolean canJump){
        this.canJump = canJump;
    }
    
    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
    
    public void incrementScore(){
        this.score+=5;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public int getCollisionSize(){
        return this.collisions.size();
    }

    public String getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(String facingDirection) {
        this.facingDirection = facingDirection;
    }

    public int getId() {
        return id;
    }

    public void setCollisions(List<String> collisions) {
        this.collisions = collisions;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getCollisions() {
        return collisions;
    }
    
    public void setDirections(String[] directions) {
        this.directions = directions;
    }

    public boolean hasUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public int getInitialPos() {
        return initialPos;
    }

    public void setInitialPos(int initialPos) {
        this.initialPos = initialPos;
    }        

}
