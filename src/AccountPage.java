import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// This page is loaded when the authentication is successful. It loads the data from the MongoDB server

public class AccountPage extends JFrame{
	
	
	public void loadAccountPage(){
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
		
		// Name details
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(10, 10, 80, 25);
		panel.add(nameLabel);
		
		JLabel nameText = new JLabel();
		nameText.setBounds(100, 10, 160, 25);
		panel.add(nameText);
		
		
		JLabel cityLabel = new JLabel("City: ");
		cityLabel.setBounds(10, 40, 80, 25);
		panel.add(cityLabel);
		
		JLabel cityText = new JLabel();
		cityText.setBounds(160, 40, 160, 25);
		panel.add(cityText);
		
		
	}
	
	
	public static void main(String[] a){
		AccountPage newAccount = new AccountPage();
		newAccount.loadAccountPage();
	}
	
	
	
}
