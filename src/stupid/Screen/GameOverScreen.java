package stupid.Screen;

import stupid.Models.GameImage;
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
    private static GameImage backGround =new GameImage(BACKGROUND);;
    private static GameImage sImage = new GameImage(S_RS);
    private static GameImage oImage= new GameImage(O_RS);
    private static GameImage  rImage= new GameImage(R_RS);
    private static GameImage yImage= new GameImage(Y_RS);
    private static GameImage backButton = new GameImage(BACK_RS);;
    private static final Position BACKBUTTON= new Position(400,400,backButton.getWidth(),backButton.getHeight());
    private int count ;


    public GameOverScreen()
    {

    }

    @Override
    public void MouseClick(MouseEvent m) {

    }

    @Override
    public void update() {
        if(count<300)
            count++;
        updateMouseLocal();
        if(currentMousePos.isInside(BACKBUTTON))
        {
            ScreenManager.pop();
        }
    }

    @Override
    public void draw(Graphics g) {
        backGround.draw(g,new Position(0,0));
        if(count>=60) sImage.draw(g,new Position(200,300));
        if (count>=120) oImage.draw(g,new Position(250,300));
        if (count>=180) rImage.draw(g,new Position(300,300));
        if (count>=240) rImage.draw(g,new Position(350,300));
        if (count==300) yImage.draw(g,new Position(400,300));
        if(count==300) backButton.draw(g,BACKBUTTON);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}
