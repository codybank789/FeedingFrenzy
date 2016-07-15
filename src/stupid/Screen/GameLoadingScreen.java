package stupid.Screen;

import stupid.GameWindow;
import stupid.Interface.ScreenListener;
import stupid.Loader.FishLoader;
import stupid.Models.GameImage;
import stupid.Models.Position;
import stupid.Models.ProgressBar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Yuu on 7/14/2016.
 */
public class GameLoadingScreen extends Screen {
    private GameImage loadingImage;
    private static final String LOADINGIMG_RS = "res/image 886.png";
    private ProgressBar progressBar ;

    public GameLoadingScreen()
    {
        progressBar = new ProgressBar(378, 25);
        loadingImage = new GameImage(LOADINGIMG_RS);
        loadingImage.resizeImage(GameWindow.WINDOWWIDTH, GameWindow.WINDOWHEIGHT);
    }
    @Override
    public void MouseClick(MouseEvent m) {

    }

    @Override
    public void update() {
        if (FishLoader.isDone()) {
            GameSceenNormal gameSceenNormal = new GameSceenNormal();
            gameSceenNormal.addScreenListener(new ScreenListener() {
                @Override
                public void onChildScreenFinish() {
                    ScreenManager.pop();
                    if (screenListener != null) {
                        screenListener.onChildScreenFinish();
                    }
                }
            });
            ScreenManager.push(gameSceenNormal);
        }
        updateMouseLocal();
        System.out.println(currentMousePos);
    }

    @Override
    public void draw(Graphics g) {
        loadingImage.draw(g, new Position(0, 0));
        System.out.println(MouseInfo.getPointerInfo().getLocation().x+ " " + MouseInfo.getPointerInfo().getLocation().y);

        progressBar.updatePercentage(new Position(495, 704), (double) FishLoader.getPercentage(), g);
        //System.out.println(FishLoader.loadedImages);
//        g.drawRect(312,490,177,15);
//        g.setColor(Color.YELLOW);
//        g.fillRect(312,490,(int)(FishLoader.loadedImages/1378*177),15);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}
