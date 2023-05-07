package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class Pawn extends Piece{
    
    public Pawn(boolean white, PImage img) {
        super(white, img);        
    }    

    public boolean canMove(Board board, Tile start, Tile end) {
        // return KnightCanMove(board, start, end);
        return false;
    }
}
