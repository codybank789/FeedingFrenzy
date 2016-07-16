package stupid.Screen;

import stupid.GameObjects.*;
import stupid.GameWindow;
import stupid.Interface.ObjectManagerInterface;
import stupid.Interface.ScreenListener;
import stupid.Models.*;

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
    private ScoreBoard scoreBoard;
    public static GameImage background = new GameImage("res/image 672.jpg").resizeImage(GameWindow.WINDOWWIDTH, GameWindow.WINDOWHEIGHT);;

    public GameSceenNormal() {
        pearl= new Pearl();
        childList.add(pearl);
        playerFish = new PlayerFish(0, 1, Position.RANDOMINSIDESCREEN(), this);
        playerFish.resize(0);
        childList.add(playerFish);
        screenSound = new GameSoundReader("res/sounds/stageIntro.wav");
         scoreBoard = new ScoreBoard();
    }

    @Override
    public void MouseClick(MouseEvent m) {
    }

    @Override
    public void update() {
        if (childList.size() < 9) {
            AutoFish newFish = new AutoFish((int) (Math.random()*8), 1, Position.RANDOMOUTSIDESCREEN(), this);
            if (childList.size() > 4) {
                newFish.resize(0);
            } else {
                newFish.resize(0);
                if (newFish.size == 2) {
                    newFish.positionFeed = PositionFeed.moveToPlayer;
                }
            }

            childList.add(newFish);
            System.out.println(childList.size());
        }

        if (childList.size() < 2) {
            //AutoFish newFish = new AutoFish(5, 1, Position.RANDOMOUTSIDESCREEN(), this);
            //newFish.resize(2);
            //newFish.setPositionFeed(PositionFeed.moveToPlayer);
            //childList.add(newFish);
        }

        if ((int)(Math.random()*1000)%600 == 0) {
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
    }

    @Override
    public void onResume() {
        screenSound.playOnce();
       // ScreenManager.frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB)
        //        , new Point(0, 0), "custom cursor"));
    }

    @Override
    public void onPause() {
        super.onPause();
        ScreenManager.frame.setCursor(Cursor.DEFAULT_CURSOR);
    }

    @Override
    public void callbackDelete(GameObject gameObject) {
        System.out.println(childList.size());
        childList.remove(gameObject);
        System.out.println(childList.size());
        GameObject.allObjects.remove(gameObject);
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
