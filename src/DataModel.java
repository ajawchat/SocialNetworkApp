// This is the model class for access to the database

import java.io.IOException;
import java.io.Reader;
import java.net.UnknownHostException;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.logging.Logger;

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
import com.mongodb.WriteResult;

public class DataModel {
	
	// declarre the variables
	private MongoClient mongoClient;
	private DB dbConn;
	private DBCollection collection;
	private DBCursor cursor;
	private JSONParser jsonParser;
	private JSONObject jsonObject;
	
	static Logger log;
	
	//==================================================================================================================
	// Constructor initializes the DB connections
	public DataModel(){
		initiateDBConnection();
		log = Logger.getLogger("Data Model");
	}

	//==================================================================================================================
	// This function is used to initiate connection to the DB 
	private void initiateDBConnection() {
		try {
			mongoClient = new MongoClient("localhost",27017);
						
		} catch (UnknownHostException e) {
			log.info("Error in database connection");
		}
		dbConn = mongoClient.getDB("SocialNetworkAppDB");
		log.info("Database connection initialized correctly");
		
	}
	
	//==================================================================================================================
	// This function terminates the DB connection once the application is terminated
	public void terminateDBConn(){
		mongoClient.close();
		log.info("Database connection terminated");
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
		// Get the WriteResult into a variable
		WriteResult userDataWrite = collection.insert(document);
		
		// Add the user to the Credentials DB
		collection = dbConn.getCollection("Credentials");
		BasicDBObject addCredentials = new BasicDBObject();
		addCredentials.put("UserName", userName);
		addCredentials.put("Password", password);
		// Get the WriteResult into a variable
		WriteResult credentialsWrite = collection.insert(addCredentials);
				
		long userdetails = 0;
		long credentialdetails = 0;
		
		// Parse the json received as acknowlegement from the server
		jsonParser = new JSONParser();
		try {
			jsonObject = (JSONObject) jsonParser.parse(userDataWrite.toString());
			userdetails = (long) jsonObject.get("ok");
			System.out.println(userdetails);
			
			jsonObject = (JSONObject) jsonParser.parse(credentialsWrite.toString());
			credentialdetails = (long) jsonObject.get("ok");
			
		} catch (ParseException e) { e.printStackTrace(); }
		
		// if both are 1, then it is successful
		if(userdetails == 1 && credentialdetails==1)
			return true;
		else
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
		DBObject query = new BasicDBObject("UserName",userName);
		
		 
		jsonParser = new JSONParser();
		jsonObject = (JSONObject) jsonParser.parse(collection.findOne(query).toString());
		String retrievedPassword = (String) jsonObject.get("Password");
		
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
