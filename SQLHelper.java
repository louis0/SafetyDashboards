//must have sqljdbc jar downloaded, classpath set etc https://technet.microsoft.com/en-us/library/ms378526(v=sql.105).aspx
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

class SQLHelper {
    private Statement statement;
    private Connection conn = null;
    static HashMap<String,String> m;
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
        m = new HashMap();
        try {
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                m.put(rs.getString(1),rs.getString(2));
            }
        } catch (Exception e){
            e.printStackTrace();//handle
        }
    }

     static Map getQueryResult() {
         return m;
     }
}
