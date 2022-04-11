import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends Actor
{
    private GreenfootImage image;
    private Color color;
    
    private MouseInfo mouse;
    private boolean hovering = false;
    
    public Tile(Color color){
        this.color = color;
        image = new GreenfootImage(GameWorld.TILE_SIZE + 1, GameWorld.TILE_SIZE + 1);
        drawTile(color, hovering);
    }
    
    /**
     * Act - do whatever the Tile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // mouse = Greenfoot.getMouseInfo();
        // if(Greenfoot.mouseMoved(this)){
            // hovering = true;
            // // if(!soundPlayed){ //adds sound
                // // moveSound.play();
                // // soundPlayed = true;
            // // }
            
            // drawTile(color, hovering);
        // }
        // if(Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)){
            // hovering = false;
            // // soundPlayed = false;
            
            // drawTile(color, hovering);
        // }
    }
    
    public void drawTile(Color color, boolean lit){
        image.clear();
        image.setColor(color);
        image.fill();
        if(lit){
            image.setColor(Color.GREEN);
            image.fill();
        }
        setImage(image);
    }
    
    public Color getColor() {return color;}
}
