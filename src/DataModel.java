// This is the model class for access to the database

import java.io.IOException;
import java.io.Reader;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
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
	
	//==================================================================================================================
	// Constructor initializes the DB connections
	public DataModel(){
		initiateDBConnection();
	}

	//==================================================================================================================
	// This function is used to initiate connection to the DB 
	private void initiateDBConnection() {
		try {
			mongoClient = new MongoClient("localhost",27017);
						
		} catch (UnknownHostException e) {
			System.out.println("Issue with Database connection...");
		}
		dbConn = mongoClient.getDB("SocialNetworkAppDB");
		System.out.println("Database connection initialized correctly");
	}
	
	//==================================================================================================================
	// This function terminates the DB connection once the application is terminated
	public void terminateDBConn(){
		mongoClient.close();
		System.out.println("Database connection terminated");
	}
	
	//==================================================================================================================
	
	// Function to add the details of a new user. It returns true if the insert was successful, else it returns false
	public boolean addNewUser(String name, String city, String state, String userName, String password){
		
		// Add the user to the UserProfiles DB
		collection = dbConn.getCollection("UserProfiles");
		BasicDBObject document = new BasicDBObject();
		document.put("Name", name);
		document.put("City", city);
		document.put("State", state);
		document.put("UserName", userName);
		document.put("Password", password);
		collection.insert(document);
		
		// Add the user to the Credentials DB
		collection = dbConn.getCollection("Credentials");
		BasicDBObject addCredentials = new BasicDBObject();
		addCredentials.put("UserName", userName);
		addCredentials.put("Password", password);
		collection.insert(addCredentials);
		
		return false;
	}
	
	
	//==================================================================================================================
	
	// This function authenticates if a particular userName already exists or not while creating a new user
	public boolean userNameExists(String userName){
		boolean exists = false;
		
		collection = dbConn.getCollection("Credentials");
		DBObject query = new BasicDBObject("UserName",userName);
		jsonParser = new JSONParser();
		try {
			DBObject dbObj = collection.findOne(query);
			if(dbObj != null){
				jsonObject = (JSONObject) jsonParser.parse(collection.findOne(query).toString());
				exists = true;
			}
			
		} 
		catch (ParseException e){ 
			System.out.println("Error with parsing result json file...");
		}
		
		return exists;
	}
	
	
	//==================================================================================================================
	
	// This takes the credentials as string and returns True or False based on whether it authenticated or not
	public boolean authenticateCredentials(String userName, String password) throws UnknownHostException, ParseException{
		
		collection = dbConn.getCollection("Credentials");
		DBObject query = new BasicDBObject("uname",userName);
		
		 
		jsonParser = new JSONParser();
		jsonObject = (JSONObject) jsonParser.parse(collection.findOne(query).toString());
		String retrievedPassword = (String) jsonObject.get("password");
		
		if(retrievedPassword.equals(password)){
			// authentication succeeded
			return true;
		}
		else
			// authentication failed
			return false;
	}
	
	//==================================================================================================================
	/*public static void main(String[] args) throws ParseException, IOException{
		
		DataModel model = new DataModel();
		//System.out.println(model.authenticateCredentials("ajinkya", "123"));
		
	}*/

}
