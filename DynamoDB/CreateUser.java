import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
public class CreateUser {

	 public static void main(String[] args) throws Exception {

		 	//AWS server
	        AmazonDynamoDBClient client = new AmazonDynamoDBClient()
	        	.withEndpoint("https://dynamodb.us-east-1.amazonaws.com");
	        DynamoDB dynamoDB = new DynamoDB(client);

	        //Table name
	        Table table = dynamoDB.getTable("users");

	        //User data taken from GUI, sample for now.
	        String username = "markk";
	        String email	= "email@email.com";
	        String password = "abc";

	        //Verify account doesn't already exist
	        Item outcome = null;
	        GetItemSpec spec = new GetItemSpec()
	                .withPrimaryKey("username", username);
	        	
	        	//Get data associated with username if any
	            try {
	                outcome = table.getItem(spec);

	            } catch (Exception e) {
	                
	            }
	            
	            //Username doesn't exist already
	            if( outcome == null){
	    	        //Try to place new username into DB
	    	        final Map<String, Object> infoMap = new HashMap<String, Object>();
	    	        infoMap.put("email",  email);
	    	        infoMap.put("password",  password);

	    	        try {
	    	            PutItemOutcome outcome2 = table.putItem(new Item()
	    	                .withPrimaryKey("username", username)
	    	                .withMap("info", infoMap));
	    	            System.out.println("Username added.");

	    	        } catch (Exception e) {
	    	            
	    	        }
	            	
	            }
	            
	            // Username exists already
	            else{
	            	System.out.println("Username exists.");
	            }


	    }
	 
}
