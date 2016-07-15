package stupid.Models;

import stupid.GameObjects.PlayerFish;
import stupid.GameWindow;
import stupid.Interface.DisplayInterface;
import stupid.Interface.ObjectManagerInterface;
import stupid.Interface.PositionFeed;

import java.awt.*;
import java.util.*;

/**
 * Created by NguyenDuc on 7/13/2016.
 */
abstract public class GameObject implements DisplayInterface, ObjectManagerInterface{
    public static Vector<GameObject> allObjects = new Vector<>();

    public static PlayerFish playerFish;


    public PositionFeed positionFeed;
    public Position pos;
    public int size;
    public ObjectManagerInterface manager;
    public Vector<GameObject> childList = new Vector<>();
    public AnimationManager animationManager = new AnimationManager();
    private boolean eaten;

    public GameObject() {
        }

    public GameObject(int direction, Position initialPos, ObjectManagerInterface manager) {
            this.pos = initialPos;

            this.manager = manager;
        allObjects.add(this);

        if (this instanceof PlayerFish) {
            playerFish = (PlayerFish)this;
        }
    }

    public void remove() {
        manager.callbackDelete(this);
        allObjects.remove(this);
    }

    public boolean isVisible() {
        return(pos.isInside(new Position(0, 0, GameWindow.WINDOWWIDTH, GameWindow.WINDOWHEIGHT)));
    }

    public void resize(int size) {
        this.size = size;
        animationManager.resize(size);
        pos.w = animationManager.getWidth();
        pos.h = animationManager.getHeight();
    }

    public void flip() {
        animationManager.flip();
    }

    public void printPos() {
        System.out.println(pos.x + " " + pos.y);
    }

    public void deleteCallback(GameObject object) {
        object.remove();
        childList.remove(object);
    }

    public void setPositionFeed(PositionFeed positionFeed) {
        this.positionFeed = positionFeed;
    }

    @Override
    public void update() {
        if (positionFeed == null) {
            positionFeed = PositionFeed.moveRandomly();
        }

        Position pInfo = positionFeed.getPos(pos);

        int delta = (int)(pos.x - pInfo.x);

        if (delta > 5) {
            pos.x -= 3;
            if (pos.direction == -1) {
                flip();
                animationManager.setAnimation(1);
                pos.direction = 1;
            }
        } else if (delta < -5){
            pos.x += 3;
            if (pos.direction == 1) {
                flip();
                animationManager.setAnimation(1);
                pos.direction = -1;
            }
        }

        double d = pos.y - pInfo.y;
        if (Math.abs(d) < 5) d = 0;
        pos.y += -Math.signum(d) * 3;
    }

    @Override
    public void draw(Graphics g) {
        g.drawString(String.valueOf(size), (int)pos.x, (int)pos.y);
        g.drawRect((int)pos.x, (int)pos.y, (int)pos.w, (int)pos.h);
    }
}
