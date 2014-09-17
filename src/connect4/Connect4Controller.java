package connect4;

import connect4.model.Board;
import connect4.model.ComputerPlayer20057155;
import connect4.model.Connect4;
import connect4.model.LocationState;
import connect4.model.Player;
import connect4.model.Location;

import static java.awt.Color.BLUE;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

/**
 * This is a controller class for the JavaFX GUI. The game play of the GUI is
 * determined by the methods in this class.
 * 
 * @author David Kavanagh
 */
public class Connect4Controller implements Initializable
{

	@FXML
	LocationState red = LocationState.RED;

	@FXML
	LocationState yellow = LocationState.YELLOW;

	@FXML
	private Button button1;

	@FXML
	private Button button2;

	@FXML
	private Button button3;

	@FXML
	private Button button4;

	@FXML
	private Button button5;

	@FXML
	private Button button6;

	@FXML
	private Button button7;

	@FXML
	private Text humanWins;

	@FXML
	private Text computerWins;

	@FXML
	private Text draw;

	//
	// COLUMN 0 Buttons
	//
	@FXML
	private Circle col0Row0 = new Circle();

	@FXML
	private Circle col0Row1 = new Circle();

	@FXML
	private Circle col0Row2 = new Circle();

	@FXML
	private Circle col0Row3 = new Circle();

	@FXML
	private Circle col0Row4 = new Circle();

	@FXML
	private Circle col0Row5 = new Circle();

	//
	// COLUMN 1 Buttons
	//
	@FXML
	private Circle col1Row0 = new Circle();

	@FXML
	private Circle col1Row1 = new Circle();

	@FXML
	private Circle col1Row2 = new Circle();

	@FXML
	private Circle col1Row3 = new Circle();

	@FXML
	private Circle col1Row4 = new Circle();

	@FXML
	private Circle col1Row5 = new Circle();

	//
	// COLUMN 2 Buttons
	//
	@FXML
	private Circle col2Row0 = new Circle();

	@FXML
	private Circle col2Row1 = new Circle();

	@FXML
	private Circle col2Row2 = new Circle();

	@FXML
	private Circle col2Row3 = new Circle();

	@FXML
	private Circle col2Row4 = new Circle();

	@FXML
	private Circle col2Row5 = new Circle();

	//
	// COLUMN 3 Buttons
	//
	@FXML
	private Circle col3Row0 = new Circle();

	@FXML
	private Circle col3Row1 = new Circle();

	@FXML
	private Circle col3Row2 = new Circle();

	@FXML
	private Circle col3Row3 = new Circle();

	@FXML
	private Circle col3Row4 = new Circle();

	@FXML
	private Circle col3Row5 = new Circle();

	//
	// COLUMN 4 Buttons
	//
	@FXML
	private Circle col4Row0 = new Circle();

	@FXML
	private Circle col4Row1 = new Circle();

	@FXML
	private Circle col4Row2 = new Circle();

	@FXML
	private Circle col4Row3 = new Circle();

	@FXML
	private Circle col4Row4 = new Circle();

	@FXML
	private Circle col4Row5 = new Circle();

	//
	// COLUMN 5 Buttons
	//
	@FXML
	private Circle col5Row0 = new Circle();

	@FXML
	private Circle col5Row1 = new Circle();

	@FXML
	private Circle col5Row2 = new Circle();

	@FXML
	private Circle col5Row3 = new Circle();

	@FXML
	private Circle col5Row4 = new Circle();

	@FXML
	private Circle col5Row5 = new Circle();

	//
	// COLUMN 6 Buttons
	//
	@FXML
	private Circle col6Row0 = new Circle();

	@FXML
	private Circle col6Row1 = new Circle();

	@FXML
	private Circle col6Row2 = new Circle();

	@FXML
	private Circle col6Row3 = new Circle();

	@FXML
	private Circle col6Row4 = new Circle();

	@FXML
	private Circle col6Row5 = new Circle();

	@FXML
	private Board board = new Board(7, 6);

	@FXML
	private ComputerPlayer20057155 computer = new ComputerPlayer20057155(yellow);

	@FXML
	private Player human = new Player(red);

	@FXML
	private Connect4 connect4 = new Connect4(human, computer, board);

	@FXML
	private Connect4 game;

	private int[] comCoords;

