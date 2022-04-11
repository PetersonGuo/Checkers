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
        createTiles(0);
        
        for(int j = 1; j < 9; j++)
        {
            if(j > 3 && j < 6) continue;
            for(int i = 1; i < 5 ; i++)
            {
                int offset = j % 2 == 0 ? 0 : TILE_SIZE;
                Color color = j > 5 ? pieceColor2 : pieceColor1;
                Piece piece = new Piece(color);
                addObject(piece, TILE_SIZE * i * 2 + offset - TILE_SIZE * 3/2 - 1, TILE_SIZE * j - TILE_SIZE/2);
            }
        }
        
        game = new Checkers();
    }
    
    public void createTiles(int it)
    {
        if(it > 63)return;
        Color color = it % 2 == 0 ? tileColor1:tileColor2;
        if((it/8)%2 == 1) color = oppColor(color);
        Tile tile = new Tile(color);
        addObject(tile, TILE_SIZE * (it % 8) + TILE_SIZE/2, TILE_SIZE * (it/8) + TILE_SIZE / 2);
        createTiles(it+1);
    }
    
    public Color oppColor(Color curr)
    {
        if(curr.equals(tileColor1))return tileColor2;
        return tileColor1;
    }
}