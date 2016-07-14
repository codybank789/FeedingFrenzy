package stupid.Loader;

import stupid.Models.GameAnimation;
import stupid.Models.GameImage;

import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by Yuu on 7/13/2016.
 */
public class FishLoader {

    private Vector<GameImage>[][][] listImage;
    public static final int SIZEOFFISH = 3;
    public static final int TYPEOFFISH = 11;
    public static final int TYPEOFANIMATION = 3;
    private GameImage gameImage ;
    private int numberofimg =1;
    public FishLoader()
    {
        listImage = new Vector[SIZEOFFISH][TYPEOFFISH][TYPEOFANIMATION];
    }
    public void loadAll()
    {
        int i=1;
        for (int j = 0;j<TYPEOFFISH;j++) {
            numberofimg=0;
            for (int k = 0; k < TYPEOFANIMATION; k++) {
                for (int l = 0; l < checkType(j, k); l++) {
                    numberofimg++;
                    String value = String.valueOf(numberofimg);
                    for (int m = 0; m < 3 - value.length(); m++)
                        value = "0" + value;
                    gameImage = new GameImage("res/Fish" + String.valueOf(j) + "/Fish/" + value + ".png");
                    listImage[i][j][k].add(gameImage);
                }
            }
        }
    }
    private Vector getImages(int size, int typeFish, int typeAnimation)
    {
        return listImage[size][typeFish][typeAnimation];
    }
    private int checkType(int j,int k)
    {
        int check =0;
        switch (j)
        {
            case 1:
                switch (k){
                    case 1:
                        check=14;
                        break;
                    case 2:
                        check=7;
                        break;
                    case 3:
                        check =5;
                        break;
                }
                break;
            case 2:
                switch (k){
                    case 1:
                        check =7;
                        break;
                    case 2:
                        check =6;
                        break;
                    case 3:
                        check =5;
                        break;
                }
                break;
            case 3:
                switch (k){
                    case 1:
                        check=12;
                        break;
                    case 2:
                        check =5;
                        break;
                    case 3:
                        check =4;
                        break;
                }
                break;
            case 4:
                switch (k){
                    case 1:
                        check =15;
                        break;
                    case 2:
                        check =4;
                        break;
                    case 3:
                        check =3;
                        break;
                }
                break;
            case 5:
                switch (k){
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
                switch (k){
                    case 1:
                        check =14;
                        break;
                    case 2:
                        check =5;
                        break;
                    case 3:
                        check =6;
                        break;
                }
                break;
            case 7:
                switch (k){
                    case 1:
                        check =14;
                        break;
                    case 2:
                        check = 5;
                        break;
                    case 3:
                        check =6;
                        break;
                }
                break;
            case 8:
                switch (k){
                    case 1:
                        check =15;
                        break;
                    case 2:
                        check = 4;
                        break;
                    case 3:
                        check =7;
                        break;
                }
                break;
            case 9:
                switch (k){
                    case 1:
                        check = 2;
                        break;
                    case 2:
                        check =2;
                        break;
                    case 3:
                        check =1;
                        break;
                }
                break;
            case 10:
                switch (k){
                    case 1:
                        check = 20;
                        break;
                    case 2:
                        check =0;
                        break;
                    case 3:
                        check =0;
                        break;
                }
                break;
            case 11:
                switch (k){
                    case 1:
                        check =15;
                        break;
                    case 2:
                        check =0;
                        break;
                    case 3:
                        check =0;
                        break;
                }
                break;
            default:
                break;
        }
        return check;
    }
}
