package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;

public class King extends Piece{
    
    public King(boolean white, PImage img) {
        super(white, img);        
    }
    
    //checking valid move
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

        // castling
        if (firstmove) {
            if (moveX == 0 && moveY == -2 && leftcastling(board)) {
                logic.setKing(white, end.getX(), end.getY());
                if (Blocking(board, logic, start, end)) {
                    logic.setKing(white, start.getX(), start.getY());
                    return true;
                } else {
                    logic.setKing(white, start.getX(), start.getY());
                    return false;
                }
            } else if (moveX == 0 && moveY == 2 && rightcastling(board)) {
                logic.setKing(white, end.getX(), end.getY());
                if (Blocking(board, logic, start, end)) {
                    logic.setKing(white, start.getX(), start.getY());
                    return true;
                } else {
                    logic.setKing(white, start.getX(), start.getY());
                    return false;
                }
            }
        }

        // normal move
        if (Math.abs(moveX) == 1 && Math.abs(moveY) == 1 || Math.abs(moveX) + Math.abs(moveY) == 1) {
            logic.setKing(white, end.getX(), end.getY());
            if (Blocking(board, logic, start, end)) {
                logic.setKing(white, start.getX(), start.getY());
                return true;
            } else {
                logic.setKing(white, start.getX(), start.getY());
                return false;
            }
        } 
        return false;

    }

    private boolean leftcastling(Board board) {
        if (white) {
            if (board.board_tiles[13][0].hasPiece()) {
                if (!board.board_tiles[13][0].getPiece().IsFisrtMove() || !(board.board_tiles[13][0].getPiece() instanceof Rook)) {
                    return false;
                }
            } else {
                return false;
            }
            for (int i=1; i<7; i++) {
                if (board.board_tiles[13][i].hasPiece()) {
                    return false;
                }
            }
        } else {
            if (board.board_tiles[0][0].hasPiece()) {
                if (!board.board_tiles[0][0].getPiece().IsFisrtMove() || !(board.board_tiles[0][0].getPiece() instanceof Rook)) {
                    return false;
                }
            } else {
                return false;
            }
            for (int i=1; i<7; i++) {
                if (board.board_tiles[0][i].hasPiece()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean rightcastling(Board board) {
        if (white) {
            if (board.board_tiles[13][13].hasPiece()) {
                if (!board.board_tiles[13][13].getPiece().IsFisrtMove() || !(board.board_tiles[13][13].getPiece() instanceof Rook)) {
                    System.out.println(1);
                    return false;
                }
            } else {
                System.out.println(2);
                return false;
            }
            for (int i=8; i<13; i++) {
                if (board.board_tiles[13][i].hasPiece()) {
                    System.out.println(3);
                    return false;
                }
            }
        } else {
            if (board.board_tiles[0][13].hasPiece()) {
                if (!board.board_tiles[0][13].getPiece().IsFisrtMove() || !(board.board_tiles[0][13].getPiece() instanceof Rook)) {
                    System.out.println(4);
                    return false;
                }
            } else {
                System.out.println(5);
                return false;
            }
            for (int i=8; i<13; i++) {
                if (board.board_tiles[0][i].hasPiece()) {
                    System.out.println(6);
                    return false;
                }
            }
        }
        return true;
    }

}
