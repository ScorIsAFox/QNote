import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NoteFrame extends JFrame implements ActionListener, ChangeListener, MouseMotionListener{

	protected PopMenu menu = new PopMenu();
	private JSlider slider;
	private TitleBar bar = new TitleBar(350, 35);
	private NoteContent file = new NoteContent();
	private NoteArea text;
	private Gradient bg;
	private boolean onTop = false;
	private boolean editable = true;
	private int lastX,lastY;
	public NoteFrame() {
		file.fileCreate();
		
		/* Set the window */
		this.setTitle("QNote");
		this.setLayout(null);
		this.setResizable(false); //Forbid changing the size of the frame
		this.setSize(350, 505);
		this.setLocation(1450, 50); //Set initial position
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 80));
		Image icon = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icons/Fox.png"));
		this.setIconImage(icon);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Set the Components*/
		this.getContentPane().add(bar); //Add the title bar
		this.jspCreation(); //Add the scroll pane

		URL imgUrl = this.getClass().getResource("/icons/Sunset.png"); // Add the sunset panel
		ImageIcon image = new ImageIcon(imgUrl);
		bg = new Gradient(image.getImage());
		bg.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		bg.setVisible(false);
		this.getContentPane().add(bg);
		
		/* Add listeners*/
		this.addButtonActions();
		this.addMenuActions();
		this.windowActions();

		this.validate();
		
	}
	
	private void jspCreation() {
		text = new NoteArea(326, 460, file, menu);
		MyScrollPane jsp = new MyScrollPane(
	            text,
	            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
	            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
	    );
		this.getContentPane().add(jsp);
	}
	
	private void addButtonActions() {
		bar.quit.addActionListener(this);
		bar.mini.addActionListener(this);
		bar.top.addActionListener(this);
		bar.lock.addActionListener(this);
		bar.addMouseMotionListener(this);
	}
	
	private void buttonActions(ActionEvent e) {
		/* Buttons Actions*/
		if (e.getSource() == bar.quit) {
			file.saveOperation(text);
			System.exit(0);
		}
		
		if (e.getSource() == bar.mini) {
			this.setExtendedState(ICONIFIED);
		}
		
		if (e.getSource() == bar.top) {
			onTop = !onTop; //change the mode between non-onTop &onTop
			this.setAlwaysOnTop(onTop);
			if (onTop) { //change the icon to express different mode
				ImageIcon icon = new ImageIcon(this.getClass().getResource("/icons/top1.png"));
				bar.top.setIcon(icon);
			} else {
				ImageIcon icon = new ImageIcon(this.getClass().getResource("/icons/top0.png"));
				bar.top.setIcon(icon);
			}
		}
		
		if (e.getSource() == bar.lock) {
			editable = !editable; //change the mode between non-editable and editable mode
			file.saveOperation(text);
			text.setEditable(editable);
		}
	}
	
	private void addMenuActions() {
		menu.copyM.addActionListener(this);
		menu.cutM.addActionListener(this);
		menu.pasteM.addActionListener(this);
		menu.undoM.addActionListener(this);
		menu.redoM.addActionListener(this);
		
		menu.lightM.addActionListener(this);
		menu.darkM.addActionListener(this);
		menu.summerM.addActionListener(this);
		menu.sunsetM.addActionListener(this);
		
		menu.diaM.addActionListener(this);
		menu.hideM.addActionListener(this);
		menu.helpM.addActionListener(this);
	}
	
	private void menuActions(ActionEvent e) {
		/* PopMenu Actions*/
		this.textOperations(e);
		this.skinChange(e);
		
		if (e.getSource() == menu.helpM) {
			NoteDialog helpW = new NoteDialog("Help", this.getLocation().x, this.getLocation().y);
			helpW.createHelpDialog();
			helpW.setVisible(true);
		}
	}
	
	private void textOperations(ActionEvent e) {
		if (e.getSource() == menu.copyM) {
			text.copy();
		}
		
		if (e.getSource() == menu.cutM) {
			text.cut();
		}
		
		if (e.getSource() == menu.pasteM) {
			text.paste();
		}
		
		if (e.getSource() == menu.undoM) {
			if (text.undoManager.canUndo()) {
				text.undoManager.undo();
			}
		}
		
		if (e.getSource() == menu.redoM) {
			if (text.undoManager.canRedo()) {
				text.undoManager.redo();
			}
		}
	}
	
	private void skinChange(ActionEvent e) {
		if (e.getSource() == menu.lightM) {
			this.setBackground(new Color(255, 69, 0, 80));
			bg.setVisible(false);
		}
		
		if (e.getSource() == menu.darkM) {
			this.setBackground(new Color(0, 0, 0, 80));
			bg.setVisible(false);
		}
		
		if (e.getSource() == menu.summerM) {
			this.setBackground(new Color(0, 191, 255, 80));
			bg.setVisible(false);
		}

		if (e.getSource() == menu.sunsetM) {
			this.setBackground(new Color(0, 0, 0, 1));
			bg.setAlpha(80);
			bg.setVisible(true);
		}
		
		if (e.getSource() == menu.diaM) {
			NoteDialog sliderW = new NoteDialog("Diaphaneity", this.getLocation().x, this.getLocation().y);
			sliderW.createSliderDialog();
			sliderW.slider.addChangeListener(this);
			slider = sliderW.slider;
			sliderW.setVisible(true);
		}

		if (e.getSource() == menu.hideM) {
			bar.quit.setVisible(menu.hide);
			bar.mini.setVisible(menu.hide);
			bar.top.setVisible(menu.hide);
			bar.lock.setVisible(menu.hide);
			
			menu.hide = !menu.hide;
			if (menu.hide) {
				menu.hideM.setText("Hide the Title Bar ¡Ì");
			} else {
				menu.hideM.setText("Hide the Title Bar");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.buttonActions(e);
		this.menuActions(e);		
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		this.setBackground(new Color(getBackground().getRed(),
				getBackground().getGreen(),
				getBackground().getBlue(),
				slider.getValue()));
		bg.setAlpha(slider.getValue());
		bg.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.setLocation(this.getLocation().x + e.getX() - lastX, 
				this.getLocation().y + e.getY() - lastY);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		lastX = e.getX();
		lastY = e.getY();
	}

	private void windowActions() {
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				text.requestFocusInWindow();
			}

			@Override
			public void windowClosing(WindowEvent e) {
				file.saveOperation(text);
			}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}

		});
	}
}
