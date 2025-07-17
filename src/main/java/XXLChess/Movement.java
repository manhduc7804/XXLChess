package XXLChess;

import processing.core.PApplet;
import processing.core.PImage;

import java.lang.Math;

public class Movement {
    private PApplet app;
    private Board board;
    private float speed;
    private int maxTime;
    private Logic logic;
    private Tile start;
    private Tile end;
    private Piece piece;
    private PImage pieceimg;
    private float TimeX;
    private float TimeY;
    private float SpeedX;
    private float SpeedY;
    private float i;
    private float j;

    public Movement(PApplet app, Board board, Logic logic, float speed, int maxTime) {
        this.app = app;
        this.board = board;
        this.speed = speed;
        this.maxTime = maxTime;
        this.logic = logic;
    }

    public void setMoveStart (Tile start) {
        this.start = start;
    }
    public void setMoveEnd (Tile end) {
        this.end = end;
    }
    
    public void MoveAnim () {
        if (board.board_tiles[start.getX()][start.getY()].hasPiece()) {

            // First time set up position
            piece = start.getPiece();
            board.board_tiles[start.getX()][start.getY()].setPiece(null);
            pieceimg = piece.getimg();
            pieceimg.resize(App.CELLSIZE,App.CELLSIZE);
            i = start.getX()*App.CELLSIZE;
            j = start.getY()*App.CELLSIZE;

            // Set up speed
            TimeX = (Math.abs(end.getX()-start.getX())*48)/(speed*App.FPS);
            TimeY = (Math.abs(end.getY()-start.getY())*48)/(speed*App.FPS);
            if (TimeX>maxTime) {
                TimeX = maxTime;
            } 
            if (TimeX != 0) {
                SpeedX = ((end.getX()-start.getX())*48)/(TimeX*App.FPS);
            } else {
                SpeedX = 0;
            }
            
            if (TimeY>maxTime) {
                TimeY = maxTime;
            } 
            System.out.println(TimeX+", "+TimeY);
            if (TimeY != 0) {
                SpeedY = ((end.getY()-start.getY())*48)/(TimeY*App.FPS);
            } else {
                SpeedY = 0;
            }
            if (SpeedX!=0 && SpeedY!=0) {
                float SpeedDiff = ((float) Math.abs(end.getX()-start.getX()))/Math.abs(end.getY()-start.getY());       
                if (SpeedDiff>1) {
                    SpeedY = SpeedY / SpeedDiff;
                } else {
                    SpeedX = SpeedX * SpeedDiff;
                }
                // System.out.println(Math.abs(end.getX()-start.getX())+", "+Math.abs(end.getY()-start.getY()));
                // System.out.println(SpeedDiff);
            }

            // System.out.println(SpeedX + ", " + SpeedY);
        }

        // Moving
        if (Math.round(i) == end.getX()*App.CELLSIZE && Math.round(j) == end.getY()*App.CELLSIZE) {
            board.board_tiles[end.getX()][end.getY()].setPiece(piece);
            logic.setState(State.ACTIVE);
            logic.SeeChecked();
        } else {
            i+=SpeedX;
            j+=SpeedY;
            app.image(pieceimg,j,i);
        }
    }
}
