package PlayGame;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Screen Settings
    final int originalTileSize = 16; // 16 x 16 tiles
    final int scale = 4;

    final int sizeTile = originalTileSize  * scale;
    // How many tiles can be displayed on the screen

    final int maxScreenCol = 16;
    final int getMaxScreenRow = 12;
    final int screenWidth = sizeTile * maxScreenCol;   // 768 pixels
    final int screenHeight = sizeTile  * getMaxScreenRow;  // 576 pixels

    Thread gameThread;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

    }

    public void startGameThread(){
        // we are passing gamePanel to threads class.
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
      // we will create a game-loop here
        while (gameThread != null){
            System.out.println("the game loop is running");
        }

    }
}
