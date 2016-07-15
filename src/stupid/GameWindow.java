package stupid;

import stupid.GameObjects.AutoFish;
import stupid.GameObjects.PlayerFish;
import stupid.Interface.ObjectManagerInterface;
import stupid.Models.GameImage;
import stupid.Models.GameObject;
import stupid.Models.Position;
import stupid.Screen.MenuScreen;
import stupid.Screen.ScreenManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by NguyenDuc on 7/13/2016.
 */
public class GameWindow extends Frame implements Runnable, ObjectManagerInterface{
    public static final int WINDOWWIDTH = 800;
    public static final int WINDOWHEIGHT = 600;

    BufferedImage background;
    BufferedImage bufferedScreen = new BufferedImage(WINDOWWIDTH, WINDOWHEIGHT, BufferedImage.TYPE_INT_ARGB);


    public GameWindow() {
        initWindows();
    }

    public void hideCursor() {
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB)
                , new Point(0, 0), "custom cursor"));
        //setCursor(Cursor.DEFAULT_CURSOR);
    }

    void initWindows() {
        Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB)
                , new Point(0, 0), "custom cursor");
        this.setTitle("Bloe <3");
        this.setSize(WINDOWWIDTH, WINDOWHEIGHT);
        this.setVisible(true);
        ScreenManager.setFrame(this);
        ScreenManager.push(new MenuScreen());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(69);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ScreenManager.mouseClicked(e);
            }
        });
    }

    @Override
    public void update(Graphics g) {
        ScreenManager.getInstance().getStackScreen().peek().draw(bufferedScreen.getGraphics());
        g.drawImage(bufferedScreen,0,0,null);
    }

    void gameLoop() {
        ScreenManager.getInstance().getStackScreen().peek().update();
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
