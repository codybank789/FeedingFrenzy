package stupid.Screen;

import stupid.GameObjects.AutoFish;
import stupid.GameWindow;
import stupid.Interface.ObjectManagerInterface;
import stupid.Loader.FishLoader;
import stupid.Models.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by Yuu on 7/16/2016.
 */
public class WinScreen extends Screen implements ObjectManagerInterface {
    private static final Position POSITIONINSCREEN = new Position(0,0,GameWindow.WINDOWWIDTH,GameWindow.WINDOWHEIGHT);
    private GameImage backGround = new GameImage("res/image 672.jpg").resizeImage(GameWindow.WINDOWWIDTH, GameWindow.WINDOWHEIGHT);
    private GameImage y_image = new GameImage("res/image 137.png");
    private GameImage u_image = new GameImage("res/image 151.png");
    private GameImage m_image = new GameImage("res/image 149.png");
    private GameImage backbutton = new GameImage("res/backbutton.png");

    private AnimationManager animationManager;
    Vector<GameObject> childList = new Vector<>();
    private AutoFish sexxyFish;
    private PositionFeed pos;
    public WinScreen()
    {
        for(int i =0;i< FishLoader.TYPEOFANIMATION;i++)
        {
            GameAnimation gameAnimation = new GameAnimation(1,10,i);
            animationManager.add(gameAnimation);
        }
        sexxyFish = new AutoFish(10,1,new Position(GameWindow.WINDOWWIDTH,GameWindow.WINDOWHEIGHT/2),this);
        childList.add(sexxyFish);
    }

    @Override
    public void MouseClick(MouseEvent m) {

    }

    @Override
    public void update() {
        sexxyFish.setPositionFeed(PositionFeed.moveLeft());
        if(sexxyFish.pos.isInside(POSITIONINSCREEN)) sexxyFish.remove();
    }

    @Override
    public void draw(Graphics g) {
        backGround.draw(g,new Position(0,0));
        y_image.draw(g,new Position(GameWindow.WINDOWWIDTH*8/20,GameWindow.WINDOWHEIGHT*1/3));
        u_image.draw(g,new Position(GameWindow.WINDOWWIDTH*9/20,GameWindow.WINDOWHEIGHT*1/3));
        m_image.draw(g,new Position(GameWindow.WINDOWWIDTH*10/20,GameWindow.WINDOWHEIGHT*1/3));
        m_image.draw(g,new Position(GameWindow.WINDOWWIDTH*11/20,GameWindow.WINDOWHEIGHT*1/3));
        y_image.draw(g,new Position(GameWindow.WINDOWWIDTH*12/20,GameWindow.WINDOWHEIGHT*1/3));
        backbutton.draw(g,new Position(GameWindow.WINDOWWIDTH*1/2,GameWindow.WINDOWHEIGHT*3/4));
        Enumeration<GameObject> all = childList.elements();
        while (all.hasMoreElements()) {
            all.nextElement().draw(g);
        }
    }

    @Override
    public void callbackDelete(GameObject gameObject) {

    }
}
