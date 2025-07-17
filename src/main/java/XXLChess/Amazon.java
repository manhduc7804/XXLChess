package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

import java.util.ArrayList;

public class Amazon extends Piece{
    
    public Amazon(boolean white, PImage img) {
        super(white, img); 
    }

    public boolean canMove(Board board, Logic logic, Tile start, Tile end) {
        //checking if the start and end tile is valid
        if (!end.isValidTile() || !start.isValidTile()) {
            return false;
        }
        if (KnightCanMove(board, start, end) || BishopCanMove(board, start, end) || RookCanMove(board, start, end)) {
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