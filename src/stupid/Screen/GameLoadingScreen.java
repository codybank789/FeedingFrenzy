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
    private ProgressBar progressBar ;

    public GameLoadingScreen()
    {
        progressBar = new ProgressBar(177, 15);
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
        updateMouseLocal();
    }

    @Override
    public void draw(Graphics g) {
        backGround.draw(g, new Position(0, 0));
        loadingImage.draw(g, new Position(80, 50));

        progressBar.updatePercentage(new Position(312, 490), (double) FishLoader.loadedImages/1378, g);
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
