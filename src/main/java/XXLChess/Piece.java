package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;
import java.lang.Math;


public abstract class Piece {
    
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
    
    // return image
    public PImage getimg() {
        return this.img;
    }

    // Check if this is the first move
    public boolean IsFisrtMove() {
        return firstmove;
    }

    // set the first move
    public void setFirstMove(boolean firstmove) {
        this.firstmove = firstmove;
    }

    // An abstract method that will return if the piece can move
    public abstract boolean canMove(Board board, Logic logic, Tile start, Tile end);
    
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

    // Check if the move will get the king out of checked
    public boolean Blocking(Board board, Logic logic, Tile start, Tile end) {
        Piece savedpiece;
        if (board.board_tiles[end.getX()][end.getY()].hasPiece()) {
            savedpiece = board.board_tiles[end.getX()][end.getY()].getPiece();
            if (savedpiece instanceof King && savedpiece.isWhite() != white) {
                return true;
            }
        } else {
            savedpiece = null;
        }
        Piece movingpiece = board.board_tiles[start.getX()][start.getY()].getPiece();
        if (movingpiece instanceof King) {
            logic.setKing(this.white, end.getX(), end.getY());
        }
        board.board_tiles[end.getX()][end.getY()].setPiece(board.board_tiles[start.getX()][start.getY()].getPiece());
        board.board_tiles[start.getX()][start.getY()].setPiece(null);
        if (logic.ischecked(board, this.white)) {
            board.board_tiles[start.getX()][start.getY()].setPiece(board.board_tiles[end.getX()][end.getY()].getPiece());
            board.board_tiles[end.getX()][end.getY()].setPiece(savedpiece);
            if (movingpiece instanceof King) {
                logic.setKing(this.white, start.getX(), start.getY());
            }
            return false;
        } else {
            board.board_tiles[start.getX()][start.getY()].setPiece(board.board_tiles[end.getX()][end.getY()].getPiece());
            board.board_tiles[end.getX()][end.getY()].setPiece(savedpiece);
            // System.out.println(start.getX()+","+start.getY()+" "+end.getX()+","+end.getY()+" "+this.white);
            if (movingpiece instanceof King) {
                logic.setKing(this.white, start.getX(), start.getY());
            }
            return true;
        }
    }
}