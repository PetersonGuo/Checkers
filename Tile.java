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
    
    public Tile(Color color)
    {
        this.color = color;
        image = new GreenfootImage(GameWorld.TILE_SIZE + 1, GameWorld.TILE_SIZE + 1);
        drawTile(color);
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
    
    public void drawTile(Color color)
    {
        image.setColor(color);
        image.fill();
        setImage(image);
    }
    
    public void resetTiles(int it)
    {
        if(it < 40)
            it = 40;
        else if(it == 56 || it == 47)
            it++;
        else if(it > 63)
            return;
        Tile current = (Tile)getWorld().getObjectsAt(GameWorld.TILE_SIZE * (it % 8) + GameWorld.TILE_SIZE/2, GameWorld.TILE_SIZE * (it/8) + GameWorld.TILE_SIZE / 2, Tile.class).get(0);
        current.drawTile(current.getColor());
        resetTiles(it+2);
    }
    
    public static Color oppColor(Color curr)
    {
        if(curr.equals(GameWorld.tileColor1))
            return GameWorld.tileColor2;
        return GameWorld.tileColor1;
    }
    
    public Color getColor() {return color;}
    
    public String toString()
    {
        return "(" + getX() + ", " + getY() + "), " + getColor(); 
    } 
}
