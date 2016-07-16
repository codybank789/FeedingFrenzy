package stupid.Screen;

import stupid.GameWindow;
import stupid.Interface.ScreenListener;
import stupid.Loader.FishLoader;
import stupid.Models.GameImage;
import stupid.Models.GameSoundReader;
import stupid.Models.Position;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Yuu on 7/14/2016.
 */
public class MenuScreen extends Screen {

    private static GameImage backGround = new GameImage("res/image 881.jpg").resizeImage(GameWindow.WINDOWWIDTH, GameWindow.WINDOWHEIGHT);;
    private static GameImage title = new GameImage("res/image 681.png");
    private static GameImage optionNewGame = new GameImage("res/image 684.png");
    private static GameImage optionNewGameClick = new GameImage("res/image 686.png");
    private static GameImage helpButton = new GameImage("res/help.png");

    private static GameImage optionTime = new GameImage("res/image 695.png");
    private static GameImage optionTimeClick = new GameImage("res/image 697.png");
    private static GameImage optionnew = new GameImage("res/image 881.jpg");
    private static GameImage optiontime = new GameImage("res/image 881.jpg");

    public static final Position OPTIONGAME_POSITION = new Position(GameWindow.WINDOWWIDTH*4/5,GameWindow.WINDOWHEIGHT*3/6
            , optionNewGame.getWidth(), optionNewGame.getHeight());
    public static final Position OPTIONTIME_POSITION = new Position(GameWindow.WINDOWWIDTH*4/5,GameWindow.WINDOWHEIGHT*4/6
            , optionTime.getWidth(), optionTime.getHeight());
    public static final Position HELP_POSTION = new Position(GameWindow.WINDOWWIDTH*4/5+40,GameWindow.WINDOWHEIGHT*5/6,
            helpButton.getWidth(),helpButton.getHeight());


    public MenuScreen() {
        optionnew = optionNewGame;
        optiontime = optionTime;
        screenSound = new GameSoundReader("res/music/menuMusic.wav");
    }

    public void MouseClick(MouseEvent e) {
        updateMouseLocal();
        switch (e.getButton()) {
            case 1:
                if (currentMousePos.isInside(OPTIONGAME_POSITION)) {

                        ScreenManager.push(new ChooseFishScreen());
                }

                if (currentMousePos.isInside(OPTIONTIME_POSITION)) {
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

}
