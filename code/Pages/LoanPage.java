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

public class LoanPage extends Page {

	// declare variables
	private UserInfo currentUser;
	private List<UserInfo> users;
	
	private long loanLimit = 1000L;

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
	private JLabel lb_loan;
	private JLabel lb_titleBalance;
	private JLabel lb_currentBalance;
	private JLabel lb_titleLoan;
	private JLabel lb_currentLoan;
	private JLabel lb_status;
	private JLabel lb_amount;
	private JTextField tf_amount;
	private JButton btn_loanAmount;
	private JLabel lb_img_top;
	private JLabel lb_img_bottom;


	public LoanPage(){

		// init UI
		initUI();
	}

	@Override
	public void showPage(String currentUserName){

		// get all users
		loadUserInfo();

		// get current's pointer object in the users list
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

	private boolean loanMoney(long amount){

		// check loan validity
		if(currentUser.getLoan() + amount > loanLimit){
			return false;
		}

		// add loan amount to current user
		currentUser.setLoan(currentUser.getLoan() + amount);

		// add money to current user
		currentUser.setBalance(currentUser.getBalance() + amount);

		return true;
	}

	// update UI component that will changed based on user
	private void updateUI(){
		lb_name.setText(currentUser.getUserName());
		tf_amount.setText("");
		updateBalance();
		updateLoan();
	}

	private void updateBalance(){
		lb_currentBalance.setText("$" + Long.toString(currentUser.getBalance()));
	}

	private void updateLoan(){
		lb_currentLoan.setText("- $" + Long.toString(currentUser.getLoan()));
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

				// close this page and go to transfer
				triggerPageClosed("OverviewPage");
				return;
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

				// we're already in the overview page, do nothing
			}
		});

		img_top = new ImageIcon("../assets/images/overview_top.jpg");
		lb_img_top = new JLabel(img_top);
		lb_img_top.setBounds(0, 140, this.getWidth(), 140);

		lb_loan = new JLabel("Loan");
		lb_loan.setFont(lb_loan.getFont().deriveFont(Font.BOLD, 30));
		lb_loan.setBounds(30, 290, this.getWidth() / 2, 50);

		lb_titleBalance = new JLabel("Your Balance");
		lb_titleBalance.setFont(lb_titleBalance.getFont().deriveFont(Font.BOLD, 22));
		lb_titleBalance.setBounds(100, 340, this.getWidth() / 2, 50);

		lb_currentBalance = new JLabel("");
		lb_currentBalance.setFont(lb_currentBalance.getFont().deriveFont(Font.BOLD, 22));
		lb_currentBalance.setBounds(this.getWidth() / 2, 340, this.getWidth() / 2 - 100, 50);
		lb_currentBalance.setHorizontalAlignment(SwingConstants.RIGHT);

		lb_titleLoan = new JLabel("Your Loan");
		lb_titleLoan.setFont(lb_titleLoan.getFont().deriveFont(Font.BOLD, 22));
		lb_titleLoan.setBounds(100, 400, this.getWidth() / 2, 50);

		lb_currentLoan = new JLabel("");
		lb_currentLoan.setFont(lb_currentLoan.getFont().deriveFont(Font.BOLD, 22));
		lb_currentLoan.setBounds(this.getWidth() / 2, 400, this.getWidth()  /2 - 100, 50);
		lb_currentLoan.setForeground(Color.RED);
		lb_currentLoan.setHorizontalAlignment(SwingConstants.RIGHT);

		lb_amount = new JLabel("Amount");
		lb_amount.setFont(lb_amount.getFont().deriveFont(Font.BOLD, 22));
		lb_amount.setBounds(100, 460, this.getWidth() / 2, 50);

		tf_amount = new JTextField();
		tf_amount.setFont(tf_amount.getFont().deriveFont(Font.BOLD, 18));
		tf_amount.setBounds(this.getWidth() / 2, 460, this.getWidth() / 2 - 100, 50);

		lb_status = new JLabel("");
		lb_status.setFont(lb_status.getFont().deriveFont(Font.BOLD, 22));
		lb_status.setBounds(100, 520, this.getWidth() / 2, 50);

		btn_loanAmount = new JButton("Loan");
		btn_loanAmount.setFont(btn_loanAmount.getFont().deriveFont(Font.BOLD, 22));
		btn_loanAmount.setBounds(this.getWidth() * 3 / 4, 520, this.getWidth() / 4 - 100, 50);
		btn_loanAmount.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){

				// check whether the amount is valid
				String szAmount = tf_amount.getText();
				if(szAmount == null || szAmount.length() == 0){
					lb_status.setForeground(Color.RED);
					lb_status.setText("The amount is not valid!");
					return;
				}

				long nAmount;
				try{
					nAmount = Long.parseLong(szAmount);
				}catch(Exception exception){
					lb_status.setForeground(Color.RED);
					lb_status.setText("The amount is not valid!");
					return;
				}

				if(nAmount <= 0){
					lb_status.setForeground(Color.RED);
					lb_status.setText("The amount is not valid!");
					return;
				}

				// loan money
				if( loanMoney(nAmount) == false){
					lb_status.setForeground(Color.RED);
					lb_status.setText("Limit reached. Your loan was rejected!");
					return;
				}

				// update UI balance and loan
				updateBalance();
				updateLoan();

				// write to xml
				saveUserInfo();

				lb_status.setForeground(Color.GREEN);
				lb_status.setText("Money loaned successfully!");
			}
		});

		img_bottom = new ImageIcon("../assets/images/overview_bottom.jpg");
		lb_img_bottom = new JLabel(img_bottom);
		lb_img_bottom.setBounds(0, 580, this.getWidth(), 140);

		// add components
		panel.add(lb_date);
		panel.add(lb_logo);
		panel.add(lb_hello);
		panel.add(lb_name);
		panel.add(lb_welcomeMsg);
		panel.add(btn_signOut);
		panel.add(btn_overview);
		panel.add(btn_transfer);
		panel.add(btn_loan);
		panel.add(pan_tab);
		panel.add(lb_loan);
		panel.add(lb_titleBalance);
		panel.add(lb_currentBalance);
		panel.add(lb_titleLoan);
		panel.add(lb_currentLoan);
		panel.add(lb_status);
		panel.add(tf_amount);
		panel.add(btn_loanAmount);
		panel.add(lb_img_top);
		panel.add(lb_img_bottom);

		this.add(panel);
	}
}
