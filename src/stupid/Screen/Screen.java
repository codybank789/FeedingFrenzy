package stupid.Screen;

import javafx.geometry.Pos;
import stupid.Models.Position;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Yuu on 7/14/2016.
 */
public abstract class Screen {
    public PointerInfo mouseInfo;
    public Position currentMousePos = new Position(0, 0);
    public void updateMouseLocal() {
        mouseInfo = MouseInfo.getPointerInfo();
        currentMousePos.setPosition(mouseInfo.getLocation().x, mouseInfo.getLocation().y);
    }
    public abstract void MouseClick(MouseEvent m);
    public abstract void update();
    public abstract void draw(Graphics g);
    public abstract void onResume();
    public abstract void onPause();
}
