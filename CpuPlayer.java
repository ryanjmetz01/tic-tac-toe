import java.util.Random;
/**
 * Class for the computer player 
 * @author Ryan Metz
 */
public class CpuPlayer extends APlayer
{
    /** default constructor for the class **/
    CpuPlayer() {}
    
    /**
     * Constructor for the CpuPlayer class
     * @param game the game that is being player 
     * @param symbol the symbol of the player: 'O'
     */
    CpuPlayer(Game game, char symbol) {
        this.game = game; 
        this.symbol = symbol; 
    }
    
    /**
     * This will pick the move of the computer player randomly 
     * within the bounds of the game board
     * @return the move that is picked given that
     * the space it wants to occupy is blank
     */
    public Move pickMove() {
        Random rand = new Random();
        int row;
        int col;
        int size = game.getBoardSize();
        
        //until a valid space is found
        while(true) {
            //create randoms ints in bounds of boardSize 
            row = rand.nextInt(size);
            col = rand.nextInt(size);
            if (game.board[row][col] == ' ')
                break;

        }
        Move m = new Move(row, col); 
        return m;  
    }
    
}
