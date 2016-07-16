package stupid.GameObjects;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import stupid.GameWindow;
import stupid.Models.GameImage;
import stupid.Models.GameObject;
import stupid.Models.Position;
import stupid.Models.ProgressBar;

import java.awt.*;

/**
 * Created by Yuu on 7/16/2016.
 */
public class ScoreBoard extends GameObject {
    private GameImage scoreBoard = new GameImage("res/image 810.png").resizeImage(GameWindow.WINDOWWIDTH,GameWindow.WINDOWHEIGHT/6);
    private int score;
    private int health=3;
    private ProgressBar progressBar;
    private ProgressBar progressBar1;
    public ScoreBoard()
    {
        progressBar = new ProgressBar(514,15);
        progressBar1 = new ProgressBar(224,15);
    }

    @Override
    public void draw(Graphics g) {
        scoreBoard.draw(g,new Position(9,30));
        progressBar.updatePercentage(new Position(175,116),score/15000,g);
        progressBar.updatePercentage(new Position(935,123),health/3,g);
        g.drawString(String.valueOf(score),962,65);

    }

    @Override
    public void callbackDelete(GameObject gameObject) {

    }
}
