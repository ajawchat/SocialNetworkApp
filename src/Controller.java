import java.net.UnknownHostException;

import org.json.simple.parser.ParseException;

// This is the controller class for the MVC architecture

public class Controller {
	
	private Login loginPage;
	private DataModel model;
	
	// Constructor
	public Controller(){
		
	}
	
	
	public void setModel(DataModel model){
		this.model = model;
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
		
		loginPage.setController(controller);
		controller.setModel(model);
		controller.displayLoginPage(loginPage);
		
		
	}
	
	

}
