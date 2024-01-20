// class for the the game area - This is where all the drawing of the screen occurs

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.util.Arrays;

public class GameAreaPanelOOP extends JPanel {
  
  Player player;
  Bullet bullet;
  Enemy[] enemy = new Enemy[5];

  GameAreaPanelOOP() {
    
    //Game Object Initialization
    player = new Player();
    bullet = new Bullet();
    for (int i = 0; i < enemy.length; i++){
      enemy[i] = new Enemy(player);
    }
    
    //Listeners
    PlayerKeyListener keyListener = new PlayerKeyListener(player, bullet);
    this.addKeyListener(keyListener);
    
    MyMouseListener mouseListener = new MyMouseListener();
    this.addMouseListener(mouseListener);
    
    //JPanel Stuff
    this.setFocusable(true);
    this.requestFocusInWindow(); 
    

    //Start the game loop in a separate thread (allows simple frame rate control)
    //the alternate is to delete this and just call repaint() at the end of paintComponent()
    Thread t = new Thread(new Runnable() { public void run() { animate(); }}); //start the gameLoop 
    t.start();
    
  }
  
  //The main gameloop - this is where the game state is updated
  public void animate() { 
    
    while(true){
      
      //update game content
      player.update();  //can do this in more than one way
      bullet.shoot(player);
      for (int i = 0; i<enemy.length; i++){
        enemy[i].update(player);
      }
      
      //delay
      try{ Thread.sleep(10);} 
      catch (Exception exc){
        System.out.println("Thread Error");
      }
      
      //repaint request
      this.repaint();
    }    
  }
  
  // paintComponnent Runs everytime the screen gets refreshed
  public void paintComponent(Graphics g) {   
    super.paintComponent(g); //required
    setDoubleBuffered(true); 
    
    //screen is being refreshed - draw all objects
    player.draw(g);
    bullet.drawBullet(g);
    for (int i = 0; i<enemy.length; i++){
        enemy[i].draw(g,5);
      }
    
  }   
}
