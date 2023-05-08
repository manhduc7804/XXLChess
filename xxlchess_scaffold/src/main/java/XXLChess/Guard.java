package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class Guard extends Piece{
    private static final int value = 0;
    
    public Guard(boolean white, PImage img) {
        super(white, img);        
    }
    
    //checking valid move
    public boolean canMove(Board board, Tile start, Tile end) {
        if (!end.isValidTile() || !start.isValidTile()) {
            return false;
        }

        if (end.hasPiece()) {
            if (end.getPiece().isWhite() == white) {
                return false;
            }
        }
        
        if (KnightCanMove(board, start, end)) {
            return true;
        }


        int moveX = Math.abs(end.getX()-start.getX());
        int moveY = Math.abs(end.getY()-start.getY());

        if (moveX == 1 && moveY == 1 || moveX + moveY == 1) {
            return true;
        }
        return false;

    }
    
}