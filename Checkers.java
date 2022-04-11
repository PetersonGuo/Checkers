import java.util.*;
import java.io.*;
public class Checkers
{
    private final int length = 8;
    private char[][] main;
    private int turn = 0;
    private Scanner scan;
    
    /*
     * Create a multi dimensional array
     * w stands for white
     * n stand for none/null
     * r stands for red
     */
    public Checkers()
    {
        scan = new Scanner(System.in);
        main = new char[8][8];
        for(int i = 0; i < length; i++)
        {
            main[i] = new char[8];
            for(int j = 0; j < length; j++){
                if(i < 4) main[i][j] = 'w';
                else if(i == 4 || i == 3) main[i][j] = 'n';
                else if(i < 8) main[i][j] = 'r';
            }
        }
    }
    
    public int getTurn() {return turn;}
    public char[][] getBoard() {return main;}
    public void setBoard(char[][] main) {this.main = main;}
    
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
        for(int i = 0; i < board.length; i++) for(int j = 0; j < board[i].length; j++) if(board[i][j] == 'w') white++;;;
        return white;
    }
        
    private int redLeft(char[][] board)
    {
        int red = 0;
        for(int i = 0; i < board.length; i++) for(int j = 0; j < board[i].length; j++) if(board[i][j] == 'w') red++;;;
        return red;
    }
    
    private char opp(char curr)
    {
        if(curr == 'w') return 'r';
        return 'w';
    }
    
    public boolean check(char[][] board, int inRow, int inCol, int finalRow, int finalCol)
    {
        if(finalRow >= 8|| finalCol >= 8 || finalRow < 0 || finalCol < 0) return false;
        int rowDiff = finalRow - inRow;
        int colDiff = finalCol - inCol;
        if(board[finalRow][finalCol] == 'n')
        {
            if(Math.abs(rowDiff) == 2 && Math.abs(colDiff) == 2)
            {
                if(rowDiff < 0)
                {
                    if(colDiff < 0) if(board[inRow - 1][inCol - 1] == opp(board[inRow][inCol])) return true;
                    else if(colDiff > 0) if(board[inRow - 1][inCol + 1] == opp(board[inRow][inCol])) return true;;
                }
                else if(rowDiff > 0)
                {
                    if(colDiff < 0) if(board[inRow + 1][inCol - 1] == opp(board[inRow][inCol])) return true;
                    else if(colDiff > 0) if(board[inRow + 1][inCol + 1] == opp(board[inRow][inCol])) return true;;
                }
                canJump(board, finalRow, finalCol, false, 1);
            }
            else if(Math.abs(rowDiff) == 1 && Math.abs(colDiff) == 1) return true;
        }
        return false;
    }
    
    public boolean canJump(char[][] board, int row, int col, boolean isKing, int depth)
    {
        int[] increment = {1,-1};
        if(isKing)
        {
            for(int i = 0; i < 2; i++) for(int x = 0; x < 2; x++) if(board[row + increment[i]][col + increment[x]] == opp(board[row][col]) && board[row + i*2][col + x*2] == 'n') return true;;;
        }
        else
        {
            int forward = board[row][col] == 'w' ? -1:1;
            for(int i = 0; i < 2; i++) for(int x = 0; x < 2; x++) if(board[row + forward][col + increment[i]] == opp(board[row][col]) && board[row + i*2][col + x*2] == 'n') return true;;;
        }
        return false;
    }
    
    public char[][] takeTurn(char[][] board, int inRow, int inCol, int finalRow, int finalCol)
    {
        if(check(board,inRow,inCol,finalRow,finalCol))
        {
            if(Math.abs(finalCol - inCol) == 2 && Math.abs(finalRow - inRow) == 2) board[finalRow-(finalRow - inRow)/2][finalCol-(finalCol - inCol)/2] = 'n';
            else {
                board[finalRow][finalCol] = board[inRow][inCol];
                board[inRow][inCol] = 'n';
            }
            turn++;
        }
        return board;
    }
    
    // Make deep copy of array and play possible moves there
    // Add double jump and king pieces king has value 2
    // AI chooses how many times to skip pieces
    // Remember to implement not run minimax if only one possibility for ai
    // Returns optimal value for current player(Initially called for root and maximizer)
    public int minimax(int depth, boolean white, char[][] board, int alpha, int beta, int piecesLeft)
    {
        int score = score(board);
        
        if(white)
        {
            if(depth == 20 || piecesLeft == 0) return score; // Base Case
            int best = Integer.MIN_VALUE;
            
            // Recur for left and right children
            List<Integer> indice = new ArrayList<Integer>(piecesLeft*2); //find locations of all remaining white pieces formatted [row,col]
            for(int i = 0; i < 8; i++)
            {
                for(int j = 0; j < 8; j+=2)
                {
                    if(board[i][j] == 'w') {
                        indice.add(i);
                        indice.add(j);
                    }
                }
            }
            
            for(int i = 0; i < piecesLeft*2; i+=2) 
            {
                int x = indice.get(i);
                int y = indice.get(i + 1);
                for (int j = 0; j < 2; j++)
                {
                    int[] direction = {1,-1};
                    // if can take
                    if(check(board, indice.get(i), indice.get(i+1), indice.get(i) + direction[j], indice.get(i+1) + 1)) //if is possible move take move
                    {
                        board[x][y] = 'n'; //try move
                        board[indice.get(i) + direction[j]][indice.get(i+1) + 1] = 'w'; //try move
                        int val = minimax(depth++, false, board, alpha, beta, redLeft(board));
                        best = Math.max(best, val);
                        alpha = Math.max(alpha, best);
                        board[x][y] = 'w'; //reverse move
                        board[indice.get(i) + direction[j]][indice.get(i+1) + 1] = 'n'; //reverse move
                        if (beta <= alpha) break; // Alpha Beta Pruning
                    }
                }
            }
            return best;
        }
        else
        {
            if(depth == 10 || piecesLeft == 0) return score; // Base Case
            int best = Integer.MAX_VALUE;
            
            List<Integer> indice = new ArrayList<Integer>(piecesLeft*2); //find locations of all remaining white pieces formatted [row,col]
            for(int i = 0; i < 8; i++)
            {
                for(int j = 0; j < 8; j+=2)
                {
                    if(board[i][j] == 'r') {
                        indice.add(i);
                        indice.add(j);
                    }
                }
            }
            
            for(int i = 0; i < piecesLeft*2; i+=2) 
            {
                int x = indice.get(i);
                int y = indice.get(i + 1);
                for (int j = 0; j < 2; j++)
                {
                    int[] direction = {1,-1};
                    // if can take
                    if(check(board, indice.get(i), indice.get(i+1), indice.get(i) + direction[j], indice.get(i+1) + 1)) //if is possible move take move
                    {
                        board[x][y] = 'n'; //try move
                        board[indice.get(i) + direction[j]][indice.get(i+1) + 1] = 'w'; //try move
                        int val = minimax(depth++, false, board, alpha, beta, whiteLeft(board));
                        best = Math.max(best, val);
                        alpha = Math.max(alpha, best);
                        board[x][y] = 'w'; //reverse move
                        board[indice.get(i) + direction[j]][indice.get(i+1) + 1] = 'n'; //reverse move
                        if (beta <= alpha) break; // Alpha Beta Pruning
                    }
                }
            }
            return best;
        }
    }
}
