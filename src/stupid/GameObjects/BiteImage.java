package stupid.GameObjects;

import stupid.Models.GameImage;
import stupid.Models.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Yuu on 7/16/2016.
 */
public class BiteImage extends GameObject {
    private GameImage biteImage = new GameImage("res/image 234.png");
    private int count;

    @Override
    public void update() {
        count++;
    }

    @Override
    public void draw(Graphics g) {
        if(count<30)
        {
            biteImage.draw(g,playerFish.pos);
        }
        else {
            manager.callbackDelete(this);
        }
    }

    @Override
    public void callbackDelete(GameObject gameObject) {
    }
}
