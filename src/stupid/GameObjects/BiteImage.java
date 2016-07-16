package stupid.GameObjects;

import stupid.Interface.ObjectManagerInterface;
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

    public BiteImage(ObjectManagerInterface manager) {
        this.manager = manager;
    }

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
            remove();
        }
    }

    @Override
    public void callbackDelete(GameObject gameObject) {
    }
}
