// This is the model class for access to the database

import java.io.IOException;
import java.io.Reader;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DataModel {
	
	// declarre the variables
	private MongoClient mongoClient;
	private DB dbConn;
	private DBCollection collection;
	private DBCursor cursor;
	private JSONParser jsonParser;
	private JSONObject jsonObject;
	
	// This takes the credentials as string and returns True or False based on whether it authenticated or not
	public boolean authenticateCredentials(String userName, String password) throws UnknownHostException, ParseException{
		
		mongoClient = new MongoClient("localhost",27017);
		dbConn = mongoClient.getDB("SocialNetworkAppDB");
		collection = dbConn.getCollection("Credentials");
		DBObject query = new BasicDBObject("uname",userName);
		
		 
		jsonParser = new JSONParser();
		jsonObject = (JSONObject) jsonParser.parse(collection.findOne(query).toString());
		String retrievedPassword = (String) jsonObject.get("password");
		mongoClient.close();
		
		
		if(retrievedPassword.equals(password)){
			// authentication succeeded
			return true;
		}
		else
			// authentication failed
			return false;
	}
	
	
	/*public static void main(String[] args) throws ParseException, IOException{
		
		
		
		DataModel model = new DataModel();
		System.out.println(model.authenticateCredentials("ajinkya", "123"));
		
		
	}*/

}
