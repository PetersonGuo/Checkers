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
    public final int PIECE_SIZE = GameWorld.WORLD_SIZE / 10;
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
    
    public Color getColor() {return color;}
    
    /**
     * Act - do whatever the Pieces wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        mouse = Greenfoot.getMouseInfo();
        Tile tile = (Tile)getOneIntersectingObject(Tile.class);
        if(Greenfoot.mouseClicked(this) && !clicked) {
            hovering = false;
            clicked = true;
            availableTiles = new ArrayList<Tile>();
            check();
        } else if(clicked) {
            createBoard();
            for(Tile availableTile: availableTiles) {
                if(Greenfoot.mouseClicked(availableTile)) {
                    this.setLocation(availableTile.getX(), availableTile.getY());
                }
            }
            clicked = false;
        } else if(!clicked) {
            if(Greenfoot.mouseMoved(this)) {
                hovering = true;
                // if(!soundPlayed){ //adds sound
                    // moveSound.play();
                    // soundPlayed = true;
                // }
                
                tile.drawTile(tile.getColor(), hovering);
            }
            if(Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
                hovering = false;
                // soundPlayed = false;
                
                tile.drawTile(tile.getColor(), hovering);
            }
        }
    }
    
    private void drawPiece(Color color) {
        image.clear();
        image.setColor(color);
        image.fillOval(1, 1, PIECE_SIZE, PIECE_SIZE);
        setImage(image);
    }
    
    private void check() {
        char[][] middle = createBoard();
        int newX = getX()/GameWorld.TILE_SIZE;
        int newY = getY()/GameWorld.TILE_SIZE;
        List tilesPresent = new ArrayList<>();
        if(!getColor().equals(GameWorld.pieceColor1))
        {
            if(GameWorld.game.check(middle,newX,newY,newX-1,newY-1)) {
                tilesPresent.add(getWorld().getObjectsAt(getX() - GameWorld.TILE_SIZE, getY() - GameWorld.TILE_SIZE, Tile.class));
                if(tilesPresent.size() > 0) 
                    {
                        Object x = tilesPresent.get(0);
                        availableTiles.add((Tile)x);
                    }
            }
            
            if(GameWorld.game.check(middle,newX,newY,newX+1,newY-1))
            {
                tilesPresent.add(getWorld().getObjectsAt(getX() + GameWorld.TILE_SIZE, getY() - GameWorld.TILE_SIZE, Tile.class));
                if(tilesPresent.size() > 0) 
                {Tile x = (Tile)tilesPresent.get(0);
                        availableTiles.add(x);}
            }
            for(Tile tile: availableTiles) tile.drawTile(tile.getColor(), true);
        }
    }
    
    private void printBoard(char[][] board) //debugging
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++) System.out.print(board[i][j]);
            System.out.println();
        }
    }
    
    private char[][] createBoard()
    {
        char[][] board = GameWorld.game.getBoard();
        for(int row = 0; row < 8; row++)
        {
            for(int col = 0; col < 8; col++)
            {
                int offset = (col + 1) % 2 == 0 ? 0:1;
                if((row + 1 + offset) % 2 == 1)
                {
                    List pieceAt = getWorld().getObjectsAt(50 * (col) + 25,50 * (row) + 25, Piece.class);
                    if(pieceAt.size() == 0) board[row][col] = 'n';
                    else if(((Piece)pieceAt.get(0)).getColor().equals(GameWorld.pieceColor1)) board[row][col] = 'r';
                    else board[row][col] = 'w';
                }
                else board[row][col] = 'n';
            }
        }
        return board;
    }
}
