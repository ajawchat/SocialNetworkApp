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
import javax.swing.JTextField;



public class Login extends JFrame implements ActionListener {
	
	
	public Login(){
		loginPage();
	}
	
	private void loginPage(){
		
		
		
		JButton submitButton = new JButton("Submit");
		submitButton.setToolTipText("Submit Credentials");
		
		
		JTextField uname = new JTextField(20);
		
		
		
		JTextField password = new JTextField(40);
		
		
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button Pressed");
			}
		});
		
		
		// Initialize the layout
		createLayout(submitButton, uname, password);
		
		
		setTitle("My Social Network App");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	private void createLayout(JComponent...arg) {
		
		Container mainPanel = getContentPane();
		GroupLayout groupLayout = new GroupLayout(mainPanel);
		
		mainPanel.setLayout(groupLayout);
		groupLayout.setAutoCreateContainerGaps(true);
		
		// A new label to display messages
		JLabel welcomeLabel = new JLabel("Welcome...Please enter your details");
		welcomeLabel.setSize(50, 20);
		welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		welcomeLabel.setForeground(new Color(50, 50, 25));
		
		
		JLabel welcomeLabel1 = new JLabel("Welcome...Please enter your details");
		welcomeLabel1.setSize(50, 20);
		welcomeLabel1.setFont(new Font("Serif", Font.PLAIN, 14));
		welcomeLabel1.setForeground(new Color(50, 50, 25));
		
		JLabel welcomeLabel2 = new JLabel("Welcome...Please enter your details");
		welcomeLabel2.setSize(50, 20);
		welcomeLabel2.setFont(new Font("Serif", Font.PLAIN, 14));
		welcomeLabel2.setForeground(new Color(50, 50, 25));
		
		
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(welcomeLabel)
                .addComponent(arg[1])
                .addComponent(arg[2])
                .addComponent(arg[0])
	        );

		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
	                .addComponent(welcomeLabel)
	                .addComponent(arg[1])
	                .addComponent(arg[2])
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
