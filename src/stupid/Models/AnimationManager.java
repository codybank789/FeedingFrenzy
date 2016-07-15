package stupid.Models;

import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by NguyenDuc on 7/14/2016.
 */
public class AnimationManager {
    public boolean isLocked = false;
    public Vector<GameAnimation> animationList = new Vector<>();
    public ArrayDeque<GameAnimation> currentAnimation = new ArrayDeque<>();

    public AnimationManager() {}

    public void add(GameAnimation gameAnimation) {
        animationList.add(gameAnimation);
    }

    public GameAnimation get(int pos) {
        return animationList.get(pos);
    }

    public void setAnimation(int pos) {
        if (isLocked) return;

        while (currentAnimation.size() > 0) {
            currentAnimation.removeFirst().reset();
        }
        currentAnimation.addLast(animationList.get(pos));
    }

    public void nextAnimation() {
        if (isLocked) return;

        currentAnimation.getFirst().reset();
        currentAnimation.removeFirst();
    }

    public void resize(int size) {
        isLocked = true;
        setAnimation(0);
        getCurrentAnimation().setResize(size);
        isLocked = false;

        Enumeration<GameAnimation> animationEnumeration = animationList.elements();

        while(animationEnumeration.hasMoreElements()) {
            animationEnumeration.nextElement().resize(size);
        }
    }

    public void flip() {
        Enumeration<GameAnimation> animationEnumeration = animationList.elements();

        while(animationEnumeration.hasMoreElements()) {
            animationEnumeration.nextElement().flipAnimation();
        }
    }

    public int getWidth() {
        return animationList.get(0).getWidth();
    }

    public int getHeight() {
        return animationList.get(0).getHeight();
    }

    public GameAnimation getCurrentAnimation() {
        if (!(currentAnimation.isEmpty()) && currentAnimation.getFirst().time > 0) {
            nextAnimation();
        }

        if (currentAnimation.isEmpty()) {
            currentAnimation.add(animationList.get(0));
        }

        return currentAnimation.getFirst();
    }
}
