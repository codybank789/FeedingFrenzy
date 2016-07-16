package stupid.GameObjects;

import stupid.Interface.ObjectManagerInterface;
import stupid.Models.GameImage;
import stupid.Models.GameObject;
import stupid.Models.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Yuu on 7/16/2016.
 */
public class BiteImage extends GameObject {
    private GameImage biteImage = new GameImage("res/image 234.png").resizeImage(60,60);
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
            playerFish.pos.direction *= -1;
            biteImage.draw(g,playerFish.pos.getCustomBox(-0.8,0,0.7,0.7,true));
            playerFish.pos.direction *= -1;
        }
        else {
            remove();
        }
    }

    @Override
    public void callbackDelete(GameObject gameObject) {
    }
}
