/**
 *  CS1312: Programming Assignment #2 - Spring 2001
 *  <PRE>
 *  TetrisBoard - 
 *                
 *                
 *
 *  Revisions: 1.0 Jan 30, 2001
 *                 created TetrisBoard.java
 *
 *             1.1 Feb 1, 2001
 *                 Work some more.
 *
 *             1.2 Feb 3, 2001
 *                 Finished up move down and sideways
 *             
 *             1.3 Feb 5, 2001
 *                 finished up the rotate functions
                   polished things up a little
 *
 *                 
 *
 *  </PRE>
 *  @author <A HREF="mailto:gte639v@prism.gatech.edu">Blanton Black</A>
 *  @version Version 1.1, Feb 1, 2001
 */
public class TetrisBoard implements P2Constants {
    TetrisBlock[][] board = new TetrisBlock[BOARD_WIDTH][BOARD_HEIGHT];
    TetrisPiece currentPiece;
    TetrisPiece nextPiece;
    
    int[] position = new int[2];    //i need a way to track movement
    private int xOffset, yOffset;
    
    int linesCompleted;
    int level;      //lines completed / (10 + starting level)
    int startingLevel;
    long score;
    
    
    
    
    /** 
     * pulic constructor to instantiate a new tetris board
     */
    public TetrisBoard() {
        //the default constructor
        //constructor chaining
        this(1);    //pass the level
    }
    
    /**
     * public constructor to instantiate a new tetris board
     * @param curStartingLevel the inital starting level
     */
    public TetrisBoard(int curStartingLevel) {
        //board = new TetrisBlock[BOARD_WIDTH][BOARD_HEIGHT];
        currentPiece = new TetrisPiece(getRandomPieceNum());
        nextPiece = new TetrisPiece(getRandomPieceNum());
        
        linesCompleted = 0;
        startingLevel = curStartingLevel;  //might want to set this up differently
        level = curStartingLevel;  //this should be safe
        score = 0;
    }
    
    
    /**
     * a private function to determine where to
     * place the next piece on the board
     * @return true if possible
     */
    public boolean placeNextPiece() {
        boolean retVal = true;  //this is the more likely case
        
        //figure out where the top would fit
        
        int i = 0;
        int j = 0;
        //find the top
        TetrisBlock[][] temp = currentPiece.getTetrisBlocks();
        while (temp[i][j] == null) {
            if (i == (temp.length - 1)) {
                j++;
                i = 0;
            } else {
                i++;
            }
        }
        yOffset = j;
        
        //find leftEdge
        xOffset = (BOARD_WIDTH / 2) - ((temp.length - 1) / 2) - 1;
        //System.out.println("topmost @ " + yOffset + " and left edge @ " + xOffset);
        
        // collision check
        i = 0;
        j = 0;
        //board[3][0] = new TetrisBlock();
        for (j = yOffset; j < temp.length; j++) {
            for (i = 0; i < temp.length; i++) {
                if (temp[i][j] != null && board[i + xOffset][j - yOffset] != null) {    //then collision
                    retVal = false;
                //System.out.println(temp[i][j] + " @ " + i ", " + j);
                }
            }
        }
        
        //for (i = 0; i<temp.length; i++) {
        //    for (j = 0; j<temp.length; j++) {
        //        if (temp[i][j] != null) {
        //            System.out.println("-> " + (i + xOffset) + "," + (j - yOffset));
        //        }
        //    }
        //}
        position[0] = 0 + xOffset;
        position[1] = 0 - yOffset;
        
        
        //but special case here makes game over
        return retVal;
    }
    
    /**
     * a public function to get a copy of the current tetris blocks
     * @return a tetris block array
     */
    public TetrisBlock[][] getTetrisBlocks() {
        //currentPiece = currentPiece.rotate(true);
        
        TetrisBlock[][] foo = (TetrisBlock[][]) cloneBoard();      //(TetrisBlock[][]) board.clone();
        //TetrisBlock[][] foo = new TetrisBlock[BOARD_WIDTH][BOARD_HEIGHT];
        //TetrisBlock[][] blocks = currentPiece.getTetrisBlocks();
        
        //copy the tetris piece into the temporary board
        for (int y = 0; y < currentPiece.getTetrisBlocks().length; y++) {        //blocks.length; y++) {
            for (int x = 0; x < currentPiece.getTetrisBlocks().length; x++) {      //blocks.length; x++) {
                if (currentPiece.getTetrisBlocks()[x][y] != null) {      //blocks[x][y] != null) {
                    foo[x + position[0]][y + (position[1])] = currentPiece.getTetrisBlocks()[x][y];        //blocks[x][y];
                }
            }
        }
        
        return foo;
    }
    
