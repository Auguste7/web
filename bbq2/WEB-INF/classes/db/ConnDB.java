package db;

import java.sql.*;

public class ConnDB {
	private static String USER = "root";
    private static String PASSWORD = "byddlydd";
	private static String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static String REMOTE_IP = "139.159.216.249";
    private static String DB_URL = "jdbc:mysql://" + REMOTE_IP + "/bbq"+ "?autoReconnect=true&useUnicode=true"
			+ "&characterEncoding=UTF-8&useSSL=false";

    private static Connection connection = null;
    
   
    public static Connection getConnection(){
    	try{
    		Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
    	}catch (Exception e) {
            System.out.println("database connection error");
            e.printStackTrace();
        }
    	 return connection;
    }
    
    public static void closeConnection(Connection con){
    	if(con!=null){
    		try {
                connection.close(); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
    	}
    }
}

