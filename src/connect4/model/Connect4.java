package connect4.model;

/**
 * This class is designed to manage the Connect 4 game. Player and board objects
 * are created inside this class. The first player will take a turn, the
 * currentPlayer will switch to the second Player and they get a turn. This
 * repeats until the board if full of chips i.e, a draw, or a player gets 4
 * chips in a row. In that case they are the winner
 * 
 * @author David Kavanagh
 * 
 */
public class Connect4
{

	private IPlayer human, computer;
	private Board board;
	private IPlayer currentPlayer;
	private int numTurns = 0;
	// list for the game play in the GUI
	public int[] coords = new int[2];

	/**
	 * Connect4() - Constructor for the Connect4 class
	 * 
	 * @param human
	 * 
	 * @param computer
	 * 
	 * @param board
	 */
	public Connect4(IPlayer human, IPlayer computer, Board board)
	{
		super();
		this.human = human;
		this.computer = computer;
		this.board = board;
		this.currentPlayer = human;

	}

	/**
	 * playGame() - This method controls the game play of this game. In it the
	 * human, computer and board objects are instantiated. Then a loop is used
	 * to continuously take turns for each of the players until either one
	 * player wins, or it is a draw.
	 * 
	 */
	public void playGame(Connect4 game)
	{
		// create two new location states, RED and YELLOW
		LocationState red = LocationState.RED;
		LocationState yellow = LocationState.YELLOW;

		// instantiate the human and computer players
		human = new Player(red);
		computer = new ComputerPlayer20057155(yellow);

		// instantiate the board object
		board = new Board(7, 6);
		// set up the board
		board.setUp();

		// set up the new game by using the setters for the board, human and
		// computer objects
		game.setBoard(board);
		game.setComputer(computer);
		game.setHuman(human);

		// randomly select a number between 1 and 2
		int start = (int) (Math.random() * 2);

		// start is 1, human starts the game
		if (start == 1)
		{
			currentPlayer = human;
		}
		// else the computer starts the game
		else
		{
			currentPlayer = computer;
		}

		// while there is no winner
		while (!isWin(board))
		{
			// print the board to the console
			System.out.print(board.toString() + "\n");

			// if the current player takes a turn, change player
			if (takeTurn())
			{
				nextPlayer();
			}

			// if the isDraw method returns true. The game is over
			if (isDraw())
			{
				System.out.print("");
				System.out.print("The game is a draw!!");
				return;
			}

		}

	}

	/**
	 * makeMove() - This method is an equivalent to the takeTurn() method. It is
	 * designed specially for the GUI. It will take in the column selected by
	 * the user as a parameter. It will then take the turn as normal and it will
	 * return the row that it has placed the chip on. This will be used in the
	 * controller class to evaluate where to place the players chip.
	 * 
	 * @param col
	 *            - The column selected by the user input
	 * 
	 * @return row - the row that the chip will be placed. Selected by the
	 *         method
	 */
	public int makeMove(int col)
	{
		int row = 0;

		for (int i = board.getNoRows() - 1; i >= 0; i--)
		{
			if (board.getLocationState(new Location(col, i)) == LocationState.EMPTY)
			{
				board.setLocationState(new Location(col, i),
						currentPlayer.getPlayerState());
				numTurns++;
				row = i;
				break;
			}
		}

		return row;

	}

	/**
	 * nextPlayer() - This method will toggle the players
	 * 
	 */
	public void nextPlayer()
	{

		if (currentPlayer == human)
		{
			currentPlayer = computer;
		}
		else
		{
			currentPlayer = human;
		}

	}

	/**
	 * isWin() - This method will make a call to the various win check methods
	 * and return a boolean value. True if there is a winner and false if there
	 * is not
	 * 
	 * @param board
	 *            to evaluate for winner
	 * @return boolean to detect winner
	 */
	public boolean isWin(Board board)
	{

		if (horizontalWinCheck(board) || verticalWinCheck(board)
				|| diagonalRightCheck(board) || diagonalLeftCheck(board))
		{
			return true;
		}
		return false;

	}

	/**
	 * isDraw() - This method returns true if the game is a draw
	 * 
	 * @return
	 */
	public boolean isDraw()
	{

		return numTurns == board.getNoCols() * board.getNoRows();
	}

