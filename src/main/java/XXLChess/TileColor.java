package XXLChess;

public enum TileColor {
    DARK(181,136,99),
    LIGHT(240,217,181),
    DARKBLUE(170,210,221),
    LIGHTBLUE(196,224,232),
    YELLOW(170,162,58),
    ORANGE(255,164,102),
    RED(215,0,0),
    GREEN(105,138,76),
    GRAY(180,180,180),
    WHITE(253,253,253);

    private final int r;
    private final int g;
    private final int b;

    private TileColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getr() {
        return r;
    }

    public int getg() {
        return g;
    }

    public int getb() {
        return b;
    }
}
