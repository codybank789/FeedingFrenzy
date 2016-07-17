package stupid.Screen;

import stupid.GameWindow;
import stupid.Interface.ScreenListener;
import stupid.Loader.FishLoader;
import stupid.Models.GameImage;
import stupid.Models.GameSoundReader;
import stupid.Models.Position;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Yuu on 7/16/2016.
 */
public class ChooseFishScreen extends Screen {

    boolean isInit = false;
    private GameImage backGround = new GameImage("res/image 876.jpg").resizeImage(GameWindow.WINDOWWIDTH,GameWindow.WINDOWHEIGHT);
    private GameImage fish1 = new GameImage("res/Fish/Fish1/001.png");
    private GameImage fish3 = new GameImage("res/Fish/Fish3/001.png");
    private GameImage fish7 = new GameImage("res/Fish/Fish7/001.png");
    public static int tyfeOfFish;
    public  ChooseFishScreen()
    {
        screenSound = new GameSoundReader("res/sound/track1.wav");
    }



    @Override
    public void MouseClick(MouseEvent m) {

        updateMouseLocal();
        switch (m.getButton())
        {
            case 1:
                if(currentMousePos.isInside(new Position(GameWindow.WINDOWWIDTH*1/5-150,
                        GameWindow.WINDOWHEIGHT/3,fish1.getWidth(),fish1.getHeight())))
                        {
                            tyfeOfFish = 1;
                            if (isInit == false) {
                                isInit = true;

                                GameLoadingScreen gameLoadingScreen = new GameLoadingScreen();
                                gameLoadingScreen.addScreenListener(new ScreenListener() {
                                    @Override
                                    public void onChildScreenFinish() {
                                    }
                                });
                                ScreenManager.push(gameLoadingScreen);
                                Thread thread = new Thread(new FishLoader());
                                thread.start();
                            } else {
                                ScreenManager.push(new GameSceenNormal(tyfeOfFish-1));
                            }
                        }
                if(currentMousePos.isInside(new Position(GameWindow.WINDOWWIDTH*2/5,
                    GameWindow.WINDOWHEIGHT/3,fish1.getWidth(),fish1.getHeight())))
                        {
                            if (isInit == false) {
                                isInit = true;

                                GameLoadingScreen gameLoadingScreen = new GameLoadingScreen();
                                gameLoadingScreen.addScreenListener(new ScreenListener() {
                                    @Override
                                    public void onChildScreenFinish() {
                                    }
                                });
                                ScreenManager.push(gameLoadingScreen);
                                Thread thread = new Thread(new FishLoader());
                                thread.start();
                            } else {
                                ScreenManager.push(new GameSceenNormal(tyfeOfFish-1));
                            }
                            tyfeOfFish = 3;
                        }
                if(currentMousePos.isInside(new Position(GameWindow.WINDOWWIDTH*3/5+150, GameWindow.WINDOWHEIGHT/3,fish1.getWidth(),fish1.getHeight())))
                        {
                            if (isInit == false) {
                                isInit = true;

                                GameLoadingScreen gameLoadingScreen = new GameLoadingScreen();
                                gameLoadingScreen.addScreenListener(new ScreenListener() {
                                    @Override
                                    public void onChildScreenFinish() {
                                    }
                                });
                                ScreenManager.push(gameLoadingScreen);
                                Thread thread = new Thread(new FishLoader());
                                thread.start();
                            } else {
                                ScreenManager.push(new GameSceenNormal(tyfeOfFish-1));
                            }
                            tyfeOfFish = 7;
                        }
                break;
            default:
                break;
        }
    }


    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        backGround.draw(g,new Position(0,0));
        fish1.draw(g,new Position(GameWindow.WINDOWWIDTH*1/5-150,GameWindow.WINDOWHEIGHT/3));
        fish3.draw(g,new Position(GameWindow.WINDOWWIDTH*2/5,GameWindow.WINDOWHEIGHT/3));
        fish7.draw(g,new Position(GameWindow.WINDOWWIDTH*3/5+150,GameWindow.WINDOWHEIGHT/3));

    }
    private void loadImage() {
        if (isInit == false) {
            FishLoader.initImage();
            isInit = true;
        }
    }
}
