package stupid.GameObjects;

import stupid.Interface.ObjectManagerInterface;
import stupid.Loader.FishLoader;
import stupid.Models.GameAnimation;
import stupid.Models.GameObject;
import stupid.Models.Position;

import java.awt.*;
import java.util.Enumeration;

/**
 * Created by NguyenDuc on 7/14/2016.
 */
public class AutoFish extends GameObject {

    public boolean lastTouch = false;
    int count = 0;
    Position lastPos;
    private int fishEaten = 0;

    public AutoFish(int fishType, int direction, Position initialPos, ObjectManagerInterface manager) {
        super(direction, initialPos, manager);

        for (int i = 0; i < FishLoader.TYPEOFANIMATION; i++) {
            animationManager.add(new GameAnimation(size, fishType, i));
        }
        animationManager.get(1).flipAnimation();

        pos.direction = direction;
        pos.w = animationManager.getWidth();
        pos.h = animationManager.getHeight();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        //Position mounthPos = pos.getCustomBox(1, 0.3, 0.3, 0.4, true);
        //g.drawRect((int) mounthPos.x, (int) mounthPos.y, (int) mounthPos.w, (int) mounthPos.h);
        animationManager.getCurrentAnimation().draw(g, pos);
    }

    @Override
    public void callbackDelete(GameObject gameObject) {

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

            if (current instanceof AutoFish || current instanceof PlayerFish) {

                if (mounthPos.isCollide(current.pos) && size > current.size) {
                    if (current instanceof PlayerFish) {
                        fishEaten++;

                        if (lastTouch == false) {
                            ((PlayerFish) current).scoreBoard.health -= 25;
                            ((PlayerFish) current).scoreBoard.increaseScore(-50);
                            if (((PlayerFish) current).scoreBoard.health == 0) {
                                current.remove();
                            }
                        }

                        lastTouch = true;
                    } else {
                        animationManager.eating();
                        current.remove();
                        fishEaten++;
                    }
                } else if (current instanceof PlayerFish) {
                    lastTouch = false;
                }
            } else if (current instanceof IncreaseSizeItem) {
                if (mounthPos.isCollide(current.pos)) {
                    animationManager.eating();
                    current.remove();
                    fishEaten+=3;
                }
            }
        }

        if (fishEaten > 2) {
            if (size < 2)
                resize(++size);
            fishEaten = 0;
        }

    }
}
