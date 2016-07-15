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

    public int size = 0;
    public int fishEaten = 0;
    PointerInfo pInfo = MouseInfo.getPointerInfo();

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

        int delta = (int)pos.w;
        if (pos.direction == 1) {
            delta = 0;
        }
        Position mounthPos = pos.getCustomBox(1, 0.3, 0.3, 0.4, true);

        Enumeration<GameObject> all = allObjects.elements();
        while (all.hasMoreElements()) {
            GameObject current = all.nextElement();

            if (current instanceof AutoFish) {
                if (mounthPos.isCollide(current.pos) && size >= ((AutoFish) current).size) {
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
        //super.draw(g);
        Position mounthPos = pos.getCustomBox(1, 0.3, 0.3, 0.4, true);
        g.drawRect((int)mounthPos.x, (int)mounthPos.y, (int)mounthPos.w, (int)mounthPos.h);
        animationManager.getCurrentAnimation().draw(g, pos);
    }

    @Override
    public void callbackDelete(GameObject gameObject) {

    }

    @Override
    public Position positionFeed() {
        pInfo = MouseInfo.getPointerInfo();
        return new Position(pInfo.getLocation().x, pInfo.getLocation().y);
    }
}
