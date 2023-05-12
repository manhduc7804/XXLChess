package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class Chancellor extends Piece{
    
    public Chancellor(boolean white, PImage img) {
        super(white, img);        
    }
    
    //checking valid move
    public boolean canMove(Board board, Logic logic, Tile start, Tile end) {
        if (!end.isValidTile() || !start.isValidTile()) {
            return false;
        }
        if (KnightCanMove(board, start, end) || RookCanMove(board, start, end)) {
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
