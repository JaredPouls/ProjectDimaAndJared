import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JPanel {

    public static final int FRAMEWIDTH = 1650, FRAMEHEIGHT = 900;

    private Timer timer;
    private World theWorld;

    public Main(){

        theWorld = new World(FRAMEWIDTH, FRAMEHEIGHT);

        //These are the Sprites that are added to the World... bruh
        for (int i = 0; i < 51; i++) {
            int rand = (int) (Math.random() * 3);
            int x = (int) (Math.random() * 500 + 50);
            int y = (int) (Math.random() * 500 + 50);
            if (rand == 0) ;
        }





        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                //This will call update on each sprite.
                theWorld.updateSprites();
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

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

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

    }

    //sets ups the panel and frame.
    public static void main(String[] args) {
        JFrame window = new JFrame("SpriteLand");
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