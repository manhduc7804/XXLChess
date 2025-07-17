# XXLChess - Chess with 16x16 table and new pieces

### 1. Running the app
Build the `gradle` then run it with `run` arg. Here is the full command:
```
./gradle build
./gradle run
```
If there are no errors, the game should be open in a new window. Subsequent runs (after making changes to the code) can be re-run with just the `run` command:
```
./gradle run
```

### 2. Changing config
The config for the game is in config.json. The layout of the board can be changed using the file level1.txt. The clock can be change in the time_controls segment. Use player_colour to change player start colour, piece_movement_speed to change the speed each piece moves, max_movement_time to change duration of animation, number_of_player to play against BOT or another player, ai_move_speed to control the speed of BOT decision. 

*IMPORTANT: BOT is moving randomly. The AI for a smarter BOT is not implemented.