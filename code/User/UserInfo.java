package User;

public class UserInfo{

	private String userName;
	private String password;
	private long balance;
	private long loan;

	public UserInfo(){
		userName = "";
		password = "";
		balance = 0L;
		loan = 0L;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public void setBalance(long balance){
		this.balance = balance;
	}

	public void setLoan(long loan){
		this.loan = loan;
	}

	public String getUserName(){
		return userName;
	}

	public String getPassword(){
		return password;
	}

	public long getBalance(){
		return balance;
	}

	public long getLoan(){
		return loan;
	}
}
