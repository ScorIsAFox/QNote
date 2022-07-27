import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class NoteDialog extends JDialog{
	
	protected JSlider slider = new JSlider(1, 100, 80);
		
	public NoteDialog(String title, int x, int y) {
		if (x > Toolkit.getDefaultToolkit().getScreenSize().width/2) {
			x -= 400;
		} else {
			x += 400;
		}
		y += 30;
		this.setBounds(x, y, 350, 200);
		this.setResizable(false);
		this.setTitle(title);
	}
	
	public void createSliderDialog() {
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setPaintTicks(true);
		this.add(slider);
	}
	
	public void createHelpDialog() {
		JLabel helpText = new JLabel("Help Doc", JLabel.CENTER);
		helpText.setFont(new Font("Adobe ºÚÌå Std R",Font.PLAIN,17));
		this.add(helpText);		
	}

}
