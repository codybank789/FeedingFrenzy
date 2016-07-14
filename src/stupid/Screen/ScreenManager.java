package stupid.Screen;

import java.util.Stack;

/**
 * Created by Yuu on 7/14/2016.
 */
public class ScreenManager {
    private static ScreenManager Instance = new ScreenManager();
    public Stack<Screen> stackScreen;
    public static ScreenManager getInstance() {
        return Instance;
    }

    private ScreenManager() {
        stackScreen = new Stack<Screen>();
    }

    public Stack<Screen> getStackScreen() {
        return stackScreen;
    }

    public void setStackScreen(Stack<Screen> stackScreen) {
        this.stackScreen = stackScreen;
    }
}
