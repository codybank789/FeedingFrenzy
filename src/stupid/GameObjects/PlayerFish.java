package stupid.GameObjects;

import stupid.Interface.ObjectManagerInterface;
import stupid.Loader.FishLoader;
import stupid.Models.GameAnimation;
import stupid.Models.GameObject;
import stupid.Models.Position;

import java.awt.*;
import java.util.Enumeration;

/**
 * Created by NguyenDuc on 7/13/2016.
 */
public class PlayerFish extends GameObject {

    public int size = 1;
    public int fishEaten = 0;

    public PlayerFish(int fishType, int direction, Position initialPos, ObjectManagerInterface manager) {
        super(direction, initialPos, manager);

        for(int i = 0; i < FishLoader.TYPEOFANIMATION; i++) {
            animationManager.add(new GameAnimation(size, fishType, i));
        }
        animationManager.get(1).flipAnimation();

        pos.direction = direction;
        pos.w = animationManager.getWidth();
        pos.h = animationManager.getHeight();

    }

    public void update() {
        super.update();


        Enumeration<GameObject> all = allObjects.elements();
        while (all.hasMoreElements()) {
            GameObject current = all.nextElement();

            if (current instanceof AutoFish) {
                if (pos.isCollide(current.pos) && size > ((AutoFish) current).size) {
                    current.remove();
                    fishEaten++;
                }
            }
        }

        if (fishEaten > 2) {
            if (size < 2)
                animationManager.resize(++size);
            fishEaten = 0;
        }
    }

    public void resize(int size) {
        animationManager.resize(++size);
        pos.w = animationManager.getWidth();
        pos.h = animationManager.getHeight();
    }

    @Override
    public void draw(Graphics g) {
        animationManager.getCurrentAnimation().draw(g, pos);
    }

    @Override
    public void callbackDelete(GameObject gameObject) {

    }

    @Override
    public Position positionFeed() {
        PointerInfo pInfo = MouseInfo.getPointerInfo();
        return new Position(pInfo.getLocation().x, pInfo.getLocation().y);
    }
}
