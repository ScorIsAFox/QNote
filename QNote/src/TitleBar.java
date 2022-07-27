import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;

public class TitleBar extends JPanel {
	protected JButton quit = new JButton();
	protected JButton mini = new JButton();
	protected JButton top = new JButton();
	protected JButton lock = new JButton();
	protected PopMenu menu = new PopMenu();
	
	public TitleBar(int width, int height) {
		this.setSize(width, height);
		this.setLocation(0, 0);
		this.setLayout(null);
		this.setOpaque(false);
		this.buttonSetting(this);

		JPanel move = new JPanel();
		move.setSize(198, 28);
		move.setLocation(45, 0);
		move.setOpaque(false);
		move.setCursor(new Cursor(13));
		this.add(move);
	}

	private void buttonSetting(JPanel c) {
		/* Exit button */
		quit.setSize(25, 25);
		quit.setLocation(313, 5);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icons/quit.png"));
		quit.setIcon(icon);
		quit.setBorder(null);
		quit.setContentAreaFilled(false);
		c.add(quit);
		
		/* Minimize button */
		mini.setSize(25, 25);
		mini.setLocation(278, 5);
		icon = new ImageIcon(this.getClass().getResource("/icons/mini.png"));
		mini.setIcon(icon);
		mini.setBorder(null);
		mini.setContentAreaFilled(false);
		c.add(mini);
		
		/* OnTop button */
		top.setSize(25, 25);
		top.setLocation(243, 5);
		icon = new ImageIcon(this.getClass().getResource("/icons/top0.png"));
		top.setIcon(icon);
		top.setBorder(null);
		top.setContentAreaFilled(false);
		c.add(top);
		
		/* Lock button */
		lock.setSize(40, 20);
		lock.setLocation(5, 8);
		icon = new ImageIcon(this.getClass().getResource("/icons/lock.png"));
		lock.setIcon(icon);
		lock.setBorder(null);
		lock.setContentAreaFilled(false);
		c.add(lock);
	}


}
