package stupid.Models;

import stupid.Interface.AnimationListener;

import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by NguyenDuc on 7/14/2016.
 */
public class AnimationManager {
    public boolean isLocked = false;
    public int toSize = 0;
    public Vector<GameAnimation> animationList = new Vector<>();
    public ArrayDeque<GameAnimation> currentAnimation = new ArrayDeque<>();

    public AnimationManager() {}

    public void add(GameAnimation gameAnimation) {
        gameAnimation.addListener(new AnimationListener() {
            @Override
            public void animationFinished() {
                currentAnimation.removeFirst();
            }
        });
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

    public void resize(int size) {

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
        if (currentAnimation.isEmpty()) {
            currentAnimation.addLast(animationList.get(0));
        }

        return currentAnimation.getFirst();
    }

    public void setResize(int toSize) {
        setAnimation(0);
        currentAnimation.getFirst().setResize(toSize);
        isLocked = true;

        currentAnimation.getFirst().addListener(new AnimationListener() {
            @Override
            public void animationFinished() {
                isLocked = false;
                resize(toSize);

                currentAnimation.removeFirst();

                animationList.get(0).addListener(new AnimationListener() {
                    @Override
                    public void animationFinished() {
                        currentAnimation.removeFirst();
                    }
                });
            }
        });


    }
}
