import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import net.spy.memcached.MemcachedClient;

import org.json.simple.parser.ParseException;


// This is the controller class for the MVC architecture

public class Controller {
	
	private Login loginPage;
	private DataModel model;
	private RegistrationPage register;
	private AccountPage newAccount;
	private MemcacheClass cache;
	
	static Logger log;
	
	//==================================================================================================================
	// Constructor
	public Controller(){
		log = Logger.getLogger("Controller logging");
	}
	
	//==================================================================================================================
	
	public void setRegistrationPage(RegistrationPage register){
		this.register = register;
	}
	
	//==================================================================================================================
	
	public String registerNewUser(String name, String city, String state, String userName, String password){
		// Check if username already exists
		boolean exists = model.userNameExists(userName);
		
		// Return the results to the controller
		if(exists == true){
			// report username already exists
			return "UserName already exists";
		}
		else{
			// add the new user with his details
			System.out.println("In else part");
			boolean addStatus = model.addNewUser(name, city, state, userName, password);
			
			// If the data addition is successful, redirect the user to his profile page
			if(addStatus == true){
				System.out.println("true");
				
				// Once the data is added successfully into the DB, add it to the cache as well for better lookup
				cache.addDataToCache(userName, password);
				
				newAccount.loadAccountPage(userName);
				newAccount.setVisible(true);
				register.setVisible(false);
			}
			else
				System.out.println("Something went wrong with addition of user data");
			
			
			return "User Profile Added";
			
		}
	}
	
	//==================================================================================================================
	
	public void setModel(DataModel model){
		this.model = model;
	}
	
	//==================================================================================================================
	
	public void setAccountPage(AccountPage newAccount){
		this.newAccount = newAccount;
	}
	
	//==================================================================================================================
	
	public void setCache(MemcacheClass cache){
		this.cache = cache;
	}

	//==================================================================================================================
	// Opens up the registration page when clicked from the login page
	public void openRegistrationPage(){
		System.out.println("Opening registration page soon");
		register.loadRegistrationPage();
		register.setVisible(true);
		
		// Stop running the login page
		loginPage.setVisible(false);
		
	}
	
	//==================================================================================================================
	
	// This is the point of entry
	public void displayLoginPage(Login loginPage){
		this.loginPage = loginPage;
		loginPage.setVisible(true);
	}
	
	//==================================================================================================================
	public void authenticateCredentials(String userName, String password) throws UnknownHostException, ParseException{
		// connect to Model to retrieve from DB
		long startTime = System.currentTimeMillis();
		boolean authStatus = model.authenticateCredentials(userName, password);
		long endTime = System.currentTimeMillis();	
		System.out.println("DB access for credentials took: "+(endTime - startTime));
		
		
		// Accessing the cache
		long startTimeC = System.currentTimeMillis();
		String receivedPassword = cache.getDataFromCache(userName);
		long endTimeC = System.currentTimeMillis();
		System.out.println("Cache access for credentials took: "+(endTimeC - startTimeC));
		
		if(authStatus == true){
			newAccount.loadAccountPage(userName);
			newAccount.setVisible(true);
			loginPage.setVisible(false);
		}
		else{
			System.out.println("Incorrect");
		}
	}
	
	
	//==================================================================================================================
	// Main method of controller to execute the program
	public static void main(String[] args){
		
		DataModel model = new DataModel();
		
		Controller controller = new Controller();
		
		Login loginPage = new Login();
		loginPage.setController(controller);
		
		
		RegistrationPage register = new RegistrationPage();
		register.setController(controller);
		
		
		MemcacheClass cache = new MemcacheClass();
		controller.setCache(cache);
		
		
		AccountPage newAccount = new AccountPage();
		
		controller.setAccountPage(newAccount);
		controller.setModel(model);
		controller.displayLoginPage(loginPage);
		controller.setRegistrationPage(register);
		
		
		System.out.println("Done with execution");
		
	}
	
	

}
