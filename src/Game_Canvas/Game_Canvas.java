/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Canvas;

import Abstracts.LevelCreator;
import Database.DBCaller;
import Database.PlayerRepository;
import Models.Player;
import Events.Game_Events;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import Interfaces.IAction;
import Levels.*;
import Models.Flag;
import Models.Kunai;
import Models.Ladder;
import Models.Platform;
import java.awt.Font;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author R312-Lab
 */
public class Game_Canvas extends JPanel{

    // Thread objects
    private final Timer timer;
    private final TimerTask gameCanvasTask;
    private final TimerTask kunaiTask;
    private final TimerTask gameTimerTask;
    private TimerTask movementTask;
    
    
    // Player objects
    private Player p;
    private DBCaller dbCall = new DBCaller();
    private List<String> tempCollisions;
    
    // Level objects
    private int gameMode;
    private List<Integer> keysPressed;
    private Game_Events events;
    private int gameTimer = 60;
    private int score;
    private boolean levelCleared;
    private LevelCreator levelCreator;
    
    
    public Game_Canvas(int gameMode) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.gameMode = gameMode;
        p = new Player(1, 50, 350, "Images/P1", 17);
        p.setInitialPos(p.getVirtualY());
        levelCreator = new Level1();
        levelCreator.createObjects();
        
        
        timer = new Timer(true);
        keysPressed = new ArrayList();
        events = new Game_Events(this);
        
        
        gameCanvasTask = new TimerTask(){
            @Override
            public void run() {
                try {
                    // UPDATE SCREEN
                    
                    if(gameMode == 2){
                        if(dbCall.checkId(new PlayerRepository(), p.getId()) <= 0)
                            dbCall.createPlayer(new PlayerRepository(), p);
                        else
                            dbCall.updatePlayer(new PlayerRepository(), p);

                        p = dbCall.getPlayer(new PlayerRepository(), p);
                        if(!p.isJumping())
                            p.setVirtualY(p.getInitialPos());
                    }
                    System.out.println(p.getVirtualX() + ", "+ p.getVirtualY());
                    repaint();
                    
                    playerCollsion(p);
                    if(p.isDead() || levelCleared){
                        movementTask.cancel();
                        kunaiTask.cancel();
                        gameTimerTask.cancel();
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(Game_Canvas.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        
        movementTask = new TimerTask(){
            @Override
            public void run() {
                try {
                    keyPressed(new Player_Actions()); // Actions for players
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(Game_Canvas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        kunaiTask = new TimerTask(){
            @Override
            public void run() {
                kunaiMove();
            }
        };
        gameTimerTask = new TimerTask(){
            @Override
            public void run() {
                gameTimer--;
            }  
        };
        
        timer.schedule(gameCanvasTask, 0,120);
        timer.schedule(movementTask, 0, 120);
        timer.schedule(kunaiTask, 0, 75);
        timer.schedule(gameTimerTask, 0, 1000);
        setFocusable(true);
        setBackground(Color.BLACK);
        addKeyListener(events);
    }

    // Behaviors
    private void playerCollsion(Player player) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        if(keysPressed.size() > 0){
            for(Ladder ladder : levelCreator.getLadders()){
                if(Collision.hasCollide(player, ladder)){
                    player.setCanClimb(true);
                    player.setCanJump(false);
                }
            }
            for(Platform platform : levelCreator.getPlatforms()){
                String direction = Collision.getCollisionDirection(player, platform);
                if(direction == null){
                    // continue
                }
                else if(direction.equals("down")){
                    player.setClimbing(false);
                    player.setCanJump(true);
                }
                player.addCollision(direction);
            }

            for(Flag flag : levelCreator.getFlags()){
                if(Collision.hasCollide(player, flag)){
                    levelCreator.getFlags().remove(flag);
                    score++;
                    if(levelCreator.getFlags().isEmpty())
                        levelCleared = true;
                    break;
                }
            }
            p.setUpdate(true);
        }
        for(Kunai kunai : levelCreator.getKunais()){
            if(Collision.hasCollide(player, kunai) || gameTimer <= 0){
                // Uncomment to implement death
                player.setDead(true);
            }
        }
    }
    
    private void kunaiMove(){
        for(Kunai kunai : levelCreator.getKunais())
            kunai.move();
    }
    
    public void addGameAction(int keyCode){
        if(!keysPressed.contains(keyCode))
            keysPressed.add(keyCode);
    }
    
    public void removeGameAction(int keyCode){
        for(int i=0; i<this.keysPressed.size(); i++)
            if(this.keysPressed.get(i) == keyCode)
                keysPressed.remove(i);
    }
    
    public void keyPressed(IAction entity_Action) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        if(keysPressed.size() <= 0)
            return;
        for(int i=0; i<this.keysPressed.size(); i++){
            
            /***** PLAYER 1 CONFIGURATION *****/
            if(!p.isDead() && p.getId() == 1){
                if(keysPressed.get(i) == KeyEvent.VK_S && !p.checkCollision("down"))
                    entity_Action.moveDown(p);

                if(keysPressed.get(i) == KeyEvent.VK_W)
                    entity_Action.moveUp(p);
                

                if(keysPressed.get(i) == KeyEvent.VK_D && !p.checkCollision("right"))
                    if(p.getVirtualX()+p.getWidth() < 500)
                        entity_Action.moveRight(p);

                if(keysPressed.get(i) == KeyEvent.VK_A && !p.checkCollision("left"))
                    if(p.getVirtualX() > 0)
                        entity_Action.moveLeft(p);
                
                if(keysPressed.get(i) == KeyEvent.VK_SPACE)
                    entity_Action.jump(p, gameMode);
            }            
            
            /***** PLAYER 2 CONFIGURATION *****/
            if(!p.isDead() && p.getId() == 2){
                if(keysPressed.get(i) == KeyEvent.VK_DOWN && !p.checkCollision("down"))
                    entity_Action.moveDown(p);

                if(keysPressed.get(i) == KeyEvent.VK_UP)
                    entity_Action.moveUp(p);                

                if(keysPressed.get(i) == KeyEvent.VK_RIGHT && !p.checkCollision("right"))
                    if(p.getVirtualX()+p.getWidth() < 500)
                        entity_Action.moveRight(p);

                if(keysPressed.get(i) == KeyEvent.VK_LEFT && !p.checkCollision("left"))
                    if(p.getVirtualX() > 0)
                        entity_Action.moveLeft(p);
                
                if(keysPressed.get(i) == KeyEvent.VK_NUMPAD0)
                    entity_Action.jump(p, gameMode);
            }
        }
    }
    
        
    public void drawPlayer(Graphics g, Player p){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("P" +p.getId() , p.getVirtualX()+7, p.getVirtualY());
        if(!p.isDead())
            g.drawImage(p.getImg()[p.getImageArrayIndex()], p.getVirtualX(), p.getVirtualY(), this);
        
        else
            g.drawImage(p.getImg()[p.getImg().length-1], p.getVirtualX(), p.getVirtualY()+15, this);
    }    
    
    public void drawScore(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Score: " + score, 17, 17);
    }
    
    public void drawGameOver(Graphics g){
        g.drawImage(new ImageIcon("Images/Level_Complete/1.png").getImage(), this.getWidth()/2-100, this.getHeight()/2-100, this);
    }
    
    public void drawLevelComplete(Graphics g){
        g.drawImage(new ImageIcon("Images/Level_Complete/2.png").getImage(), -10, 0, this);
    }
    
    public void drawTimer(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Time: " + gameTimer, this.getWidth()/2-50, 17);
    }
    
    // Overrides
    @Override
    public void paint(Graphics g) {
        super.paint(g);        
        Image bufferedImage = createImage(getWidth(),getHeight()); // Pre-render the image before drawing to screen
        Graphics doubleBufferGraphics = bufferedImage.getGraphics(); // Graphics that will draw the BufferedImage
        
        /****** START OF RENDERING ******/
        
        if(p.isDead())
            drawGameOver(doubleBufferGraphics);
        else if (levelCleared)
            drawLevelComplete(doubleBufferGraphics);        
        else{
            levelCreator.drawObjects(doubleBufferGraphics, this);
            drawPlayer(doubleBufferGraphics, p);
            drawScore(doubleBufferGraphics);
            drawTimer(doubleBufferGraphics);
        }
        /****** END OF RENDERING ******/
        
        // SHOW THE RENDERED IMAGE TO THE SCREEN
        g.drawImage(bufferedImage, 0, 0, this);
    }
}