	/**
	 * handleButton1Action() - This method fires when the button 1 is pressed in
	 * the GUI. The button represents a column in the the connect4 game. When it
	 * is pressed the players chip is passed into the column. The computer
	 * player then takes its turn afterwards.
	 * 
	 * @param event
	 */
	@FXML
	private void handleButton1Action(ActionEvent event)
	{
		// passes in the column selected by the user as a parameter and assigns
		// the row returned by the makeMove method to an int
		int row = connect4.makeMove(0);

		// call to the setColour method. It will set the circle at the specified
		// coordinates to the colour of the current player
		setColour(0, row);

		// check to see if there is a winner or a draw
		if (connect4.isWin(board))
		{
			humanWins.setVisible(true);
			humanWins.isVisible();
			return;
		}
		if (connect4.isDraw())
		{
			draw.setVisible(true);
			draw.isVisible();
			return;
		}

		// change player to the computer player
		connect4.nextPlayer();
		
		// take the turn for the computer player
		if (connect4.takeTurn())
		{

			int c = connect4.coords[0];
			int r = connect4.coords[1];

			setColour(c, r);
		}
		else
		{
			if (connect4.takeTurn())
			{
				int c = connect4.coords[0];
				int r = connect4.coords[1];

				setColour(c, r);
			}
			else
			{
				connect4.takeTurn();

				int c = connect4.coords[0];
				int r = connect4.coords[1];

				setColour(c, r);

			}

		}

		// check for the win or the draw
		if (connect4.isWin(board))
		{
			computerWins.setVisible(true);
			computerWins.isVisible();
			return;
		}
		if (connect4.isDraw())
		{
			draw.setVisible(true);
			draw.isVisible();
			return;

		}

		// change back to the human player
		connect4.nextPlayer();
		// end the event
		event.consume();
	}

	/**
	 * handleButton2Action() - This method fires when the button 2 is pressed in
	 * the GUI. The button represents a column in the the connect4 game. When it
	 * is pressed the players chip is passed into the column. The computer
	 * player then takes its turn afterwards.
	 * 
	 * @param event
	 */
	@FXML
	private void handleButton2Action(ActionEvent event)
	{
		int row = connect4.makeMove(1);

		setColour(1, row);

		if (connect4.isWin(board))
		{
			humanWins.setVisible(true);
			humanWins.isVisible();
			return;
		}
		if (connect4.isDraw())
		{
			draw.setVisible(true);
			draw.isVisible();
			return;
		}
		connect4.nextPlayer();
		if (connect4.takeTurn())
		{

			int c = connect4.coords[0];
			int r = connect4.coords[1];

			setColour(c, r);
		}
		else
		{
			if (connect4.takeTurn())
			{
				int c = connect4.coords[0];
				int r = connect4.coords[1];

				setColour(c, r);
			}
			else
			{
				connect4.takeTurn();

				int c = connect4.coords[0];
				int r = connect4.coords[1];

				setColour(c, r);

			}

		}

		if (connect4.isWin(board))
		{
			computerWins.setVisible(true);
			computerWins.isVisible();
			return;
		}
		if (connect4.isDraw())
		{
			draw.setVisible(true);
			draw.isVisible();
			return;
		}

		connect4.nextPlayer();
		event.consume();
	}

	/**
	 * handleButton3Action() - This method fires when the button 3 is pressed in
	 * the GUI. The button represents a column in the the connect4 game. When it
	 * is pressed the players chip is passed into the column. The computer
	 * player then takes its turn afterwards.
	 * 
	 * @param event
	 */
	@FXML
	private void handleButton3Action(ActionEvent event)
	{
		int row = connect4.makeMove(2);

		setColour(2, row);

		if (connect4.isWin(board))
		{
			humanWins.setVisible(true);
			humanWins.isVisible();
			return;
		}
		if (connect4.isDraw())
		{
			draw.setVisible(true);
			draw.isVisible();
			return;
		}

		connect4.nextPlayer();
		if (connect4.takeTurn())
		{

			int c = connect4.coords[0];
			int r = connect4.coords[1];

			setColour(c, r);
		}
		else
		{
			if (connect4.takeTurn())
			{
				int c = connect4.coords[0];
				int r = connect4.coords[1];

				setColour(c, r);
			}
			else
			{
				connect4.takeTurn();

				int c = connect4.coords[0];
				int r = connect4.coords[1];

				setColour(c, r);

			}

		}

		if (connect4.isWin(board))
		{
			computerWins.setVisible(true);
			computerWins.isVisible();
			return;
		}
		if (connect4.isDraw())
		{
			draw.setVisible(true);
			draw.isVisible();
			return;
		}

		connect4.nextPlayer();
		event.consume();
	}

