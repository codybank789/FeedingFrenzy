package stupid.Models;

import stupid.Interface.AnimationListener;
import stupid.Loader.FishLoader;

import java.awt.*;
import java.util.Vector;

/**
 * Created by NguyenDuc on 7/13/2016.
 */
public class GameAnimation {

    public AnimationListener animationLister;

    Vector<GameImage> imageList;
    public int speed = 2;
    private int count = 0;
    private int currentImage = 0;
    public int time = 0;

    int size = 0, typeFish, typeAnimation, flip = 0;
    private boolean isResizing = false;
    private int toSize;

    public void addListener(AnimationListener animationListener) {
        this.animationLister = animationListener;
    }

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

        if (isDone()) {
            if (animationLister != null) {
                animationLister.animationFinished();
                reset();
            }
        }
    }

    public void draw(Graphics g, Position drawPosition) {
        update();
        if (isResizing) {
            double factor = FishLoader.FISHSIZE[toSize] / FishLoader.FISHSIZE[size] - 1;
            double xFactor = (double)currentImage/imageList.size() * factor + 1;
            Position newPos = drawPosition.getCustomBox(0, 0, xFactor, xFactor, false);
            imageList.get(currentImage).draw(g, newPos);
        } else {
            imageList.get(currentImage).draw(g, drawPosition);
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
        flip = (flip + 1) % 2;
        imageList = FishLoader.getImages(size, this.typeFish, this.typeAnimation, flip);

    }

    public void reset() {
        count = 0;
        time = 0;
        currentImage = 0;
        isResizing = false;
    }

    public boolean isDone() {
        return time >= 1;
    }

    public void setResize(int resize) {
        isResizing = true;
        this.toSize = resize;
    }
}
