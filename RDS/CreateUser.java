import java.sql.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

class CreateUser{  
	public static void main(String args[]) throws SQLException{  

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("testuser");
		dataSource.setPassword("testuser1");
		dataSource.setServerName("testdbinstance.ctau9v0c7d2t.us-east-1.rds.amazonaws.com");
		
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		
		// Data taken from GUI
		String 	givenUsername 		= "mark23";
		String 	givenPassword 		= "markpass";
		String 	givenEmail 			= "markemail";
		Boolean usernameAvailable 	= false;
		
		//SQL statement, username returned if matching username+password
		String sql = "SELECT `Users`.`Username`"
				+ " FROM `FeedMeFeedYou`.`Users`"
				+ " WHERE `Users`.`Username` = '" + givenUsername + "';";

		//Results
		ResultSet rs = stmt.executeQuery(sql);
		   
	   //if we have results
		if(rs.next()){
			//System.out.println("Exists");
			usernameAvailable = false;
		}
		
		//No results, username not taken
		else{
			//System.out.println("Doesn't Exist");
			usernameAvailable = true;
		}
		   
	   
	   if(!usernameAvailable){
		   //Tell user it's taken
	   }
	   
	   if(usernameAvailable){
		   //Make insert sql statement
		   String sql2 = "INSERT INTO `FeedMeFeedYou`.`Users` (`Username`, `Password`, `Email`) VALUES (\"" + givenUsername + "\", \"" 
				   + givenPassword + "\", \"" + givenEmail + "\");";
		   
		   System.out.println(sql2);
		   //Query
		   int rs2 = stmt.executeUpdate(sql2);
		   if(rs2 == 0){
			   System.out.println("didn't work");
		   }
		   if(rs2 == 1){
			   //System.out.println("L58 worked");
			   //Query sucessfull, username created
		   }

	   }
		   
		rs.close();
		stmt.close();
		conn.close();
		
	}
	
}