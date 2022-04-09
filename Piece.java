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
    public static int PIECE_WIDTH = GameWorld.WORLD_WIDTH / 10;
    public static int PIECE_HEIGHT = GameWorld.WORLD_HEIGHT / 10;
    private Color color;
    
    private MouseInfo mouse;
    private boolean hovering = false;
    private boolean clicked = false;
    
    private ArrayList<Tile> availableTiles;
    
    public Piece(Color color){
        this.color = color;
        image = new GreenfootImage(PIECE_WIDTH + 1, PIECE_HEIGHT + 1);
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
            check();
            for(Tile availableTile: availableTiles){
                if(Greenfoot.mouseClicked(availableTile)){
                    this.setLocation(availableTile.getX(), availableTile.getY());
                    clicked = false;
                }
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
        image.fillOval(1, 1, PIECE_WIDTH, PIECE_HEIGHT);
        setImage(image);
    }
    
    private void check(){
        if(getY() - Tile.TILE_HEIGHT > 0){
            if(getX() - Tile.TILE_WIDTH > 0){
                List piecePresent = getWorld().getObjectsAt(getX() - Tile.TILE_WIDTH, getY() - Tile.TILE_HEIGHT, Piece.class);
                if(piecePresent == null){
                    List tilesPresent = getWorld().getObjectsAt(getX() - Tile.TILE_WIDTH, getY() - Tile.TILE_HEIGHT, Tile.class);
                    availableTiles.add((Tile)tilesPresent.get(0));
                }
                // else if(((Piece)(piecePresent.get(0))).getColor() != color){
                    // if(getX() - 2 * Tile.TILE_WIDTH > 0){
                        // if(getY() - 2 * Tile.TILE_HEIGHT > 0){
                            // piecePresent = getWorld().getObjectsAt(getX() - 2 * Tile.TILE_WIDTH, getY() - 2 * Tile.TILE_HEIGHT, Piece.class);
                            // if(piecePresent == null){
                                // List tilesPresent = getWorld().getObjectsAt(getX() - 2 * Tile.TILE_WIDTH, getY() - 2 * Tile.TILE_HEIGHT, Tile.class);
                                // availableTiles.add((Tile)tilesPresent.get(0));
                            // }
                        // }
                    // }
                // }
            }
            if(getX() + Tile.TILE_WIDTH < GameWorld.WORLD_WIDTH){
                
            }
        }
        for(Tile tile: availableTiles){
            tile.drawTile(tile.getColor(), true);
        }
    }
    
    public Color getColor(){
        return color;
    }
}
