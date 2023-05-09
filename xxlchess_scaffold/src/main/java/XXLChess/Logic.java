package XXLChess;

import processing.core.PApplet;
import processing.core.PImage;

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
                        if (piece.canMove(board,starttile,board.board_tiles[x][y])) {
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
            board.board_tiles[savedX1][savedY1].setColor(TileColor.YELLOW);
            board.board_tiles[savedX2][savedY2].setColor(TileColor.YELLOW);
        }
    }
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

}