	/**
	 * handleButton4Action() - This method fires when the button 4 is pressed in
	 * the GUI. The button represents a column in the the connect4 game. When it
	 * is pressed the players chip is passed into the column. The computer
	 * player then takes its turn afterwards.
	 * 
	 * @param event
	 */
	@FXML
	private void handleButton4Action(ActionEvent event)
	{
		int row = connect4.makeMove(3);

		setColour(3, row);

		if (connect4.isWin(board))
		{
			humanWins.setVisible(true);
			humanWins.isVisible();
			return;
		}
		if (connect4.isDraw())
		{
			draw.setVisible(true);
			draw.isVisible();
			return;
		}

		connect4.nextPlayer();
		if (connect4.takeTurn())
		{

			int c = connect4.coords[0];
			int r = connect4.coords[1];

			setColour(c, r);
		}
		else
		{
			if (connect4.takeTurn())
			{
				int c = connect4.coords[0];
				int r = connect4.coords[1];

				setColour(c, r);
			}
			else
			{
				connect4.takeTurn();

				int c = connect4.coords[0];
				int r = connect4.coords[1];

				setColour(c, r);

			}

		}

		if (connect4.isWin(board))
		{
			computerWins.setVisible(true);
			computerWins.isVisible();
			return;
		}
		if (connect4.isDraw())
		{
			draw.setVisible(true);
			draw.isVisible();
			return;
		}

		connect4.nextPlayer();
		event.consume();
	}

	/**
	 * handleButton5Action() - This method fires when the button 5 is pressed in
	 * the GUI. The button represents a column in the the connect4 game. When it
	 * is pressed the players chip is passed into the column. The computer
	 * player then takes its turn afterwards.
	 * 
	 * @param event
	 */
	@FXML
	private void handleButton5Action(ActionEvent event)
	{
		int row = connect4.makeMove(4);

		setColour(4, row);

		if (connect4.isWin(board))
		{
			humanWins.setVisible(true);
			humanWins.isVisible();
			return;
		}
		if (connect4.isDraw())
		{
			draw.setVisible(true);
			draw.isVisible();
			return;
		}

		connect4.nextPlayer();
		if (connect4.takeTurn())
		{

			int c = connect4.coords[0];
			int r = connect4.coords[1];

			setColour(c, r);
		}
		else
		{
			if (connect4.takeTurn())
			{
				int c = connect4.coords[0];
				int r = connect4.coords[1];

				setColour(c, r);
			}
			else
			{
				connect4.takeTurn();

				int c = connect4.coords[0];
				int r = connect4.coords[1];

				setColour(c, r);

			}

		}

		if (connect4.isWin(board))
		{
			computerWins.setVisible(true);
			computerWins.isVisible();
		}
		if (connect4.isDraw())
		{
			draw.setVisible(true);
			draw.isVisible();
		}

		connect4.nextPlayer();
		event.consume();
	}

	/**
	 * handleButton6Action() - This method fires when the button 6 is pressed in
	 * the GUI. The button represents a column in the the connect4 game. When it
	 * is pressed the players chip is passed into the column. The computer
	 * player then takes its turn afterwards.
	 * 
	 * @param event
	 */
	@FXML
	private void handleButton6Action(ActionEvent event)
	{
		int row = connect4.makeMove(5);

		setColour(5, row);

		if (connect4.isWin(board))
		{
			humanWins.setVisible(true);
			humanWins.isVisible();
			return;
		}
		if (connect4.isDraw())
		{
			draw.setVisible(true);
			draw.isVisible();
			return;
		}

		connect4.nextPlayer();

		if (connect4.takeTurn())
		{

			int c = connect4.coords[0];
			int r = connect4.coords[1];

			setColour(c, r);
		}
		else
		{
			if (connect4.takeTurn())
			{
				int c = connect4.coords[0];
				int r = connect4.coords[1];

				setColour(c, r);
			}
			else
			{
				connect4.takeTurn();

				int c = connect4.coords[0];
				int r = connect4.coords[1];

				setColour(c, r);

			}

		}

		if (connect4.isWin(board))
		{
			computerWins.setVisible(true);
			computerWins.isVisible();
		}
		if (connect4.isDraw())
		{
			draw.setVisible(true);
			draw.isVisible();
		}

		connect4.nextPlayer();
		event.consume();
	}

