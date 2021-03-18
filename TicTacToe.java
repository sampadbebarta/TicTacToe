import java.util.Scanner;

public class TicTacToe {
	private char[] board = new char[10];

	//UC1 create board
	public void boardCreation() {
		for (int position = 1; position<board.length; position++) {
			board[position] = ' ';
		}
	}

	//UC2 ask user to choose either X or O for player
	public char chooseSymbolForPlayer(char userLetter) {
		char computerLetter = ' ';
		if (userLetter =='X' || userLetter=='x') {
			computerLetter = 'O';
		} else if (userLetter =='O' || userLetter=='o') {
			computerLetter = 'X';
		}
		return computerLetter;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Tic Tac Toe game");
		TicTacToe ticTacToeGame = new TicTacToe();
		ticTacToeGame.boardCreation();

		System.out.println("Enter X or O:");
		char userLetter = sc.next().charAt(0);
		char computerSymbol = ticTacToeGame.chooseSymbolForPlayer(userLetter);
		System.out.println("User "+userLetter);
		System.out.println("comp " + computerSymbol);
	}
}
