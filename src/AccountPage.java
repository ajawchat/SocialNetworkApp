import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

// This page is loaded when the authentication is successful. It loads the data from the MongoDB server

public class AccountPage extends JFrame{
	
	// Users account
	private String userName;
	
	// Declare the elements we have to print as global variables. 
	// Retrieve them from the DB and load the data into the corresponding labels
	String nametext;
	String citytext;
	
	
	
	public void loadAccountPage(String userName){
		
		this.userName = userName;
		
		JPanel panel = new JPanel();
		this.add(panel);
		
		createLayout(panel);
		
		setTitle("Account page");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void createLayout(JPanel panel) {
		panel.setLayout(null);
		
		System.out.println("inside create layout 1");
		retrieveData();
		System.out.println("inside create layout 2");
		
		// Name details
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(10, 10, 80, 25);
		panel.add(nameLabel);
		
		JLabel nameText = new JLabel(nametext);
		nameText.setBounds(100, 10, 160, 25);
		panel.add(nameText);
		
		
		JLabel cityLabel = new JLabel("City: ");
		cityLabel.setBounds(10, 40, 80, 25);
		panel.add(cityLabel);
		
		JLabel cityText = new JLabel(citytext);
		cityText.setBounds(160, 40, 160, 25);
		panel.add(cityText);
		
		
	}
	
	// =================================================================================
	// This function loads the data into the variables and displays them
	private void retrieveData() {
		MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient("localhost",27017);
		} catch (UnknownHostException e) {
			System.out.println("Cannot load data temporarily...");
		}
		DB dbConn = mongoClient.getDB("SocialNetworkAppDB");
		
		DBCollection collection = dbConn.getCollection("UserProfiles");
		DBObject query = new BasicDBObject("UserName",userName);
		
		System.out.println("inside retreive method"); 
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) jsonParser.parse(collection.findOne(query).toString());
			System.out.println("Read json");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Assign the retrieved values to the global variables
		nametext = (String) jsonObject.get("Name");
		citytext = (String) jsonObject.get("City");
		
		
	}

	
	// =================================================================================
	/*
	public static void main(String[] a){
		AccountPage newAccount = new AccountPage();
		newAccount.loadAccountPage("ajawchat");
	}
	*/
	
	
}
