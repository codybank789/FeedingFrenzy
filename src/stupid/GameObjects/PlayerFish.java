package stupid.GameObjects;

import stupid.Interface.ObjectManagerInterface;
import stupid.Models.*;
import stupid.Loader.FishLoader;

import java.awt.*;
import java.util.Enumeration;

/**
 * Created by NguyenDuc on 7/13/2016.
 */
public class PlayerFish extends GameObject {

    public int fishEaten = 0;
    PointerInfo pInfo = MouseInfo.getPointerInfo();
    private static GameSoundReader biteSound;
    public PlayerFish(int fishType, int direction, Position initialPos, ObjectManagerInterface manager) {
        super(direction, initialPos, manager);
        speed = 5;

        for (int i = 0; i < FishLoader.TYPEOFANIMATION; i++) {
            animationManager.add(new GameAnimation(size, fishType, i));
        }
        animationManager.get(1).flipAnimation();

        pos.direction = direction;
        pos.w = animationManager.getWidth();
        pos.h = animationManager.getHeight();

        positionFeed = PositionFeed.followMouse;
    }

    public void update() {
        super.update();

        int delta = (int) pos.w;
        if (pos.direction == 1) {
            delta = 0;
        }
        Position mounthPos = pos.getCustomBox(1, 0.3, 0.3, 0.4, true);

        Enumeration<GameObject> all = allObjects.elements();
        while (all.hasMoreElements()) {
            GameObject current = all.nextElement();
            if (current != this) {
                if (current instanceof AutoFish) {
                    if (mounthPos.isCollide(current.pos) && size >= current.size) {
                        current.remove();
                        fishEaten++;
                        BiteImage b = new BiteImage(this);
                        childList.add(b);
                        biteSound = new GameSoundReader("res/sounds/bite1.wav");
                        biteSound.playOnce();
                    }
                } else if (current instanceof IncreaseSizeItem) {
                    if (mounthPos.isCollide(current.pos)) {
                        current.remove();
                        fishEaten+=3;
                    }
                }
            }
        }

        if (fishEaten > 2) {
            if (size < 2)
                resize(++size);
            fishEaten = 0;
        }

        Enumeration<GameObject> allchilds = childList.elements();
        while (allchilds.hasMoreElements()) {
            allchilds.nextElement().update();
        }

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        Position mounthPos = pos.getCustomBox(1, 0.3, 0.3, 0.4, true);
        g.drawRect((int) mounthPos.x, (int) mounthPos.y, (int) mounthPos.w, (int) mounthPos.h);
        animationManager.getCurrentAnimation().draw(g, pos);
        Enumeration<GameObject> all = childList.elements();
        while (all.hasMoreElements()) {
            all.nextElement().draw(g);
        }
    }

    @Override
    public void callbackDelete(GameObject gameObject) {
        childList.remove(gameObject);
    }

}
