package XXLChess;

//import org.reflections.Reflections;
//import org.reflections.scanners.Scanners;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;
import processing.core.PFont;
import processing.event.MouseEvent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.awt.Font;
import java.io.*;
import java.util.*;
import java.lang.Math;


public class App extends PApplet {

    public static final int SPRITESIZE = 480;
    public static final int CELLSIZE = 48;
    public static final int SIDEBAR = 120;
    public static final int BOARD_WIDTH = 14;

    public static int WIDTH = CELLSIZE*BOARD_WIDTH+SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH*CELLSIZE;

    public static final int FPS = 60;
	
    public String configPath;
    private Board board;
    private Logic logic;
    private boolean SinglePlayer;
    private boolean PlayerColor;
    private int AIspeed;
    private int countdown;
    private Movement mv;


    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
    */
    public void setup() {
        frameRate(FPS);

        // load config
        JSONObject conf = loadJSONObject(new File(this.configPath));
        String layout = conf.getString("layout");
        JSONObject TimeControls = conf.getJSONObject("time_controls");
        JSONObject player = TimeControls.getJSONObject("player");
        int PlayerTime = player.getInt("seconds");
        int PlayerTimeIncrement = player.getInt("increment");
        JSONObject CPU = TimeControls.getJSONObject("cpu");
        int CPUTime = CPU.getInt("seconds");
        int CPUTimeIncrement = CPU.getInt("increment"); 
        if (conf.getString("player_colour").equals("white")) {
            PlayerColor = true;
        } else if (conf.getString("player_colour").equals("black")) {
            PlayerColor = false;
        } else {
            throw new AssertionError("Wrong Colour");
        }
        float MovementSpeed = conf.getFloat("piece_movement_speed");
        int MaxMovementTime = conf.getInt("max_movement_time");
        int PlayerNumber = conf.getInt("number_of_player");
        if (PlayerNumber == 1) {
            SinglePlayer = true;
        } else if (PlayerNumber == 2) {
            SinglePlayer = false;
        } else {
            throw new AssertionError("Wrong Player Number");
        }
        AIspeed = conf.getInt("ai_move_speed");
        countdown = AIspeed;


        // Load images during setup
        this.board = new Board(this);
        this.logic = new Logic(this, board, PlayerColor, SinglePlayer);
        mv = new Movement(this, board, logic, MovementSpeed, MaxMovementTime);
        logic.setMovement(mv);
        try {
            File file = new File(layout);
            Scanner scan = new Scanner(file);
            int i = 0;
            while (scan.hasNextLine()) {
                if (i==14) {
                    System.out.println("Incorrect board");
                    System.exit(0);
                }
                String line = scan.nextLine();
                for (int j = 0; j < line.length(); j++) {
                    if (line.charAt(j) == 'P') {
                        PImage img = loadImage("src/main/resources/XXLChess/b-pawn.png");
                        Piece setuppiece = new Pawn(false, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'p') {
                        PImage img = loadImage("src/main/resources/XXLChess/w-pawn.png");
                        Piece setuppiece = new Pawn(true, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'R') {
                        PImage img = loadImage("src/main/resources/XXLChess/b-rook.png");
                        Piece setuppiece = new Rook(false, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'r') {
                        PImage img = loadImage("src/main/resources/XXLChess/w-rook.png");
                        Piece setuppiece = new Rook(true, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'N') {
                        PImage img = loadImage("src/main/resources/XXLChess/b-knight.png");
                        Piece setuppiece = new Knight(false, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'n') {
                        PImage img = loadImage("src/main/resources/XXLChess/w-knight.png");
                        Piece setuppiece = new Knight(true, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'B') {
                        PImage img = loadImage("src/main/resources/XXLChess/b-bishop.png");
                        Piece setuppiece = new Bishop(false, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'b') {
                        PImage img = loadImage("src/main/resources/XXLChess/w-bishop.png");
                        Piece setuppiece = new Bishop(true, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'H') {
                        PImage img = loadImage("src/main/resources/XXLChess/b-archbishop.png");
                        Piece setuppiece = new Archbishop(false, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'h') {
                        PImage img = loadImage("src/main/resources/XXLChess/w-archbishop.png");
                        Piece setuppiece = new Archbishop(true, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'C') {
                        PImage img = loadImage("src/main/resources/XXLChess/b-camel.png");
                        Piece setuppiece = new Camel(false, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'c') {
                        PImage img = loadImage("src/main/resources/XXLChess/w-camel.png");
                        Piece setuppiece = new Camel(true, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'G') {
                        PImage img = loadImage("src/main/resources/XXLChess/b-knight-king.png");
                        Piece setuppiece = new Guard(false, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'g') {
                        PImage img = loadImage("src/main/resources/XXLChess/w-knight-king.png");
                        Piece setuppiece = new Guard(true, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'A') {
                        PImage img = loadImage("src/main/resources/XXLChess/b-amazon.png");
                        Piece setuppiece = new Amazon(false, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'a') {
                        PImage img = loadImage("src/main/resources/XXLChess/w-amazon.png");
                        Piece setuppiece = new Amazon(true, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'K') {
                        PImage img = loadImage("src/main/resources/XXLChess/b-king.png");
                        Piece setuppiece = new King(false, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'k') {
                        PImage img = loadImage("src/main/resources/XXLChess/w-king.png");
                        Piece setuppiece = new King(true, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'E') {
                        PImage img = loadImage("src/main/resources/XXLChess/b-chancellor.png");
                        Piece setuppiece = new Chancellor(false, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    } else if (line.charAt(j) == 'e') {
                        PImage img = loadImage("src/main/resources/XXLChess/w-chancellor.png");
                        Piece setuppiece = new Chancellor(true, img);
                        board.board_tiles[i][j].setPiece(setuppiece);
                    }
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
        logic.AIFirstMove();

        // PImage spr = loadImage("src/main/resources/XXLChess/"+...);

        
    }

    /**
     * Receive key pressed signal from the keyboard.
    */
    public void keyPressed(){
        if (key == 'r' || key == 'R') {
            setup();
        }

    }
    
    /**
     * Receive key released signal from the keyboard.
    */
    public void keyReleased(){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int X = (int) Math.floor(mouseX/CELLSIZE);
        int Y = (int) Math.floor(mouseY/CELLSIZE);
        
        if (logic.getState()==State.ACTIVE) {
            if (X >=0 && X<14 && Y>=0 && Y<14) {
                logic.Clicked(Y,X);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    /**
     * Draw all elements in the game by current frame. 
    */
    public void draw() {
        board.draw(this);
        if (SinglePlayer && logic.getTurn() != PlayerColor && logic.getState() == State.ACTIVE) {
            if (countdown>0) {
                countdown = countdown - 1;
            } else if (countdown == 0) {
                logic.AITurn();
                countdown = AIspeed;
            } 
        }   
        if (logic.getState() == State.MOVING) {
            mv.MoveAnim();
        } 
        if (logic.getState() == State.FLASHRED) {
            logic.FlashRed();
        }
    }
	
	// Add any additional methods or attributes you want. Please put classes in different files.


    public static void main(String[] args) {
        PApplet.main("XXLChess.App");
    }

}