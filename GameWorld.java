import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorld extends World
{
    public static final int WORLD_SIZE = 400;
    public static final int TILE_SIZE = GameWorld.WORLD_SIZE / 8;
    
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
        super(WORLD_SIZE, WORLD_SIZE, 1);
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                Tile tile1 = new Tile(tileColor1);
                Tile tile2 = new Tile(tileColor2);
                Tile tile3 = new Tile(tileColor1);
                Tile tile4 = new Tile(tileColor2);
                addObject(tile1, WORLD_SIZE * j * 2 / 8 + WORLD_SIZE / 16, WORLD_SIZE * i * 2 / 8 + WORLD_SIZE / 16);
                addObject(tile2, WORLD_SIZE * (j * 2 + 1) / 8 + WORLD_SIZE / 16, WORLD_SIZE * i * 2 / 8 + WORLD_SIZE / 16);
                addObject(tile3, WORLD_SIZE * (j * 2 + 1) / 8 + WORLD_SIZE / 16, WORLD_SIZE * (i * 2 + 1) / 8 + WORLD_SIZE / 16);
                addObject(tile4, WORLD_SIZE * j * 2 / 8 + WORLD_SIZE / 16, WORLD_SIZE * (i * 2 + 1) / 8 + WORLD_SIZE / 16);
            }
        }
        
        for(int j = 1; j < 4; j++)
        {
            for(int i = 1; i < 5 ; i++)
            {
                int offset = j % 2 == 0 ? 0 : TILE_SIZE;
                Piece piece = new Piece(pieceColor1);
                addObject(piece, TILE_SIZE * i * 2 + offset  - TILE_SIZE * 3/2, TILE_SIZE * j - TILE_SIZE/2);
            }
        }
        
        for(int j = 1; j < 4; j++)
        {
            for(int i = 1; i < 5; i++)
            {
                int offset = j % 2 == 0 ? 0 : TILE_SIZE;
                Piece piece = new Piece(pieceColor2);
                addObject(piece, TILE_SIZE * i * 2 + offset - TILE_SIZE * 3/2, TILE_SIZE * (j + 5) - TILE_SIZE/2);
            }
        }
        
        game = new Checkers();
    }
}