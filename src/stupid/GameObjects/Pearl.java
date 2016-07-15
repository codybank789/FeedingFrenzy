package stupid.GameObjects;

import stupid.Interface.ObjectManagerInterface;
import stupid.Loader.FishLoader;
import stupid.Models.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by Yuu on 7/15/2016.
 */
public class Pearl extends GameObject implements ObjectManagerInterface {
    private static final Position POSITION = new Position(500,600);
    private static final int SIZE = 1;
    private static final int TYPEOFFISH = 9;
    private static final String WHITEPEARL_RS = "res/image 61.png";
    private static final String BLACK_RS = "res/image 58.png";
    private boolean canEat;
    private GameImage pearlImage;
    private Vector<BufferedImage> listImage = new Vector<BufferedImage>();
    public AnimationManager animationManager = new AnimationManager();
    private int count=0;


    public Pearl()
    {
        pearlImage = new GameImage(WHITEPEARL_RS);
    }

    @Override
    public Position positionFeed() {
        return POSITION;
    }

    public void checkCaneat()
    {
        if(count>3600)
        {
            canEat = true;
        }
        if (count>3900)
        {
            canEat = false;
            count =0;
        }
    }
    public void update()
    {
        count++;
        checkCaneat();
    }

    @Override
    public void draw(Graphics g) {
        animationManager.getCurrentAnimation().draw(g,POSITION);
        if(canEat)pearlImage.draw(g,POSITION);
    }

    @Override
    public void callbackDelete(GameObject gameObject) {

    }
}
