package stupid.GameObjects;

import stupid.GameWindow;
import stupid.Interface.ObjectManagerInterface;
import stupid.Main;
import stupid.Models.PositionFeed;
import stupid.Models.GameImage;
import stupid.Models.GameObject;
import stupid.Models.Position;

import java.awt.*;

/**
 * Created by NguyenDuc on 7/16/2016.
 */
public class IncreaseSizeItem extends GameObject {

    GameImage objectImage = new GameImage("res/image 755.png");

    public IncreaseSizeItem(ObjectManagerInterface manager) {
        super(1, new Position((int) (Math.random() * GameWindow.WINDOWWIDTH), 0), manager);

        pos.direction = 1;
        pos.w = objectImage.getWidth();
        pos.h = objectImage.getHeight();

        positionFeed = PositionFeed.moveDown();
    }

    public void update() {
        super.update();
        if (Math.abs(pos.y - GameWindow.WINDOWHEIGHT) <= 5){
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
