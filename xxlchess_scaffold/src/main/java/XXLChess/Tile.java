package XXLChess;
import processing.core.PApplet;

import XXLChess.Piece;

// enum TileColor {
//     DARK {
//        r = 181;
//        g = 136;
//        b = 99;
//     }, LIGHT {
//         r = 240;
//         g = 217;
//         b = 181;
//     }, DARKBLUE {
//         r = 170;
//         g = 210;
//         b = 221;
//     }, LIGHTBLUE {
//         r = 196;
//         g = 224;
//         b = 232;
//     }, YELLOW {
//         r = 170;
//         g = 162;
//         b = 58;
//     }, ORANGE {
//         r = 255;
//         g = 164;
//         b = 102;
//     }, RED {
//         r = 215;
//         g = 0;
//         b = 0;
//     }, GREEN {
//         r = 105;
//         g = 138;
//         b = 76;
//     }; 
//     // gray(180,180,180,255); white(253,253,253,255)

//     private final static int r;
//     private final static int g;
//     private final static int b;

//     public int getr() {
//         return r;
//     }

//     public int getg() {
//         return g;
//     }

//     public int getb() {
//         return b;
//     }
// }

public class Tile {

    private Piece piece;
    private int x;
    private int y;
    // public enum color;

    public Tile (PApplet app, int x, int y, Piece piece) {
        this.setX(x);
        this.setY(y);
        this.setPiece(piece);
        System.out.println("null");
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getX () {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public int getY () {
        return this.x;
    }

    public void setY(int y) {
        this.x = x;
    }

    public boolean isValidTile() {
        return x<14 && x>=0 && y<14 && y>=0;
    }

    // public enum getColor() {
    //     if ((x+y)%2==0) {
    //         color = DARK;
    //     } else {
    //         color = LIGHT;
    //     }
    //     return color;
    // }
}

