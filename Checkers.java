/**
 * Write a description of class Checkers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Checkers  
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Checkers
     */
    public Checkers()
    {
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
    
    // Returns optimal value for current player (Initially called for root and maximizer)
    static int minimax(int depth, int nodeIndex,
                       Boolean maximizingPlayer,
                       int values[], int alpha,
                       int beta)
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
