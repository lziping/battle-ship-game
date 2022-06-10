package battleship;

import java.util.Scanner;

/**
 * this is the main class of game this class will set up the game and operate
 * the game
 */

public class BattleshipGame {

	/*
	 * row
	 */
	static int row;
	/*
	 * column
	 */
	static int column;

	static boolean playAgain = true;

	public static void main(String[] args) {

		// print introduction of the game
		System.out.println("Welcome to the BattleShip!!");
		System.out.println("The computer places the ten ships on the ocean");
		System.out.println("You does not know where the ships are.");
		System.out.println("tries to hit the ships, by indicating a specific row and column number.");

		Scanner scan = new Scanner(System.in);

		boolean gameRun = true;



		while (gameRun) {

			System.out.println("Enter amount row in the ocean (10-100)");
			int newOceanRow = scan.nextInt();
			while(newOceanRow < 10 || newOceanRow > 100){
				System.out.print("Invalid! Enter the row in the ocean (10-100)");
				newOceanRow = scan.nextInt();
			}
			System.out.println("Enter amount column in the ocean(10-100)");
			int newOceanCol = scan.nextInt();
			while(newOceanCol < 10 || newOceanCol > 100){
				System.out.print("Invalid! Enter the row in the ocean (10-100)");
				newOceanCol = scan.nextInt();
			}

			// create the ocean
			Ocean ocean = new Ocean(newOceanRow,newOceanCol);
			// deploy the ships
			ocean.placeAllShipsRandomly();
			while (ocean.isGameOver() == false) {

				System.out.println("");
				ocean.print();

				// prompt for the row
				System.out.println("Enter the row(0-"+ (newOceanRow-1) + "):");
				row = scan.nextInt();
				// if the input of row is invalid, prompt to re enter
				while (row > newOceanRow-1 || column < 0) {
					System.out.print("Invalid! Enter the row again(0-"+ (newOceanRow-1) +"):");
					row = scan.nextInt();
				}

				// prompt for the column
				System.out.println("Enter the column(0-"+ (newOceanCol-1)+"):");
				column = scan.nextInt();
				// if the input of column is invalid, prompt to re enter
				while (column > newOceanCol-1 || column < 0) {
					System.out.print("Invalid! Enter the column again(0-"+ (newOceanCol-1)+"):");
					column = scan.nextInt();

				}
				ocean.shootAt(row, column);

				// if a ship is sunk, display the message
				if (ocean.getShipArray()[row][column].isSunk() == true) {
					System.out.println("hit");
					System.out.println("You just sank a " + ocean.getShipArray()[row][column].getShipType());
				}
				// display whether user hit or not
				else if (ocean.shootAt(row, column) == true) {
					System.out.println("hit");
				} else if (ocean.shootAt(row, column) == false) {
					System.out.println("miss");
				}


			}
      //game over
      //display the ocean of all sunk
			ocean.print();      
			// display the result
			System.out.println("\nGame Over~");
			System.out.println("Total hit:" + ocean.getHitCount());
			System.out.println("Total shoot you've fired:" + ocean.getShotsFired());

			// this loop will prompt the user if they want to play again
			while (true) {
				System.out.print("\nDo you want to play again(Yes or No)?:");
				String answer = scan.next();

				if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
					System.out.println("\nNew the Game");
					break;
				} else if (answer.charAt(0) == 'N' || answer.charAt(0) == 'n') {
					System.out.println("\nEnding the Game");
					gameRun = false; // set gameRun to false to break the loop
					break;
				}
			}

		}
		scan.close();
	}
}
