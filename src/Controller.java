// This is the controller class for the MVC architecture

public class Controller {
	
	Login loginPage;
	
	// Constructor
	public Controller(){
		
	}
	
	
	
	// This is the point of entry
	public void displayLoginPage(Login loginPage){
		this.loginPage = loginPage;
		loginPage.setVisible(true);
	}
	
	
	public void authenticateCredentials(String userName, String password){
		// connect to Model to retrieve from DB
		
		// This is temporary logic
		if(userName.equals("ajawchat") && password.equals("abc")){
			// Authenticated correctly
			System.out.println("Correct Credentials");
		}
		else{
			// Display error message
			System.out.println("Incorrect");
		}
	}
	
	
	
	// Main method of controller to execute the program
	public static void main(String[] args){
		Controller controller = new Controller();
		Login loginPage = new Login();
		
		loginPage.setController(controller);
		controller.displayLoginPage(loginPage);
		
	}
	
	

}
