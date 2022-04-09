import java.util.*;
import java.io.*;
public class Checkers  
{
    private ArrayList<ArrayList<Character>> row;
    private int turn = 0;
    private Scanner scan;
    
    public Checkers()
    {
        scan = new Scanner(System.in);
        row = new ArrayList<ArrayList<Character>>();
        for(int i = 0; i < 8; i++)
        {
            ArrayList<Character> x= new ArrayList<Character>();
            for(int j = 0; j < 8; j++){
                x.add('n');
            }
            row.add(x);
        }
    }
    
    public int getTurn() {return turn;}
    
    // Remember to implement not run minimax if only one possibility for ai
    // Returns optimal value for current player (Initially called for root and maximizer)
    public int minimax(int depth, int nodeIndex, boolean white, int values, int alpha, int beta)
    {
        // Base Case
        if(depth == 10)
            return values;
        
        if(white)
        {
            int best = Integer.MIN_VALUE;
            
            // Recur for left and right children
            for (int i = 0; i < 2; i++)
            {
                int val = minimax(depth + 1, nodeIndex * 2 + i, false, values, alpha, beta);
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
                
                int val = minimax(depth + 1, nodeIndex * 2 + i, true, values, alpha, beta);
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
