# Battleship Game
-----------------
This is a one-player vs. computer version, where the computer places the ships, and the human attempts to sink them.
Game will be play on a 10x10 “ocean” and will be using the following ships (“the fleet”):
| Ship        | Amount |         
| ------------| -------|
| `Battleship`| 1      | 
| `Cruiser`   | 2      |
| `Destroyers`| 3      |
| `Submarines`| 4      |

## How to Play Battleship
The computer places the ten ships on the ocean in such a way that no ships are immediately adjacent to each other, either horizontally, vertically, or diagonally. 

The player does not know where the ships are. The initial display of the ocean to be printed to the console therefore shows a 10 by 10 array of ‘.‘ 

![image](https://user-images.githubusercontent.com/77389522/172989436-3a5e8998-45ae-4c35-9072-028ecdb1046a.png)

### Hit or Miss
The human player tries to hit the ships, by indicating a specific row and column number (r,c). The computer responds with one bit of information saying, “hit” or “miss”.
hit will be indicate by "x"
miss will be indicate by "-"

![image](https://user-images.githubusercontent.com/77389522/172989558-a61f6a4c-b5c9-4652-a920-1233871b2dd3.png)

### Sunk a ship
A ship is “sunk” when every square of the ship has been hit. Thus, it takes four hits (in four different places) to sink a battleship, three to sink a cruiser, two for a destroyer, and one for a submarine.
sunk will be indicate by "s"

![image](https://user-images.githubusercontent.com/77389522/172990215-bc48d69c-59d4-471f-9ddf-e327864c3d5c.png)

### Game Over
When all ships are sunk the game is over, after the game is over program prints out a message that the game is over, and tells how many shots were fired and ask if player want to play again...

![image](https://user-images.githubusercontent.com/77389522/172990329-284c2eac-77da-4794-a38a-c16fca27e15e.png)
