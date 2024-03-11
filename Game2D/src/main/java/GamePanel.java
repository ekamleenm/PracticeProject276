

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{
    public static int w;
    public static int h;
    private Thread thread;
    private boolean running = false;
    private Graphics2D graphics2D;
    private BufferedImage img;


    public GamePanel(int width, int height) {
        w = width;
        h = height;
        setPreferredSize(new Dimension(w, h));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();
        if(thread == null){
            thread = new Thread(this);
            thread.start();
        }
    }


    public void init() {
        running =true;
        img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        graphics2D = (Graphics2D) img.getGraphics();
    }

    @Override
    public void run() {
       init();
       final double GAME_HERTZ = 60.0;
       final double TBU = 1000000000/GAME_HERTZ;     // Time before update
       final int MUBR = 5;  // Must update before render

       double lastUpdateTime = System.nanoTime();
       double lastRenderTime;

       final double TARGET_FPS = 60;
       final double TTBR = 1000000000/ TARGET_FPS;   // Total time before render

        int frameCount = 0;
        int lastSecondTime  = (int)  (lastUpdateTime/1000000000);
        int oldframeCount = 0;

       while(running){
           double now = System.nanoTime();
           int updateCount = 0;
           while((now - lastUpdateTime)>TBU && (updateCount<MUBR)){
               update();
           }

           render();
           draw();
       }
    }

    private int x = 0;

    public void update(){
        x++;
        System.out.println(x);
    }

    public void render(){
           if(graphics2D != null){
               graphics2D.setColor(new Color(79, 141, 243));
               graphics2D.fillRect(0,0,w,h);
           }
    }

    public void draw(){
       Graphics2D g2 =  (Graphics2D) this.getGraphics();
       g2.drawImage(img, 0,0,w,h,null);
       g2.dispose();
    }
}
