/**
 * Class for the game stats of a tic tac toe game
 * @author Ryan Metz
 */
public class GameStats
{
    /** the number of losses in a game **/
    int nlosses;
    /** the number of ties in a game **/
    int nties;
    /** the number of wins in a game **/
    int nwins;
    
    /**
     * constructor for the Class GameStats 
     * set all values to 0
     */
    public GameStats () {
        this.nlosses = 0;
        this.nties = 0; 
        this.nwins = 0;
    }
    
    /**
     * increments the number of wins by 1
     */
    public void recordWin() {
        nwins++;
    }
    
    /**
     * increments the number of ties by 1
     */
    public void recordTie() {
        nties++;
    }
    
    /**
     * increments the number of losses by 1
     */
    public void recordLoss() {
        nlosses++;
    }
    
    /**
     * returns a textual representation of the GameStats class with
     * the specific amount of wins, losses and ties printed out
     * @return a string that contains the correct amount of wins
     * losses and ties for a game
     */
    public String toString() {
        return "# of wins: " + nwins + " # of losses: " + nlosses + " # of ties: " + nties;
    }
}
