package XXLChess;

import java.util.ArrayList;
import java.util.Arrays;
import processing.core.PApplet;
import processing.core.PImage;


public class Board {

    public Tile[][] board_tiles;

    public Board(PApplet app) {
        board_tiles = new Tile[14][14];
    }

    // Draw
    public void draw(PApplet app) {
        for (int i=0; i<14; i++) {
            for (int j=0; j<14; j++) {
                if ((i+j)%2==0) {
                    // App.fill(board_tiles[i][j].getColor.getr,board_tiles[i][j].getColor.getg,board_tiles[i][j].getColor.getb);
                    app.fill(181,136,99);
                } else {
                    app.fill(240,217,181);
                }
                app.rect(i*App.CELLSIZE,j*App.CELLSIZE,App.CELLSIZE, App.CELLSIZE);
                // Piece piece = board_tiles[i][j].getPiece();
                // PImage pieceimg = piece.getimg();
                // pieceimg.resize(App.CELLSIZE,App.CELLSIZE);
                // app.image(pieceimg,i*App.CELLSIZE,j*App.CELLSIZE);
            }
        }
    }
    
}    