	/**
	 * handleButton7Action() - This method fires when the button 7 is pressed in
	 * the GUI. The button represents a column in the the connect4 game. When it
	 * is pressed the players chip is passed into the column. The computer
	 * player then takes its turn afterwards.
	 * 
	 * @param event
	 */
	@FXML
	private void handleButton7Action(ActionEvent event)
	{
		int row = connect4.makeMove(6);

		setColour(6, row);

		if (connect4.isWin(board))
		{
			humanWins.setVisible(true);
			humanWins.isVisible();
			return;
		}
		if (connect4.isDraw())
		{
			draw.setVisible(true);
			draw.isVisible();
			return;
		}

		connect4.nextPlayer();

		if (connect4.takeTurn())
		{

			int c = connect4.coords[0];
			int r = connect4.coords[1];

			setColour(c, r);
		}
		else
		{
			if (connect4.takeTurn())
			{
				int c = connect4.coords[0];
				int r = connect4.coords[1];

				setColour(c, r);
			}
			else
			{
				connect4.takeTurn();

				int c = connect4.coords[0];
				int r = connect4.coords[1];

				setColour(c, r);

			}

		}

		if (connect4.isWin(board))
		{
			computerWins.setVisible(true);
			computerWins.isVisible();
		}
		if (connect4.isDraw())
		{
			draw.setVisible(true);
			draw.isVisible();
		}

		connect4.nextPlayer();
		event.consume();
	}

