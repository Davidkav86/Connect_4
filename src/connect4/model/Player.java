package connect4.model;

import java.util.List;
import java.util.Scanner;

public class Player extends IPlayer
{

	public Player(LocationState playerState)
	{
		super(playerState);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see connect4.IPlayer#getMove(connect4.Board)
	 */
	@Override
	public int getMove(Board board)
	{

		int move = (int) (Math.random() * board.getNoCols());

		// Scanner sc = new Scanner(System.in);
		// System.out.print("Enter your column");
		//
		// int move = sc.nextInt();

		return move;
	}

	public String toString()
	{
		return ("Human player ");
	}

}
