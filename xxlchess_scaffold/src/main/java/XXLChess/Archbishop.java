package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class Archbishop extends Piece{
    
    public Archbishop(boolean white, PImage img) {
        super(white, img);
    }

    public boolean canMove(Board board, Tile start, Tile end) {
        if (!end.isValidTile() || !start.isValidTile()) {
            return false;
        }
        return KnightCanMove(board, start, end) || BishopCanMove(board, start, end);
    }
    
}
