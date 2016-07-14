package stupid.Models;

import stupid.GameWindow;
import stupid.Interface.DisplayInterface;
import stupid.Interface.ObjectManagerInterface;

import java.util.*;

/**
 * Created by NguyenDuc on 7/13/2016.
 */
abstract public class GameObject implements DisplayInterface, ObjectManagerInterface{
    public static Vector<GameObject> allObjects = new Vector<>();

    public Position pos;

        public ObjectManagerInterface manager;
        public Vector<GameObject> childList = new Vector<>();
        public Vector<GameAnimation> animationList = new Vector<>();
        public ArrayDeque<GameAnimation> currentAnimation = new ArrayDeque<>();

    public GameObject() {

        }

    public GameObject(int direction, Position initialPos, ObjectManagerInterface manager) {
            this.pos = initialPos;

            this.manager = manager;
        allObjects.add(this);
    }

    public void remove() {
        manager.callbackDelete(this);
        allObjects.remove(this);
    }

    public boolean isVisible() {
        return(pos.isInside(new Position(0, 0, GameWindow.WINDOWWIDTH, GameWindow.WINDOWHEIGHT)));
    }

    public void resize(double time) {
        Enumeration<GameAnimation> animationEnumeration = animationList.elements();

        while(animationEnumeration.hasMoreElements()) {
            animationEnumeration.nextElement().resize(time);
        }
    }

    public void flip() {
        Enumeration<GameAnimation> animationEnumeration = animationList.elements();

        while(animationEnumeration.hasMoreElements()) {
            animationEnumeration.nextElement().flipAnimation();
        }
    }

    public void printPos() {
        System.out.println(pos.x + " " + pos.y);
    }

    public void deleteCallback(GameObject object) {
        object.remove();
        childList.remove(object);
    }

}
