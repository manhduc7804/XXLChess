package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class Bishop extends Piece{
    
    public Bishop(boolean white, PImage img) {
        super(white, img);        
    }

    public boolean canMove(Board board, Logic logic, Tile start, Tile end) {
        if (!end.isValidTile() || !start.isValidTile()) {
            return false;
        }
        if (BishopCanMove(board, start, end)) {
            if (Blocking(board, logic, start, end)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
}