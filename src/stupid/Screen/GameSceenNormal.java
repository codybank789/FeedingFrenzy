package stupid.Screen;

import stupid.GameObjects.AutoFish;
import stupid.GameObjects.IncreaseSizeItem;
import stupid.GameObjects.Pearl;
import stupid.GameObjects.PlayerFish;
import stupid.GameWindow;
import stupid.Interface.ObjectManagerInterface;
import stupid.Interface.PositionFeed;
import stupid.Interface.ScreenListener;
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
    public static GameImage background = new GameImage("res/image 672.jpg").resizeImage(GameWindow.WINDOWWIDTH, GameWindow.WINDOWHEIGHT);;

    public GameSceenNormal() {

        playerFish = new PlayerFish(0, 1, Position.RANDOMINSIDESCREEN(), this);
        childList.add(playerFish);
//        pearl= new Pearl();
    }

    @Override
    public void MouseClick(MouseEvent m) {
    }

    @Override
    public void update() {
        if (childList.size() < 6) {
            //AutoFish newFish = new AutoFish((int) (Math.random()*5), 1, Position.RANDOMOUTSIDESCREEN(), this);
            //newFish.resize((int)(Math.random()*1000)%2);
            //childList.add(newFish);
        }

        if (childList.size() < 2) {
            AutoFish newFish = new AutoFish(5, 1, Position.RANDOMOUTSIDESCREEN(), this);
            newFish.resize(2);
            newFish.setPositionFeed(PositionFeed.moveToPlayer);
            childList.add(newFish);
        }

        if ((int)(Math.random()*1000)%300 == 0) {
            //System.out.println("added");
            childList.add(new IncreaseSizeItem(this));
        }

        Enumeration<GameObject> all = childList.elements();
        while (all.hasMoreElements()) {
            all.nextElement().update();
        }
    }

    @Override
    public void draw(Graphics g) {
        background.draw(g, new Position(0, 0));

        Enumeration<GameObject> all = childList.elements();
        while (all.hasMoreElements()) {
            all.nextElement().draw(g);
        }
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
        if (gameObject instanceof PlayerFish) {
            GameOverScreen gameOverScreen = new GameOverScreen();
            gameOverScreen.addScreenListener(new ScreenListener() {
                @Override
                public void onChildScreenFinish() {
                    ScreenManager.pop();
                    if (screenListener != null)
                        screenListener.onChildScreenFinish();
                }
            });
            ScreenManager.push(gameOverScreen);
        }

    }
}
