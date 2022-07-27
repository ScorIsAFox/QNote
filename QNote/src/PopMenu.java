import java.awt.Component;
import java.awt.Font;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopMenu extends JPopupMenu{

	protected JMenuItem copyM = new JMenuItem("Copy");
	protected JMenuItem cutM = new JMenuItem("Cut");
	protected JMenuItem pasteM = new JMenuItem("Paste");
	protected JMenuItem undoM = new JMenuItem("Undo");
	protected JMenuItem redoM = new JMenuItem("Redo");
	
	protected JMenu themeM = new JMenu("Theme");
	protected JMenuItem lightM = new JMenuItem("Light");
	protected JMenuItem darkM = new JMenuItem("Dark");
	protected JMenuItem summerM = new JMenuItem("Summer");
	protected JMenuItem sunsetM = new JMenuItem("Sunset");
	
	protected JMenuItem diaM = new JMenuItem("Diaphaneity");
	protected JMenuItem hideM = new JMenuItem("Hide the Title Bar");
	protected JMenuItem helpM = new JMenuItem("Help");
	
	public boolean hide = false;
	
	public PopMenu() {
		copyM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		cutM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		pasteM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		undoM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		redoM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		
		themeM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		lightM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		darkM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		summerM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		sunsetM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		diaM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		hideM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		helpM.setFont(new Font("Adobe 黑体 Std R",Font.PLAIN,14));
		
		themeM.add(lightM);
		themeM.add(darkM);
		themeM.add(summerM);
		themeM.add(sunsetM);

		this.add(copyM);
		this.add(cutM);
		this.add(pasteM);
		this.add(undoM);
		this.add(redoM);
		this.addSeparator();
		this.add(themeM);
		this.add(diaM);
		this.add(hideM);
		this.addSeparator();
		this.add(helpM);
	}
	
	public void showPopupMenu(Component invoker, int x, int y) {
		this.show(invoker, x, y);
	}
	
}
