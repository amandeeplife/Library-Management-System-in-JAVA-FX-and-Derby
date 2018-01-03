package assistant.addBook;

import assistant.database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    databaseHandler = new DatabaseHandler();
    }

    @FXML TextField booktilte = new TextField();
    @FXML TextField bookid = new TextField();
    @FXML TextField bookauthor = new TextField();
    @FXML TextField bookpublisher = new TextField();
    @FXML Button saveButton = new Button();
    @FXML Button    cancelButton = new Button();
    DatabaseHandler databaseHandler;


}
