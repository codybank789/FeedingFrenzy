package stupid.Screen;

import stupid.GameObjects.AutoFish;
import stupid.GameObjects.Pearl;
import stupid.GameObjects.PlayerFish;
import stupid.GameWindow;
import stupid.Interface.ObjectManagerInterface;
import stupid.Models.GameImage;
import stupid.Models.GameObject;
import stupid.Models.Position;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by Yuu on 7/14/2016.
 */
public class GameSceenNormal extends Screen implements ObjectManagerInterface{

    Vector<GameObject> childList = new Vector<>();
    PlayerFish playerFish;
    Pearl pearl;
    public static GameImage background = new GameImage("res/image 672.jpg");

    public GameSceenNormal() {

        playerFish = new PlayerFish(0, 1, Position.RANDOMINSIDESCREEN(), this);
//        pearl= new Pearl();
    }

    @Override
    public void MouseClick(MouseEvent m) {
    }

    @Override
    public void update() {
        if (childList.size() < 6) {
            childList.add(new AutoFish((int) (Math.random()*5), 1, Position.RANDOMOUTSIDESCREEN(), this));
        }

        Enumeration<GameObject> all = childList.elements();
        while (all.hasMoreElements()) {
            all.nextElement().update();
        }
        playerFish.update();
    }

    @Override
    public void draw(Graphics g) {
        background.draw(g, new Position(0, 0));
        Enumeration<GameObject> all = childList.elements();
        while (all.hasMoreElements()) {
            all.nextElement().draw(g);
        }
        playerFish.draw(g);
//        ;pearl.draw(g)
    }

    @Override
    public void onResume() {
        ScreenManager.frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB)
                , new Point(0, 0), "custom cursor"));
    }

    @Override
    public void onPause() {
        ScreenManager.frame.setCursor(Cursor.DEFAULT_CURSOR);
    }

    @Override
    public void callbackDelete(GameObject gameObject) {
        childList.remove(gameObject);
    }
}
