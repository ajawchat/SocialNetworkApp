import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.GroupLayout.Alignment.CENTER;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Login extends JFrame {
	
	
	public Login(){
		loginPage();
	}
	
	private void loginPage(){
		
		
		
		JButton submitButton = new JButton("Submit");
		submitButton.setToolTipText("Submit Credentials");
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button Pressed");
			}
		});
		
		
		// Initialize the layout
		createLayout(submitButton);
		
		
		setTitle("My Social Network App");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	private void createLayout(JComponent...arg) {
		
		Container mainPanel = getContentPane();
		GroupLayout groupLayout = new GroupLayout(mainPanel);
		
		mainPanel.setLayout(groupLayout);
		groupLayout.setAutoCreateGaps(true);
		
		// A new label to display messages
		JLabel welcomeLabel = new JLabel("Welcome...Please enter your details");
		welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		welcomeLabel.setForeground(new Color(50, 50, 25));
		
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
	                .addComponent(welcomeLabel)
	                .addComponent(arg[0])
	        );

		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(CENTER)
	                .addComponent(welcomeLabel)
	                .addComponent(arg[0])
	        );
		
		
		
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Login login = new Login();
				login.setVisible(true);
			}
		});

	}

}
