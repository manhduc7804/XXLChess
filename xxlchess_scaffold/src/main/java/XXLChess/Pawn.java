package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class Pawn extends Piece{
    
    public Pawn(boolean white, PImage img) {
        super(white, img);        
    }    

    public boolean canMove(Board board, Tile start, Tile end) {
        if (!end.isValidTile() || !start.isValidTile()) {
            return false;
        }

        if (end.hasPiece()) {
            if (end.getPiece().isWhite() == white) {
                return false;
            }
        }

        int moveX = end.getX()-start.getX();
        int moveY = end.getY()-start.getY();

        if (moveX == 2 && moveY == 0 && firstmove && !white && !end.hasPiece() && !board.board_tiles[start.getX()+1][start.getY()].hasPiece()) {
            return true;
        } else if (moveX == -2 && moveY == 0 && firstmove && white && !end.hasPiece() && !board.board_tiles[start.getX()-1][start.getY()].hasPiece()) {
            return true;
        } else if (moveX == -1 && moveY == 0 && white && !end.hasPiece()) {
            return true;
        } else if (moveX == 1 && moveY == 0 && !white && !end.hasPiece()) {
            return true;
        } else if (((moveX == 1 && moveY == 1) || (moveX == 1 && moveY == -1)) && !white && end.hasPiece()) {
            return true;
        } else if (((moveX == -1 && moveY == -1) || (moveX == -1 && moveY == 1)) && white && end.hasPiece()) {
            return true;
        }
        return false;
    }
}
