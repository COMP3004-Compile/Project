import java.sql.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

class Login{  
	public static void main(String args[]) throws SQLException{  

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("testuser");
		dataSource.setPassword("testuser1");
		dataSource.setServerName("testdbinstance.ctau9v0c7d2t.us-east-1.rds.amazonaws.com");
		
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		
		// Data taken from GUI
		String 	givenUsername 		= "mark";
		String 	givenPassword 		= "markpass";
		String 	givenEmail 			= "markemail";
		Boolean loggedIn 			= false;
		String 	loggedInUsername 	= "";
		
		//SQL statement, username returned if matching username+password
		String sql = "SELECT `Users`.`Username`"
				+ " FROM `FeedMeFeedYou`.`Users`"
				+ " WHERE `Users`.`Username` = '" + givenUsername + "'"
				+ " AND `Users`.`Password` = '" + givenPassword + "';";

		//Results
		ResultSet rs = stmt.executeQuery(sql);
		   
	   //Get results
	    while (rs.next()) {
		   
		   //Get first column, will be username if results, or empty if no results
		    String columnValue = rs.getString(1);
		   
		   //Correct username + password
		   if(columnValue.equals(givenUsername)){
			   loggedIn = true;
			   loggedInUsername = columnValue;
		    }
		   
		   //Incorrect username + password
		   else{
			   loggedIn = false;
			   loggedInUsername = "";
		    }
	    }
		rs.close();
		stmt.close();
		conn.close();
		
	}
	
}