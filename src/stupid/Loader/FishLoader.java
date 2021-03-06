package stupid.Loader;

import stupid.Models.GameAnimation;
import stupid.Models.GameImage;

import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by Yuu on 7/13/2016.
 */
public class FishLoader implements Runnable{
    public static int loadedImages = 0;
    public static final double[] FISHSIZE = {0.5, 1, 1.4, 0};
    public static final int SIZEOFFISH = 3;
    public static final int TYPEOFFISH = 11;
    public static final int TYPEOFANIMATION = 3;
    public static final int TYPEFPLIP = 2;
    public static Vector<GameImage>[][][][] listImage = new Vector[SIZEOFFISH][TYPEOFFISH][TYPEOFANIMATION][TYPEFPLIP];

    static public int getNumberOfLoadedImages() {
        return loadedImages;
    }

    public FishLoader() {}

    static public void initImage() {
        int i = 1;
        int j, k, l, m, n;
        for (j = 0; j < TYPEOFFISH; j++) {
            int numberofimg = 0;
            for (k = 0; k < TYPEOFANIMATION; k++) {
                listImage[i][j][k][0] = new Vector<>();

                int count = checkType(j + 1, k + 1);
                for (l = 0; l < count; l++) {
                    numberofimg++;
                    String value = String.valueOf(numberofimg);
                    int length = value.length();
                    for (m = 0; m < 3 - length; m++)
                        value = "0" + value;
                    listImage[i][j][k][0].add(new GameImage("res/Fish" + "/Fish" + String.valueOf(j + 1) + "/" + value + ".png"));
                    if (j <= 8) {
                        listImage[i][j][k][0].lastElement().resizeImage(150, 150);
                    }
                }
            }
        }
        for (i = 0; i < SIZEOFFISH; i++) {
            if (i != 1) {
                for (j = 0; j < TYPEOFFISH; j++)
                    for (k = 0; k < TYPEOFANIMATION; k++) {
                        listImage[i][j][k][0] = new Vector<>();

                        int count = checkType(j + 1, k + 1);
                        for (l = 0; l < count; l++) {
                            GameImage gameImage = listImage[1][j][k][0].get(l).clone();
                            gameImage.resizeImage(FISHSIZE[i]);
                            listImage[i][j][k][0].add(gameImage);
                        }
                    }
            }
        }

        for (i = 0 ;i < SIZEOFFISH; i++) {
            for (j = 0; j < TYPEOFFISH; j++)
                for (k = 0; k < TYPEOFANIMATION; k++) {
                    int count = checkType(j + 1, k + 1);
                    listImage[i][j][k][1] = new Vector<>();
                    for (l = 0; l < count; l++) {
                        GameImage gameImage = listImage[i][j][k][0].get(l).clone();
                        gameImage.flipImage();
                        listImage[i][j][k][1].add(gameImage);
                    }
                }
        }
        System.out.println(loadedImages);

    }

    public static Vector getImages(int size, int typeFish, int typeAnimation, int flip) {
        return listImage[size][typeFish][typeAnimation][flip];
    }

    private static int checkType(int j, int k) {
        int check = 0;
        switch (j) {
            case 1:
                switch (k) {
                    case 1:
                        check = 13;
                        break;
                    case 2:
                        check = 5;
                        break;
                    case 3:
                        check = 7;
                        break;
                }
                break;
            case 2:
                switch (k) {
                    case 1:
                        check = 7;
                        break;
                    case 2:
                        check = 6;
                        break;
                    case 3:
                        check = 5;
                        break;
                }
                break;
            case 3:
                switch (k) {
                    case 1:
                        check = 12;
                        break;
                    case 2:
                        check = 5;
                        break;
                    case 3:
                        check = 4;
                        break;
                }
                break;
            case 4:
                switch (k) {
                    case 1:
                        check = 15;
                        break;
                    case 2:
                        check = 4;
                        break;
                    case 3:
                        check = 3;
                        break;
                }
                break;
            case 5:
                switch (k) {
                    case 1:
                        check = 15;
                        break;
                    case 2:
                        check = 5;
                        break;
                    case 3:
                        check = 6;
                        break;
                }
                break;
            case 6:
                switch (k) {
                    case 1:
                        check = 14;
                        break;
                    case 2:
                        check = 5;
                        break;
                    case 3:
                        check = 6;
                        break;
                }
                break;
            case 7:
                switch (k) {
                    case 1:
                        check = 14;
                        break;
                    case 2:
                        check = 5;
                        break;
                    case 3:
                        check = 6;
                        break;
                }
                break;
            case 8:
                switch (k) {
                    case 1:
                        check = 15;
                        break;
                    case 2:
                        check = 4;
                        break;
                    case 3:
                        check = 7;
                        break;
                }
                break;
            case 9:
                switch (k) {
                    case 1:
                        check = 1;
                        break;
                    case 2:
                        check = 3;
                        break;
                    case 3:
                        check = 1;
                        break;
                }
                break;
            case 10:
                switch (k) {
                    case 1:
                        check = 20;
                        break;
                    case 2:
                        check = 0;
                        break;
                    case 3:
                        check = 0;
                        break;
                }
                break;
            case 11:
                switch (k) {
                    case 1:
                        check = 15;
                        break;
                    case 2:
                        check = 0;
                        break;
                    case 3:
                        check = 0;
                        break;
                }
                break;
            default:
                break;
        }
        return check;
    }

    @Override
    public void run() {
        initImage();
    }

    public static double getPercentage() {
        return (double) loadedImages/1382;
    }

    public static boolean isDone() {
        return  loadedImages == 1382;
    }
}
