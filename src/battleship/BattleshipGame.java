package battleship;

import java.util.Random;
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

	private static int[] createOcean(Scanner scan){
		System.out.println("Do you want to create the ocean by yourself, or randomly create a ocean");
		System.out.println("Enter 1 to create by yourself, 2 to randomly create a ocean");
		int create = scan.nextInt();
		while(create > 2 || create < 1){
			System.out.print("Invalid! Enter 1 to create by yourself, 2 to randomly create a ocean");
			create = scan.nextInt();
		}

		int newOceanRow = 0;
		int newOceanCol = 0;
		if(create == 1){
			System.out.println("\nPlease enter how many row and column you wish to have in the ocean");
			System.out.println("Enter amount row in the ocean (10-100)");
			newOceanRow = scan.nextInt();
			while(newOceanRow < 10 || newOceanRow > 100){
				System.out.print("Invalid! Enter the row in the ocean (10-100)");
				newOceanRow = scan.nextInt();
			}
			System.out.println("Enter amount column in the ocean(10-100)");
			newOceanCol = scan.nextInt();
			while(newOceanCol < 10 || newOceanCol > 100){
				System.out.print("Invalid! Enter the row in the ocean (10-100)");
				newOceanCol = scan.nextInt();
			}
		}
		else{
			Random rand = new Random();
			newOceanRow = rand.nextInt(100);
			newOceanCol = rand.nextInt(100);
		}


		return new int[] {newOceanRow, newOceanCol};
	}


	private static void singlePlayer(Scanner scan){

		boolean gameRun = true;
		while (gameRun) {

			int[] newOcean = createOcean(scan);
			int newOceanRow = newOcean[0];
			int newOceanCol = newOcean[1];

			// create the ocean
			Ocean ocean = new Ocean(newOceanRow,newOceanCol);
			// deploy the ships
			ocean.placeAllShipsRandomly();
			while (ocean.isGameOver() == false) {

				gameInProcess("",ocean,newOceanRow,newOceanCol,scan);


			}
			//game over
			//display the ocean of all sunk
			ocean.print();
			// display the result
			System.out.println("\nGame Over~");
			System.out.println("Total hit:" + ocean.getHitCount());
			System.out.println("Total shoot you've fired:" + ocean.getShotsFired());

			gameRun = playAgain(scan);

		}

	}

	private static void multiPlayer(Scanner scan){
		System.out.println("Enter is the name of Player 1~");
		String name1 = scan.next() + " ";
		System.out.println("Enter is the name of Player 2~");
		String name2 = scan.next() + " ";
		boolean gameRun = true;
		while (gameRun) {

			int[] newOcean = createOcean(scan);
			int newOceanRow = newOcean[0];
			int newOceanCol = newOcean[1];

			// create the ocean
			Ocean ocean1 = new Ocean(newOceanRow,newOceanCol);
			Ocean ocean2 = new Ocean(newOceanRow,newOceanCol);
			// deploy the ships
			ocean1.placeAllShipsRandomly();
			ocean2.placeAllShipsRandomly();
			while (ocean1.isGameOver() == false && ocean2.isGameOver() == false) {
				System.out.println();
				System.out.println("This is "+name1+"'s turn");
				gameInProcess(name1,ocean1,newOceanRow,newOceanCol,scan);
				System.out.println();
				System.out.println("This is "+name1+"'s turn");
				gameInProcess(name2,ocean2,newOceanRow,newOceanCol, scan);
			}
			//game over
			ocean1.print();
			// display the result
			System.out.println(name1+": Total hit:" + ocean1.getHitCount());
			System.out.println(name1+": Total shoot you've fired:" + ocean1.getShotsFired());

			ocean2.print();
			// display the result
			System.out.println(name2+": Total hit:" + ocean2.getHitCount());
			System.out.println(name2+": Total shoot you've fired:" + ocean2.getShotsFired());
			System.out.println("\nGame Over~");
			if (ocean1.isGameOver() == true){
				System.out.println("Player1: "+name1+" just win the game");
			}
			else {
				System.out.println("Player2: "+name2+" just win the game");
			}

			gameRun = playAgain(scan);

		}

	}



	private static void gameInProcess(String name, Ocean ocean,int newOceanRow,int newOceanCol,Scanner scan){
		System.out.println("");
		ocean.print();

		// prompt for the row
		System.out.println(name+"Enter the row(0-"+ (newOceanRow-1) + "):");
		row = scan.nextInt();
		// if the input of row is invalid, prompt to re enter
		while (row > newOceanRow-1 || column < 0) {
			System.out.print(name+"Invalid! Enter the row again(0-"+ (newOceanRow-1) +"):");
			row = scan.nextInt();
		}

		// prompt for the column
		System.out.println(name+"Enter the column(0-"+ (newOceanCol-1)+"):");
		column = scan.nextInt();
		// if the input of column is invalid, prompt to re enter
		while (column > newOceanCol-1 || column < 0) {
			System.out.print(name+"Invalid! Enter the column again(0-"+ (newOceanCol-1)+"):");
			column = scan.nextInt();

		}
		ocean.shootAt(row, column);

		// if a ship is sunk, display the message
		if (ocean.getShipArray()[row][column].isSunk() == true) {
			System.out.println("hit");
			System.out.println(name+"You just sank a " + ocean.getShipArray()[row][column].getShipType());
		}
		// display whether user hit or not
		else if (ocean.shootAt(row, column) == true) {
			System.out.println("hit");
		} else if (ocean.shootAt(row, column) == false) {
			System.out.println("miss");
		}
	}

	static boolean playAgain( Scanner scan){
		// this loop will prompt the user if they want to play again
		while (true) {
			System.out.print("\nDo you want to play again(Yes or No)?:");
			String answer = scan.next();

			if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
				System.out.println("\nNew the Game");
				return(true);
			} else if (answer.charAt(0) == 'N' || answer.charAt(0) == 'n') {
				System.out.println("\nEnding the Game");
				return(false);
			}
		}
	}

	public static void main(String[] args) {

		// print introduction of the game
		System.out.println("Welcome to the BattleShip!!");
		System.out.println("The computer places the ten ships on the ocean");
		System.out.println("You does not know where the ships are.");
		System.out.println("tries to hit the ships, by indicating a specific row and column number.");

		Scanner scan = new Scanner(System.in);

		System.out.println("Do you want to solo mode or two player mode");
		System.out.println("Enter 1 for solo, 2 for two player");
		int mode = scan.nextInt();
		while(mode > 2 || mode < 1){
			System.out.print("Invalid! Enter 1 for solo, 2 for two player");
			mode = scan.nextInt();
		}

		if(mode==1){
			singlePlayer(scan);
		}
		else {
			multiPlayer(scan);
		}

		scan.close();

	}
}
