package Pages;

import User.*;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageMgr implements IPageEventListener {

	private String currentUserName;
	private Page currentPage;
	
	private SignInPage signInPage;
	private SignUpPage signUpPage;
	private OverviewPage overviewPage;
	private TransferPage transferPage;
	private LoanPage loanPage;

	public PageMgr(){

		// active user
		currentUserName = "";

		// New pages
		signInPage = new SignInPage();
		signUpPage = new SignUpPage();
		overviewPage = new OverviewPage();
		transferPage = new TransferPage();
		loanPage = new LoanPage();

		// Register events
		signInPage.addEventListener((IPageEventListener)this);
		signUpPage.addEventListener((IPageEventListener)this);
		overviewPage.addEventListener((IPageEventListener)this);
		transferPage.addEventListener((IPageEventListener)this);
		loanPage.addEventListener((IPageEventListener)this);
	}

	public void run(){
		currentPage = signInPage;
		currentPage.showPage(currentUserName);
	}

	@Override
	public void onPageClosed(String nextPageName){

		// get page location on screen
		Point point = currentPage.getLocationOnScreen();

		currentUserName = currentPage.hidePage();

		switch(nextPageName){
			case "SignInPage":
				currentPage = signInPage;
				break;
			case "SignUpPage":
				currentPage = signUpPage;
				break;
			case "OverviewPage":
				currentPage = overviewPage;
				break;
			case "TransferPage":
				currentPage = transferPage;
				break;
			case "LoanPage":
				currentPage = loanPage;
				break;
			default:
				currentPage = signInPage;
		}

		currentPage.setLocation(point.x, point.y);
		currentPage.showPage(currentUserName);
	}
}
