import java.net.UnknownHostException;

import org.json.simple.parser.ParseException;

// This is the controller class for the MVC architecture

public class Controller {
	
	private Login loginPage;
	private DataModel model;
	private RegistrationPage register;
	
	// Constructor
	public Controller(){
		
	}
	
	
	
	public void setRegistrationPage(RegistrationPage register){
		this.register = register;
	}
	
	
	
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
			model.addNewUser(name, city, state, userName, password);
		}
		
		return "";
	}
	
	
	public void setModel(DataModel model){
		this.model = model;
	}
	
	
	// Opens up the registration page when clicked from the login page
	public void openRegistrationPage(){
		System.out.println("Opening registration page soon");
		register.loadRegistrationPage();
		register.setVisible(true);
		
		// Stop running the login page
		loginPage.setVisible(false);
		
	}
	
	
	
	// This is the point of entry
	public void displayLoginPage(Login loginPage){
		this.loginPage = loginPage;
		loginPage.setVisible(true);
	}
	
	
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
	
	
	
	// Main method of controller to execute the program
	public static void main(String[] args){
		
		DataModel model = new DataModel();
		
		Controller controller = new Controller();
		Login loginPage = new Login();
		RegistrationPage register = new RegistrationPage();
		register.setController(controller);
		
		loginPage.setController(controller);
		controller.setModel(model);
		controller.displayLoginPage(loginPage);
		controller.setRegistrationPage(register);
		
		
	}
	
	

}
