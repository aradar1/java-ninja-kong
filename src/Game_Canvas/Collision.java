/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Canvas;

import Abstracts.Sprite;

/**
 *
 * @author Acer
 */
public class Collision {
    
    private Collision(){
        
    }
    
    public static String getCollisionDirection(Sprite collider, Sprite toCollide){
        int toCollideHeight = toCollide.getHeight();
        int toCollideWidth = toCollide.getWidth();
        
        int colliderHeight = collider.getHeight();
        int colliderWidth = collider.getWidth();
        
//        System.out.println("Naruto Y= " + collider.getVirtualY());
//        System.out.println("Naruto X + H = " + (collider.getVirtualX() + colliderWidth));
//        System.out.println("Wall Y + H = " + (toCollide.getVirtualY() + toCollideHeight));
//        System.out.println("Wall X = " + toCollide.getVirtualX());
            
        if(Math.abs(collider.getVirtualX() - (toCollide.getVirtualX()+toCollideWidth)) <= 6 && (toCollide.getVirtualY() - colliderHeight <= collider.getVirtualY() && (toCollide.getVirtualY() + toCollideHeight) >= collider.getVirtualY()))
            return "left";
        
        if(Math.abs(collider.getVirtualX() + colliderWidth - toCollide.getVirtualX()) <= 6 && (toCollide.getVirtualY() - colliderHeight <= collider.getVirtualY() && (toCollide.getVirtualY() + toCollideHeight) >= collider.getVirtualY()))
            return "right";
        
        if(Math.abs(collider.getVirtualY() - (toCollide.getVirtualY()+toCollideHeight)) <= 6 && (toCollide.getVirtualX() - colliderWidth <= collider.getVirtualX() && (toCollide.getVirtualX() + toCollideWidth) >= collider.getVirtualX()))
            return "up";
        
        if(Math.abs(collider.getVirtualY() + colliderHeight - toCollide.getVirtualY()) <= 6 && (toCollide.getVirtualX() - colliderWidth <= collider.getVirtualX() && (toCollide.getVirtualX() + toCollideWidth) >= collider.getVirtualX()))
            return "down";
        
        return null;
    }
    
    public static boolean hasCollide(Sprite sprite1, Sprite sprite2){
        return sprite1.getBounds().intersects(sprite2.getBounds());
    }
}
