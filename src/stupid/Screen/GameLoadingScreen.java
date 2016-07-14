package stupid.Screen;

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
    private GameImage backGround;
    private static final String BACKGROUND_RS ="res/image 881.jpg";
    private GameImage loadingImage;
    private static final String LOADINGIMG_RS = "res/image 886.png";
    private ProgressBar progressBar;

    public GameLoadingScreen()
    {
        progressBar = new ProgressBar(100, 10);
        backGround = new GameImage(BACKGROUND_RS);
        loadingImage = new GameImage(LOADINGIMG_RS);
    }
    @Override
    public void MouseClick(MouseEvent m) {

    }

    @Override
    public void update() {
        if (FishLoader.loadedImages == 1378) {
            ScreenManager.push(new GameSceenNormal());
        }
    }

    @Override
    public void draw(Graphics g) {
        backGround.draw(g, new Position(0, 0));
        loadingImage.draw(g, new Position(20, 20));
        progressBar.updatePercentage(new Position(50, 50), (double) FishLoader.loadedImages/1378, g);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}
