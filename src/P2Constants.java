/**
 *  CS1312: Programming Assignment #2 - Spring 2001
 *  <PRE>
 *  P2Constants - a interface that consists constants that are used throughout
 *                the P2 assignment.
 *
 *  Revisions: 1.1 December 27, 2000
 *                 Added Phase III Constants and added documentation
 *             1.0 December 14, 2000
 *                 Created interface P2Constants
 *
 *  </PRE>
 *  @author <A HREF="mailto:demox@cc.gatech.edu">Luke A. Olbrish</A>
 *  @version Version 1.1, December 27, 2000
 */

public interface P2Constants
{  
  // Constants

  /**
   *  The start states for all the pieces in our Tetris Game.  This array is a
   *  three dimensional array that contains pieces indexed by the first index
   *  from 0 to PIECE.length.  Each index holds a two dimensional square array
   *  of booleans that represents the start state of the Tetris Pieces that
   *  will be used in our tetris game.  For example,
   *  PIECE[1][0][ PIECE[1][0].length ] will refer to the second Tetris Piece
   *  boolean stored in the lower left corner.  If the boolean is true, then a
   *  TetrisBlock should be created for the TetrisPiece, otherwise no
   *  TetrisBlock should be created.
   */
  public static final boolean[][][] PIECE =
  { { { false, true,  false, false },   // - - - -
      { false, true,  false, false },   // # # # #
      { false, true,  false, false },   // - - - -
      { false, true,  false, false } }, // - - - -

    { { false, true,  true  },          // - - -
      { false, true,  false },          // # # #
      { false, true,  false } },        // # - -

    { { false, true,  false },          // - - -
      { false, true,  false },          // # # #
      { false, true,  true  } },        // - - #

    { { false, true,  false },          // - - -
      { false, true,  true  },          // # # #
      { false, true,  false } },        // - # -

    { { false, true,  false },          // - - -
      { false, true,  true  },          // # # -
      { false, false, true  } },        // - # #

    { { false, false, true  },          // - - -
      { false, true,  true  },          // - # #
      { false, true,  false } },        // # # -

    { { true,  true },                  // # #
      { true,  true } } };              // # #

  /**
   *  The width of the Tetris Board.  This constant should be used in order to
   *  find and set the width of the Tetris Board and the components that use
   *  the Tetris Board.
   *
   *  @see #BOARD_HEIGHT
   */
  public static final int BOARD_WIDTH = 10;


  /**
   *  The height of the Tetris Board.  This constant should be used in order to
   *  find and set the height of the Tetris Board and the components that use
   *  the Tetris Board.
   *
   *  @see #BOARD_WIDTH
   */
  public static final int BOARD_HEIGHT = 18;


  /**
   *  The score modifier for forcing movement downward.  This modifier is
   *  applied only if movment occurs and the formula is SCORE_MOVE_DOWN *
   *  current level.
   */
  public static final int SCORE_MOVE_DOWN = 10;

  /**
   *  The array of values corresponding to the modifier used depending on how
   *  many lines are completed at one time.  The entire formula is
   *  SCORE_LINES[ lines_completed ] * ( SCORE_BASE + ( level *
   *  SCORE_LEVEL_MODIFIER )).
   *  
   *  @see #SCORE_BASE
   *  @see #SCORE_LEVEL_MODIFIER
   */
  public static final int[] SCORE_LINES = { 0, 10, 25, 45, 70, 100 };

  /**
   *  The base modifier in calculating score.  This value sets a basic level
   *  to the score.  The entire formula is SCORE_LINES[ lines_completed ] *
   *  ( SCORE_BASE + ( level * SCORE_LEVEL_MODIFIER )).
   *
   *  @see #SCORE_LEVEL_MODIFIER
   *  @see #SCORE_LINES
   */
  public static final int SCORE_BASE = 40;

  /**
   *  The modifier used in conjunction with the current level to add weight to
   *  the score in harder levels.  The entire formula is
   *  SCORE_LINES[ lines_completed ] * ( SCORE_BASE + ( level *
   *  SCORE_LEVEL_MODIFIER )).
   *
   *  @see #SCORE_BASE
   *  @see #SCORE_LINES
   */
  public static final int SCORE_LEVEL_MODIFIER = 5;

}// end of interface P2Constants
