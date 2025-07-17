package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class Rook extends Piece{
    
    public Rook(boolean white, PImage img) {
        super(white, img);        
    }

    public boolean canMove(Board board, Logic logic, Tile start, Tile end) {
        if (!end.isValidTile() || !start.isValidTile()) {
            return false;
        }
        if (RookCanMove(board, start, end)) {
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