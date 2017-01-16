/*
 * 
 */
package br.unb.ppmec.cbrmeca.view.notification;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.WindowConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationManager.
 */
public class NotificationManager {
	
	/** The Constant instance. */
	private static final NotificationManager instance = new NotificationManager();
	
	/**
	 * Instantiates a new notification manager.
	 */
	private NotificationManager(){
		
	}
	
	/**
	 * Gets the single instance of NotificationManager.
	 *
	 * @return single instance of NotificationManager
	 */
	public static NotificationManager getInstance(){
		return instance;
	}
	
	/** The notification frame. */
	private static Notification notificationFrame = null;
	
	/**
	 * Setup notification.
	 */
	public void setupNotification() {
		if(notificationFrame != null){
			return;
		}
		
		notificationFrame = new Notification();
		notificationFrame
				.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(
				notificationFrame.getGraphicsConfiguration());
		notificationFrame.setLocation(
				scrSize.width - notificationFrame.getWidth(), scrSize.height
						- toolHeight.bottom - notificationFrame.getHeight());
	}
	
	/**
	 * Notify user.
	 *
	 * @param title the title
	 * @param text the text
	 * @param type the type
	 */
	public void notifyUser(String title, String text, int type) {
		if(notificationFrame == null){
			setupNotification();
		}

		notificationFrame.setStrings(title, text, type);
		notificationFrame.setVisible(true);

		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000); // time after which pop up will be
										// disappeared.
					notificationFrame.setVisible(false);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
}
