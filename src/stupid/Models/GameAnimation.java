package stupid.Models;

import stupid.Interface.AnimationAdapter;
import stupid.Interface.DisplayInterface;
import stupid.Loader.FishLoader;

import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by NguyenDuc on 7/13/2016.
 */
public class GameAnimation {

    Vector<GameImage> imageList;
    public int speed = 5;
    public boolean resizing = false;
    public int to = 0;
    private int count = 0;
    private int currentImage = 0;
    public int time = 0;

    int size = 0, typeFish, typeAnimation, flip = 0;

    public GameAnimation(int size, int typeFish, int typeAnimation) {
        this.size = size;
        this.typeFish = typeFish;
        this.typeAnimation = typeAnimation;

        imageList = FishLoader.getImages(size, typeFish, typeAnimation, 0);
    }

    public void update() {
        count++;
        currentImage = count / speed;
        time = currentImage / imageList.size();
        currentImage = currentImage % imageList.size();

        if (isDone() && resizing) {
            reset();
        }
    }

    public void draw(Graphics g, Position drawPosition) {
        update();
        if (resizing == false) {
            imageList.get(currentImage).draw(g, drawPosition);
        } else {
            double xFactor = FishLoader.FISHSIZE[size] +
                    (FishLoader.FISHSIZE[to] - FishLoader.FISHSIZE[size]) * (count/imageList.size());
            double yFactor = xFactor;
            imageList.get(currentImage).draw(g, drawPosition.getCustomBox(0, 0
                    , xFactor, yFactor, false));
        }

    }

    public int getWidth() {
        return imageList.get(1).objectImage.getWidth();
    }

    public int getHeight() {
        return imageList.get(1).objectImage.getHeight();
    }

    public void resize(int size) {
        this.size = size;
        imageList = FishLoader.getImages(size, this.typeFish, this.typeAnimation, flip);
    }

    public void flipAnimation() {
        flip = (flip+1)%2;
        imageList = FishLoader.getImages(size, this.typeFish, this.typeAnimation, flip);

    }

    public void reset() {
        count = 0;
        time = 0;
        currentImage = 0;
        resizing = false;
    }

    public void setResize(int to) {
        reset();
        resizing = true;
        this.to = to;
    }

    public boolean isDone() {
        return time >= 1;
    }
}
