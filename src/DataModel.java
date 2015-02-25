// This is the model class for access to the database

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class DataModel {
	
	
	// This takes the credentials as string and returns True or False based on whether it authenticated or not
	public boolean authenticateCredentials(String userName, String password){
		return false;
	}
	
	
	public static void main(String[] args) throws UnknownHostException{
		String uname = "ajawchat";
		String pass = "123";
		
		MongoClient mongoClient = new MongoClient("localhost",27017);
		DB db = mongoClient.getDB("SocialNetworkAppDB");
		
		DBCollection collection = db.getCollection("Credentials");
		
		BasicDBObject query = new BasicDBObject("uname", "ajawchat");

		DBCursor cursor = collection.find(query);
		try {
		   while(cursor.hasNext()) {
		       System.out.println(cursor.next());
		   }
		} finally {
		   cursor.close();
		}
		
		
		// Inserting a document
		BasicDBObject doc = new BasicDBObject("uname", "pathak")
        		.append("password", "567");
		collection.insert(doc);
		
		
		// parsing the document as a json object
		JsonParserFactory factory=JsonParserFactory.getInstance();
		JSONParser parser=factory.newJsonParser();
		Map jsonMap=parser.parseJson(jsonString);
		
		
		
		
		
		mongoClient.close();
		
	}

}
