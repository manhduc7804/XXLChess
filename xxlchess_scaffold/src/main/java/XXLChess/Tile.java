package XXLChess;
import processing.core.PApplet;

import XXLChess.Piece;
import XXLChess.TileColor;

public class Tile {

    private Piece piece;
    private int x;
    private int y;
    private TileColor color;

    public Tile (PApplet app, int x, int y, Piece piece) {
        this.setX(x);
        this.setY(y);
        this.setPiece(piece);
        if ((x+y)%2==0) {
            color = TileColor.DARK;
        } else {
            color = TileColor.LIGHT;
        }
    }

    public boolean hasPiece() {
        return piece != null;
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
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isValidTile() {
        return x<14 && x>=0 && y<14 && y>=0;
    }

    public void setColor(TileColor color) {
        this.color = color;
    }
    public TileColor getColor() {
        return color;
    }
}

