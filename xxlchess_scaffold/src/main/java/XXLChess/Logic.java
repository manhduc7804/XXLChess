package XXLChess;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Logic {
    private PApplet app;
    private Board board;
    private int preX;
    private int preY;
    private int savedX1 = -1;
    private int savedX2 = -1;
    private int savedY1 = -1;
    private int savedY2 = -1;
    private boolean turn = true;
    private int bKx = 0;
    private int bKy = 7;
    private int wKx = 13;
    private int wKy = 7;

    public Logic(PApplet app, Board board) {
        this.app = app;
        this.board = board;
    }

    public void Clicked(int i, int j) {

        Tile starttile = board.board_tiles[i][j];

        // Moving
        if (starttile.getColor() == TileColor.DARKBLUE || starttile.getColor() == TileColor.LIGHTBLUE || starttile.getColor() == TileColor.ORANGE) {
            if ((board.board_tiles[preX][preY].getPiece() instanceof King)) {
                if (i-preX == 0) {
                    if (j-preY==2) {
                        board.board_tiles[i][j].setPiece(board.board_tiles[preX][preY].getPiece()); 
                        board.board_tiles[i][j].getPiece().setFirstMove(false);
                        board.board_tiles[preX][preY].setPiece(null);
                        if (board.board_tiles[i][j].getPiece().isWhite()) {
                            board.board_tiles[13][8].setPiece(board.board_tiles[13][13].getPiece());
                            board.board_tiles[13][8].getPiece().setFirstMove(false);
                            board.board_tiles[13][13].setPiece(null);
                        } else {
                            board.board_tiles[0][8].setPiece(board.board_tiles[0][13].getPiece());
                            board.board_tiles[0][8].getPiece().setFirstMove(false);
                            board.board_tiles[0][13].setPiece(null);
                        }
                    } else if (j-preY==-2) {
                        board.board_tiles[i][j].setPiece(board.board_tiles[preX][preY].getPiece()); 
                        board.board_tiles[i][j].getPiece().setFirstMove(false);
                        board.board_tiles[preX][preY].setPiece(null);
                        if (board.board_tiles[i][j].getPiece().isWhite()) {
                            board.board_tiles[13][6].setPiece(board.board_tiles[13][0].getPiece());
                            board.board_tiles[13][6].getPiece().setFirstMove(false);
                            board.board_tiles[13][0].setPiece(null);
                        } else {
                            board.board_tiles[0][6].setPiece(board.board_tiles[0][0].getPiece());
                            board.board_tiles[0][6].getPiece().setFirstMove(false);
                            board.board_tiles[0][0].setPiece(null);
                        }
                    }
                } else {
                    board.board_tiles[i][j].setPiece(board.board_tiles[preX][preY].getPiece()); 
                    board.board_tiles[i][j].getPiece().setFirstMove(false);
                    board.board_tiles[preX][preY].setPiece(null);
                }
                if (board.board_tiles[i][j].getPiece().isWhite()) {
                    wKx = i;
                    wKy = j;
                } else {
                    bKx = i;
                    bKy = j;
                }
            } else {
                board.board_tiles[i][j].setPiece(board.board_tiles[preX][preY].getPiece()); 
                board.board_tiles[i][j].getPiece().setFirstMove(false);
                board.board_tiles[preX][preY].setPiece(null);
            }
            savedX1 = preX;
            savedY1 = preY;
            savedX2 = i;
            savedY2 = j;
            Promotion();
            board.resetBoard();
            if (turn) {
                turn = false;
            } else {
                turn = true;
            }

        // Checking legal moves if it has a piece
        } else if (starttile.hasPiece()) {
            board.resetBoard();
            Piece piece = starttile.getPiece();
            
            // Check correct turn
            if (piece.isWhite() == turn) {
                for (int x = 0; x < 14; x++) {
                    for (int y = 0; y < 14; y++) {

                        // check if it is the same tile
                        if (x==i && y == j) {
                            continue;
                        }
                        
                        // check legal move
                        if (piece.canMove(board,this,starttile,board.board_tiles[x][y])) {
                            if (board.board_tiles[x][y].hasPiece()) {
                                board.board_tiles[x][y].setColor(TileColor.ORANGE);
                            } else {
                                if (board.board_tiles[x][y].getColor() == TileColor.DARK) {
                                    board.board_tiles[x][y].setColor(TileColor.DARKBLUE);
                                } else if (board.board_tiles[x][y].getColor() == TileColor.LIGHT) {
                                    board.board_tiles[x][y].setColor(TileColor.LIGHTBLUE);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            // reset board if click on empty tile
            board.resetBoard();
        }
        preX = i;
        preY = j;
        if (savedX1>=0 && savedX1 < 14 && savedY1>=0 && savedY1 < 14 && savedX2>=0 && savedX2 < 14 && savedY2>=0 && savedY2 < 14) {
            if (!(board.board_tiles[savedX1][savedY1].getColor() == TileColor.DARKBLUE) 
                && !(board.board_tiles[savedX1][savedY1].getColor() == TileColor.LIGHTBLUE) 
                    && !(board.board_tiles[savedX1][savedY1].getColor() == TileColor.ORANGE)) {
                        board.board_tiles[savedX1][savedY1].setColor(TileColor.YELLOW);
            }
            if (!(board.board_tiles[savedX2][savedY2].getColor() == TileColor.DARKBLUE) 
                && !(board.board_tiles[savedX2][savedY2].getColor() == TileColor.LIGHTBLUE) 
                    && !(board.board_tiles[savedX2][savedY2].getColor() == TileColor.ORANGE)) {
                        board.board_tiles[savedX2][savedY2].setColor(TileColor.YELLOW);
            }
        }

        // Check and checkmate
        if (ischecked(board, turn)) {
            if (turn) {
        //         if (Checkmate(turn)) {
        //             board.resetBoard();
        //             ArrayList<Tile> checklist = new ArrayList<>();
        //             checklist.addAll(check(board,turn));
        //             for (Tile attackingPiece : checklist) {
        //                 attackingPiece.setColor(TileColor.ORANGE);
        //             }
        //         }
                board.board_tiles[wKx][wKy].setColor(TileColor.RED);
            } else {
        //         if (Checkmate(turn)) {
        //             board.resetBoard();
        //             ArrayList<Tile> checklist = new ArrayList<>();
        //             checklist.addAll(check(board,turn));
        //             for (Tile attackingPiece : checklist) {
        //                 attackingPiece.setColor(TileColor.ORANGE);
        //             }
        //         }
                board.board_tiles[bKx][bKy].setColor(TileColor.RED);
            }
        }
        System.out.println(ischecked(board, turn)+ " " + turn + " " + Checkmate(turn));
        System.out.println("white King "+wKx+","+wKy);
        System.out.println("Black King "+bKx+","+bKy);
    }

    // Pawn promotion to queen
    public void Promotion() {
        for (int i =0; i<14; i++) {
            if (board.board_tiles[6][i].hasPiece()) {
                if (!board.board_tiles[6][i].getPiece().isWhite() && (board.board_tiles[6][i].getPiece() instanceof Pawn)) {
                    PImage img = app.loadImage("src/main/resources/XXLChess/b-queen.png");
                    board.board_tiles[6][i].setPiece(new Queen(false, img));
                }
            }
            if (board.board_tiles[7][i].hasPiece()) {
                if (board.board_tiles[7][i].getPiece().isWhite() && (board.board_tiles[7][i].getPiece() instanceof Pawn)) {
                    PImage img = app.loadImage("src/main/resources/XXLChess/w-queen.png");
                    board.board_tiles[7][i].setPiece(new Queen(true, img));
                }
            }
        }
    }

    // Return the list of pieces that are checking the king
    public ArrayList<Tile> check(Board board, boolean white) {
        ArrayList<Tile> attacking = new ArrayList<>();
        for (int i=0; i<14; i++) {
            for (int j=0; j<14; j++) {
                if (white) {
                    if (board.board_tiles[i][j].hasPiece()) {
                        Piece piece = board.board_tiles[i][j].getPiece();
                        if (piece.isWhite() != white && piece.canMove(board, this, board.board_tiles[i][j], board.board_tiles[wKx][wKy])) {
                            attacking.add(board.board_tiles[i][j]);
                        } 
                    }
                } else {
                    if (board.board_tiles[i][j].hasPiece()) {
                        Piece piece = board.board_tiles[i][j].getPiece();
                        if (piece.isWhite() != white && piece.canMove(board, this, board.board_tiles[i][j], board.board_tiles[bKx][bKy])) {
                            attacking.add(board.board_tiles[i][j]);
                        } 
                    }
                }
            }
        }
        return attacking;
    }

    // checkmate 
    public boolean Checkmate(boolean white) {
        for (int i=0; i<14; i++) {
            for (int j=0; j<14; j++) {
                if (board.board_tiles[i][j].hasPiece()) {
                    Piece piece = board.board_tiles[i][j].getPiece();
                    if (piece.isWhite() == white) {
                        for (int x=0; x<14; x++) {
                            for (int y=0; y<14; y++) {
                                if (piece.canMove(board, this, board.board_tiles[i][j], board.board_tiles[x][y])) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    // Return if the king is checked
    public boolean ischecked(Board board, boolean white) {
        ArrayList<Tile> checklist = new ArrayList<>();
        checklist.addAll(check(board,white));
        if (checklist.size() !=0) {
            return true;
        } else {
            return false;
        }
    }


    public void setKing(boolean white, int x, int y) {
        if (white) {
            wKx = x;
            wKy = y;
        } else {
            bKx = x;
            bKy = y;
        }
    }
}
