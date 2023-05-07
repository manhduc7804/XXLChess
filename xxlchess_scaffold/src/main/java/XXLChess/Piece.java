package XXLChess;

import processing.core.PImage;
import processing.core.PApplet;
import java.lang.Math;


public abstract class Piece {
    
    protected boolean killed = false;
    protected static boolean white = false;
    protected PImage img;

    // Contructor
    public Piece (boolean white, PImage img) {
        this.white = white;
        this.img = img;
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

    public abstract boolean canMove(Board board, Tile start, Tile end);
    
    // Knight legal move
    protected static boolean KnightCanMove(Board board, Tile start, Tile end) {
         // check if the end tile is the same piece color or not
        if (end.getPiece().isWhite() == white) {
            return false;
        }

        int moveX = Math.abs(end.getX()-start.getX());
        int moveY = Math.abs(end.getY()-start.getY());

        if (Math.abs(moveX) * Math.abs(moveY) == 2) {
            return true;
        }
        return false;
    }

    // Bishop legal move
    protected static boolean BishopCanMove(Board board, Tile start, Tile end) {
        if (end.getPiece().isWhite() == white) {
            return false;
        }

        int moveX = Math.abs(end.getX()-start.getX());
        int moveY = Math.abs(end.getY()-start.getY());

        if (Math.abs(moveX) == Math.abs(moveY)) {
            return true;
        }
        return false;
    }

    // Rook legal move
    public boolean RookCanMove(Board board, Tile start, Tile end) {
        if (end.getPiece().isWhite() == white) {
            return false;
        }

        int moveX = Math.abs(end.getX()-start.getX());
        int moveY = Math.abs(end.getY()-start.getY());

        if (Math.abs(moveX) + Math.abs(moveY) == 1) {
            return true;
        }
        return false;
    }
}