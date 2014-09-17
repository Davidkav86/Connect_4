package connect4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * @author David Kavanagh
 */
public class Connect4 extends Application
{

	/**
	 * The main() method is ignored in correctly deployed JavaFX application.
	 * main() serves only as fallback in case the application can not be
	 * launched through deployment artifacts, e.g., in IDEs with limited FX
	 * support.
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args)
	{
		Application.launch(Connect4.class, (java.lang.String[]) null);
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception
	{

		Parent root = FXMLLoader.load(getClass().getResource("Connect4.fxml"));

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.show();
	}

}
