package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class Chancellor extends Piece{
    
    public Chancellor(boolean white, PImage img) {
        super(white, img);        
    }
    
    //checking valid move
    public boolean canMove(Board board, Tile start, Tile end) {
        if (!end.isValidTile() || !start.isValidTile()) {
            return false;
        }
        return KnightCanMove(board, start, end) || RookCanMove(board, start, end);
    }
    
}
