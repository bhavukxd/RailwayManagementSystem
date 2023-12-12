import java.sql.*;

public class Database {
    
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/javaproject";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Bhavuk@2005";
    Statement stml = null;
    Connection c = null;
    public Database(){
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
         c = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        c.setAutoCommit(false);
        stml = c.createStatement();
      } 
      catch ( Exception e ) {
        c= null;
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(1);
      }
    }
        public boolean addUser(String email,String password){
        try{
            String query = String.format("Insert into users1 values('%s','%s');",email,password);
            stml.executeUpdate(query);
            c.commit();
            System.out.println("User Added");
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public String[] getUser(String email){
        try{
            String query = String.format("Select * from users1 where username='%s';",email);
            ResultSet res = stml.executeQuery(query);
            String[] user = new String[2];
            while (res.next()) {                
                user[0] = res.getString(1);
                user[1] = res.getString(2);
            }
            return user;
        }catch(Exception e){
            return null;
        }
    }
   
    public void closeConnection(){
        try{
            stml.close();
            c.commit();
            c.close();
        }catch(Exception e){
            System.out.println(e);
            System.exit(0);
        }
    } 
    }