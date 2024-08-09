
/**
 * a Class for a Move object 
 * @author Ryan Metz
 */
public class Move
{
    /** the column of a move **/
   int col;
   /** the row of a move **/
   int row;
   
   /**
    * Constructor for the move class
    * @param row this is the row for the move
    * @param col this is the column for the move
    */
   Move(int row, int col) {
       this.row = row;
       this.col = col; 
   }
}
