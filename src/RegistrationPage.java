import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RegistrationPage extends JFrame implements ActionListener {
	
	private Controller controller;
	private String registrationStatus;
	
	
	public void setController(Controller controller){
		//System.out.println("controller initiated");
		this.controller = controller;
	}
	
	
	// Constructor
	public RegistrationPage(){
		
	}
	
	
	public void loadRegistrationPage(){
		registrationPage();
	}
	
	
	public void registrationPage(){
		JPanel panel = new JPanel();
		this.add(panel);
		
		createLayout(panel);
		
		setTitle("registration page");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	// Creates the UI components and adds them to the panel
	private void createLayout(JPanel panel) {
		panel.setLayout(null);
		
		// Name details
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(10, 10, 80, 25);
		panel.add(nameLabel);
		
		final JTextField nameText = new JTextField(20);
		nameText.setBounds(100, 10, 160, 25);
		panel.add(nameText);
		
		
		// City
		JLabel cityLabel = new JLabel("City: ");
		cityLabel.setBounds(10, 35, 80, 25);
		panel.add(cityLabel);
		final JTextField cityText = new JTextField(20);
		cityText.setBounds(100, 35, 160, 25);
		panel.add(cityText);
		
		
		// State
		JLabel stateLabel = new JLabel("State: ");
		stateLabel.setBounds(10, 60, 80, 25);
		panel.add(stateLabel);
		
		final JTextField stateText = new JTextField(20);
		stateText.setBounds(100, 60, 160, 25);
		panel.add(stateText);
		
		
		// UserName details
		JLabel userNameLabel = new JLabel("UserName: ");
		userNameLabel.setBounds(10, 85, 80, 25);
		panel.add(userNameLabel);
		
		final JTextField userNameText = new JTextField(20);
		userNameText.setBounds(100, 85, 160, 25);
		panel.add(userNameText);
		
		// Password details
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(10, 110, 80, 25);
		panel.add(passwordLabel);
		
		final JTextField passwordText = new JTextField(20);
		passwordText.setBounds(100, 110, 160, 25);
		panel.add(passwordText);
		
		
		final JLabel statusLabel = new JLabel("");
		statusLabel.setBounds(10, 180, 180, 35);
		panel.add(statusLabel);
		
		
		
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(50, 150, 100, 25);
		panel.add(registerButton);
		
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrationStatus = controller.registerNewUser(nameText.getText(), cityText.getText(), stateText.getText(), userNameText.getText(), passwordText.getText());
				
				if(!registrationStatus.equals("")){
					statusLabel.setText(registrationStatus);
				}
			}
		});
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	


	/*
	public static void main(String[] args) {
		RegistrationPage registration = new RegistrationPage();
		registration.setVisible(true);

	}*/

	
	

}
