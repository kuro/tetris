/**
 *  CS1312: Program 6 - Spring 2001
 *  <PRE>
 *  
 *  Revisions: 1.0 April 13, 2001
 *                 created Tetris.java
 *                 
 *             1.1 April 14, 2001
 *                 finished Tetris.java
 *
 *  </PRE>
 *  @author <A HREF="mailto:gte639v@prism.gatech.edu">Blanton Black</A>
 *  @version Version 1.0, April 14, 2001
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Tetris {
    
    // field variables
    private JFrame frame;
    private BlockRenderer br, br2;
    private TetrisBoard tb;
    private JPanel jPanel1, jPanel2, jPanel3;
    
    private JLabel score1, score2, level1, level2;
    
    // menu resources
    private JMenuBar jMenuBar;
    private JMenu jMenu1, jMenu2, jMenu3;
    private JMenuItem pause, instruct;
    private JMenuItem[] jLev;
    private Timer timer;
    
    
    // these just allow for better functionality overall
    // but not so much essential
    boolean isPaused = true;
    boolean pauseAble = false;
    
    
   /**
    * public constructor to create a new Tetris
    */
    public Tetris () {
        initComponents();
        initMenu();
        initMenuHandler();
        initHandler();
        
        //frame.pack();
        frame.show();
        
        // test it out
        //tb.placeNextPiece();
        //br.update(tb.getTetrisBlocks());
        br.update(niceGrid());
        br2.update(niceGrid2());
        
    }
    
    
   /**
    * private helper returns a nice looking array of TetrisBlocks
    * which may be passed to the BlockRenderer and Displayed on-screen
    * @return an array of TetrisBlocks
    */
    private TetrisBlock[][] niceGrid  () {
        TetrisBlock[][] foo = new TetrisBlock[10][18];
        for (int i = 9; i >= 0; i--) {
            for (int j = 17; j >= 0; j--) {
                
                foo[i][j] = new TetrisBlock(new Color( (1-((float)i)/9f) , (1-((float)j)/17f) , .25f ));
            }
        }
        return foo;
    }
    
   /**
    * private helper returns a nice looking array of TetrisBlocks
    * which may be passed to the BlockRenderer and Displayed on-screen
    * a graphic representaion of the word "Tetris"
    * @return an array of TetrisBlocks
    */
    private TetrisBlock[][] niceGrid2  () {
        // none of this is called for
        // i did it for the fun of it
        // i'm sure there are faster ways of doing it
        // but this was quick and dirty
        
        TetrisBlock[][] foo = new TetrisBlock[30][5];
        
        boolean[][] grid = new boolean[30][5];
        // T
        grid[1][0] = true;
        grid[2][0] = true;
        grid[3][0] = true;
        grid[2][1] = true;
        grid[2][2] = true;
        grid[2][3] = true;
        grid[2][4] = true;
        // E
        grid[6][0] = true;
        grid[6][1] = true;
        grid[6][2] = true;
        grid[6][3] = true;
        grid[6][4] = true;
        grid[7][0] = true;
        grid[8][0] = true;
        grid[7][2] = true;
        grid[7][4] = true;
        grid[8][4] = true;
        // T
        grid[11][0] = true;
        grid[12][0] = true;
        grid[13][0] = true;
        grid[12][1] = true;
        grid[12][2] = true;
        grid[12][3] = true;
        grid[12][4] = true;
        // R
        grid[16][0] = true;
        grid[17][0] = true;
        grid[16][1] = true;
        grid[18][1] = true;
        grid[16][2] = true;
        grid[17][2] = true;
        grid[16][3] = true;
        grid[18][3] = true;
        grid[16][4] = true;
        grid[19][4] = true;
        // I
        grid[21][0] = true;
        grid[22][0] = true;
        grid[23][0] = true;
        grid[22][1] = true;
        grid[22][2] = true;
        grid[22][3] = true;
        grid[21][4] = true;
        grid[22][4] = true;
        grid[23][4] = true;
        // S
        grid[27][0] = true;
        grid[28][0] = true;
        grid[26][1] = true;
        grid[27][2] = true;
        grid[28][3] = true;
        grid[26][4] = true;
        grid[27][4] = true;
        
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 5; j++) {
                if (grid[i][j]) foo[i][j] = new TetrisBlock(new Color( ((float)i)/29f , ((float)j)/5f , .25f ));
            }
        }
        
        /*
                   11111111112222222222
         012345678901234567890123456789
        0 ###  ###  ###  ##   ###   ## 0
        1  #   #     #   # #   #   #   1
        2  #   ##    #   ##    #    #  2
        3  #   #     #   # #   #     # 3
        4  #   ###   #   #  # ###  ##  4
         012345678901234567890123456789
                   11111111112222222222
        */
        
                           
        return foo;
    }
    
   /**
    * private method to set up the main gui components
    */
    private void initComponents () {
        frame = new JFrame("Tetris");
        tb = new TetrisBoard(0);
        jPanel2 = new JPanel(null);
        br = new BlockRenderer();
        
        // i made a border for the primary BlockRenderer
        br.setBorder(BorderFactory.createBevelBorder(0));
        br.setBorder(BorderFactory.createLineBorder(Color.black));
        //jPanel2.setSize(300, 300);
        
        br.setSize(10*20, 18*20);
        br.setLocation(0, 0);
        jPanel2.add(br);
        //System.out.println(jPanel2.getWidth());
        
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(jPanel2, BorderLayout.CENTER);
        
        
        jPanel1 = new JPanel(new GridLayout(6, 1));
        br2 = new BlockRenderer();
        score1 = new JLabel("Score:");
        score2 = new JLabel(Long.toString(tb.getScore()));
        level1 = new JLabel("Level:");
        level2 = new JLabel(Long.toString(tb.getLevel()));
        
        JPanel panel1 = new JPanel(new GridLayout(2,1));
        JPanel panel2 = new JPanel(new GridLayout(2,1));
        panel1.add(score1);
        panel1.add(score2);
        panel2.add(level1);
        panel2.add(level2);
        
        //jPanel1.add(new JPanel());
        //jPanel1.add(score1);
        //jPanel1.add(score2);
        jPanel1.add(panel1);
        //jPanel1.add(new JPanel());
        //jPanel1.add(level1);
        //jPanel1.add(level2);
        jPanel1.add(panel2);
        jPanel1.add(new JPanel());
        jPanel1.add(new JLabel("Next:"));
        jPanel1.add(br2);
        jPanel1.add(new JPanel());
        
        
        frame.getContentPane().add(jPanel1, BorderLayout.EAST);
        
        // i found this at the last minute
        // otherwise, i would have used it more
        br2.setPreferredSize(new Dimension(50, 50));
        
        frame.setSize(263, 410);
        
    }
    
   /**
    * a private method to initialize the menu
    */
    private void initMenu() {
        jMenuBar = new JMenuBar();
        jMenu1 = new JMenu("Game");
        jMenu2 = new JMenu("Help");
        jMenu3 = new JMenu("New Game");
        pause = new JMenuItem("Pause Game");
        instruct = new JMenuItem("Instructions");
        jLev = new JMenuItem[10];
        for (int i = 0; i < 10; i++) {
            jLev[i] = new JMenuItem("Starting at Level " + Integer.toString(i));
            jMenu3.add(jLev[i]);
        }
        
        jMenu1.add(jMenu3);
        jMenu1.add(pause);
        jMenu2.add(instruct);
        jMenuBar.add(jMenu1);
        jMenuBar.add(jMenu2);
        
        frame.setJMenuBar(jMenuBar);
    }
    
   /**
    * private method that sets up event handling for the menu system
    */
    private void initMenuHandler () {
        // user hits pause in menu
        pause.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                //System.out.println(e.getActionCommand());
                pauseGame();
            }
        });
        
        // user hits instructions in menu
        // as i recall, this was called for in the info file
        // but as of yet, i have not seen what to do with it!
        // 
        instruct.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                //System.out.println(e.getActionCommand());
            }
        });
        
        // user hits one of the new game options
        // this implementation seems to work well
        for (int i = 0; i < 10; i++) {
            jLev[i].addActionListener( new ActionListener() {
                public void actionPerformed( ActionEvent e ) {
                    //System.out.println(e.getActionCommand());
                    String command = e.getActionCommand();
                    //Integer.parseInt(command.charAt(command.length()-1));
                    // the only trick is that
                    // we must find which item was actually clicked on
                    // which level?
                    int i = Character.getNumericValue(command.charAt(command.length()-1));
                    //System.out.println(i);
                    startNewGame(i);
                }
            });
        }
    }
    
   /**
    * private method sets up the main event handling
    * including a keyboard listener and a timer
    */
    private void initHandler () {
        
        // set up keyboard handling
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed ( KeyEvent e ) {
                //System.out.println(e.getKeyCode());
                //if (!isPaused) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            //System.out.println("left");
                            if (!isPaused) doMovePieceSideways(false);
                            break;
                        case KeyEvent.VK_RIGHT:
                            //System.out.println("right");
                            if (!isPaused) doMovePieceSideways(true);
                            break;
                        case KeyEvent.VK_DOWN:
                            //System.out.println("down");
                            if (!isPaused) doMovePieceDown(true);
                            break;
                        case KeyEvent.VK_UP:
                        case KeyEvent.VK_X:
                            //System.out.println("rotate right")
                            if (!isPaused) doRotatePiece(true);
                            break;
                        case KeyEvent.VK_Z:
                            //System.out.println("rotate left");
                            if (!isPaused) doRotatePiece(false);
                            break;
                        case KeyEvent.VK_P:
                            //System.out.println("down");
                            pauseGame();
                            break;
                    }
                //}
            }
        });
        
        
        //timer.start();
        
        // set up window handling
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    
    
   /**
    * private method that starts a new game whenever requested
    * @param start an integer corresponding to the starting level and speed
    */
    private void startNewGame (int start) {
        int delay = (100 * (10 - start));
        
        tb = new TetrisBoard(start);
        tb.placeNextPiece();
        br.update(tb.getTetrisBlocks());
        score2.setText(Long.toString(tb.getScore()));
        level2.setText(Long.toString(tb.getLevel()));
        br2.update(tb.getNextPiece().getTetrisBlocks());
        
        if (!frame.getTitle().equals("Tetris")) frame.setTitle("Tetris");
        
        isPaused = false;
        pauseAble = true;
        // create a new timer
        // init timer
        try {
            timer.stop();
            timer.setDelay(delay);
        }
        catch (Exception e) {
            // timer doesn't exist, must create a new one
            //System.out.println("creating new timer");
            timer = new Timer (delay, new ActionListener() {
                public void actionPerformed( ActionEvent e) {
                    //System.out.println(e.getActionCommand());
                    doMovePieceDown(false);
                }
            });
        }
        
        timer.start();
    }
    
    
   /**
    * private method to rotate a piece as fired by the keyboard
    * @param isClockwise a clockwise rotation if true
    */
    private void doRotatePiece (boolean isClockwise) {
        if (tb.rotatePiece(isClockwise)) {
            // success
            br.update(tb.getTetrisBlocks());
        }
    }
    
   /**
    * private method to shift piece sideways as fired by the keyboard
    * @param isRight right shift if true
    */
    private void doMovePieceSideways (boolean isRight ) {
        if (tb.movePieceSideways(isRight)) {
            // success
            br.update(tb.getTetrisBlocks());
        }
    }
    
   /**
    * private method to move piece down as fired by the keyboard or timer
    * @param isForced forced true if called by keyboard
    */
    private void doMovePieceDown (boolean isForced) {
        //System.out.println("Ping");
        //if (!isForced) {
            if (tb.movePieceDown(isForced)) {
                //System.out.println("done");
                br.update(tb.getTetrisBlocks());
            } else {
                //System.out.println("oops");
                // at this point, add a new piece to the top
                // if the new piece doesn't fit
                // game over
                
                if (tb.placeNextPiece()) {
                    br.update(tb.getTetrisBlocks());
                    // then set preview space
                    br2.update(tb.getNextPiece().getTetrisBlocks());
                    // set score
                    score2.setText(Long.toString(tb.getScore()));
                    // set level
                    if (!level2.getText().equals(Long.toString(tb.getLevel()))) {
                        // the level changed
                        level2.setText(Long.toString(tb.getLevel()));
                        // reset time
                        // if change of level, reset timer
                        timer.setDelay((100 * (10 - tb.getLevel())));
                    }
                } else {
                    //System.out.println("Game Over");
                    frame.setTitle("Tetris - Game Over");
                    timer.stop();
                    br.update(niceGrid());
                    br2.update(niceGrid2());
                    pauseAble = false;
                    
                }
            }
        //} else {
        //    // a forced move
        //}
    }
    
    
   /**
    * private method which pauses the game
    */
    private void pauseGame () {
        // stuff like is pausable i added later
        // since it cannot be paused it there is no current game
        // this is just to prevent an glitches
        if (pauseAble) {
        if (isPaused) {
            // unpause
            
            try {
                timer.start();
                br.update(tb.getTetrisBlocks());
                br2.update(tb.getNextPiece().getTetrisBlocks());
                frame.setTitle("Tetris");
                isPaused=!isPaused;
            }
            catch (Exception e) {
                //isPaused = true;
                //System.out.println("timer start error");
            }
            
        } else {
            // pause
            //try {
                timer.stop();
                br.update(niceGrid());
                br2.update(niceGrid2());
                frame.setTitle("Tetris - Paused");
                isPaused=!isPaused;
            //}
            //catch (Exception e) {
            //    isPaused = true;
            //    System.out.println("timer stop error");
            //}
            
            
        }
        }
    }
    
   /**
    * public main to start Tetris, or Tetris
    * @param argv an array of command line arguments
    */
    public static void main (String[] argv) {
        Tetris fooTetris = new Tetris();
    }
}