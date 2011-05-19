/**
 *  CS1312: Programming Assignment #2 - Spring 2001
 *  <PRE>
 *  TetrisPiece - 
 *                
 *                
 *
 *  Revisions: 1.0 Jan 29, 2001
 *                 created TetrisPiece.java
 *             1.1 Jan 30, 2001
 *                 worked on rotate
 *
 *  </PRE>
 *  @author <A HREF="mailto:gte639v@prism.gatech.edu">Blanton Black</A>
 *  @version Version 1.0, Jan 29, 2001
 */
public class TetrisPiece implements P2Constants {
    
    //create a 2D array of TetrisBlock objects
    private TetrisBlock[][] tbArray;
    

    /**
     * create an instance of a TetrisPiece
     * @param selection a number from 0 to 6 designating which piece to create
     */
    public TetrisPiece(int selection) {
        //set the color to use
        java.awt.Color color;
        color = new java.awt.Color( (float) Math.random(), (float) Math.random(), (float) Math.random(), (float) .75);
                
        if (selection > PIECE.length) {
            selection = 0;
        }
        
        tbArray = new TetrisBlock[PIECE[selection].length][PIECE[selection][0].length];    //initialize the array
        
        for (int i = 0; i < PIECE[selection].length; i++) {
            for (int j = 0; j < PIECE[selection][i].length; j++) {
                //if there is to be a color assigned, do it
                if (PIECE[selection][i][j]) {
                    tbArray[i][j] = new TetrisBlock(color);
                }   //end if
            }   //end for
        }   //end for
            
    }
    
    /**
     * constructor to create a new TetrisPiece
     * this gives direct acces to the array
     * @param tetrisBlocks the array to store to tbArray
     */
    public TetrisPiece(TetrisBlock[][] tetrisBlocks) {
        tbArray = tetrisBlocks;
    }
    
    /**
     * accessor to return the array
     * @return return the array of blocks tbArray
     */
    public TetrisBlock[][] getTetrisBlocks() {
        return tbArray;
    }
    
    /**
     * a function to rotate the the array of blocks
     * does not change the actual value, but a new piece
     * @param isClockwise true to turn clockwise
     * @return return the TetrisPiece
     */
    public TetrisPiece rotate(boolean isClockwise) {
        TetrisBlock[][] myArray = new TetrisBlock[tbArray.length][tbArray[0].length];
        
        for ( int i=0 ; i<tbArray.length ; i++) {
            for ( int j=0 ; j<tbArray[i].length ; j++) {
                //System.out.println(i + "," + j + " to " + ((tbArray.length-1)-j) + "," + i);
                if (isClockwise) {
                    myArray[(tbArray.length-1)-j][i] = tbArray[i][j];
                } else {
                    myArray[j][(tbArray.length-1)-i] = tbArray[i][j];
                }
            }
        }
        TetrisPiece foopar = new TetrisPiece(myArray);
        return foopar;
    }
    
    /**
     * public function to override the default toString
     * @return a string depicting the tetris piece
     */
    public String toString() {
        int i = 0;
        int j = 0;
        String retVal;
        retVal = "A Tetris piece that looks like:";
        
        for (j = 0; j < tbArray[0].length; j++) {
            retVal = retVal + "\n";
            for (i = 0; i < tbArray.length; i++) {
                if (tbArray[i][j] != null) {
                    retVal = retVal + "#";
                } else {
                    retVal = retVal + "-";
                }
            }
        }   //end for
        return retVal;
    }
    
    /**
     * Main method.
     * @param agrv array of command line arguments
     */
    public static void main (String[] argv) {
        TetrisPiece foo = new TetrisPiece(1);
        
        System.out.println(foo.getTetrisBlocks()[0][0]);
        System.out.println(foo.getTetrisBlocks()[0][1]);
        System.out.println(foo.getTetrisBlocks()[0][2]);
        System.out.println(foo.getTetrisBlocks()[1][0]);
        System.out.println(foo.getTetrisBlocks()[1][1]);
        System.out.println(foo.getTetrisBlocks()[1][2]);
        System.out.println(foo.getTetrisBlocks()[2][0]);
        System.out.println(foo.getTetrisBlocks()[2][1]);
        System.out.println(foo.getTetrisBlocks()[2][2]);
        
        System.out.println(foo);
        
        //test the rotate function
        //clockwise
        foo = foo.rotate(true);
        System.out.println(foo);
        foo = foo.rotate(true);
        System.out.println(foo);
        foo = foo.rotate(true);
        System.out.println(foo);
        foo = foo.rotate(true);
        System.out.println(foo);
        //counter clockwise
        System.out.println();
        System.out.println("next");
        foo = foo.rotate(false);
        System.out.println(foo);
        foo = foo.rotate(false);
        System.out.println(foo);
        foo = foo.rotate(false);
        System.out.println(foo);
        foo = foo.rotate(false);
        System.out.println(foo);
    }

}