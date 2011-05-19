/**
 *  CS1312: Program 4 - Spring 2001
 *  <PRE>
 *  
 *  Revisions: 1.0 April 13, 2001
 *                 created BlockRenderer.java
 *                 and finished it
 *
 *  </PRE>
 *  @author <A HREF="mailto:gte639v@prism.gatech.edu">Blanton Black</A>
 *  @version Version 1.0, April 13, 2001
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BlockRenderer extends JPanel {
    // the field
    private TetrisBlock[][] blocks;
    
    private int xMax;
    private int yMax;
    private int width;
    private int height;
    
   /**
    * public constructor creates new BlockRenderer
    */
    public BlockRenderer () {
        this.setSize(200, 360);
    }
    
   /**
    * public function updates the BlockRenerer component
    * @param newBlocks an array of TetrisBlocks to be represented
    */
    public void update (TetrisBlock[][] newBlocks) {
        blocks = newBlocks;
        xMax = blocks.length;
        yMax = blocks[0].length;
        
        repaint();
    }
    
   /**
    * public function overrides paintComonent
    * @param g a graphics object
    */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //System.out.println(blocks.length);
        //System.out.println(blocks[0].length);
        try {
        width = this.getWidth() / xMax;   //System.out.println(getWidth());
        height = this.getHeight() / yMax; //System.out.println(getHeight());
        
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                //System.out.println(i + "\t" + j);
                if (blocks[i][j] != null) {
                    //System.out.println("found something!");
                    //System.out.println(i + "\t" + j);
                    
                    //g.setColor(blocks[i][j].getColor());
                    g.setColor(blocks[i][j].getColor());
                    g.fill3DRect(i*width, j*height, width, height, true);
                    //System.out.println(i*width + "\t" + j*height + "\t" + width + "\t" + height);
                } else {
                    // clear space
                    g.setColor(this.getBackground());
                    g.fillRect(i*width, j*height, width, height);
                }
            }
        }
        }
        catch (Exception e) {
            // probably either an arithmetic error
            // or an null pointer exception
            // just don't bother to repaint and continue
        }
        /*  // for testing purposes
        i = 0; j = 0; g.fillRect(i*width, j*height, width, height);
        i = 1; j = 1; g.fillRect(i*width, j*height, width, height);
        i = 2; j = 2; g.fillRect(i*width, j*height, width, height);
        i = 3; j = 3; g.fillRect(i*width, j*height, width, height);
        i = 4; j = 4; g.fillRect(i*width, j*height, width, height);
        i = 5; j = 5; g.fillRect(i*width, j*height, width, height);
        i = 6; j = 6; g.fillRect(i*width, j*height, width, height);
        i = 7; j = 7; g.fillRect(i*width, j*height, width, height);
        i = 8; j = 8; g.fillRect(i*width, j*height, width, height);
        i = 9; j = 9; g.fillRect(i*width, j*height, width, height);
        */
    }
    
    
    
   /**
    * public main for testing purposes
    * @param argv an array of command line arguments
    */
    public static void main (String[] argv) {
//        JFrame frame = new JFrame();
//        TetrisBoard tb = new TetrisBoard(1);
//        BlockRenderer br = new BlockRenderer(/*tb.getTetrisBlocks()*/);
//        
//        frame.getContentPane().setLayout(new BorderLayout());
//        frame.setSize(tb.getTetrisBlocks().length*20, tb.getTetrisBlocks()[0].length*20);
//        
//        frame.getContentPane().add(br);
//        
//        frame.show();
//        
//        //br.setSize(200, 200);
//        //System.out.println(br.getWidth());
//        
//        tb.placeNextPiece();
//        br.update(tb.getTetrisBlocks());
//        tb.movePieceDown(false);br.update(tb.getTetrisBlocks());
//        tb.movePieceDown(false);br.update(tb.getTetrisBlocks());
//        tb.movePieceDown(false);br.update(tb.getTetrisBlocks());
//        tb.movePieceDown(false);
//        br.update(tb.getTetrisBlocks());
//        //System.out.println(tb);
//        
//        
//        frame.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
//        
    }
}