    /**
     * a public that I made to return a copy of the current tetris board array
     * note: the clone function could have been overridden
     * @return an array of tetis blocks on the board
     */
    private TetrisBlock[][] cloneBoard() {
        TetrisBlock[][] foo = new TetrisBlock[BOARD_WIDTH][BOARD_HEIGHT];
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                foo[x][y] = board[x][y];
            }
        }
        return foo;
    }
    
    /**
     * a private function to return the a random number
     * the number is used to create a new tetris piece
     * @return a random number to be sent to create a new tetris piece
     */
    private int getRandomPieceNum() {
        return (int) (Math.random() * PIECE.length);
    }
    
    /**
     * a public function to override the default toString
     * @return a string representing the current contents of the tetris board
     */
    public String toString () {
        int i = 0;
        int j = 0;
        String retVal;
        retVal = "A Tetris Board with a score of " + score + " and level " + level + " that looks like:";
        
        TetrisBlock[][] temp = currentPiece.getTetrisBlocks();
        String foopar = "";
        for (int y = 0; y < temp[0].length; y++) {
            foopar = foopar + "\n";
            for (int x = 0; x < temp.length; x++) {
                if (temp[x][y] != null) {
                    foopar = foopar + "#";
                } else {
                    foopar = foopar + "-";
                }
            }
        }
        //System.out.println(foopar);
        for (j = 0; j < board[0].length; j++) {
            retVal = retVal + "\n";
            for (i = 0; i < board.length; i++) {
                if (board[i][j] != null) {
                    retVal = retVal + "#";
                } else {
                    retVal = retVal + "-";
                }
            }
        }   //end for
        return retVal;
    }
    
    
    
    
    /**
     * a function to move the piece down the board
     * @param isForced set to true if the move is forced
     * @return returns true if downward movement was successful
     */
    public boolean movePieceDown(boolean isForced) {
        // if isForced is true, then the down movement is forced
        //return true if the down was successful
        
        //check to see if a downward movement is possible
        boolean possible = true;
        TetrisBlock[][] pieceArray = currentPiece.getTetrisBlocks();
        for (int y = 0; y < pieceArray.length; y++) {
            for (int x = 0; x < pieceArray.length; x++) {
                //loop through this stuff and determine if possible
                if (pieceArray[x][y] != null) {   //only check if there is something there
                    if ( (y + position[1]) >= (BOARD_HEIGHT - 1)) {
                        //edge of board right beneath
                        possible = false;
                    } else if (board[ (x + position[0]) ] [ (y + position[1] + 1) ] != null) {
                        //a piece on board right beneath
                        possible = false;
                    } else {
                        //not a prob
                    }
                }
            }
        }
        if (possible) {
            //move down
            position[1]++;
            
            
        } else {    //not possible
                //place piece on board
            board = getTetrisBlocks();
                //next piece becomes current
            currentPiece = getNextPiece();
                //randomly choose next
            nextPiece = new TetrisPiece(getRandomPieceNum());
                //check for completed lines
                    //for every line completed
                        //remove it
                            //shift all blocks down one
                        //modify score
                            //  (SCORE_LINES[lines_completed] * (SCORE_BASE + (level * SCORE_LEVEL_MODIFIER)))
                boolean line;
                int linesCompleted = 0;
                for ( int b = (BOARD_HEIGHT - 1); b > 0 ; b--) {
                    line = true;
                    //start from the bottom and go up
                    for ( int a = 0 ; a < BOARD_WIDTH; a++) {
                        //loop through this entire line
                        //looking for a break
                        if (board[a][b] == null) {
                            line = false;
                            //a break found should end search
                        }
                    }
                    if (line) {
                        linesCompleted++;
                        //if we have a line, move all that's up down one
                        for (int d = b; d > 0; d--) {
                            for ( int c = 0; c < BOARD_WIDTH; c++) {
                                board[c][d] = board[c][d-1];
                            }
                            
                        }
                    b++;
                    }
                }
                score+= (SCORE_LINES[linesCompleted] * (SCORE_BASE + (level * SCORE_LEVEL_MODIFIER)));
        }
        
        //check if it is forced
        if (isForced && !possible) {     // a forced movement
            //change the score
            //i'm not really sure how to do this
            score+= SCORE_MOVE_DOWN * level;
        }
        
        return possible;
    }
    
    /**
     * public function to move the piece sideways
     * @param isRight set to true if attempt to move right
     * @return returns true if possible
     */
    public boolean movePieceSideways(boolean isRight) {
        boolean possible = true;
        TetrisBlock[][] pieceArray = currentPiece.getTetrisBlocks();
        int farRight = 0;
        int farLeft = 0;
        
        if (isRight) {  //check on go to right
            //find far right
            farRight = 0;
            for (int x = (pieceArray.length - 1); x > 0; x--) {
                for (int y = 0 ; y < pieceArray.length ; y++) {
                    if (pieceArray[x][y] != null) {
                        if (x > farRight) {
                            farRight = x;
                        }
                    }
                }
            }
            if ((farRight + position[0]) >= (BOARD_WIDTH - 1)) {
                possible = false;
            }
        } else {    //check on go to left
            //find far left
            farLeft = pieceArray.length;
            for (int x = 0 ; x < (pieceArray.length - 1) ; x++) {
                for (int y = 0; y < pieceArray.length; y++) {
                    if (pieceArray[x][y] != null) {
                        if (x < farLeft) {
                            farLeft = x;
                        }
                    }
                }
            }
            if ((farLeft + position[0]) <= 0) {
                possible = false;
            }
        }
        
        if (possible) {
            for ( int a = 0; a < pieceArray.length; a++) {
                for ( int b = 0; b < pieceArray.length; b++) {
                    if ( pieceArray != null) {
                        if (isRight) {
                            //check right side
                            if ( pieceArray[a][b] != null && board[position[0] + a + 1][position[1] + b] != null) {
                                possible = false;
                            }
                        } else {
                            //check left side
                            if ( pieceArray[a][b] != null && board[position[0] + a - 1][position[1] + b] != null) {
                                possible = false;
                            }
                        }
                    }
                }
            }
        }
        
        
        
        if (possible && isRight) {
            //move right
            position[0]++;
        } else if (possible && !isRight) {
            //move left
            position[0]--;
        } else {
            //do nothing
        }
        return possible;
    }
    
    /**
     * public function to rotate the piece on the tetris board
     * @param isClockwise set to true if a clockwise attempt
     * @return true if successful
     */
    public boolean rotatePiece(boolean isClockwise) {
        boolean possible = true;
        //get a rotated piece
        TetrisPiece foopar = (TetrisPiece) currentPiece.rotate(isClockwise);
        //I reversed the above line because the z and x keys seemed reversed
        TetrisBlock[][] temp = foopar.getTetrisBlocks();
        
        // now we have a piece, so see if it fits
        for (int y = 0; y < temp.length; y++) {
            for (int x = 0; x < temp.length; x++) {
                if (temp[x][y] != null) {
                    if ( (y + position[1]) < 0 ) {
                        //new piece causes collision with top of board
                        possible = false;
                    
                    } else if ( (x + position[0]) < 0) {
                        //new piece causes over left edge
                        possible = false;
                        //position[0]++;
                    } else if ( (x + position[0] + 1) > BOARD_WIDTH) {
                        //new piece causes over right edge
                        possible = false;
                        //position[0]--;
                    } else if (board[x + position[0]][y + position[1]] != null) {
                        //piece collides with another piece
                        possible = false;
                    } else {
                        //not a prob
                    }
                }
            }
        }
        
        if (possible) {
            currentPiece = foopar;
        }
                
        return possible;
    }
    
    
    /**
     * public accessor to get the next piece
     * @return the next tetris piece
     */
    public TetrisPiece getNextPiece () {
        return nextPiece;
    }
    
    /**
     * public accessor to get the level
     * @return the level number
     */
    public int getLevel () {
        return level;
    }
    
    /**
     * public accessor to get the score
     * @return the score
     */
    public long getScore () {
        return score;
    }
    
    /**
     * public accessor to get the number of lines completed
     * @return the number of lines completed
     */
    public int getLines () {
        return linesCompleted;
    }
    
    /**
     * Main method.
     * @param agrv array of command line arguments
     */
    public static void main (String[] argv) {
        TetrisBoard tb = new TetrisBoard();
        System.out.println("current\n" + tb.currentPiece);
        //System.out.println("next\n" + tb.nextPiece);
        
        // place a piece on the board
        if(tb.placeNextPiece()){System.out.println("Piece Added");}else{System.out.println("Game Over");}
        //tb.printBoard();
        
        //add piece to board for real
        tb.position[1]=9;
        //tb.board = tb.getTetrisBlocks();
        System.out.println(tb);//tb.printBoard();
        //tb.position[1] = 15;
        //if(tb.movePieceDown(false)){System.out.println("Moved Down");}else{System.out.println("No More Down");};
        //tb.position[1] = 16;
        //if(tb.movePieceDown(false)){System.out.println("Moved Down");}else{System.out.println("No More Down");};
        //tb.position[1] = 17;
        //if(tb.movePieceDown(false)){System.out.println("Moved Down");}else{System.out.println("No More Down");};
        
        
        // Please note:
        // I found that once I had enough code completed, I could use the Tetris class to test it.
        // I found this much more usefull as opposed to using this test main
        // I did make extensive use of the test main, until I found it less and less productive.
    }
}