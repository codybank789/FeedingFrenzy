package stupid.Screen;

import stupid.GameWindow;
import stupid.Interface.ScreenListener;
import stupid.Loader.FishLoader;
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
public class MenuScreen extends Screen {
    boolean isInit = false;
    private static GameImage backGround = new GameImage("res/image 881.jpg").resizeImage(GameWindow.WINDOWWIDTH, GameWindow.WINDOWHEIGHT);;
    private static GameImage title = new GameImage("res/image 681.png");
    private static GameImage optionNewGame = new GameImage("res/image 684.png");
    private static GameImage optionNewGameClick = new GameImage("res/image 686.png");
    private static GameImage helpButton = new GameImage("res/help.png");
    //    private BufferedImage optionExit;
//    private BufferedImage optionExitClick;
    private static GameImage optionTime = new GameImage("res/image 695.png");
    private static GameImage optionTimeClick = new GameImage("res/image 697.png");
    private static GameImage optionnew = new GameImage("res/image 881.jpg");
    private static GameImage optiontime = new GameImage("res/image 881.jpg");

    private static final int OPTIONNEWGAMEX = 200;
    private static final int OPTIONNEWGAMEY = 400;
    private static final int OPTIONTIMEX = 400;
    private static final int OPTTIONTIMEY = 400;
    public static final Position OPTIONGAME_POSITION = new Position(GameWindow.WINDOWWIDTH*4/5,GameWindow.WINDOWHEIGHT*3/6
            , optionNewGame.getWidth(), optionNewGame.getHeight());
    public static final Position OPTIONTIME_POSITION = new Position(GameWindow.WINDOWWIDTH*4/5,GameWindow.WINDOWHEIGHT*4/6
            , optionTime.getWidth(), optionTime.getHeight());
    public static final Position HELP_POSTION = new Position(GameWindow.WINDOWWIDTH*4/5+40,GameWindow.WINDOWHEIGHT*5/6,
            helpButton.getWidth(),helpButton.getHeight());

    //    private static final int optionExitPosX =400;
//    private static final int optionExitPosY = 600;
    public MenuScreen() {
        optionnew = optionNewGame;
        optiontime = optionTime;
    }

    public void MouseClick(MouseEvent e) {
        updateMouseLocal();
        switch (e.getButton()) {
            case 1:
                if (currentMousePos.isInside(OPTIONGAME_POSITION)) {
                    if (isInit == false) {
                        isInit = true;

                        GameLoadingScreen gameLoadingScreen = new GameLoadingScreen();
                        gameLoadingScreen.addScreenListener(new ScreenListener() {
                            @Override
                            public void onChildScreenFinish() {
                                //ScreenManager.pop();
                            }
                        });
                        ScreenManager.push(gameLoadingScreen);
                        Thread thread = new Thread(new FishLoader());
                        thread.start();
                    } else {
                        ScreenManager.push(new GameSceenNormal());
                    }

                }

                if (currentMousePos.isInside(OPTIONTIME_POSITION)) {
                    //ScreenManager.push(new GameScreenTime());
                }
                if(currentMousePos.isInside(HELP_POSTION))
                {
                    ScreenManager.push(new HelpScreen());
                }

                break;
            default:
                break;
        }
    }

    private void loadImage() {
        if (isInit == false) {
            FishLoader.initImage();
            isInit = true;
        }
    }

    public void checkMouse() {
        updateMouseLocal();
        if (currentMousePos.isInside(OPTIONGAME_POSITION)) {
            optionnew = optionNewGameClick;
        } else {
            optionnew = optionNewGame;
        }

        if (currentMousePos.isInside(OPTIONTIME_POSITION)) {
            optiontime = optionTimeClick;
        } else {
            optiontime = optionTime;
        }
    }

    @Override
    public void update() {
        checkMouse();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backGround.objectImage, 0, 0, null);
        g.drawImage(title.objectImage, 100, 100, null);
        optionnew.draw(g,OPTIONGAME_POSITION);
        optiontime.draw(g,OPTIONTIME_POSITION);
        helpButton.draw(g,HELP_POSTION);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}
