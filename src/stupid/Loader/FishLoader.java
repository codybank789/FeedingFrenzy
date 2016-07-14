package stupid.Loader;

import stupid.Models.GameAnimation;
import stupid.Models.GameImage;

import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by Yuu on 7/13/2016.
 */
public class FishLoader {

    public static final int SIZEOFFISH = 3;
    public static final int TYPEOFFISH = 11;
    public static final int TYPEOFANIMATION = 3;
    private static final int NUMBERIMAGE = 7;

    private static Vector<GameImage>[][][] listImage;

    static {
        listImage = new Vector[SIZEOFFISH][TYPEOFFISH][TYPEOFANIMATION];
        int imageCount = 0;
        int i = 1;
            for (int j = 0;j<TYPEOFFISH;j++)
                for(int k =0;k<TYPEOFANIMATION;k++)
                {
                    listImage[i][j][k] = new Vector<>();

                    for (int l = 0;l<NUMBERIMAGE;l++) {
                        imageCount++;

                        String value = String.valueOf(imageCount);
                        int length = value.length();
                        for(int t = 0; t < 3 - length; t++) {
                            value = "0" + value;
                        }

                        listImage[i][j][k].add(new GameImage("res/Fish/"+value+".png"));
                    }

                }
    }

    public static Vector getImages(int size, int typeFish, int typeAnimation)
    {
     return listImage[size][typeFish][typeAnimation];
    }
}
