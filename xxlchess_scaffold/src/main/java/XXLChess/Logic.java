package XXLChess;

import processing.core.PApplet;

public class Logic {
    private PApplet app;
    private Board board;
    private int savedX;
    private int savedY;

    public Logic(PApplet app, Board board) {
        this.app = app;
        this.board = board;
    }

    public void Clicked(int i, int j) {

        // check if the tile has piece and show legal moves
        Tile starttile = board.board_tiles[i][j];
        if (starttile.getColor() == TileColor.DARKBLUE || starttile.getColor() == TileColor.LIGHTBLUE) {
            board.board_tiles[i][j].setPiece(board.board_tiles[savedX][savedY].getPiece()); 
            board.board_tiles[savedX][savedY].setPiece(null);
            board.resetBoard();
        } else if (starttile.hasPiece()) {
            board.resetBoard();
            Piece piece = starttile.getPiece();
            for (int x = 0; x < 14; x++) {
                for (int y = 0; y < 14; y++) {

                    // check if it is the same tile
                    if (x==i && y == j) {
                        continue;
                    }
                    
                    // check legal move
                    if (piece.canMove(board,starttile,board.board_tiles[x][y])) {
                        if (board.board_tiles[x][y].getColor() == TileColor.DARK) {
                            board.board_tiles[x][y].setColor(TileColor.DARKBLUE);
                        } else if (board.board_tiles[x][y].getColor() == TileColor.LIGHT) {
                            board.board_tiles[x][y].setColor(TileColor.LIGHTBLUE);
                        }
                    }
                }
            }
        } else {
            // reset board
            board.resetBoard();
        }
        savedX = i;
        savedY = j;
    }
    public void highlight() {

    }

}
