package connect4.model;

/**
 * 
 * Abstract class to represent a player in a Connect 4 game. Extend this to
 * create your player. Dependent on LocationState and Board types
 * 
 * @author Frank
 * 
 */
public abstract class IPlayer
{

	private LocationState playerState;

	public IPlayer(LocationState playerState)
	{
		super();
		this.playerState = playerState;
	}

	/**
	 * This method should return the next move for a Connect 4 game. Assume
	 * columns go from 1 to 7. Move computed from board parameter using suitable
	 * algorithm.
	 * 
	 * @param board
	 *            - Connect 4 board as type Board
	 * @return column number for next turn as integer.
	 */
	public abstract int getMove(Board board);

	/**
	 * This method returns the location state (i.e. colour) associated with the
	 * player.
	 * 
	 * @return playerState - colour of players piece as LocationState.
	 */
	public LocationState getPlayerState()
	{
		return playerState;
	}

}