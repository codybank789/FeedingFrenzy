package stupid.GameObjects;

import stupid.Interface.ObjectManagerInterface;
import stupid.Loader.FishLoader;
import stupid.Models.GameAnimation;
import stupid.Models.GameObject;
import stupid.Models.Position;

import java.awt.*;

/**
 * Created by NguyenDuc on 7/13/2016.
 */
public class Fish extends GameObject {

    public Fish(int fishType, int direction, Position initialPos, ObjectManagerInterface manager) {
        super(direction, initialPos, manager);

        for(int i = 0; i < FishLoader.TYPEOFANIMATION; i++) {
            animationList.add(new GameAnimation(1, fishType, i));
        }
        animationList.get(1).flipAnimation();

        pos.direction = direction;
        //pos.w = animationList.get(1).getWidth();
        //pos.h = animationList.get(1).getHeight();
    }

    public void setAnimation(GameAnimation animation) {
        while (currentAnimation.size() > 0) {
            currentAnimation.removeFirst().reset();
        }
        currentAnimation.addLast(animation);
    }

    public void nextAnimation() {
        currentAnimation.getFirst().reset();
        currentAnimation.removeFirst();
    }

    @Override
    public void update() {
        PointerInfo pInfo = MouseInfo.getPointerInfo();

        int delta = (int)pos.x - pInfo.getLocation().x;

        if (delta > 5) {
            pos.x -= 3;
            if (pos.direction == -1) {
                flip();
                setAnimation(animationList.get(1));
                pos.direction = 1;
            }
        } else if (delta < -5){
            pos.x += 3;
            if (pos.direction == 1) {
                flip();
                setAnimation(animationList.get(1));
                pos.direction = -1;
            }
        }

        pos.y += -Math.signum(pos.y - pInfo.getLocation().y) * 3;
    }

    public GameAnimation getCurrentAnimation() {
        if (currentAnimation.getFirst().time > 0) {
            nextAnimation();
        }

        if (currentAnimation.isEmpty()) {
            currentAnimation.add(animationList.get(0));
        }

        return currentAnimation.getFirst();
    }

    @Override
    public void draw(Graphics g) {
        getCurrentAnimation().draw(g, pos);
    }

    @Override
    public void callbackDelete(GameObject gameObject) {

    }
}
