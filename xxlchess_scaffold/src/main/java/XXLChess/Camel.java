package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class Camel extends Piece{
    
    public Camel(boolean white, PImage img) {
        super(white, img);
    }
    
    public boolean canMove(Board board, Tile start, Tile end) {
         // check if the end tile is the same piece color or not
         if (!end.isValidTile() || !start.isValidTile()) {
            return false;
        }
         if (end.getPiece().isWhite() == white) {
            return false;
        }

        int moveX = Math.abs(end.getX()-start.getX());
        int moveY = Math.abs(end.getY()-start.getY());

        if (Math.abs(moveX) * Math.abs(moveY) == 3) {
            return true;
        }
        return false;
    }
    
}