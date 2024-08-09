/**
 * an abstract class for a tic tac toe player
 * @author Ryan Metz
 */
abstract class APlayer
{
    /** the game the player is playing **/
    protected Game game;
    /** the symbol for the player **/
    protected char symbol;
    
    /**
     * default constructor for a player
     */
    protected APlayer(){}
    
    /**
     * a constructor for the class APlayer
     * @param game the game that the player is player
     * @param symbol the symbol of the player
     */
    protected APlayer(Game game, char symbol) {
        this.game = game;
        this.symbol = symbol;
        
    }
    
    /**
     * returns the symbol of the player
     * @return a char representing the symbol of the player
     */
    char getSymbol() {
        return symbol; 
    }
    
    /**
     * will pick the move of the player
     */
    abstract Move pickMove();
    
    
}
