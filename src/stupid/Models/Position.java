package stupid.Models;

import stupid.GameWindow;

/**
 * Created by NguyenDuc on 7/13/2016.
 */
public class Position {
    public double x, y;
    public double w, h;
    public int direction;


    public Position(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Position(double x, double y) {
        this(x, y, 0, 0);
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position getCustomPosition(double xFactor, double yFactor, boolean dirAware) {
        if (dirAware) {
            if (direction == 1) {
                return(new Position(x + w * xFactor
                        , y + h * yFactor, w, h));
            } else {
                return(new Position(x + w * xFactor
                        , y + h * (1-yFactor), w, h));
            }
        }

        return(new Position(x + w * xFactor
                , y + h * yFactor, w, h));
    }

    public boolean isInside(Position aPos) {
        return ((x >= aPos.x) && (y >= aPos.y) && (x <= (aPos.x + aPos.w)) && (y <= (aPos.y + aPos.h)));
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public Position getVerBox() {
        Position result = new Position(0, 0);
        result.x = x + w * 0.3;
        result.y = y;
        result.w = w * 0.4;
        result.h = h;
        result.direction = direction;

        return result;
    }

    public Position getHorBox() {
        Position result = new Position(0, 0);
        result.x = x;
        result.y = y + h * 0.3;
        result.h = h * 0.4;
        result.w = w;
        result.direction = direction;

        return result;
    }

    public void print() {
        System.out.println(x + " " + y);
    }

    public static Position RANDOM() {
        return new Position(Math.random() * GameWindow.WINDOWWIDTH, Math.random() * GameWindow.WINDOWHEIGHT);
    }
}
