package stupid.Models;

import javafx.geometry.Pos;
import stupid.GameWindow;

/**
 * Created by NguyenDuc on 7/13/2016.
 */
public class Position {
    public double x, y;
    public double w, h;
    public int direction = -1;


    public Position(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Position(double x, double y) {
        this(x, y, -1, -1);
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position getCustomPosition(double xFactor, double yFactor, boolean dirAware) {
        if (dirAware) {
            if (direction == -1) {
                return(new Position(x + w * xFactor
                        , y + h * yFactor, w, h));
            } else {
                return(new Position(x + w * (1-xFactor)
                        , y + h * yFactor, w, h));
            }
        }

        return(new Position(x + w * xFactor
                , y + h * yFactor, w, h));
    }

    public Position getCustomBox(double xFactor, double yFactor, double wFactor, double hFactor, boolean dirAware) {
        Position point = getCustomPosition(xFactor, yFactor, dirAware);
        if (dirAware) {
            if (direction == -1) {
                return new Position(point.x, point.y, w*wFactor, h*hFactor);
            } else {
                return new Position(point.x-w*wFactor, point.y, w*wFactor, h*hFactor);
            }
        } else {
            return new Position(point.x, point.y, w*wFactor, h*hFactor);
        }
    }

    public boolean isInside(Position aPos) {
        return ((x >= aPos.x) && (y >= aPos.y) && (x <= (aPos.x + aPos.w)) && (y <= (aPos.y + aPos.h)));
    }

    public boolean isCollide(Position aPos) {
        boolean collideX = (x >= aPos.x && x <= aPos.x + aPos.w) || (aPos.x >= x && aPos.x <= x + w);
        boolean collideY = (y >= aPos.y && y <= aPos.y + aPos.h) || (aPos.y >= y && aPos.y <= y + h);
        return collideX && collideY;
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

    public static Position RANDOMINSIDESCREEN() {
        return new Position(Math.random() * GameWindow.WINDOWWIDTH, Math.random() * GameWindow.WINDOWHEIGHT);
    }

    public static Position RANDOMOUTSIDESCREEN() {
        return new Position((Math.random()-0.5) * GameWindow.WINDOWWIDTH * 2, (Math.random()-0.5) * GameWindow.WINDOWHEIGHT * 2);
    }
}
