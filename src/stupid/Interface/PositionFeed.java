package stupid.Interface;

import stupid.GameObjects.PlayerFish;
import stupid.GameWindow;
import stupid.Models.GameObject;
import stupid.Models.Position;

import java.awt.*;

/**
 * Created by NguyenDuc on 7/16/2016.
 */
public abstract class PositionFeed {
    public abstract Position getPos(Position oldPos);

    public static PositionFeed moveDown() {
        return new PositionFeed() {
            @Override
            public Position getPos(Position oldPos) {
                return new Position(oldPos.x, GameWindow.WINDOWHEIGHT, oldPos.w, oldPos.h);
            }
        };
    }

    public static PositionFeed moveUp() {
        return new PositionFeed() {
            @Override
            public Position getPos(Position oldPos) {
                return new Position(oldPos.x, 0, oldPos.w, oldPos.h);
            }
        };
    }

    public static PositionFeed moveLeft() {
        return new PositionFeed() {
            @Override
            public Position getPos(Position oldPos) {
                return new Position(0, oldPos.y, oldPos.w, oldPos.h);
            }
        };
    }

    public static PositionFeed moveRight() {
        return new PositionFeed() {
            @Override
            public Position getPos(Position oldPos) {
                return new Position(GameWindow.WINDOWWIDTH, oldPos.y, oldPos.w, oldPos.h);
            }
        };
    }

    public static PositionFeed moveRandomly() {
        return new PositionFeed() {
            int count = 0;
            Position lastPos;

            @Override
            public Position getPos(Position oldPos) {
                count++;
                if (count > 180 || lastPos == null) {
                    count = 0;
                    lastPos = Position.RANDOM();
                }
                return lastPos;
            }
        };
    }

    public static PositionFeed followMouse = new PositionFeed() {
        @Override
        public Position getPos(Position oldPos) {
            PointerInfo pointerInfo = MouseInfo.getPointerInfo();
            return new Position(pointerInfo.getLocation().x, pointerInfo.getLocation().y, oldPos.w, oldPos.h);
        }
    };

    public static PositionFeed moveToPlayer = new PositionFeed() {
        int count = 0;
        int count1 = 0;
        Position lastPos = null;
        PlayerFish playerFish;

        @Override
        public Position getPos(Position oldPos) {
            playerFish = GameObject.playerFish;

            count++;
            if (count <= 3600 || lastPos == null) {
                lastPos = playerFish.pos;
            } else if (count <= 1200){
                count1++;
                if (count1 > 120) {
                    lastPos = Position.RANDOMINSIDESCREEN();
                    count1 = 0;
                }
            } else {
                count = 0;
            }
            return lastPos;
        }
    };
}
