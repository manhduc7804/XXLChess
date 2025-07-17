package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class Queen extends Piece{
    
    public Queen(boolean white, PImage img) {
        super(white, img); 
    }

    public boolean canMove(Board board, Logic logic, Tile start, Tile end) {
        //checcking if the start and end tile is valid
        if (!end.isValidTile() || !start.isValidTile()) {
            return false;
        }
        if (BishopCanMove(board, start, end) || RookCanMove(board, start, end)) {
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
