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
        currentImage = count / 5;
        time = currentImage / imageList.size();
        currentImage = currentImage % imageList.size();
    }

    public void draw(Graphics g, Position drawPosition) {
        update();
        System.out.println(currentImage);
        imageList.get(currentImage).draw(g, drawPosition);
    }

    public int getWidth() {
        return imageList.get(1).objectImage.getWidth();
    }

    public int getHeight() {
        return imageList.get(1).objectImage.getHeight();
    }

    public void resize(int size) {
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
    }
}
