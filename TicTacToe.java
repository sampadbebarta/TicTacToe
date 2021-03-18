public class TicTacToe {
	private char[] board = new char[10];

	public void boardCreation() {
		for (int position = 1; position<board.length; position++) {
			board[position] = ' ';
		}
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Tic Tac Toe game");
		TicTacToe ticTacToeGame = new TicTacToe();
		ticTacToeGame.boardCreation();
	}
}
