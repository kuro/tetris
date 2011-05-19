/**
 *  CS1312: Programming Assignment #2 - Spring 2001
 *  <PRE>
 *  TetrisBlock - This class holds a color.
 *                But it only represents a single block
 *                which may be used to build other structures.
 *
 *  Revisions: 1.0 Jan 29, 2001
 *                 created TetrisBlock.java
 *
 *  </PRE>
 *  @author <A HREF="mailto:gte639v@prism.gatech.edu">Blanton Black</A>
 *  @version Version 1.0, Jan 29, 2001
 */

public class TetrisBlock {

    //instance variables
    private java.awt.Color blockColor;
    
    /** 
     * Public constructor creates a new TetrisBlock
     */
    public TetrisBlock() {
        this(java.awt.Color.black);
    }
    
    /** 
     * Public constructor creates a new TetrisBlock
     * @param newColor defines the new block's color
     */
    public TetrisBlock(java.awt.Color newColor) {
        setColor(newColor);
    }
    
    //accessors
    
    /**
     * returns the blocks color
     * @return the block's color
     */
    public java.awt.Color getColor() {
        return blockColor;
    }
    
    //modifiers
    
    /**
     * set the block's color
     * @param newColor the block color
     */
    public void setColor(java.awt.Color newColor) {
        blockColor = newColor;
    }
    
    /**
     * redifine the toString
     * @return return a string displaying the block's color
     */
    public String toString() {
        //redefines the default toString
        return ("TetrisBlock object with a color of " + getColor().toString());
    }
    
    /**
     * Main method.
     * @param agrv array of command line arguments
     */
    public static void main (String[] argv) {
        //test the TetrissBlock class
        TetrisBlock b = new TetrisBlock(java.awt.Color.red);
        System.out.println(b);
    }
    
}