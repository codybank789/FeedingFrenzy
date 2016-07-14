package stupid.Models;

import stupid.Interface.DisplayInterface;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by NguyenDuc on 7/13/2016.
 */
public class GameImage{

    BufferedImage objectImage;
    int direction = 1;

    public GameImage (String path, int direction) {
        this(path);

        if (direction == -1) {
            flipImage();
        }
    }
    public GameImage (String path) {
        try {
            objectImage = ImageIO.read(new File(path));
        }catch (IOException e) {
            System.out.println(path);
        }
    }

    public GameImage(BufferedImage objectImage) {
        this.objectImage = objectImage;
    }


    public void flipImage() {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-objectImage.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        objectImage = op.filter(objectImage, null);
    }
    public void resizeImage(double time) {
        int newHeight = (int)(objectImage.getHeight()*time);
        int newWidth = (int)(objectImage.getWidth()*time);
        Image tmp = objectImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage newPlaneImage = new BufferedImage(newWidth, newHeight,BufferedImage.TYPE_INT_ARGB);
        newPlaneImage.getGraphics().drawImage(tmp,0,0,null);
        objectImage = newPlaneImage;
    }

    public void draw(Graphics g, Position drawPosition) {
        g.drawImage(objectImage, (int)drawPosition.x, (int)drawPosition.y, null);
    }
    public void customDraw(Graphics g, Position drawPosition, double xFactor, double yFactor, boolean dirAware) {
        if (dirAware) {
            if (drawPosition.direction == 1) {
                draw(g, drawPosition.getCustomPosition(-xFactor, -yFactor, false));
            } else {
                draw(g, drawPosition.getCustomPosition(-xFactor, yFactor, true));
            }
        } else {
            draw(g, drawPosition.getCustomPosition(-xFactor, -yFactor, false));
        }
    }

    public GameImage clone() {
        BufferedImage objectImageClone = new BufferedImage(objectImage.getWidth()
                , objectImage.getHeight(),BufferedImage.TYPE_INT_ARGB);
        objectImageClone.getGraphics().drawImage(objectImage, 0, 0, null);
        return new GameImage(objectImageClone);
    }

}