	/**
	 * setColour() - This method takes in a column and a row as a parameter. It
	 * uses those coordinates to change the colour of the circle object in the
	 * GUI to the colour chip that the current player is using.
	 * 
	 * @param col
	 *            - The column of the circle
	 * 
	 * @param row
	 *            - The row of the circle
	 */
	private void setColour(int col, int row)
	{
		//
		// COLUMN 0
		//
		if (col == 0 && row == 5
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col0Row0.setFill(Color.YELLOW);
		}
		if (col == 0 && row == 5 && connect4.getCurrentPlayer().equals(human))
		{
			col0Row0.setFill(Color.RED);
		}
		if (col == 0 && row == 4
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col0Row1.setFill(Color.YELLOW);
		}
		if (col == 0 && row == 4 && connect4.getCurrentPlayer().equals(human))
		{
			col0Row1.setFill(Color.RED);
		}
		if (col == 0 && row == 3
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col0Row2.setFill(Color.YELLOW);
		}
		if (col == 0 && row == 3 && connect4.getCurrentPlayer().equals(human))
		{
			col0Row2.setFill(Color.RED);
		}
		if (col == 0 && row == 2
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col0Row3.setFill(Color.YELLOW);
		}
		if (col == 0 && row == 2 && connect4.getCurrentPlayer().equals(human))
		{
			col0Row3.setFill(Color.RED);
		}
		if (col == 0 && row == 1
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col0Row4.setFill(Color.YELLOW);
		}
		if (col == 0 && row == 1 && connect4.getCurrentPlayer().equals(human))
		{
			col0Row4.setFill(Color.RED);
		}
		if (col == 0 && row == 0
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col0Row5.setFill(Color.YELLOW);
		}
		if (col == 0 && row == 0 && connect4.getCurrentPlayer().equals(human))
		{
			col0Row5.setFill(Color.RED);
		}
		//
		// COLUMN 1
		//
		if (col == 1 && row == 5
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col1Row0.setFill(Color.YELLOW);
		}
		if (col == 1 && row == 5 && connect4.getCurrentPlayer().equals(human))
		{
			col1Row0.setFill(Color.RED);
		}
		if (col == 1 && row == 4
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col1Row1.setFill(Color.YELLOW);
		}
		if (col == 1 && row == 4 && connect4.getCurrentPlayer().equals(human))
		{
			col1Row1.setFill(Color.RED);
		}
		if (col == 1 && row == 3
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col1Row2.setFill(Color.YELLOW);
		}
		if (col == 1 && row == 3 && connect4.getCurrentPlayer().equals(human))
		{
			col1Row2.setFill(Color.RED);
		}
		if (col == 1 && row == 2
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col1Row3.setFill(Color.YELLOW);
		}
		if (col == 1 && row == 2 && connect4.getCurrentPlayer().equals(human))
		{
			col1Row3.setFill(Color.RED);
		}
		if (col == 1 && row == 1
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col1Row4.setFill(Color.YELLOW);
		}
		if (col == 1 && row == 1 && connect4.getCurrentPlayer().equals(human))
		{
			col1Row4.setFill(Color.RED);
		}
		if (col == 1 && row == 0
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col1Row5.setFill(Color.YELLOW);
		}
		if (col == 1 && row == 0 && connect4.getCurrentPlayer().equals(human))
		{
			col1Row5.setFill(Color.RED);
		}
		//
		// COLUMN 2
		//
		if (col == 2 && row == 5
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col2Row0.setFill(Color.YELLOW);
		}
		if (col == 2 && row == 5 && connect4.getCurrentPlayer().equals(human))
		{
			col2Row0.setFill(Color.RED);
		}
		if (col == 2 && row == 4
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col2Row1.setFill(Color.YELLOW);
		}
		if (col == 2 && row == 4 && connect4.getCurrentPlayer().equals(human))
		{
			col2Row1.setFill(Color.RED);
		}
		if (col == 2 && row == 3
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col2Row2.setFill(Color.YELLOW);
		}
		if (col == 2 && row == 3 && connect4.getCurrentPlayer().equals(human))
		{
			col2Row2.setFill(Color.RED);
		}
		if (col == 2 && row == 2
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col2Row3.setFill(Color.YELLOW);
		}
		if (col == 2 && row == 2 && connect4.getCurrentPlayer().equals(human))
		{
			col2Row3.setFill(Color.RED);
		}
		if (col == 2 && row == 1
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col2Row4.setFill(Color.YELLOW);
		}
		if (col == 2 && row == 1 && connect4.getCurrentPlayer().equals(human))
		{
			col2Row4.setFill(Color.RED);
		}
		if (col == 2 && row == 0
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col2Row5.setFill(Color.YELLOW);
		}
		if (col == 2 && row == 0 && connect4.getCurrentPlayer().equals(human))
		{
			col2Row5.setFill(Color.RED);
		}
		//
		// COLUMN 3
		//
		if (col == 3 && row == 5
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col3Row0.setFill(Color.YELLOW);
		}
		if (col == 3 && row == 5 && connect4.getCurrentPlayer().equals(human))
		{
			col3Row0.setFill(Color.RED);
		}
		if (col == 3 && row == 4
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col3Row1.setFill(Color.YELLOW);
		}
		if (col == 3 && row == 4 && connect4.getCurrentPlayer().equals(human))
		{
			col3Row1.setFill(Color.RED);
		}
		if (col == 3 && row == 3
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col3Row2.setFill(Color.YELLOW);
		}
		if (col == 3 && row == 3 && connect4.getCurrentPlayer().equals(human))
		{
			col3Row2.setFill(Color.RED);
		}
		if (col == 3 && row == 2
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col3Row3.setFill(Color.YELLOW);
		}
		if (col == 3 && row == 2 && connect4.getCurrentPlayer().equals(human))
		{
			col3Row3.setFill(Color.RED);
		}
		if (col == 3 && row == 1
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col3Row4.setFill(Color.YELLOW);
		}
		if (col == 3 && row == 1 && connect4.getCurrentPlayer().equals(human))
		{
			col3Row4.setFill(Color.RED);
		}
		if (col == 3 && row == 0
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col3Row5.setFill(Color.YELLOW);
		}
		if (col == 3 && row == 0 && connect4.getCurrentPlayer().equals(human))
		{
			col3Row5.setFill(Color.RED);
		}
		//
		// COLUMN 4
		//
		if (col == 4 && row == 5
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col4Row0.setFill(Color.YELLOW);
		}
		if (col == 4 && row == 5 && connect4.getCurrentPlayer().equals(human))
		{
			col4Row0.setFill(Color.RED);
		}
		if (col == 4 && row == 4
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col4Row1.setFill(Color.YELLOW);
		}
		if (col == 4 && row == 4 && connect4.getCurrentPlayer().equals(human))
		{
			col4Row1.setFill(Color.RED);
		}
		if (col == 4 && row == 3
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col4Row2.setFill(Color.YELLOW);
		}
		if (col == 4 && row == 3 && connect4.getCurrentPlayer().equals(human))
		{
			col4Row2.setFill(Color.RED);
		}
		if (col == 4 && row == 2
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col4Row3.setFill(Color.YELLOW);
		}
		if (col == 4 && row == 2 && connect4.getCurrentPlayer().equals(human))
		{
			col4Row3.setFill(Color.RED);
		}
		if (col == 4 && row == 1
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col4Row4.setFill(Color.YELLOW);
		}
		if (col == 4 && row == 1 && connect4.getCurrentPlayer().equals(human))
		{
			col4Row4.setFill(Color.RED);
		}
		if (col == 4 && row == 0
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col4Row5.setFill(Color.YELLOW);
		}
		if (col == 4 && row == 0 && connect4.getCurrentPlayer().equals(human))
		{
			col4Row5.setFill(Color.RED);
		}
		//
		// COLUMN 5
		//
		if (col == 5 && row == 5
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col5Row0.setFill(Color.YELLOW);
		}
		if (col == 5 && row == 5 && connect4.getCurrentPlayer().equals(human))
		{
			col5Row0.setFill(Color.RED);
		}
		if (col == 5 && row == 4
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col5Row1.setFill(Color.YELLOW);
		}
		if (col == 5 && row == 4 && connect4.getCurrentPlayer().equals(human))
		{
			col5Row1.setFill(Color.RED);
		}
		if (col == 5 && row == 3
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col5Row2.setFill(Color.YELLOW);
		}
		if (col == 5 && row == 3 && connect4.getCurrentPlayer().equals(human))
		{
			col5Row2.setFill(Color.RED);
		}
		if (col == 5 && row == 2
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col5Row3.setFill(Color.YELLOW);
		}
		if (col == 5 && row == 2 && connect4.getCurrentPlayer().equals(human))
		{
			col5Row3.setFill(Color.RED);
		}
		if (col == 5 && row == 1
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col5Row4.setFill(Color.YELLOW);
		}
		if (col == 5 && row == 1 && connect4.getCurrentPlayer().equals(human))
		{
			col5Row4.setFill(Color.RED);
		}
		if (col == 5 && row == 0
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col5Row5.setFill(Color.YELLOW);
		}
		if (col == 5 && row == 0 && connect4.getCurrentPlayer().equals(human))
		{
			col5Row5.setFill(Color.RED);
		}
		//
		// COLUMN 6
		//
		if (col == 6 && row == 5
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col6Row0.setFill(Color.YELLOW);
		}
		if (col == 6 && row == 5 && connect4.getCurrentPlayer().equals(human))
		{
			col6Row0.setFill(Color.RED);
		}
		if (col == 6 && row == 4
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col6Row1.setFill(Color.YELLOW);
		}
		if (col == 6 && row == 4 && connect4.getCurrentPlayer().equals(human))
		{
			col6Row1.setFill(Color.RED);
		}
		if (col == 6 && row == 3
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col6Row2.setFill(Color.YELLOW);
		}
		if (col == 6 && row == 3 && connect4.getCurrentPlayer().equals(human))
		{
			col6Row2.setFill(Color.RED);
		}
		if (col == 6 && row == 2
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col6Row3.setFill(Color.YELLOW);
		}
		if (col == 6 && row == 2 && connect4.getCurrentPlayer().equals(human))
		{
			col6Row3.setFill(Color.RED);
		}
		if (col == 6 && row == 1
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col6Row4.setFill(Color.YELLOW);
		}
		if (col == 6 && row == 1 && connect4.getCurrentPlayer().equals(human))
		{
			col6Row4.setFill(Color.RED);
		}
		if (col == 6 && row == 0
				&& connect4.getCurrentPlayer().equals(computer))
		{
			col6Row5.setFill(Color.YELLOW);
		}
		if (col == 6 && row == 0 && connect4.getCurrentPlayer().equals(human))
		{
			col6Row5.setFill(Color.RED);
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb)
	{

	}

}
