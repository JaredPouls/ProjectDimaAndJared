import sun.tools.tree.WhileStatement;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JPanel {

    public static final int FRAMEWIDTH = 1400, FRAMEHEIGHT = 800;// thing

    private Timer timer;
    private World theWorld;
    private Sprite boi = new Boi(100,100,Sprite.NORTH,theWorld);
    private ArrayList<Sprite> badboi = new ArrayList<Sprite>();
    private boolean[] keys;
    private ArrayList<Sprite> bullets = new ArrayList<Sprite>();
    private int level;
    private int points = 0;

    public Main(){

        theWorld = new World(FRAMEWIDTH, FRAMEHEIGHT);
        theWorld.setBackground("Backy.jpeg");
        keys = new boolean[512];

//        for (int i = 0; i < 5; i++) {
//
//
//            badboi.add(new BadBoi((int) (Math.random() * (FRAMEWIDTH-150)+75), (int) (Math.random() * (FRAMEHEIGHT-150)+75), Sprite.NORTH, theWorld));
//
//        }
//
//        for (Sprite b: badboi) {
//            bullets.add(new Bullet(b.getLoc().x, b.getLoc().y, theWorld, boi));
//
//        }
        level = 0;

        //loadLevel();






        //These are the Sprites that are added to the World... bruh.
        for (int i = 0; i < 51; i++) {
            int rand = (int) (Math.random() * 3);
            int x = (int) (Math.random() * 500 + 50);
            int y = (int) (Math.random() * 500 + 53);
            if (rand == 0) ;
        }

        timer = new Timer(40, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(keys[KeyEvent.VK_W]){
                    boi.setDir(Sprite.NORTH);
                    boi.update();
                }
                if(keys[KeyEvent.VK_S]) {
                    boi.setDir(Sprite.SOUTH);
                    boi.update();
                }
                if(keys[KeyEvent.VK_A]){
                    boi.setDir(Sprite.WEST);
                    boi.update();
                }
                if(keys[KeyEvent.VK_D]){
                    boi.setDir(Sprite.EAST);
                    boi.update();
                }
                if(keys[KeyEvent.VK_R]){
                    level = 1;
                    loadLevel();
                    boi.setLoc(new Point(100, 100));
                    points = 0;
                }
                if(keys[KeyEvent.VK_SPACE]){
                    level = 1;
                    loadLevel();
                    boi.setLoc(new Point(100, 100));
                    points = 0;
                }
                if(keys[KeyEvent.VK_M]){
                    level = 0;
                    loadLevel();
                }
                //This will call update on each sprite.
                theWorld.updateSprites();
//                for(Sprite b: badboi){
//                    b.update();
//                }



                for(Sprite b: bullets){
                    b.update();
                    if(b.intersects(boi)){
                        boi.setLoc(new Point(1000000, 10000000));

                    }
                }
                for (Sprite b: bullets) {
                    for (Sprite c: badboi) {

                        if(((Bullet)b).getCounter() >= 20) {
                            if (b.intersects(c)) {
                                b.setLoc(new Point(1000000, 100000000));
                                c.setLoc(new Point(100000, 10000000));
                                points++;
                            }
                            if (c.intersects(boi)){
                                boi.setLoc(new Point(1000000, 10000000));
                            }
                        }

                    }

                }
                if(isDead() == true && level > 0){
                    level++;
                    loadLevel();
                }



                if(boi.getLoc().x == 1000000)
                    for(Sprite s: bullets){
                        ((Bullet)s).setSpeed(0);
                    }


                if(level == 3 && isDead() == true){

                }


                repaint();
            }


        });
        timer.start();


        //EventListeners.  Not using them at the moment.
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
               keys[keyEvent.getKeyCode()] =  true;
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] =  false;
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                //Ask the world if any sprites contain the click
                theWorld.click(mouseEvent);

                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });


    }

    //Our paint method.
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;



        //Draws all the sprites.
        theWorld.drawSprites(g2);
        boi.draw(g2);
        for (Sprite b: badboi) {
            b.draw(g2);


        }

        for (Sprite b: bullets) {
            b.draw(g2);

        }
        g2.setPaint(Color.GREEN);
        g2.drawString("Points: " + points, 50, 50);

        if(level == 0){
            g2.setPaint(Color.WHITE);
            g2.fillRect(0, 0, FRAMEWIDTH, FRAMEHEIGHT);
            g2.setPaint(Color.BLUE);
            g2.setFont(new Font("Arial", Font.BOLD, 32));
            g2.drawString("WELCOME TO", FRAMEWIDTH/2, FRAMEHEIGHT/3);
            g2.setFont(new Font("Arial", Font.BOLD, 46));
            g2.drawString("DODGE", FRAMEWIDTH/2, FRAMEHEIGHT/2);
            g2.setFont(new Font("Arial", Font.BOLD, 24));
            g2.drawString("By: Dima, and Jared", FRAMEWIDTH/2, FRAMEHEIGHT*2/3);
            g2.drawString("Press SPACE BAR to play", FRAMEWIDTH/3 - 100, FRAMEHEIGHT/2);

        }

        if(boi.getLoc().x >= FRAMEWIDTH + 500 && level > 0){
            g2.setPaint(Color.BLACK);
            g2.fillRect(0, 0, FRAMEWIDTH, FRAMEHEIGHT);
            g2.setPaint(Color.RED);
            g2.setFont(new Font("Arial", Font.BOLD, 32));
            g2.drawString("YOU LOOSE", FRAMEWIDTH/2, FRAMEHEIGHT/3);
            g2.setFont(new Font("Arial", Font.BOLD, 46));
            g2.drawString("GAME OVER", FRAMEWIDTH/2, FRAMEHEIGHT/2);
            g2.setFont(new Font("Arial", Font.BOLD, 24));
            g2.drawString("PRESS R TO RESTART", FRAMEWIDTH/2, FRAMEHEIGHT*2/3);
            g2.drawString("You Got " + points + " Point(s).", FRAMEWIDTH/3, FRAMEHEIGHT/2);
            g2.drawString("Press M for main menu", FRAMEWIDTH/3 - 100, FRAMEHEIGHT*2/3);

        }
        if(level == 3 && isDead() == true && level > 0){
            g2.setPaint(Color.BLACK);
            g2.fillRect(0, 0, FRAMEWIDTH, FRAMEHEIGHT);
            g2.setPaint(Color.GREEN);
            g2.setFont(new Font("Arial", Font.BOLD, 32));
            g2.drawString("CONGRATULATIONS", FRAMEWIDTH/2, FRAMEHEIGHT/3);
            g2.setFont(new Font("Arial", Font.BOLD, 46));
            g2.drawString("YOU WIN!!!!", FRAMEWIDTH/2, FRAMEHEIGHT/2);
            g2.setFont(new Font("Arial", Font.BOLD, 24));
            g2.drawString("PRESS R TO RESTART", FRAMEWIDTH/2, FRAMEHEIGHT*2/3);
            g2.drawString("You Got " + points + " Point(s).", FRAMEWIDTH/3, FRAMEHEIGHT/2);
            g2.drawString("Press M for main menu", FRAMEWIDTH/3 - 100, FRAMEHEIGHT*2/3);

        }

    }

    public void loadLevel(){


        if(level == 0){
            for(Sprite b: bullets){
                ((Bullet)b).setSpeed(0);
            }

        }

        if(level == 1){

            badboi.clear();
            bullets.clear();
            for (int i = 0; i < 5; i++) {
//                badboi.clear();
//                bullets.clear();
                BadBoi b = new BadBoi((int) (Math.random() * (FRAMEWIDTH-150)+75), (int) (Math.random() * (FRAMEHEIGHT-150)+75), Sprite.NORTH, theWorld);
                badboi.add(b);
//                for(Sprite b: badboi){
                    bullets.add(new Bullet(b.getLoc().x, b.getLoc().y, theWorld, boi));

            }
        }
        if(level == 2){
            badboi.clear();
            bullets.clear();
            for (int i = 0; i < 6; i++) {

                BadBoi b = new BadBoi((int) (Math.random() * (FRAMEWIDTH-150)+75), (int) (Math.random() * (FRAMEHEIGHT-150)+75), Sprite.NORTH, theWorld);
                badboi.add(b);
//                for(Sprite b: badboi){
                bullets.add(new Bullet(b.getLoc().x, b.getLoc().y, theWorld, boi));
//                for(Sprite e: bullets){
//                    e.setSpeed((int)(Math.random() * 7) + 5);
//                }
                //timer.setDelay(timer.getDelay() - 10);


            }
        }
        if(level == 3){
            badboi.clear();
            bullets.clear();
            for (int i = 0; i < 7; i++) {

                BadBoi b = new BadBoi((int) (Math.random() * (FRAMEWIDTH-150)+75), (int) (Math.random() * (FRAMEHEIGHT-150)+75), Sprite.NORTH, theWorld);
                badboi.add(b);
//                for(Sprite b: badboi){
                bullets.add(new Bullet(b.getLoc().x, b.getLoc().y, theWorld, boi));

            }
        }

    }
    public boolean isDead(){
        if(badboi.size()>=0) {
            for (Sprite b : badboi) {
                if (b.getLoc().x != 100000) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    //sets ups the panel and frame.
    public static void main(String[] args) {
        JFrame window = new JFrame("Dodger Land");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT + 22); //(x, y, w, h) 20 due to title bar.

        Main panel = new Main();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);

        panel.setFocusable(true);
        panel.grabFocus();


        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}