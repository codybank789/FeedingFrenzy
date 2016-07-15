package stupid.GameObjects;

import javafx.geometry.Pos;
import stupid.Interface.ObjectManagerInterface;
import stupid.Loader.FishLoader;
import stupid.Models.GameAnimation;
import stupid.Models.GameObject;
import stupid.Models.Position;

import java.awt.*;

/**
 * Created by NguyenDuc on 7/14/2016.
 */
public class AutoFish extends GameObject{

    int count = 0;
    Position lastPos;
    public int size = 0;

    public AutoFish(int fishType, int direction, Position initialPos, ObjectManagerInterface manager) {
        super(direction, initialPos, manager);

        for(int i = 0; i < FishLoader.TYPEOFANIMATION; i++) {
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
        animationManager.getCurrentAnimation().draw(g, pos);
    }

    @Override
    public void callbackDelete(GameObject gameObject) {

    }

    @Override
    public Position positionFeed() {
        count++;
        if (count > 180 || lastPos == null) {
            count = 0;
            lastPos = Position.RANDOM();
        }
        return lastPos;
    }
}
