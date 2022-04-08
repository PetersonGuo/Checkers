import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorld extends World
{
    public static final int WORLD_WIDTH = 400;
    public static final int WORLD_HEIGHT = 400;
    
    /**
     * Constructor for objects of class GameWorld.
     * 
     */
    public GameWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        
        setBackground(drawBackground());
    }
    
    public static GreenfootImage drawBackground(){
        GreenfootImage bg = new GreenfootImage(WORLD_WIDTH, WORLD_HEIGHT);
        bg.clear();
        
        bg.setColor(Color.BLACK);
        bg.fill();
        bg.setColor(Color.WHITE);
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 4; j++){
                if(i % 2 == 0) bg.fillRect(j * 2 * WORLD_WIDTH / 8, i * WORLD_HEIGHT / 8, WORLD_WIDTH / 8, WORLD_HEIGHT / 8);
                else bg.fillRect(j * 2 * WORLD_WIDTH / 8 + WORLD_WIDTH / 8, i * WORLD_HEIGHT / 8, WORLD_WIDTH / 8, WORLD_HEIGHT / 8);
            }
        }
        
        return bg;
    }
}