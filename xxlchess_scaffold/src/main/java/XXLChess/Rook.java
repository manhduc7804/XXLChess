package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class Rook extends Piece{
    
    public Rook(boolean white, PImage img) {
        super(white, img);        
    }

    public boolean canMove(Board board, Tile start, Tile end) {
        return RookCanMove(board, start, end);
    }
    
}