package stupid.Screen;

import stupid.GameWindow;
import stupid.Interface.ScreenListener;
import stupid.Models.GameImage;
import stupid.Models.GameSoundReader;
import stupid.Models.Position;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Yuu on 7/14/2016.
 */
public class GameOverScreen extends Screen {

    private static final String BACKGROUND = "res/image 672.jpg";
    private static final String S_RS = "res/image 131.png";
    private static final String O_RS = "res/image 133.png";
    private static final String R_RS = "res/image 135.png";
    private static final String Y_RS = "res/image 137.png";
    private static final String BACK_RS = "res/backbutton.png";
    private static GameImage backGround =new GameImage(BACKGROUND).resizeImage(GameWindow.WINDOWWIDTH, GameWindow.WINDOWHEIGHT);;
    private static GameImage sImage = new GameImage(S_RS);
    private static GameImage oImage= new GameImage(O_RS);
    private static GameImage  rImage= new GameImage(R_RS);
    private static GameImage yImage= new GameImage(Y_RS);
    private static GameImage backButton = new GameImage(BACK_RS);;
    private static final Position BACKBUTTON= new Position(600,400,backButton.getWidth(),backButton.getHeight());
    private int count ;




    public GameOverScreen() {
        screenSound = new GameSoundReader("res/sounds/GameOver.wav");
    }

    @Override
    public void MouseClick(MouseEvent m) {
        if (m.getButton() == 1){
            if(currentMousePos.isInside(BACKBUTTON))
            {
                if (screenListener != null)
                    screenListener.onChildScreenFinish();
                ScreenManager.pop();
            }
        }
    }

    @Override
    public void update() {
        if(count<300)
            count+=5;
        updateMouseLocal();

    }

    @Override
    public void draw(Graphics g) {
        backGround.draw(g,new Position(0,0));
        if(count>=60) sImage.draw(g,new Position(500,300));
        if (count>=120) oImage.draw(g,new Position(550,300));
        if (count>=180) rImage.draw(g,new Position(600,300));
        if (count>=240) rImage.draw(g,new Position(650,300));
        if (count==300) yImage.draw(g,new Position(700,300));
        if(count==300) backButton.draw(g,BACKBUTTON);
    }
}
