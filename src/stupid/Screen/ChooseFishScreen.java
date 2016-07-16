package stupid.Screen;

import stupid.GameWindow;
import stupid.Models.GameImage;
import stupid.Models.Position;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Yuu on 7/16/2016.
 */
public class ChooseFishScreen extends Screen {

    private GameImage backGround = new GameImage("res/image 876.png").resizeImage(GameWindow.WINDOWWIDTH,GameWindow.WINDOWHEIGHT);
    private GameImage fish1 = new GameImage("res/Fish/Fish1/001.png");
    private GameImage fish3 = new GameImage("res/Fish/Fish3/001.png");
    private GameImage fish7 = new GameImage("res/Fish/Fish7/001.png");
    public static int tyfeOfFish;
    public  ChooseFishScreen()
    {

    }



    @Override
    public void MouseClick(MouseEvent m) {

        updateMouseLocal();
        switch (m.getButton())
        {
            case 1:
                if(currentMousePos.isInside(new Position(GameWindow.WINDOWWIDTH*2/5,
                        GameWindow.WINDOWHEIGHT/3,fish1.getWidth(),fish1.getHeight())))
                        {
                            tyfeOfFish = 1;
                        }
                if(currentMousePos.isInside(new Position(GameWindow.WINDOWWIDTH*3/5,
                    GameWindow.WINDOWHEIGHT/3,fish1.getWidth(),fish1.getHeight())))
                        {
                            tyfeOfFish = 3;
                        }
                if(currentMousePos.isInside(new Position(GameWindow.WINDOWWIDTH*4/5, GameWindow.WINDOWHEIGHT/3,fish1.getWidth(),fish1.getHeight())))
                        {
                            tyfeOfFish = 7;
                        }
                break;
            default:
                break;
        }
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        backGround.draw(g,new Position(0,0));
        fish1.draw(g,new Position(GameWindow.WINDOWWIDTH*2/5,GameWindow.WINDOWHEIGHT/3));
        fish3.draw(g,new Position(GameWindow.WINDOWWIDTH*3/5,GameWindow.WINDOWHEIGHT/3));
        fish7.draw(g,new Position(GameWindow.WINDOWWIDTH*4/5,GameWindow.WINDOWHEIGHT/3));

    }
}
