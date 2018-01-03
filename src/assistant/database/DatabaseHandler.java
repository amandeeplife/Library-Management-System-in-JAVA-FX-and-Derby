package assistant.database;

import javax.swing.*;
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
                stmt.execute("CREATE TABLE "+TABLE_NAME+"(ID VARCHAR(200) PRIMARY KEY" +
                        ",title varchar(200)," +
                        "author varchar(200)," +
                        "publisher varchar(100)," +
                        "intcode varchar(100)," +
                        "isAvail boolean default true)");
           System.out.print(" Database Created!");
            }


        }catch (Exception e){

        }
    }

    public ResultSet excuteQuery(String query){
        ResultSet resultSet;
        try{
        stmt = conn.createStatement();
        resultSet=stmt.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    return resultSet;
    }
    public boolean execAction(String qu){
        try{
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error"+e.getMessage(),"Error Occured",JOptionPane.ERROR_MESSAGE);
            System.out.print("Exceptuon at exeQuery:dataHandler "+e.getLocalizedMessage());
            return false;
        }
    }
    public boolean checkifExists(String id){
        ResultSet resultSet;
        String query ="SELECT * From BOOK WHERE id = '"+id+"'";
        try{
            stmt = conn.createStatement();
            resultSet=stmt.executeQuery(query);
                  if(resultSet.next()!=false){
             return  true;
                    }
                    else {
            System.out.print("not Exist");
            return  false;
        }
        }catch (Exception e){
        e.printStackTrace();
    return false;
        }

    }


}
