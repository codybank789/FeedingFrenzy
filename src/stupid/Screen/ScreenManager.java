package stupid.Screen;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Stack;

/**
 * Created by Yuu on 7/14/2016.
 */
public class ScreenManager {
    public static Frame frame;
    private static ScreenManager Instance = new ScreenManager();
    public static Stack<Screen> stackScreen = new Stack<>();
    public static ScreenManager getInstance() {
        return Instance;
    }

    private ScreenManager() {}

    public static void setFrame(Frame mFrame) {
        frame = mFrame;
    }

    public static Stack<Screen> getStackScreen() {
        return stackScreen;
    }

    public static void mouseClicked(MouseEvent e) {
        stackScreen.peek().MouseClick(e);
    }

    public static Screen getCurrentScreen() {
        return stackScreen.peek();
    }

    public static void push(Screen screen) {
        if (!(stackScreen.isEmpty())) {
            stackScreen.peek().onPause();
        }
        stackScreen.add(screen);
        stackScreen.peek().onResume();
    }

    public static Screen pop() {
        Screen tmp = stackScreen.pop();
        tmp.onPause();
        return tmp;
    }
}
