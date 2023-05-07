package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class Amazon extends Piece{
    
    public Amazon(boolean white, PImage img) {
        super(white, img); 
    }

    public boolean canMove(Board board, Tile start, Tile end) {
        //checcking if the start and end tile is valid
        if (!end.isValidTile() || !start.isValidTile()) {
            return false;
        }
        return KnightCanMove(board, start, end) || BishopCanMove(board, start, end) || RookCanMove(board, start, end);
    }
    
}