package stupid.Screen;

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
    private BufferedImage backGround;
    private static final String BACKGROUND_RS ="res/image 881.png";
    private BufferedImage loadingImage;
    private static final String LOADINGIMG_RS = "res/image 886.png";
    public GameLoadingScreen()
    {
        try {
            backGround = ImageIO.read(new File(BACKGROUND_RS));
            loadingImage = ImageIO.read(new File(LOADINGIMG_RS));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void MouseClick(MouseEvent m) {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

    }
}
