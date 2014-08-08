package tools;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class ConnDB {	
		public Connection conn=null;
		public Statement stmt=null;
		public ResultSet rs=null;
		private static String propFileName= "connDB.properties";
		private static Properties prop=new Properties();
		private static String dbClassName="com.mysql.jdbc.Driver";
		private static String dbUrl="jdbc:mysql://localhost/diaryweb?user=root&password=null&useUnicode=true";
		public ConnDB() throws Exception{
			InputStream in = getClass().getResourceAsStream(propFileName);
			prop.load(in);
			dbClassName=prop.getProperty("DB_CLASS_NAME");
			dbUrl=prop.getProperty("DB_URL",dbUrl);
		}
		public static Connection getConnection(){
			Connection conn =null;
			try {
				Class.forName(dbClassName);
				 conn=DriverManager.getConnection(dbUrl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		    if(conn==null){
		    	System.err.println("警告： DbConnectonManager.getConnection()获得数据库连接失败。\n");
		   
		    }
			return conn;
		}
		public ResultSet executeQuery(String sql) throws Exception{
			conn = getConnection();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery(sql);
			if(rs==null){
				System.err.print("query err!");
			}	
			return rs;
		}
		public int executeUpdate(String sql) throws SQLException {
			int result=0;
			conn = getConnection();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			result=stmt.executeUpdate(sql);
			return result;
		}
		public void close() throws SQLException{
			if(rs!=null){
				rs.close();
			}
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		}
}
