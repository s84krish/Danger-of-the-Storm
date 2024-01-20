import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import javax.swing.*;


public class Player { 
  private int x, y; 
  private int playerX, playerY;
  private int xdirection,ydirection;  
  static public int playerW;
  static public int playerH;
  static public int backgroundW;
  static public int backgroundH;
  static public final int TOP = 0;
  static public final int RIGHT = 2000;
  static public final int BOTTOM = 1333;
  static public final int LEFT = 0;
  
  Rectangle hitbox;
  
  private BufferedImage sprite;
  private BufferedImage background;
  
   public Player() { 
     loadSprite();
     this.x=0;
     this.y=0;
     this.playerX = 500;
     this.playerY = 768/2;
     this.xdirection=0;
     this.ydirection=0; 
     this.hitbox = new Rectangle(this.playerX, this.playerY, playerW, playerH);
   }
   
   public void loadSprite() {
     try {
     sprite = ImageIO.read(new File("sprite.png"));
     background = ImageIO.read(new File("background.png"));

     } catch(Exception e) { 
       System.out.println("error loading sprite");   
     }
     
       playerW = sprite.getWidth();
       playerH = sprite.getHeight();
       backgroundW = background.getWidth();
       backgroundH = background.getHeight();
       
   }
 
   
   public void draw(Graphics g) {
     g.drawImage(background,x,y,null);
     g.drawImage(sprite,playerX,playerY,null);
   }
   
   public void update() { 
     this.x=this.x+this.xdirection;
     this.y=this.y+this.ydirection;  
     
     if (this.x > this.playerX){
       this.x = this.playerX;
     }
     if((this.x+backgroundW)<(this.playerX+90)){
       this.x = this.playerX+90-backgroundW;
     }
     
     if (this.y > this.playerY){
       this.y = this.playerY;
     }
     if ((this.y+backgroundH)<(this.playerY+115)){
       this.y = this.playerY+115-backgroundH;
     }
        
   }
   
   
   public void setXDirection(int xdirection){
     this.xdirection = xdirection;
   }
   
   public void setYDirection(int ydirection){
     this.ydirection = ydirection;
   }
   
   public int getXDirection(){
     return this.xdirection;
   }
   
   public int getYDirection(){
     return this.ydirection;
   }
   
   public int getPlayerX(){
     return this.playerX;
   }
   
   public int getPlayerY(){
     return this.playerY;
   }
 
   public int getX(){
     return this.x;
   }
   
   public int getY(){
     return this.y;
   }
   
   public Rectangle getHitbox(){
     return this.hitbox;
   }

   
 }