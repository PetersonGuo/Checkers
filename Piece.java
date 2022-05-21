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
    public static final int PIECE_SIZE = GameWorld.WORLD_SIZE / 10;
    private Color color;
    
    private MouseInfo mouse;
    private boolean hovering = false;
    private boolean clicked = false;
    
    private ArrayList<Tile> availableTiles;
    
    public Piece(Color color)
    {
        this.color = color;
        availableTiles = new ArrayList<Tile>();
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
        List<Piece> pieceAt = getWorld().getObjectsAt(getX()-GameWorld.TILE_SIZE,getY()-GameWorld.TILE_SIZE,Piece.class);
        List tileClicked = mouse != null && mouse.getButton() == 1 ? getWorld().getObjectsAt(mouse.getX(),mouse.getY(),Tile.class) : new ArrayList<>();
        long start = System.nanoTime(), end = System.nanoTime();
        if(tileClicked.size() > 0 && clicked && valid(((Tile)tileClicked.get(0))))
        {
            System.out.println("moved");
        }
        else if(tileClicked.size() == 0 && clicked)
        {
            clicked = false;
            hovering = true;
            Tile tile = new Tile(new Color(0,0,0,0));
            tile.resetTiles(0);
        }
        else if(!clicked && Greenfoot.mouseMoved(this)) 
        {
            Tile tileOn = (Tile)getOneIntersectingObject(Tile.class);
            // if(!soundPlayed){ //adds sound
                // moveSound.play();
                // soundPlayed = true;
            // }
            
            tileOn.drawTile(Color.GREEN);
            end = System.nanoTime();
        }
        else if(Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this) && !clicked && (end-start)/1000 > 2) 
        {
            // soundPlayed = false;
            Tile tileOn = (Tile)getOneIntersectingObject(Tile.class);
            tileOn.drawTile(tileOn.getColor());
            start = System.nanoTime();
        }
        else if(pieceAt.size() > 0 && (pieceAt.get(0)).getColor().equals(GameWorld.pieceColor2))
        {
            Tile tileOn = (Tile)getOneIntersectingObject(Tile.class);
            if(Greenfoot.mouseClicked(this) && !clicked) 
            {
                hovering = false;
                clicked = true;
                checkPlayer();  
            }
        }
    }
    
    private void drawPiece(Color color) {
        image.clear();
        image.setColor(color);
        image.fillOval(1, 1, PIECE_SIZE, PIECE_SIZE);
        setImage(image);
    }
    
    private boolean valid(Tile tile)
    {
        for(Tile i : availableTiles)
        {
            System.out.println(i);
            if(tile.equals(i))
                return true;
        }
        return false;
    }

    private void checkPlayer() 
    {
        char[][] middle = createBoard();
        int newX = getX()/GameWorld.TILE_SIZE; // Sets location to integers from 0-8
        int newY = getY()/GameWorld.TILE_SIZE;

        if(GameWorld.game.check(middle,newX,newY,newX-1,newY-1)) 
            availableTiles.add((Tile)(getWorld().getObjectsAt(getX() - GameWorld.TILE_SIZE, getY() - GameWorld.TILE_SIZE, Tile.class).get(0)));
        if(GameWorld.game.check(middle,newX,newY,newX+1,newY-1))
            availableTiles.add((Tile)(getWorld().getObjectsAt(getX() + GameWorld.TILE_SIZE, getY() - GameWorld.TILE_SIZE, Tile.class).get(0)));
        if(GameWorld.game.check(middle,newX,newY,newX-2,newY-2))
            availableTiles.add((Tile)(getWorld().getObjectsAt(getX() - GameWorld.TILE_SIZE*2, getY() - GameWorld.TILE_SIZE*2, Tile.class).get(0)));
        if(GameWorld.game.check(middle,newX,newY,newX+2,newY-2))
            availableTiles.add((Tile)(getWorld().getObjectsAt(getX() + GameWorld.TILE_SIZE*2, getY() - GameWorld.TILE_SIZE*2, Tile.class).get(0)));
        for(Tile tile: availableTiles) 
            tile.drawTile(Color.GREEN);
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
                    List pieceAt = getWorld().getObjectsAt(GameWorld.TILE_SIZE * (col) + GameWorld.TILE_SIZE/2,GameWorld.TILE_SIZE * (row) + GameWorld.TILE_SIZE/2, Piece.class);
                    if(pieceAt.size() == 0) 
                        board[row][col] = 'n';
                    else if(((Piece)pieceAt.get(0)).getColor().equals(GameWorld.pieceColor1)) 
                        board[row][col] = 'r';
                    else 
                        board[row][col] = 'w';
                }
                else 
                    board[row][col] = 'n';
            }
        }
        return board;
    }
    
    private static void print(char[][] arr)
    {
        for(char[] i : arr)
            System.out.println(Arrays.toString(i));
    }
}