package stupid.Screen;

import stupid.Loader.FishLoader;
import stupid.Models.GameImage;
import stupid.Models.Position;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Yuu on 7/15/2016.
 */
public class HelpScreen extends Screen {

    public static GameImage background = new GameImage("res/helpbackground.jpg");
    private static final String BACK_RS = "res/backbutton.png";
    private static GameImage backButton = new GameImage(BACK_RS);
    private static final Position BACKBUTTON= new Position(350,450,backButton.getWidth(),backButton.getHeight());



    @Override
    public void MouseClick(MouseEvent m) {
        updateMouseLocal();
        switch (m.getButton()) {
            case 1:
                if(currentMousePos.isInside(BACKBUTTON))
                {
                    ScreenManager.pop();
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
        background.draw(g,new Position(0,0));
        backButton.draw(g,BACKBUTTON);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}
