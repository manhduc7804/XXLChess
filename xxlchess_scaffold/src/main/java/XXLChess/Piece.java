package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;
import java.lang.Math;


public abstract class Piece {
    
    protected final boolean killed = false;
    protected final boolean white;
    protected PImage img;
    protected boolean firstmove;

    // Contructor
    public Piece (boolean white, PImage img) {
        this.white = white;
        this.img = img;
        firstmove = true;
    }

    // Set and return if the piece is white or not
    public boolean isWhite() {
        return this.white;
    }

    // Set and return if the piece is killed or not
    public boolean isKilled() {
        return this.killed;
    }
    
    // return image
    public PImage getimg() {
        return this.img;
    }

    public boolean IsFisrtMove() {
        return firstmove;
    }

    public void setFirstMove(boolean firstmove) {
        this.firstmove = firstmove;
    }

    public abstract boolean canMove(Board board, Tile start, Tile end);
    
    // Knight legal move
    protected boolean KnightCanMove(Board board, Tile start, Tile end) {
         // check if the end tile is the same piece color or not
        if (end.hasPiece()) {
            if (end.getPiece().isWhite() == this.white) {
                return false;
            }
        }

        int moveX = Math.abs(end.getX()-start.getX());
        int moveY = Math.abs(end.getY()-start.getY());

        if (moveX * moveY == 2) {
            return true;
        }
        return false;
    }

    // Bishop legal move
    protected boolean BishopCanMove(Board board, Tile start, Tile end) {
        if (end.hasPiece()) {
            if (end.getPiece().isWhite() == this.white) {
                return false;
            }
        }

        int moveX = end.getX()-start.getX();
        int moveY = end.getY()-start.getY();

        if (moveX == moveY) {
            // check if there is a blocking piece
            if (moveX>0) {
                for (int block=1; block < moveX; block++) {
                    if (board.board_tiles[start.getX()+block][start.getY()+block].hasPiece()) {
                        return false;
                    }
                }
            } else {
                for (int block=1; block < Math.abs(moveX); block++) {
                    if (board.board_tiles[start.getX()-block][start.getY()-block].hasPiece()) {
                        return false;
                    }
                }
            }
            return true;
        } else if (moveX == -moveY) {
            if (moveX>0) {
                for (int block = 1; block<moveX; block++) {
                    if (board.board_tiles[start.getX()+block][start.getY()-block].hasPiece()) {
                        return false;
                    }
                }
            } else {
                for (int block = 1; block<Math.abs(moveY); block++) {
                    if (board.board_tiles[start.getX()-block][start.getY()+block].hasPiece()) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    // Rook legal move
    public boolean RookCanMove(Board board, Tile start, Tile end) {
        if (end.hasPiece()) {
            if (end.getPiece().isWhite() == this.white) {
                return false;
            }
        }

        int moveX = end.getX()-start.getX();
        int moveY = end.getY()-start.getY();

        if (moveX == 0) {
            if (moveY>0) {
                for (int block=start.getY()+1; block < end.getY(); block++) {
                    if (board.board_tiles[start.getX()][block].hasPiece()) {
                        return false;
                    }
                }
            } else {
                for (int block=start.getY()-1; block > end.getY(); block--) {
                    if (board.board_tiles[start.getX()][block].hasPiece()) {
                        return false;
                    }
                }
            }
            return true;
        } else if (moveY == 0) {
            if (moveX>0) {
                for (int block=start.getX()+1; block < end.getX(); block++) {
                    if (board.board_tiles[block][start.getY()].hasPiece()) {
                        return false;
                    }
                }
            } else {
                for (int block=start.getX()-1; block > end.getX(); block--) {
                    if (board.board_tiles[block][start.getY()].hasPiece()) {
                        return false;
                    }
                }
            }
            return true;
        }

        return false;
    }
}