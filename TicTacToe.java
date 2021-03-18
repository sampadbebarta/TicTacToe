import java.util.Scanner;

public class TicTacToe {
	private char[] board = new char[10];
	private final static int USER = 0;
	private final static int COMPUTER = 1;

	// UC1 create board
	public void boardCreation() {
		for (int position = 1; position < board.length; position++) {
			board[position] = ' ';
		}
	}

	// UC2 ask user to choose either X or O for player
	public char chooseSymbolForPlayer(char userLetter) {
		char computerLetter = ' ';
		if (userLetter == 'X' || userLetter == 'x') {
			computerLetter = 'O';
		} else if (userLetter == 'O' || userLetter == 'o') {
			computerLetter = 'X';
		}
		return computerLetter;
	}

	// UC3 show the game board
	public void showBoard() {
		System.out.println("Current Board: ");
		System.out.println(board[1] + " | " + board[2] + " | " + board[3]);
		System.out.println(" ------- ");
		System.out.println(board[4] + " | " + board[5] + " | " + board[6]);
		System.out.println(" ------- ");
		System.out.println(board[7] + " | " + board[8] + " | " + board[9]);
	}

	// UC4 players put their symbol at a desired location
	public void playersMakeMove(Scanner input, char playerSymbol) {
		boolean isLocationFree;
		int boardLocation;
		do {
			System.out.println("Enter the location (1-9) to put symbol: ");
			boardLocation = input.nextInt();
			isLocationFree = false;
			if (boardLocation >= 1 && boardLocation <= 9) {
				isLocationFree = checkIfPositionFree(boardLocation);
			} else {
				System.out.println("Invalid position entered.");
			}
		} while (!isLocationFree);
		board[boardLocation] = playerSymbol;
		showBoard();
	}

	// UC5 check if there is free space or not
	public boolean checkIfPositionFree(int boardLocation) {
		if (board[boardLocation] == ' ') {
			return true;
		} else {
			System.out.println("Already filled");
			return false;
		}
	}

	// UC6 decides who plays first
	public String getWhoPlaysFirst() {
		int tossResult = (int) (Math.random() * 10) % 2;

		if (tossResult == USER) {
			return "Player";
		} else {
			return "Computer";
		}
	}

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Welcome to Tic Tac Toe game");
		TicTacToe ticTacToeGame = new TicTacToe();
		ticTacToeGame.boardCreation();

		System.out.println("Enter X or O:");
		char userLetter = userInput.next().charAt(0);
		char computerSymbol = ticTacToeGame.chooseSymbolForPlayer(userLetter);
		System.out.println("User " + userLetter);
		System.out.println("comp " + computerSymbol);
		ticTacToeGame.showBoard();

		ticTacToeGame.playersMakeMove(userInput, userLetter);

		String playsFirst = ticTacToeGame.getWhoPlaysFirst();
		System.out.println("Plays first: " + playsFirst);

		userInput.close();
	}
}
