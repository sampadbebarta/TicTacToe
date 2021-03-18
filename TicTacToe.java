import java.util.Scanner;

public class TicTacToe {
	private char[] board = new char[10];
	private final static int USER = 0; // when tossResult= 0 (HEAD), then user plays
	private final static int COMPUTER = 1; // when tossResult= 1 (TAILS), then computer plays

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

	// UC7 get winner or change the turn
	public boolean getWinnerOrChangeTurn(char userSymbol) {
		if ((board[1] == userSymbol && board[2] == userSymbol && board[3] == userSymbol)
				|| (board[4] == userSymbol && board[5] == userSymbol && board[6] == userSymbol)
				|| (board[7] == userSymbol && board[8] == userSymbol && board[9] == userSymbol)
				|| (board[1] == userSymbol && board[5] == userSymbol && board[9] == userSymbol)
				|| (board[3] == userSymbol && board[5] == userSymbol && board[7] == userSymbol)
				|| (board[1] == userSymbol && board[4] == userSymbol && board[7] == userSymbol)
				|| (board[2] == userSymbol && board[5] == userSymbol && board[8] == userSymbol)
				|| (board[3] == userSymbol && board[6] == userSymbol && board[9] == userSymbol))
			return true;

		return false;
	}

	// UC8 if computer plays
	public void computerMakesMove(char computerSymbol) {
		boolean isLocationFree = false;
		int boardLocation = 1;
		while (!isLocationFree) {
			boardLocation = (int) ((Math.random() * 10) % 9) + 1;
			isLocationFree = false;
			isLocationFree = checkIfPositionFree(boardLocation);
		}
		board[boardLocation] = computerSymbol;
		showBoard();
	}

	//Both players plays until game is over
	public void computerPlayerBothPlay(Scanner userInput, char userLetter, char computerLetter, boolean playerTurn) {
		boolean playerWins = false, computerWins = false;
		int moveNumber = 1;
		while (moveNumber < 10) {
			if (playerTurn) {
				playersMakeMove(userInput, userLetter);
				playerWins = getWinnerOrChangeTurn(userLetter);
				if (!playerWins) {
					playerTurn = false;
				} else {
					System.out.println("Player wins");
					return;
				}
			} else {
				computerMakesMove(computerLetter);
				computerWins = getWinnerOrChangeTurn(computerLetter);
				if (!computerWins) {
					playerTurn = true;
				} else {
					System.out.println("Computer wins");
					return;
				}
			}
			moveNumber++;
		}
		System.out.println("It is tie");
	}

	public static void main(String[] args) {
		boolean playerTurn = false;
		Scanner userInput = new Scanner(System.in);
		System.out.println("Welcome to Tic Tac Toe game");
		TicTacToe ticTacToeGame = new TicTacToe();
		ticTacToeGame.boardCreation();

		String playsFirst = ticTacToeGame.getWhoPlaysFirst();
		System.out.println("Plays first: " + playsFirst);
		if (playsFirst.equals("Player")) {
			playerTurn = true;
		}

		System.out.println("Enter X or O:");
		char userLetter = userInput.next().charAt(0);
		char computerSymbol = ticTacToeGame.chooseSymbolForPlayer(userLetter);
		System.out.println("User " + userLetter);
		System.out.println("comp " + computerSymbol);
		ticTacToeGame.showBoard();

		ticTacToeGame.computerPlayerBothPlay(userInput, userLetter, computerSymbol, playerTurn);
		userInput.close();
	}
}
