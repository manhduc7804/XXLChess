package XXLChess;

import java.util.ArrayList;
import java.util.Arrays;
import processing.core.PApplet;
import processing.core.PImage;


public class Board {

    public Tile[][] board_tiles;

    public Board(PApplet app) {
        board_tiles = new Tile[14][14];
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                board_tiles[i][j] = new Tile(app, i, j, null);
            }
        }
    }

    // reset the Board
    public void resetBoard() {
        for (int i=0; i<14; i++) {
            for (int j=0; j<14; j++) {
                if ((i+j)%2==0) {
                    board_tiles[i][j].setColor(TileColor.DARK);
                } else {
                    board_tiles[i][j].setColor(TileColor.LIGHT);
                }
            }
        }
    }
    

    // Draw
    public void draw(PApplet app) {
        for (int i=0; i<14; i++) {
            for (int j=0; j<14; j++) {
                Tile tile = board_tiles[i][j];
                app.fill(tile.getColor().getr(),tile.getColor().getg(),tile.getColor().getb());
                app.rect(j*App.CELLSIZE,i*App.CELLSIZE,App.CELLSIZE, App.CELLSIZE); 
                if (tile.hasPiece()) {
                    Piece piece = tile.getPiece();
                    PImage pieceimg = piece.getimg();
                    pieceimg.resize(App.CELLSIZE,App.CELLSIZE);
                    app.image(pieceimg,j*App.CELLSIZE,i*App.CELLSIZE);
                }
            }
        }
    }
    
}    

