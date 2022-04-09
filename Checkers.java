import java.util.*;
import java.io.*;
public class Checkers  
{
    private final int length = 8;
    private char[][] main;
    private int turn = 0;
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
    
    private int score(char[][] board) 
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
        return white - red;
    }
    
    private int whiteLeft(char[][] board)
    {
        int white = 0;
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[i][j] == 'w') white++;
            }
        }
        return white;
    }
        
    private int redLeft(char[][] board)
    {
        int red = 0;
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[i][j] == 'w') red++;
            }
        }
        return red;
    }
    
    private char opp(char curr)
    {
        if(curr == 'w') return 'r';
        return 'w';
    }
    
    private boolean check(char[][] board, int inRow, int inCol, int finalRow, int finalCol)
    {
        int rowDiff = finalRow - inRow;
        int colDiff = finalCol - inCol;
        if(board[finalRow][finalCol] == 'n')
        {
            if(Math.abs(rowDiff) == 2 && Math.abs(colDiff) == 2)
            {
                if(rowDiff < 0)
                {
                    if(colDiff < 0)
                    {
                        if(board[inRow - 1][inCol - 1] == opp(board[inRow][inCol])) return true;
                    }
                    else if(colDiff > 0) if(board[inRow - 1][inCol + 1] == opp(board[inRow][inCol])) return true;;
                }
                else if(rowDiff > 0)
                {
                    if(colDiff < 0)
                    {
                        if(board[inRow + 1][inCol - 1] == opp(board[inRow][inCol])) return true;
                    }
                    else if(colDiff > 0) if(board[inRow + 1][inCol + 1] == opp(board[inRow][inCol])) return true;;
                }
            }
            else if(Math.abs(rowDiff) == 1 && Math.abs(colDiff) == 1)
            {
                board[finalRow][finalCol] = board[inRow][inCol];
            }
        }
        return false;
    }
    
    public char[][] takeTurn(char[][] board, int inRow, int inCol, int finalRow, int finalCol)
    {
        if(check(board,inRow,inCol,finalRow,finalCol))
        {
            turn++;
            if(Math.abs(finalCol - inCol) == 2 && Math.abs(finalRow - inRow) == 2)
            {
                board[finalRow-(finalRow - inRow)/2][finalCol-(finalCol - inCol)/2] = 'n';
            }
            board[finalRow][finalCol] = board[inRow][inCol];
            board[inRow][inCol] = 'n';
        }
        return board;
    }
    
    // Add double jump and king pieces king has value 2
    // AI chooses how many times to skip pieces
    // Remember to implement not run minimax if only one possibility for ai
    // Returns optimal value for current player(Initially called for root and maximizer)
    public int minimax(int depth, boolean white, char[][] board, int alpha, int beta, int piecesLeft)
    {
        int score = score(board);
        
        if(white)
        {
            if(depth == 10 || piecesLeft == 0) return score; // Base Case
            int best = Integer.MIN_VALUE;
            
            // Recur for left and right children
            for(int i = 0; i < piecesLeft; i++) {
                for (int j = 0; j < 2; j++)
                {
                    //if can skip
                    // if() if is possible move
                    // {
                        
                    // }
                    int val = minimax(depth++, false, board, alpha, beta, redLeft(board));
                    best = Math.max(best, val);
                    alpha = Math.max(alpha, best);
                    
                    if (beta <= alpha) // Alpha Beta Pruning
                        break;
                }
            }
            return best;
        }
        else
        {
            if(depth == 10 || piecesLeft == 0) return score; // Base Case
            int best = Integer.MAX_VALUE;
            
            // Recur for left and right children
            for(int i = 0; i < piecesLeft; i++)
            {
                for (int j = 0; j < 2; j++)
                {
                    //if can double skip
                    int val = minimax(depth++, true, board, alpha, beta, whiteLeft(board));
                    best = Math.min(best, val);
                    beta = Math.min(beta, best);
                    
                    if (beta <= alpha) // Alpha Beta Pruning
                        break;
                }
            }
            return best;
        }
    }
}
