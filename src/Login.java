import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import static javax.swing.GroupLayout.Alignment.CENTER;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.simple.parser.ParseException;



public class Login extends JFrame implements ActionListener {
	
	private Controller controller;
	
	
	public Login(){
		loginPage();
	}
	
	// Set the controller object to reference for authentication
	public void setController(Controller controller){
		this.controller = controller;
	}
	
	// Creates and initializes the panel for the login page
	private void loginPage(){
		
		JPanel panel = new JPanel();
		this.add(panel);
		
		createLayout(panel);
		
		setTitle("My Social Network App");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	
	private void createLayout(JPanel panel) {
		
		panel.setLayout(null);
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);
				
		final JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		final JTextField passwordText = new JTextField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);

		JButton loginButton = new JButton("Submit");
		loginButton.setBounds(10, 80, 80, 25);
		panel.add(loginButton);
		
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(180, 80, 80, 25);
		panel.add(registerButton);
		
		
		registerButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				controller.openRegistrationPage();
			}
		});
		
		 
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = userText.getText();
				String password = passwordText.getText();
				System.out.println("Pressed loginButton");
				
				try {
					controller.authenticateCredentials(userName, password);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		
		
	}

	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
