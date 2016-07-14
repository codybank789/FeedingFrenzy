package stupid;

import stupid.GameObjects.AutoFish;
import stupid.GameObjects.PlayerFish;
import stupid.Interface.ObjectManagerInterface;
import stupid.Models.GameImage;
import stupid.Models.GameObject;
import stupid.Models.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by NguyenDuc on 7/13/2016.
 */
public class GameWindow extends Frame implements Runnable, ObjectManagerInterface{
    public static final int WINDOWWIDTH = 1366;
    public static final int WINDOWHEIGHT = 768;

    PlayerFish stupidFish;
    AutoFish zombieFish;
    GameImage test;
    BufferedImage background;
    BufferedImage bufferedScreen = new BufferedImage(WINDOWWIDTH, WINDOWHEIGHT, BufferedImage.TYPE_INT_ARGB);


    public GameWindow() {
        initWindows();
        initCursor();

        stupidFish = new PlayerFish(0, 1, new Position(100, 100), this);
        zombieFish = new AutoFish(0, 1, Position.RANDOM(), this);
        try {
            background = ImageIO.read(new File("res/airPlanesBackground.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    void initCursor() {
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB) {
        }, new Point(0, 0), "custom cursor"));
    }

    void initWindows() {
        this.setTitle("Bloe <3");
        this.setSize(WINDOWWIDTH, WINDOWHEIGHT);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(69);
            }
        });
    }

    @Override
    public void update(Graphics g) {
        bufferedScreen.getGraphics().drawImage(background, 0, 0, null);
        stupidFish.draw(bufferedScreen.getGraphics());
        zombieFish.draw(bufferedScreen.getGraphics());
        g.drawImage(bufferedScreen, 0, 0, null);
    }

    void gameLoop() {
        stupidFish.update();
        zombieFish.update();
        repaint();
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gameLoop();
    }

    @Override
    public void run() {
        gameLoop();
    }

    @Override
    public void callbackDelete(GameObject gameObject) {

    }
}
