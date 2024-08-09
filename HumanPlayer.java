import java.util.Scanner;
/**
 * the class for a human player of tic tac toe
 * @author Ryan Metz
 */
public class HumanPlayer extends APlayer
{
    /** default constructor for the class **/
    HumanPlayer() {}

    /**
     * Constructor for HumanPlayer class
     * @param game the game that is being played
     * @param symbol the symbol for a human player: 'X'
     */
    HumanPlayer(Game game, char symbol) {
        this.game = game;
        this.symbol = symbol; 
    }

    /**
     * will ask the user to input a move via the keyboard, if the move does not 
     * fit the correct style rc, with r meaning the desired row and c meaning
     * the desired column for the move, then they are asked to input another move.
     * Likewise if a move is in the form rc but is already occupied then they are 
     * asked for another move, returns a move once is it valid 
     * @return a Move that has been validated 
     */
    public Move pickMove() {
        Scanner scan = new Scanner(System.in);
        Move m;
        String move;
        String MOVE;
        char r;
        char c;
        int row;
        int col;
        
        while (true) {
            System.out.println("Enter your move:");
            move = scan.next();
            MOVE = move.toUpperCase();
            // quit the game if the user enters quit
            if (MOVE.equals("QUIT"))
                return null;
            // if the move is inputed in the correct format than 
            // create a new move and check to see if is is valid
            else if (MOVE.length() == 2 && MOVE.charAt(0) != ' ' && MOVE.charAt(1) != ' ') {
                r = MOVE.charAt(0);
                c = MOVE.charAt(1);
                // turn the chars into ints to pass into Move object
                row = (int) r - 'A';
                col = (int) c - '1';
                m = new Move(row, col);
                char res = game.isValidMove(m); 
                // checking to see if m is a valid move
                if (res == 'R' || res == 'C') {
                    System.out.println("This move was not inputed correctly");
                    System.out.println("Moves should be two character: the first being the letter for the row");
                    System.out.println("and the second being the number for the column\n");
                }
                else if (res == 'O')
                    System.out.println("This space is already occupied"); 
                else 
                    return m; 
            }
            // move was not inputted correctly by the user
            else {
                System.out.println("The move was not inputted correctly"); 
                System.out.println("Moves should be two character: the first being the letter for the row");
                System.out.println("and the second being the number for the column\n");
            }
        }




    }


}
