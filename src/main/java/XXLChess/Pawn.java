package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class Pawn extends Piece{
    
    public Pawn(boolean white, PImage img) {
        super(white, img);        
    }    

    public boolean canMove(Board board, Logic logic, Tile start, Tile end) {
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
            if (Blocking(board, logic, start, end)) {
                return true;
            } else {
                return false;
            }
        } else if (moveX == -2 && moveY == 0 && firstmove && white && !end.hasPiece() && !board.board_tiles[start.getX()-1][start.getY()].hasPiece()) {
            if (Blocking(board, logic, start, end)) {
                return true;
            } else {
                return false;
            }
        } else if (moveX == -1 && moveY == 0 && white && !end.hasPiece()) {
            if (Blocking(board, logic, start, end)) {
                return true;
            } else {
                return false;
            }
        } else if (moveX == 1 && moveY == 0 && !white && !end.hasPiece()) {
            if (Blocking(board, logic, start, end)) {
                return true;
            } else {
                return false;
            }
        } else if (((moveX == 1 && moveY == 1) || (moveX == 1 && moveY == -1)) && !white && end.hasPiece()) {
            if (Blocking(board, logic, start, end)) {
                return true;
            } else {
                return false;
            }
        } else if (((moveX == -1 && moveY == -1) || (moveX == -1 && moveY == 1)) && white && end.hasPiece()) {
            if (Blocking(board, logic, start, end)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
