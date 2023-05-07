package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;
import java.lang.Math;

import XXLChess.Board;

public class Knight extends Piece{
    
    //setting the knight
    public Knight(boolean white, PImage img) {
        super(white, img);        
    }
    
    //checking valid move
    public boolean canMove(Board board, Tile start, Tile end) {
        return KnightCanMove(board, start, end);
    }
}