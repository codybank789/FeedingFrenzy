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
public class PlayerFish extends GameObject {

    public PlayerFish(int fishType, int direction, Position initialPos, ObjectManagerInterface manager) {
        super(direction, initialPos, manager);

        for(int i = 0; i < FishLoader.TYPEOFANIMATION; i++) {
            animationManager.add(new GameAnimation(1, fishType, i));
        }
        animationManager.get(1).flipAnimation();

        pos.direction = direction;
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
