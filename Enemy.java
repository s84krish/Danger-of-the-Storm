import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import javax.swing.*;



public class Enemy { 
  
  private int x, y; 
  private int width, height; 
  
  Rectangle hitbox;
  
  
  private BufferedImage enemy;
  
   public Enemy(Player p) { 
     loadSprite();
     this.x = generateCoordinateX(p);
     this.y = generateCoordinateY(p);
     this.width=25;
     this.height=75;
     this.hitbox = new Rectangle(this.x, this.y, this.width, this.height);
     System.out.println("x: " + this.x + " y: " + this.y);
   }
   
   public void loadSprite() {
     try {
     enemy = ImageIO.read(new File("enemy.png"));

     } catch(Exception e) { 
       System.out.println("error loading sprite");   
     }
   }
 
   
   public void draw(Graphics g, int numEnemies) {
     //g.drawImage(enemy,this.x,this.x,null);
     for (int i = 0; i < numEnemies; i++){
       g.fillRect(this.x,this.y,this.width,this.height);
     }
   }
   
   public void update(Player p) { 
     /**if (playerCollision(p)){
       System.out.println("player collision");
     }
     else if(enemyCollision(e)){
       System.out.println("Enemy collision");
     }
     else{
     }
     **/
       this.x=this.x + p.getXDirection() + isCloseX(p);
       this.y=this.y + p.getYDirection() + isCloseY(p);
     
     
     
     /*
     if (p.getX() > p.getPlayerX()){ //player moving left
       this.x = p.getX()+ this.gapX;
     }
     if((p.getX()+p.backgroundW)<(p.getPlayerX()+90)){ //player moving right
       this.x = p.getX()+ this.gapX;
     }
     
     if (p.getY() > p.getPlayerY()){ //player moving up
       this.y = p.getY() + this.gapY;
     }
     if ((p.getY()+p.backgroundH)<(p.getPlayerY()+115)){ //player moving down
       this.y = p.getY() + this.gapY;
     }
     */
     
   }
   
   public int isCloseX(Player p){
     //horiztonal
     if ((this.x - p.getPlayerX() < 250) && (this.x - p.getPlayerX() >= 0) && (((this.y - p.getPlayerY() < 262) && (this.y - p.getPlayerY() >= 0)) ||((p.getPlayerY() - this.y < 262) && (p.getPlayerY() - this.y >= 0)))){
       if(this.x+12==p.getPlayerX()+50){
         return 0;
       }
       else{
         return (-4);
       }
     }
     else if ((p.getPlayerX() - this.x < 250) && (p.getPlayerX() - this.x >= 0) && (((this.y - p.getPlayerY() < 262) && (this.y - p.getPlayerY() >= 0)) ||((p.getPlayerY() - this.y < 262) && (p.getPlayerY() - this.y >= 0)))){
       if(this.x+12==p.getPlayerX()+50){
         return 0;
       }
       else{
         return (4);
       }
     }
     return 0;
   }
    
   public int isCloseY(Player p){
     //vertical
     if ((this.y - p.getPlayerY() < 262) && (this.y - p.getPlayerY() >= 0) && ((p.getPlayerX() - this.x < 250) && (p.getPlayerX() - this.x >= 0) ||(p.getPlayerX() - this.x < 250) && (p.getPlayerX() - this.x >= 0))){
       if(this.y+32==p.getPlayerY()+62){
         return 0;
       }
       else{
         return (-4);
       }
     }
     else if ((p.getPlayerY() - this.y < 262) && (p.getPlayerY() - this.y >= 0) && ((p.getPlayerX() - this.x < 250) && (p.getPlayerX() - this.x >= 0) ||(p.getPlayerX() - this.x < 250) && (p.getPlayerX() - this.x >= 0))){
       if(this.y+32==p.getPlayerY()+62){
         return 0;
       }
       else{
         return (4);
       }
     }
     
     return 0;
     
   }
   
   public int generateCoordinateX(Player p){
     int xCoord = (int) (Math.random()*(p.backgroundW-p.getX()))+1;
     return xCoord;
   }
   
   public int generateCoordinateY(Player p){
     int yCoord = (int) (Math.random()*(p.backgroundH-p.getY()))+1;
     return yCoord;
   }
 
   public boolean enemyCollision(Enemy e){
     if (this.hitbox.intersects(e.getHitbox())){
       return true;
     }
     return false;
   }

   public boolean playerCollision(Player p){
     if (this.hitbox.intersects(p.getHitbox())){
       return true;
     }
     return false;
   }

   public Rectangle getHitbox(){
     return this.hitbox;
   }

   public int getX(){
     return this.x;
   }
   
   public int getY(){
     return this.y;
   }
   

   
 }