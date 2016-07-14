package stupid;

public class Main {

    public static GameWindow GAMEWINDOW = new GameWindow();
    public static void main(String[] args) {
        Thread thread = new Thread(GAMEWINDOW);
        thread.start();
    }
}
