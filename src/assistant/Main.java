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
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        conn = DriverManager.getConnection("jdbc:mysql://localhost/lms","root","root");
        statement=conn.createStatement();
    databaseHandler =new DatabaseHandler();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
