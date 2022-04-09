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
    
    public static Checkers game;
    
    public static Color tileColor1 = Color.RED;
    public static Color tileColor2 = Color.BLACK;
    public static Color pieceColor1 = Color.BLUE; //AI color
    public static Color pieceColor2 = Color.MAGENTA; //User Color
    
    /**
     * Constructor for objects of class GameWorld.
     * 
     */
    public GameWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                Tile tile1 = new Tile(tileColor1);
                Tile tile2 = new Tile(tileColor2);
                Tile tile3 = new Tile(tileColor1);
                Tile tile4 = new Tile(tileColor2);
                addObject(tile1, WORLD_WIDTH * j * 2 / 8 + WORLD_WIDTH / 16, WORLD_HEIGHT * i * 2 / 8 + WORLD_HEIGHT / 16);
                addObject(tile2, WORLD_WIDTH * (j * 2 + 1) / 8 + WORLD_WIDTH / 16, WORLD_HEIGHT * i * 2 / 8 + WORLD_HEIGHT / 16);
                addObject(tile3, WORLD_WIDTH * (j * 2 + 1) / 8 + WORLD_WIDTH / 16, WORLD_HEIGHT * (i * 2 + 1) / 8 + WORLD_HEIGHT / 16);
                addObject(tile4, WORLD_WIDTH * j * 2 / 8 + WORLD_WIDTH / 16, WORLD_HEIGHT * (i * 2 + 1) / 8 + WORLD_HEIGHT / 16);
            }
        }
        
        for(int j = 1; j < 6; j += 2)
        {
            for(int i = 0; i < 4; i++){
                Piece piece = new Piece(pieceColor2);
                addObject(piece, WORLD_WIDTH * i * 2 / 8 + WORLD_WIDTH / 16 - 1, WORLD_HEIGHT * j / 16 - 1);
            }
        }
        
        for(int j = 11; j < 16; j += 2)
        {
            for(int i = 0; i < 4; i++){
                Piece piece = new Piece(pieceColor2);
                addObject(piece, WORLD_WIDTH * i * 2 / 8 + WORLD_WIDTH / 16 - 1, WORLD_HEIGHT * j / 16 - 1);
            }
        }
        
        game = new Checkers();
    }
}