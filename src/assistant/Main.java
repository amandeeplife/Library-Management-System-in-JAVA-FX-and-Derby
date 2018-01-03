package assistant;

import assistant.database.DatabaseHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main extends Application {
    Connection conn;
    Statement statement;
    DatabaseHandler databaseHandler;
   public static Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("listBook/bookList.fxml"));
        window = primaryStage;
        window.setTitle("Hello World");
        window.setScene(new Scene(root, 600, 475));
        window.show();

        conn = DriverManager.getConnection("jdbc:mysql://localhost/lms","root","root");
        statement=conn.createStatement();
    databaseHandler =new DatabaseHandler();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
