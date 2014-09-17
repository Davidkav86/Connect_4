package connect4.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This is artificial intelligence player for the connect 4 game. This player
 * will compete against a human player and other computer players.
 * 
 * @author David Kavanagh
 * 
 */
public class ComputerPlayer20057155 extends IPlayer
{

	private final int twoInSequence = 10;
	private final int threeInSequence = 30;
	private final int vertical = 1;
	private final int horizontal = 3;
	private final int diagonal = 5;
	private int max = Integer.MAX_VALUE;
	private int min = Integer.MIN_VALUE;

	public ComputerPlayer20057155(LocationState playerState)
	{
		super(playerState);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see connect4.model.IPlayer#getMove(connect4.model.Board)
	 */
	@Override
	public int getMove(Board board)
	{
		// find the middle of the board
		int middle = board.getNoCols() / 2;

		// if the middle of the board is free on the first go, place colour
		// there
		if (board.getLocationState(new Location(middle, 5)) == LocationState.EMPTY)
		{
			// cast middle as an int and return
			return (int) middle;
		}

		// int move = (int) (Math.random() * board.getNoCols());

		int move = makeMove(board);

		return move;

	}

	/**
	 * This method calculates the best move the player can make. It does so by
	 * generating a list of possible valid moves. It then evaluates each move
	 * using the heuristic for that move and chooses the best column
	 * 
	 * makeMove() - This method will use an ArrayList of locations to store all
	 * the possible locations that are available to this player. Then it will
	 * use the heuristic method to evaluate the best possible move available for
	 * this player relating to the current state of the board
	 * 
	 * @param board
	 *            - The board being used in the current game
	 * 
	 * @return bestMove - The best available column for this player
	 */
	private int makeMove(Board board)
	{

		// The int that will be returned as the best possible column following
		// the evaluation
		int bestMove = 0;

		// This
		int highestEvaluation = 0;

		// An ArrayList to store all possible locations available to this player
		ArrayList<Location> possibleLocations = new ArrayList<Location>();

		for (int i = 0; i < board.getNoCols(); i++)
		{
			// Assign the top row of the column to a new location
			Location firstCheck = new Location(i, 0);

			// A check make sure the column is not full
			if (board.getLocationState(firstCheck).equals(LocationState.EMPTY))
			{

				int lowestavailableSpace = 0;

				for (int j = 1; j < board.getNoRows(); j++)
				{
					// create a new location to check if it is free
					Location checkLocation = new Location(i, j);
					// if the location is empty
					if (board.getLocationState(checkLocation).equals(
							LocationState.EMPTY))
					{
						// assign the row to the lowest location available
						lowestavailableSpace = j;
					}
					else
					{
						break;
					}
				}
				// create a new location with the column and the lowest
				// available space and add it to the possible locations list
				Location available = new Location(i, lowestavailableSpace);
				possibleLocations.add(available);
			}
		}

		for (int k = 0; k < possibleLocations.size(); k++)
		{
			// Create a new location to test against all the possible locations
			// in the list
			Location testLocation = possibleLocations.get(k);

			// Place the test location in the board
			board.setLocationState(testLocation, getPlayerState());

			// Pass the board into the heuristic method and assign the score
			// returned to an int
			int evaluation = heuristic(this.getPlayerState(), board);

			// if the highest evaluation is less than the evaluation from the
			// heuristic method, assign the evaluation to the highest evaluation
			// int and assign the column of the test location to the best move
			if (evaluation > highestEvaluation)
			{
				highestEvaluation = evaluation;
				bestMove = testLocation.getX();
			}
			// set the location on the board back to empty so it doesn't effect
			// the game
			board.setLocationState(testLocation, LocationState.EMPTY);
		}
		return bestMove;
	}

	/**
	 * heuristic() - This method will first check to see in if this player or
	 * the opposing will be able to get a win by making a move. If not it will
	 * use all the score checking methods to evaluate the total score
	 * 
	 * @param currentPlayer
	 *            - The location state of the current player
	 * 
	 * @param board
	 *            - The board being used in the game play
	 * 
	 * @return score - Score of total evaluation
	 */
	public int heuristic(LocationState currentPlayer, Board board)
	{

		int score = 0;
	
		score += possibleWin(currentPlayer, board);
		// If the next move is a winning move. Make that move.
		if (score == max || score == min)
		{

			return score;
		}
		else
		{
			score += horizontalScoreCheck(currentPlayer, board);

			score += verticalScoreCheck(currentPlayer, board);

			score += diagonalScoreCheck(currentPlayer, board);

			return score;
		}
	}

	/**
	 * possibleWin() - This method will return a max value if this player has
	 * made a possible winning move and a min value if the opposition player has
	 * made a possible move
	 * 
	 * @param currentPlayer
	 *            - The current players location state
	 * 
	 * @param board
	 *            - The board being used in the game play
	 * 
	 * @return an int representing a max or min value. Or possibly a 0 if no
	 *         winning move
	 */
	public int possibleWin(LocationState currentPlayer, Board board)
	{
		// if any of the win checks return true
		if (horizontalCheck(currentPlayer, board)
				|| verticalCheck(currentPlayer, board)
				|| diagonalLeftCheck(currentPlayer, board)
				|| diagonalRightCheck(currentPlayer, board))
		{
			// if it is this player return max
			if (currentPlayer == this.getPlayerState())
			{

				return max;
			}
			// else return min
			else
			{
				return min;
			}
		}

		else
		{
			return 0;
		}
	}

	/**
	 * horizontalCheck() - Checks if there is a horizontal win for the current
	 * player
	 * 
	 * @param currentPlayer
	 *            - The current players location state
	 * 
	 * @param board
	 *            - The board being used in the game play
	 * 
	 * @return a boolean
	 */
	public boolean horizontalCheck(LocationState currentPlayer, Board board)
	{

		int counter = 0;
		
		for (int row = board.getNoRows() - 1; row >= 0; row--)
		{
			for (int col = 0; col < board.getNoCols() - 3; col++)
			{
				if (board.getBoard()[col][row] == currentPlayer)
				{
					counter = 1;
					while (board.getBoard()[++col][row] == currentPlayer)
					{
						counter++;
						if (counter == 4)
						{
							return true;
						}
					}

				}
			}
		}
		return false;
	}

	/**
	 * verticalCheck() - Checks if there is a vertical win for the current
	 * player
	 * 
	 * @param currentPlayer
	 *            - The current players location state
	 * 
	 * @param board
	 *            - The board being used in the game play
	 * 
	 * @return a boolean
	 */
	public boolean verticalCheck(LocationState currentPlayer, Board board)
	{

		int counter = 0;
		
		for (int col = 0; col < board.getNoCols(); col++)
		{
			for (int row = 0; row < board.getNoRows() - 3; row++)
			{
				if (board.getBoard()[col][row] == currentPlayer)
				{
					while (board.getBoard()[col][row++] == currentPlayer)
					{
						counter++;
						if (counter == 4)
						{
							return true;
						}
					}

				}

			}
		}
		return false;
	}

	/**
	 * diagonalLeftCheck() - Checks if there is a diagonal win for the current
	 * player
	 * 
	 * @param currentPlayer
	 *            - The current players location state
	 * 
	 * @param board
	 *            - The board being used in the game play
	 * 
	 * @return a boolean
	 */
	public boolean diagonalLeftCheck(LocationState currentPlayer, Board board)
	{

		int counter = 0;
		
		for (int col = 0; col < board.getNoCols() - 3; col++)
		{
			for (int row = 0; row < board.getNoRows() - 3; row++)
			{
				if (board.getBoard()[col][row] == currentPlayer)
				{
					for (int tmpRow = row, tmpCol = col; board.getBoard()[tmpCol++][tmpRow++] == currentPlayer;)
					{
						counter++;
						if (counter == 4)
						{
							return true;
						}
					}

				}
			}
		}
		return false;

	}

	/**
	 * diagonalRightCheck() - Checks if there is a diagonal win for the current
	 * player
	 * 
	 * @param currentPlayer
	 *            - The current players location state
	 * 
	 * @param board
	 *            - The board being used in the game play
	 * 
	 * @return a boolean
	 */
	public boolean diagonalRightCheck(LocationState currentPlayer, Board board)
	{

		int counter = 0;
		for (int col = board.getNoCols() - 1; col > 2; col--)
		{
			for (int row = 0; row < 3; row++)
			{
				if (board.getBoard()[col][row] == currentPlayer)
				{
					for (int tmpRow = row, tmpCol = col; board.getBoard()[tmpCol--][tmpRow++] == currentPlayer;)
					{
						counter++;
						if (counter == 4)
						{
							return true;
						}
					}

				}
			}

		}
		return false;
	}

	/**
	 * horizontalScoreCheck() - This method will check the board to see if the
	 * player has any chips in bunches of twos or threes horizontally. It will
	 * return an int that represents a value attributed to each of the possible
	 * positions
	 * 
	 * 
	 * @param currentPlayer
	 *            - The current players location
	 * 
	 * @param board
	 *            - the board that the game is currently being played on
	 * 
	 * @return score -
	 */
	private int horizontalScoreCheck(LocationState currentPlayer, Board board)
	{

		// this int will be be returned as the score for each of the possible
		// moves that can be made by the player
		int score = 0;

		//
		// This will check for the various ways that a player can have two
		// chips in a row horizontally
		//
		for (int i = 0; i < board.getNoCols() - 4; i++)
		{
			for (int j = 0; j < 6; j++)
			{

				if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 1, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 2, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 3, j)) == LocationState.EMPTY)
					score += twoInSequence * horizontal;

				else if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 2, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 1, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 3, j)) == LocationState.EMPTY)
					score += twoInSequence * horizontal;

				else if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 3, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 1, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 2, j)) == LocationState.EMPTY)
					score += twoInSequence * horizontal;

				else if (board.getLocationState(new Location(i, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 3, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 1, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 2, j)) == currentPlayer)
					score += twoInSequence * horizontal;

				else if (board.getLocationState(new Location(i, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 2, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 1, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 3, j)) == currentPlayer)
					score += twoInSequence * horizontal;

				else if (board.getLocationState(new Location(i, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 1, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 2, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 3, j)) == currentPlayer)
					score += twoInSequence * horizontal;
			}
		}
		//
		// This will check for the various ways that a player can have three
		// chips in a row horizontally
		//
		for (int i = 0; i < board.getNoCols() - 4; i++)
		{
			for (int j = 0; j < board.getNoRows(); j++)
			{

				if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 1, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 2, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 3, j)) == LocationState.EMPTY)
					score += threeInSequence * horizontal;

				else if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 1, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 3, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 2, j)) == currentPlayer)
					score += threeInSequence * horizontal;

				else if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 2, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 3, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 1, j)) == LocationState.EMPTY)
					score += threeInSequence * horizontal;
				else if (board.getLocationState(new Location(i, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 1, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 2, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 3, j)) == currentPlayer)
					score += threeInSequence * horizontal;
			}
		}

		return score;

	}

	/**
	 * verticalScoreCheck() - This method will check the board to see if the
	 * player has any chips in bunches of twos or threes vertically in any
	 * column. It will return an int that represents a value attributed to each
	 * of the possible positions
	 * 
	 * 
	 * @param currentPlayer
	 *            - The current players location
	 * 
	 * @param board
	 *            - the board that the game is currently being played on
	 * 
	 * @return score - An int representing
	 */
	private int verticalScoreCheck(LocationState currentPlayer, Board board)
	{

		// this int will be be returned as the score for each of the possible
		// moves that can be made by the player
		int score = 0;

		//
		// This will check for the various ways that a player can have two
		// chips in a row vertically
		//
		for (int i = 0; i < board.getNoCols(); i++)
		{
			for (int j = board.getNoRows() - 3; j >= 2; j--)
			{
				Location one = new Location(i, j);
				Location two = new Location(i, j + 1);
				Location three = new Location(i, j + 2);
				if (board.getLocationState(one) == currentPlayer
						&& board.getLocationState(two) == currentPlayer
						&& board.getLocationState(three) == LocationState.EMPTY)
					score += twoInSequence * vertical;
			}
		}
		//
		// This will check for the various ways that a player can have three
		// chips in a row vertically
		//
		for (int i = 0; i < board.getNoCols(); i++)
		{
			for (int j = board.getNoRows() - 1; j >= 3; j--)
			{
				Location one = new Location(i, j);
				Location two = new Location(i, j - 1);
				Location three = new Location(i, j - 2);
				Location four = new Location(i, j - 3);
				if (board.getLocationState(one) == currentPlayer
						&& board.getLocationState((two)) == currentPlayer
						&& board.getLocationState(three) == currentPlayer
						&& board.getLocationState(four) == LocationState.EMPTY)
					score += threeInSequence * vertical;
			}
		}
		return score;
	}

	/**
	 * diagonalScoreCheck() - This method will check the board to see if the
	 * player has any chips in bunches of twos or threes on the diagonal. It
	 * will return an int that represents a value attributed to each of the
	 * possible positions
	 * 
	 * 
	 * @param currentPlayer
	 *            - The current players location
	 * 
	 * @param board
	 *            - the board that the game is currently being played on
	 * 
	 * @return score - An int representing
	 */
	private int diagonalScoreCheck(LocationState currentPlayer, Board board)
	{

		// this int will be be returned as the score for each of the possible
		// moves that can be made by the player
		int score = 0;

		//
		// This will check for the various ways that a player can have two
		// chips in a row running diagonally from top left to bottom right
		//
		for (int i = 0; i < board.getNoCols() - 3; i++)
		{
			for (int j = board.getNoRows() - 4; j >= 0; j--)
			{

				if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 1, j + 1)) == currentPlayer
						&& board.getLocationState(new Location(i + 2, j + 2)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 3, j + 3)) == LocationState.EMPTY)
					score += twoInSequence * diagonal;

				else if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 3, j + 3)) == currentPlayer
						&& board.getLocationState(new Location(i + 1, j + 1)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 2, j + 2)) == LocationState.EMPTY)
					score += twoInSequence * diagonal;

				else if (board.getLocationState(new Location(i, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 3, j + 3)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 1, j + 1)) == currentPlayer
						&& board.getLocationState(new Location(i + 2, j + 2)) == currentPlayer)
					score += twoInSequence * diagonal;

				else if (board.getLocationState(new Location(i, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 2, j + 2)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 1, j + 1)) == currentPlayer
						&& board.getLocationState(new Location(i + 3, j + 3)) == currentPlayer)
					score += twoInSequence * diagonal;

				else if (board.getLocationState(new Location(i, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 1, j + 1)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 2, j + 2)) == currentPlayer
						&& board.getLocationState(new Location(i + 3, j + 3)) == currentPlayer)
					score += twoInSequence * diagonal;

				else if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 2, j + 2)) == currentPlayer
						&& board.getLocationState(new Location(i + 1, j + 1)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 3, j + 3)) == LocationState.EMPTY)
					score += twoInSequence * diagonal;
			}
		}

		//
		// This will check for the various ways that a player can have two
		// chips in a row running diagonally from bottom left to top right
		//
		for (int i = board.getNoCols() - 1; i >= 3; i--)
		{
			for (int j = board.getNoRows() - 4; j >= 0; j--)
			{

				if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i - 1, j + 1)) == currentPlayer
						&& board.getLocationState(new Location(i - 2, j + 2)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i - 3, j + 3)) == LocationState.EMPTY)
					score += twoInSequence * diagonal;

				else if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i - 3, j + 3)) == currentPlayer
						&& board.getLocationState(new Location(i - 1, j + 1)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i - 2, j + 2)) == LocationState.EMPTY)
					score += twoInSequence * diagonal;

				else if (board.getLocationState(new Location(i, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i - 3, j + 3)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i - 1, j + 1)) == currentPlayer
						&& board.getLocationState(new Location(i - 2, j + 2)) == currentPlayer)
					score += twoInSequence * diagonal;

				else if (board.getLocationState(new Location(i, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i - 2, j + 2)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i - 1, j + 1)) == currentPlayer
						&& board.getLocationState(new Location(i - 3, j + 3)) == currentPlayer)
					score += twoInSequence * diagonal;

				else if (board.getLocationState(new Location(i, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i - 1, j + 1)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i - 2, j + 2)) == currentPlayer
						&& board.getLocationState(new Location(i - 3, j + 3)) == currentPlayer)
					score += twoInSequence * diagonal;

				else if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i - 2, j + 2)) == currentPlayer
						&& board.getLocationState(new Location(i - 1, j + 1)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i - 3, j + 3)) == LocationState.EMPTY)
					score += twoInSequence * diagonal;
			}
		}

		//
		// This will check for the various ways that a player can have three
		// chips in a row running diagonally from top left to bottom right
		//
		for (int i = 0; i < board.getNoCols() - 3; i++)
		{
			for (int j = board.getNoRows() - 4; j >= 0; j--)
			{

				if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 1, j + 1)) == currentPlayer
						&& board.getLocationState(new Location(i + 2, j + 2)) == currentPlayer
						&& board.getLocationState(new Location(i + 3, j + 3)) == LocationState.EMPTY)
					score += threeInSequence * diagonal;

				else if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 1, j + 1)) == currentPlayer
						&& board.getLocationState(new Location(i + 3, j + 3)) == currentPlayer
						&& board.getLocationState(new Location(i + 2, j + 2)) == LocationState.EMPTY)
					score += threeInSequence * diagonal;

				else if (board.getLocationState(new Location(i, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i + 1, j + 1)) == currentPlayer
						&& board.getLocationState(new Location(i + 2, j + 2)) == currentPlayer
						&& board.getLocationState(new Location(i + 3, j + 3)) == currentPlayer)
					score += threeInSequence * diagonal;

				else if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i + 2, j + 2)) == currentPlayer
						&& board.getLocationState(new Location(i + 3, j + 3)) == currentPlayer
						&& board.getLocationState(new Location(i + 1, j + 1)) == LocationState.EMPTY)
					score += threeInSequence * diagonal;
			}
		}

		//
		// This will check for the various ways that a player can have three
		// chips in a row running diagonally from bottom left to top right
		//
		for (int i = board.getNoCols() - 1; i >= 3; i--)
		{
			for (int j = board.getNoRows() - 4; j >= 0; j--)
			{

				if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i - 1, j + 1)) == currentPlayer
						&& board.getLocationState(new Location(i - 2, j + 2)) == currentPlayer
						&& board.getLocationState(new Location(i - 3, j + 3)) == LocationState.EMPTY)
					score += threeInSequence * diagonal;

				else if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i - 1, j + 1)) == currentPlayer
						&& board.getLocationState(new Location(i - 3, j + 3)) == currentPlayer
						&& board.getLocationState(new Location(i - 2, j + 2)) == LocationState.EMPTY)
					score += threeInSequence * diagonal;

				else if (board.getLocationState(new Location(i, j)) == LocationState.EMPTY
						&& board.getLocationState(new Location(i - 1, j + 1)) == currentPlayer
						&& board.getLocationState(new Location(i - 2, j + 2)) == currentPlayer
						&& board.getLocationState(new Location(i - 3, j + 3)) == currentPlayer)
					score += threeInSequence * diagonal;

				else if (board.getLocationState(new Location(i, j)) == currentPlayer
						&& board.getLocationState(new Location(i - 2, j + 2)) == currentPlayer
						&& board.getLocationState(new Location(i - 3, j + 3)) == currentPlayer
						&& board.getLocationState(new Location(i - 1, j + 1)) == LocationState.EMPTY)
					score += threeInSequence * diagonal;
			}
		}

		return score;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return ("Computer player ");
	}
}
