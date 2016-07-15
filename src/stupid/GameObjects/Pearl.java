package stupid.GameObjects;

import stupid.GameWindow;
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
    private static final Position POSITION = new Position(GameWindow.WINDOWWIDTH*2/3,GameWindow.WINDOWHEIGHT*4/6);
    private static final int SIZE = 1;
    private static final int TYPEOFFISH = 9;
    private static final String WHITEPEARL_RS = "res/image 61.png";
    private static final String BLACK_RS = "res/image 58.png";
    private boolean canEat;
    private GameImage pearlImage;
    private Vector<BufferedImage> listImage = new Vector<BufferedImage>();
    public AnimationManager animationManager = new AnimationManager();
    private int count;


    public Pearl()
    {
        for(int i =0;i<FishLoader.TYPEOFANIMATION;i++) {
            animationManager.add(new GameAnimation(2,8,i));
        }
        pearlImage = new GameImage(WHITEPEARL_RS).resizeImage(40,40);
    }


    public void checkCaneat()
    {
        animationManager.setAnimation(0);
        if(count>120)
        {
            animationManager.setAnimation(1);
            canEat = true;
            animationManager.setAnimation(2);
        }
        if (count>210)
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
        if(canEat)pearlImage.draw(g,new Position(POSITION.x+30,POSITION.y+70));
    }

    @Override
    public void callbackDelete(GameObject gameObject) {

    }
}
