import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Piece here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Piece extends Actor
{
    private GreenfootImage image;
    public final int PIECE_SIZE = GameWorld.WORLD_WIDTH / 10;
    public final int TILE_SIZE = GameWorld.WORLD_WIDTH / 8;
    public Color color;
    
    private MouseInfo mouse;
    private boolean hovering = false;
    private boolean clicked = false;
    
    private ArrayList<Tile> availableTiles;
    
    public Piece(Color color){
        this.color = color;
        image = new GreenfootImage(PIECE_SIZE + 1, PIECE_SIZE + 1);
        drawPiece(color);
    }
    
    /**
     * Act - do whatever the Pieces wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        mouse = Greenfoot.getMouseInfo();
        Tile tile = (Tile)getOneIntersectingObject(Tile.class);
        if(Greenfoot.mouseClicked(this)){
            hovering = false;
            clicked = true;
            tile.drawTile(tile.getColor(), clicked);
            availableTiles = new ArrayList<Tile>();
            check();
        }
        else if(clicked){
            for(Tile availableTile: availableTiles){
                if(Greenfoot.mouseClicked(availableTile)){
                    this.setLocation(availableTile.getX(), availableTile.getY());
                    clicked = false;
                    createBoard();
                }
                else if(Greenfoot.mouseClicked(this)) clicked = false;
            }
        }
        else if(!clicked){
            if(Greenfoot.mouseMoved(this)){
                hovering = true;
                // if(!soundPlayed){ //adds sound
                    // moveSound.play();
                    // soundPlayed = true;
                // }
                
                tile.drawTile(tile.getColor(), hovering);
            }
            if(Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)){
                hovering = false;
                // soundPlayed = false;
                
                tile.drawTile(tile.getColor(), hovering);
            }
        }
    }
    
    private void drawPiece(Color color){
        image.clear();
        image.setColor(color);
        image.fillOval(1, 1, PIECE_SIZE, PIECE_SIZE);
        setImage(image);
    }
    
    private void check(){
        if(getY() - TILE_SIZE > 0){
            if(getX() - TILE_SIZE > 0){
                List piecePresent = getWorld().getObjectsAt(getX() - TILE_SIZE, getY() - TILE_SIZE, Piece.class);
                if(piecePresent.size() == 0){
                    List tilesPresent = getWorld().getObjectsAt(getX() - TILE_SIZE, getY() - TILE_SIZE, Tile.class);
                    availableTiles.add((Tile)tilesPresent.get(0));
                }
                else if(((Piece)(piecePresent.get(0))).getColor() != color)
                {
                    if(getX() - 2 * TILE_SIZE > 0){
                        if(getY() - 2 * TILE_SIZE > 0){
                            piecePresent = getWorld().getObjectsAt(getX() - 2 * TILE_SIZE, getY() - 2 * TILE_SIZE, Piece.class);
                            if(piecePresent.get(0) == null){
                                List tilesPresent = getWorld().getObjectsAt(getX() - 2 * TILE_SIZE, getY() - 2 * TILE_SIZE, Tile.class);
                                availableTiles.add((Tile)tilesPresent.get(0));
                            }
                        }
                    }
                }
            }
            
            if(getX() + TILE_SIZE < GameWorld.WORLD_WIDTH){
                
            }
        }
        for(Tile tile: availableTiles){
            tile.drawTile(tile.getColor(), true);
        }
    }
    
    private char[][] createBoard()
    {
        char[][] board = GameWorld.game.getBoard();
        int initial = TILE_SIZE/2;
        for(int x = 0; x < 8; x++)
        {
            for(int y = 0; y < 8; y++)
            {
                List piecePresent = getWorld().getObjectsAt(initial + TILE_SIZE * x, initial + TILE_SIZE * y, Piece.class);
                if(piecePresent.get(0).getColor().equals(GameWorld.pieceColor1))
                {
                    board[x][y] = 'w';
                }
                else if(piecePresent.get(0).getColor().equals(GameWorld.pieceColor2))
                {
                    board[x][y] = 'r';
                }
                else board[x][y] = 'n';
            }
        }
        return board;
    }
    
    public Color getColor() {return color;}
}
