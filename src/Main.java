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


    public Main(){

        theWorld = new World(FRAMEWIDTH, FRAMEHEIGHT);
        theWorld.setBackground("Backy.jpeg");
        keys = new boolean[512];

        for (int i = 0; i < 5; i++) {


            badboi.add(new BadBoi((int) (Math.random() * (FRAMEWIDTH - 150)+75), (int) (Math.random() * (FRAMEHEIGHT - 150)+75), Sprite.NORTH, theWorld));

        }

        for (Sprite b: badboi) {
            bullets.add(new Bullet(b.getLoc().x, b.getLoc().y, theWorld, boi));

        }




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
                            }
                        }

                    }

                }



                if(boi.getLoc().x == 1000000)
                    for(Sprite s: bullets){
                        ((Bullet)s).setSpeed(0);
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

        if(boi.getLoc().x == 1000000){
            g2.fillRect(0,0,FRAMEWIDTH,FRAMEHEIGHT);
        }

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