/**
 * Write a description of class Checkers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Checkers  
{

    /**
     * Constructor for objects of class Checkers
     */
    public Checkers()
    {
        
    }
    
    // Returns optimal value for current player (Initially called for root and maximizer)
    public int minimax(int depth, int nodeIndex, boolean maximizingPlayer, int values[], int alpha, int beta)
    {
        // Base Case
        if (depth == 3)
            return values[nodeIndex];
     
        if (maximizingPlayer)
        {
            int best = Integer.MIN_VALUE;
     
            // Recur for left and right children
            for (int i = 0; i < 2; i++)
            {
                int val = minimax(depth + 1, nodeIndex * 2 + i,
                                  false, values, alpha, beta);
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
                 
                int val = minimax(depth + 1, nodeIndex * 2 + i,
                                  true, values, alpha, beta);
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
