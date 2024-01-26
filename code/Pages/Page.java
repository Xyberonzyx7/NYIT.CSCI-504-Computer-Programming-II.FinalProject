package Pages;

import User.*;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class Page extends JFrame{

	// event
	List<IPageEventListener> listeners = new ArrayList<IPageEventListener>();

	protected void addEventListener(IPageEventListener listener){
		listeners.add(listener);
	}

	// Trigger Event
	protected void triggerPageClosed(String nextPageName){
		for(IPageEventListener listener : listeners){
			if(listener != null){
				listener.onPageClosed(nextPageName);
			}
		}
	}

	// show
	// show current user's info
	public void showPage(String username){

		// can be overried by child class based on its need.
		this.setVisible(true);
	}

	// hide
	// return current username
	public String hidePage(){

		// can be override by child class based on its need.
		this.setVisible(false);

		return "";
	}
	
}
