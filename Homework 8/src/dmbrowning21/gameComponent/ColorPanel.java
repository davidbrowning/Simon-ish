package dmbrowning21.gameComponent;


import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ColorPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel flash;
	private JLabel dark;
	//boolean buttonDown = false;

	
	
	
	public ColorPanel(Color color, JLabel l, JLabel f, JLabel d) {
		this.flash = f;
		this.dark = d;
		this.setLayout(null);
		
		this.add(l);
		this.add(d);
		l.setBounds(0,0,500,500);
		dark.setBounds(0,0,500,500);
		l.setVerticalAlignment(SwingConstants.TOP);
		flash.setBounds(0, 0, 500, 500);
		this.setOpaque(false);
		
		
	}
	
	public void changeBackground(Color c){
		this.setBackground(c);
	}
	
	public void pressed() {
		this.remove(dark);
		this.add(flash);
	//	buttonDown = true;
		update(getGraphics());
	}
	public void released() {
		this.remove(flash);
		this.add(dark);
	//	buttonDown = false;
		update(getGraphics());
	}
	public void reset() {
		this.remove(flash);
		this.add(dark);
		update(getGraphics());
	}
}
