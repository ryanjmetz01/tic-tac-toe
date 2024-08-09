/**
 * Game Class for Tic Tac Toe
 * @author Ryan Metz
 * 
 */
public class Game
{
    /** the representation of the board as an array of chars **/
    char[][] board;
    /** the board size for each game **/
    int boardSize; 
    /** hold references to the game's players **/
    APlayer[] players;
    /** symbol for a blank space on the board (' ') **/
    final char Symbol_Blank = ' ';
    /** symbol for the cpu player ('O') **/
    final char Symbol_Cpu = 'O';
    /** symbol for the human player ('X') **/
    final char Symbol_Human = 'X'; 

    /**
     * default constructor for the class Game
     */
    public Game() {
        this.boardSize = 3;
        board = new char[boardSize][boardSize];

        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = Symbol_Blank; 
            }

        }

        players = new APlayer[2];

    }

    /** 
     * constructor for the class Game 
     * @param boardSize the desired board size, board will be boardSize X boardSize
     */
    public Game(int boardSize) {
        this.boardSize = boardSize;
        board = new char[boardSize][boardSize];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = Symbol_Blank; 
            }
        }

        players = new APlayer[2];

    }

    /**
     * returns the board size of the game
     * @return boardSize of the game
     */
    public int getBoardSize() {
        return boardSize;
    }

    /** 
     * Creates a textual representation of the game board 
     * @return a string representing the game board
     */
    public String toString() {
        String res = "";
        char row = 'A'; 

        //print out the row of numbers at the top of the board
        for(int i = 1; i <= board.length; i++) {
            res += "   " + i;

        }
        res += "\n";
        // printing out the main body of the board
        for (int i = 0; i < board.length; i++) {
            res += row;
            // print out the rows starting with A 
            // containing the pieces of the board  
            for (int j = 0; j < board.length; j++) {
                if(j == 0)
                    res += "  " + board[i][j] + " "; 
                else 
                    res += " " + board[i][j] + " ";

                if (j != board[i].length-1)
                    res += "|";
            }
            res += "\n  ";
            // print out the dotted deviding lines
            // but not one on the bottom of the board
            if (i != board[i].length-1) {
                for (int k = 0; k < board.length; k++) {
                    res += "---";
                    if(k != board[i].length-1)
                        res += "|";
                }
            }
            res += "\n";
            row++;
        }
        return res;
    }

    /** 
     * executes the move passed as an argument. If not a valid move return false
     * @param move the move to be executed 
     * @param symbol the symbol of player making the move
     * @return returns true if the move is executed 
     */
    protected boolean executeMove(Move move, char symbol) {
        if (isValidMove(move) == 'V') {
            board[move.row][move.col] = symbol;
            return true;
        }

        return false; 
    }

    /**
     * will check to see if a move is valid. Will return 'V' if the move
     * is valid or another character if the move is invalid
     * @param move the move to be checked
     * @return 'V' if the move is valid. 'R' if the row of the move is invalid. 
     * 'C' if the column of the move is invalid. 'O' if the move given 
     * is already occupied
     */
    char isValidMove(Move move) {

        if ((move.row >= boardSize) || (move.row < 0)) {
            return 'R';
        }
        else if ((move.col >= boardSize) || (move.col < 0)) {
            return 'C'; 
        }
        else if ((board[move.row][move.col] == Symbol_Human) || (board[move.row][move.col] == Symbol_Cpu)){
            return 'O';
        }
        else 
            return 'V';
    }

    /**
     * will reset the game by clearing of the board of all previous moves
     */
    protected void resetGame() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = Symbol_Blank; 
            }
        }
    }

    /**
     * Plays a single game of tic tac toe, randomly picking if the computer or 
     * human player goes first and then having them pick moves in turn
     * @return A character representing the result of the game: 
     * 'H' if the human player has won, 'C' if the computer player has won
     * 'T' if there was a tie, 'Q' if the game is quit
     */
    public char playSingleGame() {
        char status;
        double rand = Math.random();

        System.out.println("---------New game----------");
        System.out.println(this.toString());
        // the human gets to move first
        if (rand >= .5) {
            status = getGameStatus();
            while (true)  {
                Move m = players[0].pickMove(); 
                if (m == null) {
                    return 'Q';
                }
                else {
                    executeMove(m, Symbol_Human);
                    System.out.println(this.toString());
                    status = getGameStatus(); 
                    if (status != '%') 
                        break;
                }

                System.out.println("Computer's move");
                Move n = players[1].pickMove();
                executeMove(n, Symbol_Cpu);
                System.out.println(this.toString());
                status = getGameStatus();
                if (status != '%') 
                    break;
            } 
        }
        // computer moves first 
        else {
            while (true)  {
                System.out.println("Computer's move");
                Move n = players[1].pickMove();
                executeMove(n, Symbol_Cpu);
                System.out.println(this.toString());
                status = getGameStatus();
                if (status != '%') 
                    break;

                Move m = players[0].pickMove();
                if (m == null) {
                    return 'Q';
                }
                else {
                    executeMove(m, Symbol_Human);
                    System.out.println(this.toString());
                    status = getGameStatus(); 
                    if (status != '%') 
                        break;
                }

            } 

        }

        if (status == Symbol_Human) {
            System.out.println("The human player has won");
            return 'H';
        }
        else if (status == Symbol_Cpu) {
            System.out.println("The computer has won");
            return 'C';
        }
        else {
            System.out.println("The game is a tie");
            return 'T';
        }

    }

    /**
     * this will check the status of the game and then return a character.
     * Will check to see if a player has won via completing a row, column 
     * or diagonal. Will also check if the board is full and no one has won.
     * This will result in a tie.
     * @return will return a '%" if the game is still ongoing, 'T' the game is 
     * over and there is a tie, or a player has won and then return that player's
     * symbol('X' or 'O')
     */
    public char getGameStatus() {
        char rPlay = ' '; 
        char cPlay= ' ';
        char d1Play = ' ';
        char d2Play = ' ';
        char tie = 'T';
        char row = 'r';
        char col = 'c';
        char d1 = ' ';
        char d2 = ' ';

        // loop through the board to see if there is any blank spaces
        // ties cannot happen if there is a blank space
        // tie will not change if there is no blank spaces 
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Symbol_Blank) {
                    tie ='t'; 
                }
            }
        }

        //check rows for wins 
        for (int i = 0; i < board.length; i++) {
            // check first to see if a win has already been read
            if (row == 'R') 
                break;
            // check to see if there is a play at the start of the row
            else if (board[i][0] == Symbol_Human || board[i][0] == Symbol_Cpu) { 
                rPlay = board[i][0];
                // loop through the row
                for (int j = 1; j < board[i].length; j++) {
                    // if a place in the row does not equal the start 
                    // break out to next row
                    if(rPlay != board[i][j]){
                        row = 'r';
                        break;
                    }
                    // the row had all the same values so its a win ->  
                    // lower case r for no win, upper case for win
                    else
                        row = 'R';
                }
            }
        }

        // return the winner's char if a row has won
        if(row == 'R') {
            return rPlay;
        }

        // check columns for wins 
        for(int i = 0; i < board.length; i++) {
            // check to first to see if a win has occured
            if (col == 'C') {
                break;
            }
            // set the first symbol of the col to that symbol
            else if (board[0][i] == Symbol_Human || board[0][i] == Symbol_Cpu) {
                cPlay = board[0][i];
                // loop through checking if all the symbols 
                // in a col are the same 
                for(int j = 1; j < board[i].length; j++) {
                    if (cPlay != board[j][i]) {
                        col = 'c';
                        break;
                    }
                    // set col to capital C to sgnify a win here 
                    else 
                        col = 'C';
                }

            }
        }
        
        // return winner's char if a column has won
        if (col == 'C') 
            return cPlay;

        // check diagonals starting at (0,0)
        if (board[0][0] != Symbol_Blank) {
            d1Play = board[0][0]; 
            // loop through the board
            for(int i = 1; i < board.length; i++) {
                if (board[i][i] == d1Play) {
                    d1 = 'D';
                }
                else {
                    d1 = 'd'; 
                    break;
                }

            }
        }

        // check diagonal starting at (0, boardSize-1) 
        if (board[0][boardSize-1] != Symbol_Blank) {
            d2Play = board[0][boardSize-1];
            int in = boardSize-2; 
            for (int i = 1; i < board.length; i++) {
                if (board[i][in] == d2Play) {
                    d2 = 'B';
                    in--; 
                }
                else {
                    d2 = 'b';
                    break;
                }
            }
        }

        // return the winner's char if a diagonal has won
        if (d1 == 'D') 
            return d1Play;
        else if (d2 == 'B') 
            return d2Play;
        // tie when there is no blank spaces and no one has won
        else if (tie == 'T') 
            return 'T';
        else 
            return '%'; 

    }

    /**
     * will run consecutive Tic tac toe games until the user gets tired and quits.
     * When this happens, the game stats of wins-losses-ties will be printed.
     * @param args The first argument represnts the desired game board size and
     * should be an integer [1,9]. If no argument is present or does not comply,
     * then a default 3X3 game board will be used 
     */
    public static void main(String[] args) {
        Game g; 
        if(args.length == 0){
            g = new Game();
        }
        else {
            try {
                int size = Integer.parseInt(args[0]);
                if (size <= 9 && size > 0) {
                    g = new Game(size); 
                }
                else {
                    System.out.println("Invalid board size given. 3X3 board created");
                    g = new Game();
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Board size given in invalid format. 3X3 board created");
                g = new Game();
            }

        }

        g.players[0] = new HumanPlayer(g, g.Symbol_Human);
        g.players[1] = new CpuPlayer(g, g.Symbol_Cpu); 
        GameStats gs = new GameStats();

        while(true) {
            char res = g.playSingleGame();
            if (res == 'H') {
                gs.recordWin();
            }
            else if (res == 'C') {
                gs.recordLoss();
            }
            else if (res == 'T') {
                gs.recordTie();
            }
            else {
                System.out.println(gs);
                break;
            }

            g.resetGame();

        }

    }
}