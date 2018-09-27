//must have sqljdbc jar downloaded, classpath set etc https://technet.microsoft.com/en-us/library/ms378526(v=sql.105).aspx
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class SQLHelper {
    private static Statement statement;
    private Connection conn = null;
    private static HashMap<String,String> m;

    void connect(String db_connect_string, String db_user, String db_password){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(db_connect_string, db_user, db_password);
            statement = conn.createStatement();
            System.out.println("connected");
        } catch (Exception e){
            e.printStackTrace(); //handle
        }
    }

    void close(){
        try {
            conn.close();
            System.out.println("connection closed");
        } catch (Exception e){
            e.printStackTrace(); //handle
        }
    }

    void select(String query){
        m = new HashMap<>();
        try {
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                m.put(rs.getString(1),rs.getString(2));
            }
        } catch (Exception e){
            e.printStackTrace();//handle
        }
    }

    static ArrayList genericSel(String query){
        ArrayList results = new ArrayList<>();

        try {
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                ArrayList<String> currentRow = new ArrayList<>();
                for(int i=1; i<rs.getMetaData().getColumnCount()+1; i++) {
                    currentRow.add(rs.getString(i).trim());
                }
                results.add(currentRow);
            }
        } catch (Exception e){
            e.printStackTrace();//handle
        }
        return results;
    }



    static void insert(String query) {
        try {
            int noAffected = statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //https://stackoverflow.com/questions/2860943/how-can-i-hash-a-password-in-java
    void loginRequest(String user, String pass){
        //select * from users where Username = user.
    }

     static Map getQueryResult() {
         return m;
     }
}
