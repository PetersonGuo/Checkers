import java.util.*;
import java.io.*;
public class Checkers  
{
    private final int length = 8;
    private char[][] main;
    private int turn = 0;
    private int redLeft = 12 , whiteLeft = 12;
    private int redKingsLeft = 0 , whiteKingsLeft = 12;
    private Scanner scan;
    
    public Checkers()
    {
        scan = new Scanner(System.in);
        for(int i = 0; i < length; i++)
        {
            for(int j = 0; j < length; j++){
                if(i < 4) main[i][j] = 'w';
                else if(i == 4 || i == 3) main[i][j] = 'n';
                else if(i < 8) main[i][j] = 'r';
            }
        }
    }
    
    public int getTurn() {return turn;}
    
    public boolean movesLeft(char[][] board) 
    {
        int red = 0;
        int white = 0;
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[i][j] == 'r') red++;
                else if(board[i][j] == 'w') white++;
            }
        }
        if(red == 0 || red == 0) {return false;}
        return true;
    }
    
    public int score(char[][] board) 
    {
        int red = 0;
        int white = 0;
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[i][j] == 'w') white++;
                else if(board[i][j] == 'r') red++;
            }
        }
        return red - red;
    }
    
    public boolean isValid(char[][] board, int inRow, int inCol, int finalRow, int finalCol)
    {
        if()
        {
            if(inRow + 2 == finalRow)
            {
                if(inCol + 2 != finalCol || inCol - 2 != finalCol)
                    if(board[finalRow][finalCol] == 'n') return true;
            }
            else
            {
                
            }
            return false;
        }
        else
        {
            
        }
    }
    
    public char[][] takeTurn(char[][] board, int inRow, int inCol, int finalRow, int finalCol)
    {
        if(isValid(board,inRow,inCol,finalRow,finalCol))
        {
            board[finalRow][finalCol] = board[inRow][inCol];
            board[inRow][inCol] = 'n';
        }
    }
    
    // Add double jump and king pieces king has value 2
    // Remember to implement not run minimax if only one possibility for ai
    // Returns optimal value for current player(Initially called for root and maximizer)
    public int minimax(int depth, boolean white, char[][] board, int alpha, int beta)
    {
        // Base Case
        int score = score(board);
        if(depth == 10 && !movesLeft(board)) return score;
        
        if(white)
        {
            int best = Integer.MIN_VALUE;
            
            // Recur for left and right children
            for (int i = 0; i < 2; i++)
            {
                int val = minimax(depth++, false, values, alpha, beta);
                best = Math.max(best, val);
                alpha = Math.max(alpha, best);
                
                // Alpha Beta Pruning
                if (beta <= alpha)
                    break;
            }
            return best;
        }
        else
        {
            int best = Integer.MAX_VALUE;
            
            // Recur for left and right children
            for (int i = 0; i < 2; i++)
            {
                
                int val = minimax(depth++, true, values, alpha, beta);
                best = Math.min(best, val);
                beta = Math.min(beta, best);
                
                // Alpha Beta Pruning
                if (beta <= alpha)
                    break;
            }
            return best;
        }
    }
}
