package Pages;

import User.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.*;

public class OverviewPage extends Page {

	// declare variables
	private UserInfo currentUser;
	private List<UserInfo> users;

	private JPanel panel;
	private ImageIcon logo;
	private JLabel lb_logo;
	private JLabel lb_hello;
	private JPanel pan_tab;
	private JLabel lb_name;
	private JLabel lb_welcomeMsg;
	private LocalDate date;
	private JLabel lb_date;
	private JButton btn_signOut;
	private JButton btn_overview;
	private JButton btn_transfer;
	private JButton btn_loan;
	private ImageIcon img_top;
	private ImageIcon img_bottom;
	private JLabel lb_img_top;
	private JLabel lb_img_bottom;
	private JLabel lb_overview;
	private JLabel lb_accountBalance;
	private JLabel lb_balance;
	private JLabel lb_accountLoan;
	private JLabel lb_loan;
	private JLabel lb_bankInfo;
	private JLabel lb_info;

	public OverviewPage(){

		// init UI
		initUI();
	}

	@Override
	public void showPage(String currentUserName){

		// get all users
		loadUserInfo();

		// get current user's object pointer in the users list
		for(UserInfo user : users){
			if(user.getUserName().equals(currentUserName)){
				currentUser = user;
				break;
			}
		}

		// updateUI
		updateUI();

		// show page
		super.showPage(currentUserName);
	}

	@Override
	public String hidePage(){

		// save all users' data
		saveUserInfo();

		// hide page
		super.hidePage();

		// return current user's name
		return currentUser.getUserName();
	}

	private void loadUserInfo(){
		UserInfoReader reader = new UserInfoReader();
		users = reader.read();
	}

	private void saveUserInfo(){
		UserInfoWriter writer = new UserInfoWriter();
		writer.write(users);
	}

	// update UI component that will changed based on user
	private void updateUI(){
		lb_name.setText(currentUser.getUserName());
		lb_balance.setText("$" + currentUser.getBalance());
		lb_loan.setText("- $" + currentUser.getLoan());
	}

	// init UI component that will not changed based on user
	private void initUI(){

		// set frame properties
		this.setTitle("Simple Bank Application");
		this.setSize(1280, 720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);

		// new components
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, this.getWidth(), this.getHeight());
		panel.setBackground(Color.WHITE);

		logo = new ImageIcon("../assets/images/logo.png");
		lb_logo = new JLabel(logo);
		lb_logo.setBounds(0, 5, 250, 50);

		lb_hello = new JLabel("Hello, ");
		lb_hello.setFont(lb_hello.getFont().deriveFont(Font.BOLD, 22));
		lb_hello.setBounds(30, 160, 100, 50);
		lb_hello.setForeground(Color.WHITE);

		lb_name = new JLabel("");
		lb_name.setFont(lb_name.getFont().deriveFont(Font.BOLD, 22));
		lb_name.setBounds(110, 160, 200, 50);
		lb_name.setForeground(Color.WHITE);

		lb_welcomeMsg = new JLabel("Welcome to SBA");
		lb_welcomeMsg.setFont(lb_welcomeMsg.getFont().deriveFont(Font.BOLD, 22));
		lb_welcomeMsg.setBounds(30, 210, 400, 50);
		lb_welcomeMsg.setForeground(Color.WHITE);

		pan_tab = new JPanel();
		pan_tab.setBounds(0, 70, this.getWidth(), 70);
		pan_tab.setBackground(Color.ORANGE);

		date = LocalDate.now();
		int day = date.getDayOfMonth();
		int month = date.getMonthValue();
		int year = date.getYear();
		lb_date = new JLabel(
				Integer.toString(month) + " / " + 
				Integer.toString(day) + " / " + 
				Integer.toString(year)
			);
		lb_date.setFont(lb_date.getFont().deriveFont(Font.BOLD, 22));
		lb_date.setBounds(1050, 80, 200, 50);

		btn_signOut = new JButton("Sign Out");
		btn_signOut.setFont(btn_signOut.getFont().deriveFont(Font.BOLD, 22));
		btn_signOut.setBounds(1050, 10, 180, 50);
		btn_signOut.setFocusPainted(false);
		btn_signOut.setBorderPainted(false);
		btn_signOut.setBackground(Color.ORANGE);
		btn_signOut.setForeground(Color.WHITE);
		btn_signOut.addActionListener(new ActionListener(){

			// go to overview
			@Override
			public void actionPerformed(ActionEvent e){

				// close this page and move on to another page
				triggerPageClosed("SignInPage");
				return;
			}
		});
		
