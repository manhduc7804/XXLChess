package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class Bishop extends Piece{
    
    public Bishop(boolean white, PImage img) {
        super(white, img);        
    }

    public boolean canMove(Board board, Tile start, Tile end) {
        if (!end.isValidTile() || !start.isValidTile()) {
            return false;
        }
        return BishopCanMove(board, start, end);
    }
    
}