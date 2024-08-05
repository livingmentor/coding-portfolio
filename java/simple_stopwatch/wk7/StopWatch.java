package wk7;

import java.awt.event.*;
import java.math.*;
import javax.swing.*;
import java.awt.*;
//import java.applet.*;

/**
 *  I removed all the comments from the code that was given to us.  I only have comments on
 *  code that I contributed.  This should make it easier to distinguish my work from what 
 *  was given.
 */

class StopWatchLabel extends JLabel implements MouseListener, ActionListener {  
	//Implemented ActionListener to track the time and pass to timer object.
	//Simplified the coding required over creating a new ActionListener

	private static final long serialVersionUID = 1L;  //Just to get the warning to stop.
	private long startTime;
	private boolean running;
	private Timer timer;  //Added swing timer.

	public StopWatchLabel() {
		super("  Click to start timer.  ", JLabel.CENTER);
		addMouseListener(this);
	}

	public boolean isRunning() {
		return running;
	}
	/**
	 * Added the actionPerformed method for the ActionListener.
	 */
	public void actionPerformed(ActionEvent evt) {
		/**
		 * I put this in it's own thread hoping to resolve a flicker issue.  It did not, but once I resolved the flicker problem,
		 * I didn't feel the need to change it back.
		 */
		Thread thread = new Thread() { 
			public void run() {
				setText(BigDecimal.valueOf((System.currentTimeMillis() - startTime) / 1000.0).setScale(3, RoundingMode.HALF_UP).toString());
			}
		};
		thread.start();

		
	}

	public void mousePressed(MouseEvent evt) {
		if (running == false) {
			running = true;
			startTime = evt.getWhen();
			if (timer == null) {
				// refreshes at approx. 30x per second.  It saves on CPU and the human eye can't tell the difference.
				timer = new Timer(33,this);  
				timer.start();
			} else {
				timer.restart();
			}
		}
		else {
			timer.stop();
			running = false;
			
			//None of this was needed:
			
			//long endTime = evt.getWhen();
			//double seconds = (endTime - startTime) / 1000.0;
			//setText("Time: " + seconds + " sec.");
		}
	}

	public void mouseReleased(MouseEvent evt) { }
	public void mouseClicked(MouseEvent evt) { }
	public void mouseEntered(MouseEvent evt) { }
	public void mouseExited(MouseEvent evt) { }

}


public class StopWatch { //extends Applet 

	//private static final long serialVersionUID = 1L;
	
	/**
	 * For whatever reason, the applet's implementation of frame/panel (or whatever it uses)
	 * caused horrible screen flicker.  I resolved it by setting it up as an application 
	 * instead of an applet.
	 */
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		StopWatchLabel watch = new StopWatchLabel();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		watch.setFont( new Font("SansSerif", Font.BOLD, 24) );
		watch.setBackground(Color.white);
		watch.setForeground( new Color(180,0,0) );
		
		panel.setBackground(Color.white);
		panel.setLayout(new BorderLayout() );
		panel.add(watch, BorderLayout.CENTER);
		
		frame.setContentPane( panel );
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation( (screenSize.width - frame.getWidth())/2, (screenSize.height - frame.getHeight())/2 );
		frame.setVisible(true);
		
	}

//	public void init() {
//
//		StopWatchLabel watch = new StopWatchLabel();
//		watch.setFont( new Font("SansSerif", Font.BOLD, 24) );
//		watch.setBackground(Color.white);
//		watch.setForeground( new Color(180,0,0) );
//		setBackground(Color.white);
//		setLayout(new BorderLayout() );
//		add(watch, BorderLayout.CENTER);
//
//	}
}
