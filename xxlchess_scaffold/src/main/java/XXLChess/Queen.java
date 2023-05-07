package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class Queen extends Piece{
    
    public Queen(boolean white, PImage img) {
        super(white, img); 
    }

    public boolean canMove(Board board, Tile start, Tile end) {
        //checcking if the start and end tile is valid
        if (!end.isValidTile() || !start.isValidTile()) {
            return false;
        }
        return BishopCanMove(board, start, end) || RookCanMove(board, start, end);
    }
    
}