		btn_overview = new JButton("Overview");
		btn_overview.setFont(btn_overview.getFont().deriveFont(Font.BOLD, 22));
		btn_overview.setBounds(0, 80, 180, 50);
		btn_overview.setFocusPainted(false);
		btn_overview.setBorderPainted(false);
		btn_overview.setBackground(Color.ORANGE);
		btn_overview.setForeground(Color.WHITE);
		btn_overview.addActionListener(new ActionListener(){

			// go to overview
			@Override
			public void actionPerformed(ActionEvent e){

				// we're already in the overview page, do nothing
			}
		});

		btn_transfer = new JButton("Transfers");
		btn_transfer.setFont(btn_transfer.getFont().deriveFont(Font.BOLD, 22));
		btn_transfer.setBounds(180, 80, 180, 50);
		btn_transfer.setFocusPainted(false);
		btn_transfer.setBorderPainted(false);
		btn_transfer.setBackground(Color.ORANGE);
		btn_transfer.setForeground(Color.WHITE);
		btn_transfer.addActionListener(new ActionListener(){

			// go to transfer
			@Override
			public void actionPerformed(ActionEvent e){

				// close this page and go to transfer
				triggerPageClosed("TransferPage");
				return;
			}
		});

		btn_loan = new JButton("Loan");
		btn_loan.setFont(btn_loan.getFont().deriveFont(Font.BOLD, 22));
		btn_loan.setBounds(360, 80, 180, 50);
		btn_loan.setFocusPainted(false);
		btn_loan.setBorderPainted(false);
		btn_loan.setBackground(Color.ORANGE);
		btn_loan.setForeground(Color.WHITE);
		btn_loan.addActionListener(new ActionListener(){

			// go to loan
			@Override
			public void actionPerformed(ActionEvent e){

				// close this page and go to transfer
				triggerPageClosed("LoanPage");
				return;
			}
		});

		img_top = new ImageIcon("../assets/images/overview_top.jpg");
		lb_img_top = new JLabel(img_top);
		lb_img_top.setBounds(0, 140, this.getWidth(), 140);

		lb_overview = new JLabel("Overview");
		lb_overview.setFont(lb_overview.getFont().deriveFont(Font.BOLD, 30));
		lb_overview.setBounds(30, 290, this.getWidth() / 2, 50);

		lb_accountBalance = new JLabel("Account Balance");
		lb_accountBalance.setFont(lb_accountBalance.getFont().deriveFont(Font.BOLD, 22));
		lb_accountBalance.setBounds(100, 340, this.getWidth() / 2, 50);

		lb_balance = new JLabel("");
		lb_balance.setFont(lb_balance.getFont().deriveFont(Font.BOLD, 22));
		lb_balance.setBounds(this.getWidth() / 2, 340, this.getWidth() / 2 - 100, 50);
		lb_balance.setHorizontalAlignment(SwingConstants.RIGHT);

		lb_accountLoan = new JLabel("Account Loan");
		lb_accountLoan.setFont(lb_accountLoan.getFont().deriveFont(Font.BOLD, 22));
		lb_accountLoan.setBounds(100, 390, this.getWidth() / 2, 50);

		lb_loan = new JLabel("");
		lb_loan.setFont(lb_loan.getFont().deriveFont(Font.BOLD, 22));
		lb_loan.setBounds(this.getWidth() / 2, 390, this.getWidth() / 2 - 100, 50);
		lb_loan.setForeground(Color.RED);
		lb_loan.setHorizontalAlignment(SwingConstants.RIGHT);

		lb_bankInfo = new JLabel("Bank Information");
		lb_bankInfo.setFont(lb_bankInfo.getFont().deriveFont(Font.BOLD, 30));
		lb_bankInfo.setBounds(30, 470, this.getWidth() - 30, 50);

		lb_info = new JLabel("Due to recession, a 0.0% interest rate is being applied to all saving accounts.");
		lb_info.setFont(lb_info.getFont().deriveFont(Font.BOLD, 22));
		lb_info.setBounds(100, 520, this.getWidth() - 100, 50);

		img_bottom = new ImageIcon("../assets/images/overview_bottom.jpg");
		lb_img_bottom = new JLabel(img_bottom);
		lb_img_bottom.setBounds(0, 580, this.getWidth(), 140);

		// add components
		panel.add(lb_date);
		panel.add(lb_logo);
		panel.add(lb_hello);
		panel.add(lb_name);
		panel.add(lb_welcomeMsg);
		panel.add(lb_img_top);
		panel.add(lb_img_bottom);
		panel.add(lb_overview);
		panel.add(lb_accountBalance);
		panel.add(lb_balance);
		panel.add(lb_accountLoan);
		panel.add(lb_loan);
		panel.add(btn_signOut);
		panel.add(btn_overview);
		panel.add(btn_transfer);
		panel.add(btn_loan);
		panel.add(pan_tab);
		panel.add(lb_bankInfo);
		panel.add(lb_info);
		

		this.add(panel);
	}
}
