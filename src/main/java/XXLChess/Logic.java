package XXLChess;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Logic {
    private PApplet app;
    private Board board;
    private RandomAI AI;
    private Movement mv;
    private int preX;
    private int preY;
    private int savedX1 = -1;
    private int savedX2 = -1;
    private int savedY1 = -1;
    private int savedY2 = -1;
    private boolean turn = true;
    private boolean playerTurn;
    private int bKx = 0;
    private int bKy = 7;
    private int wKx = 13;
    private int wKy = 7;
    private boolean SinglePlayer;
    private State state;
    private int flashRedTimer = 120;

    public Logic(PApplet app, Board board, boolean playerTurn, boolean SinglePlayer) {
        this.app = app;
        this.board = board;
        this.SinglePlayer = SinglePlayer;
        this.playerTurn = playerTurn;
        if (SinglePlayer) {
            this.AI = new RandomAI(app, board, this, !playerTurn);
        }
        this.state = State.ACTIVE;
    }

    public void Clicked(int i, int j) {

        Tile starttile = board.board_tiles[i][j];

        // Moving
        if (starttile.getColor() == TileColor.DARKBLUE || starttile.getColor() == TileColor.LIGHTBLUE || starttile.getColor() == TileColor.ORANGE) {
            if ((board.board_tiles[preX][preY].getPiece() instanceof King)) {
                if (i-preX == 0 && j-preY==2) {
                        board.board_tiles[i][j].setPiece(board.board_tiles[preX][preY].getPiece()); 
                        board.board_tiles[i][j].getPiece().setFirstMove(false);
                        board.board_tiles[preX][preY].setPiece(null);
                        if (board.board_tiles[i][j].getPiece().isWhite()) {
                            board.board_tiles[13][8].setPiece(board.board_tiles[13][13].getPiece());
                            board.board_tiles[13][8].getPiece().setFirstMove(false);
                            board.board_tiles[13][13].setPiece(null);
                            wKx = i;
                            wKy = j;
                        } else {
                            board.board_tiles[0][8].setPiece(board.board_tiles[0][13].getPiece());
                            board.board_tiles[0][8].getPiece().setFirstMove(false);
                            board.board_tiles[0][13].setPiece(null);
                            bKx = i;
                            bKy = j;
                        }
                } else if (i-preX == 0 && j-preY==-2) {
                        board.board_tiles[i][j].setPiece(board.board_tiles[preX][preY].getPiece()); 
                        board.board_tiles[i][j].getPiece().setFirstMove(false);
                        board.board_tiles[preX][preY].setPiece(null);
                        if (board.board_tiles[i][j].getPiece().isWhite()) {
                            board.board_tiles[13][6].setPiece(board.board_tiles[13][0].getPiece());
                            board.board_tiles[13][6].getPiece().setFirstMove(false);
                            board.board_tiles[13][0].setPiece(null);
                            wKx = i;
                            wKy = j;
                        } else {
                            board.board_tiles[0][6].setPiece(board.board_tiles[0][0].getPiece());
                            board.board_tiles[0][6].getPiece().setFirstMove(false);
                            board.board_tiles[0][0].setPiece(null);
                            bKx = i;
                            bKy = j;
                        }
                } else {
                    mv.setMoveStart(board.board_tiles[preX][preY]);
                    mv.setMoveEnd(board.board_tiles[i][j]);
                    // board.board_tiles[i][j].setPiece(board.board_tiles[preX][preY].getPiece()); 
                    // board.board_tiles[preX][preY].getPiece().setFirstMove(false);
                    state = State.MOVING;
                    // board.board_tiles[preX][preY].setPiece(null);
                    if (board.board_tiles[preX][preY].getPiece().isWhite()) {
                        wKx = i;
                        wKy = j;
                    } else {
                        bKx = i;
                        bKy = j;
                    }
                }
            } else {
                mv.setMoveStart(board.board_tiles[preX][preY]);
                mv.setMoveEnd(board.board_tiles[i][j]);
                // board.board_tiles[i][j].setPiece(board.board_tiles[preX][preY].getPiece()); 
                board.board_tiles[preX][preY].getPiece().setFirstMove(false);
                // board.board_tiles[preX][preY].setPiece(null);
                state = State.MOVING;
            }
            System.out.println("Player moved from: "+preX+", "+preY+" to "+i+", "+j);
            setSave(preX, preY, i, j);
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
            SeeChecked();
        } else {
            // reset board if click on empty tile
            board.resetBoard();
            if (ischecked(board, turn)) {
                FlashRed();
            }
        }
        preX = i;
        preY = j;
        PaintYellow();

        // Check and checkmate
        // System.out.println(ischecked(board, turn)+ " " + turn + " " + Checkmate(turn));
        // System.out.println("white King "+wKx+","+wKy);
        // System.out.println("Black King "+bKx+","+bKy+"\n");
    }

    public void AITurn() {
        if (turn == AI.getColor()) {
            AI.RandomMove();
            if (turn) {
                turn = false;
            } else {
                turn = true;
            }
            board.resetBoard();
            PaintYellow();
        }      
    }

    // Pawn promotion to queen
    public void Promotion() {
        for (int i =0; i<14; i++) {
            if (board.board_tiles[7][i].hasPiece()) {
                if (!board.board_tiles[7][i].getPiece().isWhite() && (board.board_tiles[7][i].getPiece() instanceof Pawn)) {
                    PImage img = app.loadImage("src/main/resources/XXLChess/b-queen.png");
                    board.board_tiles[7][i].setPiece(new Queen(false, img));
                }
            }
            if (board.board_tiles[6][i].hasPiece()) {
                if (board.board_tiles[6][i].getPiece().isWhite() && (board.board_tiles[6][i].getPiece() instanceof Pawn)) {
                    PImage img = app.loadImage("src/main/resources/XXLChess/w-queen.png");
                    board.board_tiles[6][i].setPiece(new Queen(true, img));
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
                    if (i == wKx && j == wKy) {
                        continue;
                    }
                    if (board.board_tiles[i][j].hasPiece()) {
                        Piece piece = board.board_tiles[i][j].getPiece();
                        if (piece.isWhite() != white && piece.canMove(board, this, board.board_tiles[i][j], board.board_tiles[wKx][wKy])) {
                            attacking.add(board.board_tiles[i][j]);
                        } 
                    }
                } else {
                    if (i == bKx && j == bKy) {
                        continue;
                    }
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
        if (!white || board.board_tiles[wKx][wKy].hasPiece()) {
            if (!(board.board_tiles[wKx][wKy].getPiece() instanceof King)) {
                return false;
            }
        } else if (white || board.board_tiles[bKx][bKy].hasPiece()) {
            if (!(board.board_tiles[bKx][bKy].getPiece() instanceof King)) {
                return false;
            }
        }
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

    public void setSave(int x1, int y1, int x2, int y2) {
        savedX1 = x1;
        savedY1 = y1;
        savedX2 = x2;
        savedY2 = y2;
    }

    // Set moved tiles yellow
    public void PaintYellow() {
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
    }

    public void AIFirstMove() {
        if (!playerTurn && SinglePlayer) {
            AI.RandomMove();
            if (turn) {
                turn = false;
            } else {
                turn = true;
            }
        }
    }

    public void setState(State state) {
        this.state = state;
    }
    
    public State getState() {
        return this.state;
    }

    // See if the opponent king is checked after the move
    public void SeeChecked() {
        if (ischecked(board, turn)) {
            if (turn) {
                if (Checkmate(turn)) {
                    board.resetBoard();
                    ArrayList<Tile> checklist = new ArrayList<>();
                    checklist.addAll(check(board,turn));
                    for (Tile attackingPiece : checklist) {
                        attackingPiece.setColor(TileColor.ORANGE);
                        System.out.println("White checkmate");
                        state = State.CHECKMATE;
                    }
                }
                board.board_tiles[wKx][wKy].setColor(TileColor.RED);
            } else {
                if (Checkmate(turn)) {
                    board.resetBoard();
                    ArrayList<Tile> checklist = new ArrayList<>();
                    checklist.addAll(check(board,turn));
                    for (Tile attackingPiece : checklist) {
                        attackingPiece.setColor(TileColor.ORANGE);
                        System.out.println("Black checkmate");
                        state = State.CHECKMATE;
                    }
                }
                board.board_tiles[bKx][bKy].setColor(TileColor.RED);
            }
        } 
    }

    public boolean getTurn() {
        return turn;
    }

    public void setMovement(Movement mv) {
        this.mv = mv;
        if (SinglePlayer) {
            AI.setMovement(mv);
        }
    }

    public void FlashRed() {
        state = State.FLASHRED;
        board.resetBoard();
        if (flashRedTimer>0) {
            if (flashRedTimer == 120 || (flashRedTimer <= 90 && flashRedTimer > 60) || flashRedTimer <= 30) {
                SeeChecked();
            } 
            flashRedTimer = flashRedTimer - 1;
        } else {
            SeeChecked();
            state = State.ACTIVE;
            flashRedTimer = 120;
        }
        PaintYellow();
    }
}
