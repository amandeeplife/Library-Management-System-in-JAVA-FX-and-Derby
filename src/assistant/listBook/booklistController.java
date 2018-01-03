package assistant.listBook;

import assistant.database.DatabaseHandler;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.print.Book;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class booklistController implements Initializable{
    ObservableList<Book> lst= FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      initCol();
      loadData();
     }

    private void loadData() {
        DatabaseHandler handler = new DatabaseHandler();
        String qu = "SELECT * FROM BOOK";
        ResultSet resultSet = handler.excuteQuery(qu);
        try{
            while(resultSet.next()){
                String id = resultSet.getString("ID");
                String tit = resultSet.getString("title");
                String author = resultSet.getString("author");
                String pub = resultSet.getString("publisher");
                Boolean avail = resultSet.getBoolean("isAvail");
                if(id!=null){
                Book bk = new Book(tit,id,author,pub,avail);
                lst.add(bk);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initCol() {
        titleClmn.setCellValueFactory(new PropertyValueFactory<>("title"));
        idClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        publisherClmn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        authorClmn.setCellValueFactory(new PropertyValueFactory<>("author"));
        availClmn.setCellValueFactory(new PropertyValueFactory<>("avail"));
        table.setItems(lst);


    }

    @FXML    TableView<Book> table;
    @FXML TableColumn<Book,String> titleClmn;
    @FXML TableColumn<Book,String> idClmn;
    @FXML TableColumn<Book,String> publisherClmn;
    @FXML TableColumn<Book,String> authorClmn;
    @FXML TableColumn<Book,Boolean> availClmn;


public class Book{
    private SimpleStringProperty title;
    private SimpleStringProperty id;
    private SimpleStringProperty author;
    private SimpleStringProperty publisher;




    public String getTitle() {
        return title.get();
    }
    public String getId() {
        return id.get();
    }
    public String getAuthor() {
        return author.get();
    }

    public String getPublisher() {
        return publisher.get();
    }

    public boolean isAvail() {
        return avail.get();
    }


    private SimpleBooleanProperty avail;
    Book(String title,String id,String author,String pub, Boolean avail){
        this.title=new SimpleStringProperty(title);
        this.id=new SimpleStringProperty(id);
        this.publisher=new SimpleStringProperty(pub);
        this.author=new SimpleStringProperty(author);
        this.avail=new SimpleBooleanProperty(avail);


    }


}

}
