package Pages;

import User.*;
import java.util.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.*;

public class SignInPage extends Page {

	// declare variables
	private List<UserInfo> users;
	private String currentUserName;

	private JPanel panel = new JPanel();
	private JLabel lb_welcome = new JLabel("<html>Welcome to SBA<br>The Simple Bank Application </html>");
	private ImageIcon icon = new ImageIcon("../assets/images/sign_in.jpg");
	private JLabel label = new JLabel(icon);
	private JLabel lb_userName = new JLabel("User Name");
	private JLabel lb_password = new JLabel("Password");
	private JTextField tf_userName = new JTextField("");
	private JPasswordField tf_password = new JPasswordField("");
	private JLabel lb_warning = new JLabel("");
	private JButton btn_signIn = new JButton("Sign In");
	private JLabel lb_or = new JLabel("or");
	private JButton btn_signUp = new JButton("Sign Up");
		
	public SignInPage(){

		// init UI
		initUI();
	}

	@Override
	public void showPage(String currentUserName){

		this.currentUserName = currentUserName;

		// clear UI
		clearUI();

		// init variables
		loadUserInfo();

		// show page
		super.showPage(currentUserName);
	}

	@Override
	public String hidePage(){

		super.hidePage();

		return currentUserName;
	}

	private void clearUI(){
		tf_userName.setText("");
		tf_password.setText("");
		lb_warning.setText("");
	}

	private void loadUserInfo(){
		UserInfoReader reader = new UserInfoReader();
		users = reader.read();
	}

	private void initUI(){
		
		// set frame properties
		this.setTitle("Simple Bank Application");
		this.setSize(1280, 720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null); // to let component.setBounds take effect

		// new components
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0,0,this.getWidth(), this.getHeight());
		panel.setBackground(Color.WHITE);

		lb_welcome = new JLabel("<html>Welcome to SBA<br>The Simple Bank Application </html>");
		lb_welcome.setFont(lb_welcome.getFont().deriveFont(Font.BOLD, 22));
		lb_welcome.setBounds(50, 250, 600, 120);
		lb_welcome.setForeground(Color.WHITE);

		icon = new ImageIcon("../assets/images/sign_in.jpg");
		JLabel label = new JLabel(icon);
		label.setBounds(0, 0, 640, this.getHeight());

		lb_userName = new JLabel("User Name");
		lb_userName.setFont(lb_userName.getFont().deriveFont(Font.BOLD, 22));
		lb_userName.setBounds(700, 70, 530, 60);

		lb_password = new JLabel("Password");
		lb_password.setFont(lb_password.getFont().deriveFont(Font.BOLD, 22));
		lb_password.setBounds(700, 200, 530, 60);

		tf_userName = new JTextField("");
		tf_userName.setFont(tf_userName.getFont().deriveFont(Font.BOLD, 22));
		tf_userName.setBounds(700, 130, 530, 60);

		tf_password = new JPasswordField("");
		tf_password.setFont(tf_password.getFont().deriveFont(Font.BOLD, 22));
		tf_password.setBounds(700, 260, 530, 60);

		lb_warning = new JLabel("");
		lb_warning.setFont(lb_warning.getFont().deriveFont(Font.BOLD, 22));
		lb_warning.setBounds(700, 360, 530, 60);
		lb_warning.setForeground(Color.RED);
		lb_warning.setHorizontalAlignment(SwingConstants.CENTER);

		btn_signIn = new JButton("Sign In");
		btn_signIn.setFont(btn_signIn.getFont().deriveFont(Font.BOLD, 22));
		btn_signIn.setBounds(700, 480, 530, 60);
		btn_signIn.setFocusPainted(false);
		btn_signIn.setBorderPainted(false);
		btn_signIn.setBackground(Color.ORANGE);
		btn_signIn.setForeground(Color.WHITE);
		btn_signIn.addActionListener(new ActionListener(){

			// sign in action
			@Override
			public void actionPerformed(ActionEvent e){

				// check if the user is valid
				if(tf_userName.getText() == null || tf_password.getText() == null){
					lb_warning.setForeground(Color.RED);
					lb_warning.setText("Invalid username or password!");
					return;
				}

				for(UserInfo user : users){
					if(user.getUserName().equals(tf_userName.getText()) && 
					   user.getPassword().equals(tf_password.getText())){
							lb_warning.setForeground(Color.GREEN);
							lb_warning.setText("Sign in successed!");

							// get current user's name
							currentUserName = user.getUserName();

							// close this page and move on to another page
							triggerPageClosed("OverviewPage");
							return;
					   }
				}

				lb_warning.setForeground(Color.RED);
				lb_warning.setText("Invalid username or password!");
			}
		});

		lb_or = new JLabel("or");
		lb_or.setFont(lb_or.getFont().deriveFont(Font.BOLD, 22));
		lb_or.setBounds(700, 540, 530, 60);
		lb_or.setHorizontalAlignment(SwingConstants.CENTER);

		btn_signUp = new JButton("Sign Up");
		btn_signUp.setFont(btn_signUp.getFont().deriveFont(Font.BOLD, 22));
		btn_signUp.setBounds(700, 600, 530, 60);
		btn_signUp.setFocusPainted(false);
		btn_signUp.setBorderPainted(false);
		btn_signUp.setBackground(Color.ORANGE);
		btn_signUp.setForeground(Color.WHITE);
		btn_signUp.addActionListener(new ActionListener(){

			// sign up action
			@Override
			public void actionPerformed(ActionEvent e){
				
				// close this page and move on to another page
				triggerPageClosed("SignUpPage");
				return;
			}
		});

		// Add components
		panel.add(lb_welcome);
		panel.add(label, BorderLayout.WEST);
		panel.add(lb_userName);
		panel.add(lb_password);
		panel.add(tf_userName);
		panel.add(lb_warning);
		panel.add(btn_signIn);
		panel.add(tf_password);
		panel.add(lb_or);
		panel.add(btn_signUp);

		this.add(panel);
	}
}
