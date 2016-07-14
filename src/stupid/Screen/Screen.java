package stupid.Screen;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Yuu on 7/14/2016.
 */
public abstract class Screen {
    private PointerInfo mouseInfo;
    public int mouseLocalX;
    public int mouseLocalY;
    public void updateMouseLocal()
    {
        mouseInfo = MouseInfo.getPointerInfo();
        mouseLocalX = mouseInfo.getLocation().x;
        mouseLocalY = mouseInfo.getLocation().y;
    }
    public abstract void MouseClick(MouseEvent m);
    public abstract void update();
    public abstract void draw(Graphics g);
}
