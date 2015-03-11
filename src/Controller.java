import java.net.UnknownHostException;

import org.json.simple.parser.ParseException;

// This is the controller class for the MVC architecture

public class Controller {
	
	private Login loginPage;
	private DataModel model;
	private RegistrationPage register;
	private AccountPage newAccount;
	
	//==================================================================================================================
	// Constructor
	public Controller(){
		
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
				newAccount.loadAccountPage();
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
		
		boolean authStatus = model.authenticateCredentials(userName, password);
		
		if(authStatus == true){
			System.out.println("Correct Credentials");
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
		
		
		AccountPage newAccount = new AccountPage();
		
		controller.setAccountPage(newAccount);
		controller.setModel(model);
		controller.displayLoginPage(loginPage);
		controller.setRegistrationPage(register);
		
		
		
		
	}
	
	

}
