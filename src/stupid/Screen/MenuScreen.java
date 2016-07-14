package stupid.Screen;

import javafx.scene.shape.Circle;
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
    private BufferedImage backGround;
    private BufferedImage title;
    private BufferedImage optionNewGame;
    private BufferedImage optionNewGameClick;
//    private BufferedImage optionExit;
//    private BufferedImage optionExitClick;
    private BufferedImage optionTime;
    private BufferedImage optionTimeClick;
    private BufferedImage optionnew;
    private BufferedImage optiontime;
    private static  final  int  OPTIONNEWGAMEX = 200;
    private static final int OPTIONNEWGAMEY = 400;
    private static final int OPTIONTIMEX = 400;
    private static final int OPTTIONTIMEY = 400;
    private boolean canClickNew;
    private boolean canClickTime;
//    private static final int optionExitPosX =400;
//    private static final int optionExitPosY = 600;
    public MenuScreen()
    {
        try {
            backGround = ImageIO.read(new File("res/image 881.jpg"));
            title = ImageIO.read(new File(("res/image 681.png")));
            optionNewGame = ImageIO.read(new File("res/image 684.png"));
            optionNewGameClick = ImageIO.read(new File("res/image 686.png"));
            optionTime = ImageIO.read(new File("res/image 695.png"));
            optionTimeClick = ImageIO.read(new File("res/image 697.png"));
            optionnew = optionNewGame;
            optiontime = optionTime;

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if(mouseLocalX>=OPTIONTIMEX&&mouseLocalX<=(OPTIONTIMEX+optionNewGame.getWidth())&&mouseLocalY>=OPTTIONTIMEY&&mouseLocalY<(OPTTIONTIMEY+optionNewGame.getHeight()))
        {   canClickNew = true;
            optionnew = optionNewGameClick;}
        else {
            optionnew = optionNewGame;
            canClickNew = false;
        }

        if(mouseLocalX>=OPTIONNEWGAMEX&&mouseLocalX<=(OPTIONNEWGAMEX+optionNewGame.getWidth())&&mouseLocalY>=OPTIONNEWGAMEY&&mouseLocalY<(OPTIONNEWGAMEY+optionNewGame.getHeight()))
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
        g.drawImage(backGround,0,0,null);
        g.drawImage(title,100,100,null);
        g.drawImage(optionnew,OPTIONNEWGAMEX,OPTIONNEWGAMEY,null);
        g.drawImage(optiontime,OPTIONTIMEX,OPTTIONTIMEY,null);
    }
}
