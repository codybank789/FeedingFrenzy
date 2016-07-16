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
    boolean isLevelingUp = false;
    public static final int LEVELUP = 300;
    public static final int MAXHEALTH = 100;
    public static final int SPEED = 7;
    private GameImage scoreBoard = new GameImage("res/image 810.png").resizeImage(GameWindow.WINDOWWIDTH,GameWindow.WINDOWHEIGHT/6);
    public int score=0;
    public int oldScore = 0;
    public int health=MAXHEALTH;
    public int oldHealth = MAXHEALTH;
    public int overallScore = 0;
    public int level = 0;

    private ProgressBar progressBar;
    private ProgressBar progressBar1;
    private int nextScore = 0;

    public ScoreBoard()
    {
        progressBar = new ProgressBar(514,15);
        progressBar1 = new ProgressBar(224,15);
    }

    @Override
    public void draw(Graphics g) {
        if (score < 0) score = 0;
        if (Math.abs(oldScore-score) < SPEED) {
            if (isLevelingUp) {
                oldScore = 0;
                score = nextScore;
                isLevelingUp = false;
            } else {
                oldScore = score;
            }
        }
        if (Math.abs(oldHealth-health) < SPEED) oldHealth = health;
        oldScore += Math.signum(score - oldScore)*SPEED;
        oldHealth += Math.signum(health - oldHealth)*SPEED;
        scoreBoard.draw(g,new Position(9,30));
        progressBar.updatePercentage(new Position(175,116),(double)oldScore/LEVELUP,g);
        progressBar1.updatePercentage(new Position(935,123),(double)oldHealth/MAXHEALTH,g);
        g.drawString(String.valueOf(overallScore),962,65);
    }

    @Override
    public void callbackDelete(GameObject gameObject) {

    }

    public void increaseScore(int delta) {
        if (level < 3){
            score += delta;
        }
        overallScore += delta;
    }

    public void levelup() {
        level++;
        if (level == 3) {
            score = LEVELUP;
        } else {
            nextScore = score - LEVELUP;
            score = LEVELUP;
            isLevelingUp = true;
        }
    }
}
