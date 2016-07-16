package stupid.GameObjects;

import stupid.GameWindow;
import stupid.Interface.ObjectManagerInterface;
import stupid.Models.PositionFeed;
import stupid.Models.GameImage;
import stupid.Models.GameObject;
import stupid.Models.Position;

import java.awt.*;

/**
 * Created by NguyenDuc on 7/16/2016.
 */
public class IncreaseSizeItem extends GameObject {

    Position destination;
    GameImage objectImage = new GameImage("res/image 755.png");

    public IncreaseSizeItem(ObjectManagerInterface manager) {
        super(1, new Position((int) (Math.random() * GameWindow.WINDOWWIDTH), 0), manager);
        destination = new Position(pos.x, GameWindow.WINDOWHEIGHT+100);

        pos.direction = 1;
        pos.w = objectImage.getWidth();
        pos.h = objectImage.getHeight();

        positionFeed = PositionFeed.moveDown();
    }

    public void update() {
        super.update();
        if (pos.y == destination.y) {
            remove();
        }
    }

    public void draw(Graphics g) {
        super.draw(g);
        objectImage.draw(g, pos);
        //System.out.println("drawn");
    }

    @Override
    public void callbackDelete(GameObject gameObject) {

    }

}
