package assistant.database;

import java.sql.*;

public class DatabaseHandler {
    private static DatabaseHandler handler;
    private static final String DB_URL="jdbc:derby:database;create=true";
    private static Connection conn=null;
    private static Statement stmt = null;
    public DatabaseHandler(){
        createConnection();
        setupBookTable();
    }
    void createConnection(){
        try{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        conn= DriverManager.getConnection(DB_URL);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    void setupBookTable(){
        String TABLE_NAME="BOOK";
        try{
            stmt = conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null,null,TABLE_NAME.toUpperCase(),null);

            if(tables.next()){
                System.out.print("Table " +TABLE_NAME+" Exists lets go!");
            }
            else {
                stmt.execute("CREATE TABLE "+TABLE_NAME+"(ID VARCHAR(200) PRIMARY KEY,title varchar(200),author varchar(200),publisher varchar(100),intcode varchar(100),isAvail boolean default true)");
           System.out.print(" Database Created!");
            }


        }catch (Exception e){

        }
    }
}
