package stupid.Screen;

import stupid.Models.GameImage;
import stupid.Models.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Yuu on 7/14/2016.
 */
public class MenuScreen extends Screen{
    private static GameImage backGround = new GameImage("res/image 881.jpg");
    private static GameImage title = new GameImage("res/image 681.png");
    private static GameImage optionNewGame = new GameImage("res/image 684.png");
    private static GameImage optionNewGameClick= new GameImage("res/image 686.png");
//    private BufferedImage optionExit;
//    private BufferedImage optionExitClick;
    private static GameImage optionTime = new GameImage("res/image 695.png");
    private static GameImage optionTimeClick= new GameImage("res/image 697.png");
    private static GameImage optionnew= new GameImage("res/image 881.jpg");
    private static GameImage optiontime= new GameImage("res/image 881.jpg");

    private static  final  int  OPTIONNEWGAMEX = 200;
    private static final int OPTIONNEWGAMEY = 400;
    private static final int OPTIONTIMEX = 400;
    private static final int OPTTIONTIMEY = 400;
    public static final Position OPTIONGAME_POSITION = new Position(200, 400
                                    , optionNewGame.getWidth(), optionNewGame.getHeight());
    public static final Position OPTIONTIME_POSITION = new Position(400, 400
            , optionTime.getWidth(), optionTime.getHeight());
    private boolean canClickNew;
    private boolean canClickTime;
//    private static final int optionExitPosX =400;
//    private static final int optionExitPosY = 600;
    public MenuScreen()
    {
        optionnew = optionNewGame;
        optiontime = optionTime;
    }

    public void MouseClick(MouseEvent e)
    {
        switch (e.getButton()) {
            case 1:
            if (canClickNew)
                ScreenManager.getInstance().getStackScreen().push(new GameSceenNormal());
            if (canClickTime)
                ScreenManager.getInstance().getStackScreen().push(new GameScreenTime());
                break;
            default:
                break;
        }
    }

    public void checkMouse()
    {
        mouseInfo = MouseInfo.getPointerInfo();
        Position mousePositon = new Position(mouseInfo.getLocation().x, mouseInfo.getLocation().y);
        //mousePositon.print();
        if (mousePositon.isInside(OPTIONGAME_POSITION))
        {
            System.out.println("called");
            canClickNew = true;
            optionnew = optionNewGameClick;}
        else {
            optionnew = optionNewGame;
            canClickNew = false;
        }

        if (mousePositon.isInside(OPTIONTIME_POSITION))
        {
            optiontime = optionNewGameClick;
            canClickTime = true;
        }
        else {
            canClickNew = false;
            optiontime = optionTime;
        }
    }
    @Override
    public void update() {
        updateMouseLocal();
        checkMouse();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backGround.objectImage,0,0,null);
        g.drawImage(title.objectImage,100,100,null);
        g.drawImage(optionnew.objectImage,OPTIONNEWGAMEX ,OPTIONNEWGAMEY,null);
        g.drawImage(optiontime.objectImage,OPTIONTIMEX,OPTTIONTIMEY,null);
    }
}