	/**
	 * takeTurn() - This method will make a call to the currentPlayers getMove()
	 * method to get which column the player will choose. If the location state
	 * is empty, the currentPlayer's colour is placed in that spot
	 * 
	 * @return boolean indicating move take successfully
	 */
	public boolean takeTurn()
	{

		int col = currentPlayer.getMove(board);

		for (int i = board.getNoRows() - 1; i >= 0; i--)
		{
			if (board.getLocationState(new Location(col, i)) == LocationState.EMPTY)
			{
				board.setLocationState(new Location(col, i),
						currentPlayer.getPlayerState());
				numTurns++;
				// assign the column and row to this list. It will be used in
				// the GUI version of the game
				coords[0] = col;
				coords[1] = i;

				return true;

			}
		}
		return false;
	}

	/**
	 * horizontalWinCheck() - This method will take in the board as a parameter.
	 * It then iterates through the the board, starting at the bottom and
	 * increments a counter each if the currentPlayers colour is found. If the
	 * colour is found four times in a row, true will be returned
	 * 
	 * @param board
	 *            - The 2d Array that is acting as the board for the game
	 * 
	 * @return boolean
	 */
	public boolean horizontalWinCheck(Board board)
	{
		for (int i = board.getNoRows() - 1; i >= 0; i--)
		{
			int counter = 0;

			for (int j = 0; j < board.getNoCols(); j++)
			{
				Location location = new Location(j, i);

				// if the location state at this index on the board matches the
				// currentPlayers location state
				if (board.getLocationState(location).equals(
						currentPlayer.getPlayerState()))
				{
					counter++;
				}
				// else reset the counter
				else
				{
					counter = 0;
				}
				// a test to improve efficiency.
				if (counter == 0 && j == 4)
				{
					break;
				}
				// if the counter hits 4 the game is over
				if (counter == 4)
				{
					System.out.print(currentPlayer.toString()
							+ "has won horizontaly");
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * verticalWinCheck() - This method will take in the board as a parameter.
	 * It then iterates through the the board, starting at first column and
	 * checks the row from bottom to top. It increments the counter if the
	 * currentPlayers colour is found. If the colour is found four times in a
	 * row, true will be returned
	 * 
	 * @param board
	 *            - The 2d Array that is acting as the board for the game
	 * 
	 * @return boolean
	 */
	public boolean verticalWinCheck(Board board)
	{
		for (int i = 0; i < board.getNoCols(); i++)
		{
			int counter = 0;

			for (int j = board.getNoRows() - 1; j >= 0; j--)
			{
				Location location = new Location(i, j);

				// if the location state at this index on the board matches the
				// currentPlayers location state
				if (board.getLocationState(location).equals(
						currentPlayer.getPlayerState()))
				{
					counter++;
				}
				// else reset the counter
				else
				{
					counter = 0;
				}
				// a test to improve efficiency.
				if (counter == 0 && i == 2)
				{
					break;
				}
				// if the counter hits 4 the game is over
				if (counter == 4)
				{
					System.out.print(currentPlayer.toString()
							+ "has won vertically");
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * diagonalRightWinCheck() - This method will take in the board as a
	 * parameter. If a players colour is found in a position that allows for a
	 * diagonal right win, the board positions on the diagonal are checked. If a
	 * player has four in a row, they win the game. If not, the game will
	 * continue.
	 * 
	 * @param board
	 *            - The 2d Array that is acting as the board for the game
	 * 
	 * @return boolean
	 */
	public boolean diagonalRightCheck(Board board)
	{
		int counter = 0;

		for (int i = 0; i < board.getNoCols() - 3; i++)
		{
			for (int j = board.getNoRows() - 3; j < board.getNoRows(); j++)
			{
				// create a new location and assign its coordinates i cols and j
				// rows
				Location Location = new Location(i, j);

				// if the location on the board has the same value as the
				// currentPlayers
				if (board.getLocationState(Location).equals(
						currentPlayer.getPlayerState()))
				{
					counter++;

					// assign two new ints the coordinates of the next diagonal
					// position on the board
					int k = i + 1;
					int t = j - 1;

					// while k and t are inside the coordinates of the board
					while (k < board.getNoCols() && t >= 0)
					{
						// create a new location using k and t coordinates
						Location diagonal = new Location(k, t);

						if (board.getLocationState(diagonal).equals(
								currentPlayer.getPlayerState()))
						{
							counter++;
						}
						// else reset the counter
						else
						{
							counter = 0;
						}
						// if the counter hits 4 the game is over
						if (counter == 4)
						{
							System.out.print(currentPlayer.toString()
									+ "has won diagonal right");
							return true;
						}
						// increment k and decrement t
						k++;
						t--;
					}
					// reset the counter
					counter = 0;
				}
			}
		}
		return false;
	}

	/**
	 * diagonaleftWinCheck() - This method will take in the board as a
	 * parameter. If a players colour is found in a position that allows for a
	 * diagonal left win, the board positions on the diagonal are checked. If a
	 * player has four in a row, they win the game. If not, the game will
	 * continue.
	 * 
	 * @param board
	 *            - The 2d Array that is acting as the board for the game
	 * 
	 * @return boolean
	 */
	public boolean diagonalLeftCheck(Board board)
	{
		int counter = 0;

		for (int i = board.getNoCols() - 1; i > 2; i--)
		{
			for (int j = board.getNoRows() - 3; j < board.getNoRows(); j++)
			{
				// create a new location and assign its coordinates i cols and j
				// rows
				Location Location = new Location(i, j);

				// if the location on the board has the same value as the
				// currentPlayers
				if (board.getLocationState(Location).equals(
						currentPlayer.getPlayerState()))
				{
					counter++;

					// assign two new ints the coordinates of the next diagonal
					// position on the board
					int k = i - 1;
					int t = j - 1;

					// while k and t are inside the coordinates of the board
					while (k >= 0 && t >= 0)
					{
						// create a new location using k and t coordinates
						Location diagonal = new Location(k, t);

						if (board.getLocationState(diagonal).equals(
								currentPlayer.getPlayerState()))
						{
							counter++;
						}
						// else reset the counter
						else
						{
							counter = 0;
						}
						// if the counter hits 4 the game is over
						if (counter == 4)
						{
							System.out.print(currentPlayer.toString()
									+ "has won diagonal left");
							return true;
						}
						// decrement k and t
						k--;
						t--;
					}
					// reset the counter
					counter = 0;
				}
			}
		}
		return false;
	}

	/**
	 * getBoard() - Getter for the board object.
	 * 
	 * @return board - the board object
	 */
	public Board getBoard()
	{
		return board;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// create a new Connect4 game. This is to ensure all the
		Connect4 game = new Connect4(null, null, null);
		// call the playGame() method t begin playing
		game.playGame(game);

	}

	/**
	 * getHuman() - Getter for the human IPlayer object
	 * 
	 * @return human - the human IPlayer object
	 */
	public IPlayer getHuman()
	{
		return human;
	}

	/**
	 * setHuman() - Setter for the human IPlayer object
	 * 
	 * @param human
	 *            - the human IPlayer object
	 */
	public void setHuman(IPlayer human)
	{
		this.human = human;
	}

	/**
	 * getComputer() - Getter for the computer IPlayer object
	 * 
	 * @return computer - the computer IPlayer object
	 */
	public IPlayer getComputer()
	{
		return computer;
	}

	/**
	 * setComputer() - Setter for the computer IPlayer object
	 * 
	 * @param computer
	 *            - the computer IPlayer object
	 */
	public void setComputer(IPlayer computer)
	{
		this.computer = computer;
	}

	/**
	 * getCurrentPlayer() - Getter for the current player field. The current
	 * player is the the player that is currently taking their turn
	 * 
	 * @return currentPlayer - The current player field
	 */
	public IPlayer getCurrentPlayer()
	{
		return currentPlayer;
	}

	/**
	 * setCurrentPlayer() - Setter for the current player field. The current
	 * player is the the player that is currently taking their turn
	 * 
	 * @param currentPlayer
	 *            - The current player
	 */
	public void setCurrentPlayer(IPlayer currentPlayer)
	{
		this.currentPlayer = currentPlayer;
	}

	/**
	 * getNumTurns() - Getter for the numTurns field.
	 * 
	 * @return numTurns - The numTurns field
	 */
	public int getNumTurns()
	{
		return numTurns;
	}

	/**
	 * setNumTurns() - Setter for the numTurns field.
	 * 
	 * @param numTurns
	 *            - The numTurns field
	 */
	public void setNumTurns(int numTurns)
	{
		this.numTurns = numTurns;
	}

	/**
	 * setBoard() - Setter for the board object
	 * 
	 * @param board
	 *            - the board object
	 */
	public void setBoard(Board board)
	{
		this.board = board;
	}

}
