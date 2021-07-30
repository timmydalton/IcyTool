package icyGUI;

import database.Controller;
import database.DatabaseInfo;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LogInGUI extends JFrame {

	Controller controller = new Controller();
	private JTextField textPass;
	private JTextField textUser;
	
	LogInGUI(){
		//
		//Khoi tao UI
		
		//
		super("IcyTool");
		this.setSize(480, 640);
		getContentPane().setLayout(null);
		
		//Tao button LogIn
		JButton btnLogIn = new JButton("Log In");
		getContentPane().add(btnLogIn);
		btnLogIn.setBounds(176, 450, 111, 38);
		
		//Tao textfield
		textPass = new JTextField();
		textPass.setBounds(136, 377, 279, 30);
		getContentPane().add(textPass);
		textPass.setColumns(10);
		
		textUser = new JTextField();
		textUser.setColumns(10);
		textUser.setBounds(136, 325, 279, 30);
		getContentPane().add(textUser);
		
		//Tao label cho textfield
		JLabel lblPass = new JLabel("Password:");
		lblPass.setFont(new Font("Arial", Font.BOLD, 16));
		lblPass.setBounds(30, 377, 93, 30);
		getContentPane().add(lblPass);
		
		JLabel lblUser = new JLabel("User name:");
		lblUser.setFont(new Font("Arial", Font.BOLD, 16));
		lblUser.setBounds(30, 325, 93, 30);
		getContentPane().add(lblUser);
		
		//
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\rsc\\icon.jpg"));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//Xu ly cac event
		
		//Event cho button LogIn
		btnLogIn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					DatabaseInfo.dbUser = textUser.getText();
					DatabaseInfo.dbPass = textPass.getText();
					Connection connection = controller.getConnect();
					JOptionPane.showMessageDialog(LogInGUI.this, "Log in successful");
					LogInGUI.this.setVisible(false);
					new MainGUI();
				} catch (Exception er) {
					JOptionPane.showMessageDialog(LogInGUI.this, "Log in failed, wrong username or password");
				}
			}
		});
		
		//Event enter tai textPass -> dang nhap
		textPass.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						DatabaseInfo.dbUser = textUser.getText();
						DatabaseInfo.dbPass = textPass.getText();
						Connection connection = controller.getConnect();
						JOptionPane.showMessageDialog(LogInGUI.this, "Log in successful");
						LogInGUI.this.setVisible(false);
						new MainGUI();
					} catch (Exception er) {
						JOptionPane.showMessageDialog(LogInGUI.this, "Log in failed, wrong username or password");
					}
				}
			}
		});
		
		//Event enter tai textUser -> focus toi textPass
		textUser.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textPass.requestFocus();
				}
			}
		});

	}
	
	public static void main(String[] args) {
		new LogInGUI();
	}
}
