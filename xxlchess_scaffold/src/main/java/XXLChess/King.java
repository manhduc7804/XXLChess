package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class King extends Piece{
    
    public King(boolean white, PImage img) {
        super(white, img);        
    }
    
    //checking valid move
    public boolean canMove(Board board, Tile start, Tile end) {
        if (!end.isValidTile() || !start.isValidTile()) {
            return false;
        }

        if (end.getPiece().isWhite() == white) {
            return false;
        }

        int moveX = Math.abs(end.getX()-start.getX());
        int moveY = Math.abs(end.getY()-start.getY());

        if ((Math.abs(moveX) + Math.abs(moveY) == 1) || ((Math.abs(moveX) + Math.abs(moveY) == 2)) ) {
            return true;
        }
        return false;

    }
    
}
