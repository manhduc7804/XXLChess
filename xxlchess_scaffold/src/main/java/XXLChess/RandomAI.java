package XXLChess;

import java.util.Random;
import java.util.ArrayList;

import processing.core.PApplet;

public class RandomAI {
    private PApplet app;
    private Board board;
    private boolean AIcolor;
    private Logic logic;
    private Movement mv;
    
    public RandomAI (PApplet app, Board board, Logic logic, boolean AIcolor) {
        this.app = app;
        this.board = board;
        this.AIcolor = AIcolor;
        this.logic = logic;
    }

    public void RandomMove() {
        Random rn = new Random();
        ArrayList<Tile> movepiece = new ArrayList<>();
        ArrayList<ArrayList<Tile>> Piecemovelist = new ArrayList<>();
        for (int i=0; i<14; i++) {
            for (int j=0; j<14; j++) {
                if (board.board_tiles[i][j].hasPiece()) {
                    if (board.board_tiles[i][j].getPiece().isWhite() == AIcolor) {
                        ArrayList<Tile> movelist = new ArrayList<>();
                        for (int x=0; x<14; x++) {
                            for (int y=0; y<14; y++) {
                                if (board.board_tiles[i][j].getPiece().canMove(board, logic, board.board_tiles[i][j], board.board_tiles[x][y])) {
                                    movelist.add(board.board_tiles[x][y]);
                                    // System.out.println("Possible Moves: "+x+", "+y);
                                }
                            }
                        }
                        if (movelist.size()>0) {
                            movepiece.add(board.board_tiles[i][j]);
                            Piecemovelist.add(movelist);
                        } 
                    }
                }
            }
        }
        if (movepiece.size()>0) {
            int randomPiece = rn.nextInt(movepiece.size());
            int randomMove = rn.nextInt(Piecemovelist.get(randomPiece).size());
            Tile end = Piecemovelist.get(randomPiece).get(randomMove);
            mv.setMoveStart(board.board_tiles[movepiece.get(randomPiece).getX()][movepiece.get(randomPiece).getY()]);
            mv.setMoveEnd(board.board_tiles[end.getX()][end.getY()]);
            // board.board_tiles[end.getX()][end.getY()].setPiece(board.board_tiles[movepiece.get(randomPiece).getX()][movepiece.get(randomPiece).getY()].getPiece());
            board.board_tiles[movepiece.get(randomPiece).getX()][movepiece.get(randomPiece).getY()].getPiece().setFirstMove(false);
            // board.board_tiles[movepiece.get(randomPiece).getX()][movepiece.get(randomPiece).getY()].setPiece(null);
            if (board.board_tiles[movepiece.get(randomPiece).getX()][movepiece.get(randomPiece).getY()].getPiece() instanceof King) {
                logic.setKing(AIcolor, end.getX(), end.getY());
            }
            logic.setSave(movepiece.get(randomPiece).getX(), movepiece.get(randomPiece).getY(), end.getX(), end.getY());
            System.out.println("AI moved from: "+movepiece.get(randomPiece).getX()+", "+movepiece.get(randomPiece).getY()+" to "+end.getX()+", "+end.getY());
            logic.setState(State.MOVING);
        }
    }

    public boolean getColor() {
        return AIcolor;
    }

    public void setMovement(Movement mv) {
        this.mv = mv;
    }
}
