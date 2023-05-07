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

        // Load images during setup
        try {
            File file = new File("level1.txt");
            Scanner scan = new Scanner(file);
            this.board = new Board(this);
            for (int i=0; i<14; i++) {
                String line = scan.nextLine();
                assert line.length() != 14: "Wrong board initial setup";
                    for (int j=0; j<14; j++) {
                    if (line == "\n") {
                        board.board_tiles[i][j] = new Tile(this, i, j, null);
                    // } else if (line.charAt(j) == 'P') {
                        // PImage img = loadImage("src/main/resources/XXLChess/b-pawn.png");
                        // Piece setuppiece = new Pawn(false, img);
                        // board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                        // System.out.println("Correct");
                //     } else if (line.charAt(j) == 'p') {
                //         PImage img = loadImage("src/main/resources/XXLChess/w-pawn.png");
                //         Piece setuppiece = new Pawn(true, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'R') {
                //         PImage img = loadImage("src/main/resources/XXLChess/b-rook.png");
                //         Piece setuppiece = new Rook(false, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'r') {
                //         PImage img = loadImage("src/main/resources/XXLChess/w-rook.png");
                //         Piece setuppiece = new Rook(true, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'N') {
                //         PImage img = loadImage("src/main/resources/XXLChess/b-knight.png");
                //         Piece setuppiece = new Knight(false, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'n') {
                //         PImage img = loadImage("src/main/resources/XXLChess/w-knight.png");
                //         Piece setuppiece = new Knight(true, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'B') {
                //         PImage img = loadImage("src/main/resources/XXLChess/b-bishop.png");
                //         Piece setuppiece = new Bishop(false, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'b') {
                //         PImage img = loadImage("src/main/resources/XXLChess/w-bishop.png");
                //         Piece setuppiece = new Bishop(true, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'H') {
                //         PImage img = loadImage("src/main/resources/XXLChess/b-archbishop.png");
                //         Piece setuppiece = new Archbishop(false, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'h') {
                //         PImage img = loadImage("src/main/resources/XXLChess/w-archbishop.png");
                //         Piece setuppiece = new Archbishop(true, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'C') {
                //         PImage img = loadImage("src/main/resources/XXLChess/b-camel.png");
                //         Piece setuppiece = new Camel(false, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'c') {
                //         PImage img = loadImage("src/main/resources/XXLChess/w-camel.png");
                //         Piece setuppiece = new Camel(true, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'G') {
                //         PImage img = loadImage("src/main/resources/XXLChess/b-knight-king.png");
                //         Piece setuppiece = new Guard(false, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'g') {
                //         PImage img = loadImage("src/main/resources/XXLChess/w-knight-king.png");
                //         Piece setuppiece = new Guard(true, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'A') {
                //         PImage img = loadImage("src/main/resources/XXLChess/b-amazon.png");
                //         Piece setuppiece = new Amazon(false, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'a') {
                //         PImage img = loadImage("src/main/resources/XXLChess/w-amazon.png");
                //         Piece setuppiece = new Amazon(true, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'K') {
                //         PImage img = loadImage("src/main/resources/XXLChess/b-king.png");
                //         Piece setuppiece = new King(false, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'k') {
                //         PImage img = loadImage("src/main/resources/XXLChess/w-king.png");
                //         Piece setuppiece = new King(true, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'E') {
                //         PImage img = loadImage("src/main/resources/XXLChess/b-chancellor.png");
                //         Piece setuppiece = new Chancellor(false, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                //     } else if (line.charAt(j) == 'e') {
                //         PImage img = loadImage("src/main/resources/XXLChess/w-chancellor.png");
                //         Piece setuppiece = new Chancellor(true, img);
                //         board.board_tiles[i][j] = new Tile(this, i, j, setuppiece);
                    } 
                } 
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
        

        // PImage spr = loadImage("src/main/resources/XXLChess/"+...);

		// load config
        JSONObject conf = loadJSONObject(new File(this.configPath));
        
    }

    /**
     * Receive key pressed signal from the keyboard.
    */
    public void keyPressed(){


    }
    
    /**
     * Receive key released signal from the keyboard.
    */
    public void keyReleased(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // mouseX
        // mouseY
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    /**
     * Draw all elements in the game by current frame. 
    */
    public void draw() {
        // board.draw(this);
        rect(3,3,CELLSIZE,CELLSIZE);
        PImage img = loadImage("src/main/resources/XXLChess/b-pawn.png");
        image(img,0,0);
    }
	
	// Add any additional methods or attributes you want. Please put classes in different files.


    public static void main(String[] args) {
        PApplet.main("XXLChess.App");
    }

}
