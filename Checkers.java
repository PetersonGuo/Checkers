import java.util.*;
public class Checkers  
{
    // instance variables - replace the example below with your own
    private Scanner scan;
    private int turn = 0;
    private ArrayList<ArrayList<Character>> row = new ArrayList<ArrayList<Character>>();

    /**
     * Constructor for objects of class Checkers
     */
    public Checkers()
    {
        scan = new Scanner(System.in);
        for(int i = 0; i < 8; i++)
        {
            ArrayList<Character> col = new ArrayList<Character>();
            for(int j = 0; j < 4; j++)
            {
                col.add('n');
            }
            row.add(col);
        }
    }
    
    // Returns optimal value for current player (Initially called for root and maximizer)
        static int minimax(int depth, int nodeIndex, boolean maximizingPlayer, int values[], int alpha, int beta)
        {
        // Base Case
        if (depth == 0)
            return values[nodeIndex]; 
         
        if (maximizingPlayer)
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
                int val = minimax(depth + 1, nodeIndex * 2 + i,true, values, alpha, beta);
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
