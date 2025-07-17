package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class Camel extends Piece{
    
    public Camel(boolean white, PImage img) {
        super(white, img);
    }
    
    public boolean canMove(Board board, Logic logic, Tile start, Tile end) {
         // check if the end tile is the same piece color or not
         if (!end.isValidTile() || !start.isValidTile()) {
            return false;
        }
        if (end.hasPiece()) {
            if (end.getPiece().isWhite() == white) {
                return false;
            }
        }

        int moveX = Math.abs(end.getX()-start.getX());
        int moveY = Math.abs(end.getY()-start.getY());

        if (moveX * moveY == 3) {
            if (Blocking(board, logic, start, end)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
}