package assistant.addBook;

import assistant.Main;
import assistant.database.DatabaseHandler;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AddBookController implements Initializable{
        @Override
    public void initialize(URL location, ResourceBundle resources) {
    databaseHandler = new DatabaseHandler();
    checkData();
    }
        Main main;
    private void checkData() {
        String qu = "SELECT * FROM BOOK";
        ResultSet rs =databaseHandler.excuteQuery(qu);
        try{
        while(rs.next()){
            String ttl = rs.getString("title");
            String pub = rs.getString("publisher");
            System.out.println("Title :"+ttl+ " and publisher is :"+pub);
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML TextField booktilte = new TextField();
    @FXML TextField bookid = new TextField();
    @FXML TextField bookauthor = new TextField();
    @FXML TextField bookpublisher = new TextField();
    @FXML Button saveButton = new Button();
    @FXML Button    cancelButton = new Button();
    DatabaseHandler databaseHandler;
    @FXML public void cancel(){
    main.window.close();
    }
    @FXML
    private void addBook(){
        Boolean chk =true;

        String id = bookid.getText();
        String author = bookauthor.getText();
        String publisher = bookpublisher.getText();
        String title = booktilte.getText();

        chk= databaseHandler.checkifExists(id);
        System.out.print("Value of boolean "+chk);
        if(chk ){
            Alert alert = new Alert( Alert.AlertType.WARNING);
            alert.setContentText("Value already Exists");
            alert.showAndWait();
            return;

        }

        if(id.isEmpty()||author.isEmpty()||publisher.isEmpty()||title.isEmpty()){
            Alert alert  = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Enter all fields");
            alert.showAndWait();
        }

        else {
            String query = "INSERT into BOOK Values('" + id + "','" + title + "','" + author + "','" + publisher + "','12','true')";
           if(databaseHandler.execAction(query)){
                Alert alert  = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Success!");
               checkData();

            }
            else {
               Alert alert  = new Alert(Alert.AlertType.INFORMATION);
               alert.setContentText("Failed!");

           }
        }
    }



}
