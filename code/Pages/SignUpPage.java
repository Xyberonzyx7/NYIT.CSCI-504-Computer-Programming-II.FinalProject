package Pages;

import User.*;
import java.util.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.*;

public class SignUpPage extends Page {

	// declare variables
	private List<UserInfo> users;

	// new components
	private JPanel panel = new JPanel();
	private JLabel lb_welcome = new JLabel("<html>Welcome to SBA<br>The Simple Bank Application </html>");
	private ImageIcon icon = new ImageIcon("../assets/images/sign_up.jpg");
	private JLabel label = new JLabel(icon);
	private JLabel lb_userName = new JLabel("User Name");
	private JLabel lb_password = new JLabel("Password");
	private JLabel lb_confirmPassword = new JLabel("Confirm Password");
	private JTextField tf_userName = new JTextField("");
	private JPasswordField tf_password = new JPasswordField("");
	private JPasswordField tf_confirmPassword = new JPasswordField("");
	private JLabel lb_warning = new JLabel("");
	private JButton btn_cancel = new JButton("Cancel");
	private JButton btn_create = new JButton("Create Account");

	public SignUpPage(){

		// init variables
		loadUserInfo();

		// init UI
		initUI();
	}

	@Override
	public void showPage(String username){

		// clear UI
		clearUI();

		// no need to use user
		super.showPage(username);
	}

	@Override
	public String hidePage(){
		
		// no need to update user
		super.hidePage();

		// no sign-in active user
		return "";
	}

	private void clearUI(){
		tf_userName.setText("");
		tf_password.setText("");
		tf_confirmPassword.setText("");
		lb_warning.setText("");
	}

	private void loadUserInfo(){
		UserInfoReader reader = new UserInfoReader();
		users = reader.read();
	}

	private void saveUserInfo(List<UserInfo> userList){
		UserInfoWriter writer = new UserInfoWriter();
		writer.write(userList);
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

		icon = new ImageIcon("../assets/images/sign_up.jpg");
		JLabel label = new JLabel(icon);
		label.setBounds(0, 0, 640, this.getHeight());

		lb_userName = new JLabel("User Name");
		lb_userName.setFont(lb_userName.getFont().deriveFont(Font.BOLD, 22));
		lb_userName.setBounds(700, 70, 530, 60);

		lb_password = new JLabel("Password");
		lb_password.setFont(lb_password.getFont().deriveFont(Font.BOLD, 22));
		lb_password.setBounds(700, 200, 530, 60);

		lb_confirmPassword = new JLabel("Confirm Password");
		lb_confirmPassword.setFont(lb_confirmPassword.getFont().deriveFont(Font.BOLD, 22));
		lb_confirmPassword.setBounds(700, 330, 530, 60);

		tf_userName = new JTextField("");
		tf_userName.setFont(tf_userName.getFont().deriveFont(Font.BOLD, 22));
		tf_userName.setBounds(700, 130, 530, 60);

		tf_password = new JPasswordField("");
		tf_password.setFont(tf_password.getFont().deriveFont(Font.BOLD, 22));
		tf_password.setBounds(700, 260, 530, 60);

		tf_confirmPassword = new JPasswordField("");
		tf_confirmPassword.setFont(tf_confirmPassword.getFont().deriveFont(Font.BOLD, 22));
		tf_confirmPassword.setBounds(700, 380, 530, 60);

		lb_warning = new JLabel("");
		lb_warning.setFont(lb_warning.getFont().deriveFont(Font.BOLD, 22));
		lb_warning.setBounds(700, 520, 530, 60);
		lb_warning.setForeground(Color.RED);
		lb_warning.setHorizontalAlignment(SwingConstants.CENTER);

		btn_cancel = new JButton("Cancel");
		btn_cancel.setFont(btn_cancel.getFont().deriveFont(Font.BOLD, 22));
		btn_cancel.setBounds(700, 460, 530, 60);
		btn_cancel.setFocusPainted(false);
		btn_cancel.setBorderPainted(false);
		btn_cancel.setBackground(Color.ORANGE);
		btn_cancel.setForeground(Color.WHITE);
		btn_cancel.addActionListener(new ActionListener(){

			// cancel action
			@Override
			public void actionPerformed(ActionEvent e){

				// close this page and move on to another page
				triggerPageClosed("SignInPage");
			}
		});

		btn_create = new JButton("Create Account");
		btn_create.setFont(btn_create.getFont().deriveFont(Font.BOLD, 22));
		btn_create.setBounds(700, 600, 530, 60);
		btn_create.setFocusPainted(false);
		btn_create.setBorderPainted(false);
		btn_create.setBackground(Color.ORANGE);
		btn_create.setForeground(Color.WHITE);
		btn_create.addActionListener(new ActionListener(){

			// sign up action
			@Override
			public void actionPerformed(ActionEvent e){

				// check if the username is valid
				for(UserInfo user: users){
					if(user.getUserName().equals(tf_userName.getText())){
						lb_warning.setForeground(Color.RED);
						lb_warning.setText("Username has already exists!");
						return;
					}
				}

				if(tf_userName.getText().equals("")){
					lb_warning.setForeground(Color.RED);
					lb_warning.setText("Username is not valid!");
					return;
				}

				// check if the password is valid
				if(tf_password.getText().equals(tf_confirmPassword.getText()) == false){
						lb_warning.setForeground(Color.RED);
						lb_warning.setText("Passwords are not the same!");
						return;
				}

				if(tf_password.getText().equals("")){
					lb_warning.setForeground(Color.RED);
					lb_warning.setText("Password is not valid!");
					return;
				}

				// add new user
				Random random = new Random();
				UserInfo user = new UserInfo();
				user.setUserName(tf_userName.getText());
				user.setPassword(tf_password.getText());
				user.setBalance((long) random.nextInt(10001) + 10000);
				user.setLoan(0L);

				// save this new user to database
				users.add(user);
				saveUserInfo(users);

				// close this page and move on to another page
				lb_warning.setForeground(Color.GREEN);
				lb_warning.setText("Your account has been created!");

				triggerPageClosed("SignInPage");
				return;
			}
		});

		// Add components
		panel.add(lb_welcome);
		panel.add(label, BorderLayout.WEST);
		panel.add(lb_userName);
		panel.add(lb_password);
		panel.add(tf_userName);
		panel.add(tf_password);
		panel.add(lb_warning);
		panel.add(lb_confirmPassword);
		panel.add(tf_confirmPassword);
		panel.add(btn_cancel);
		panel.add(btn_create);

		this.add(panel);
	}
}

