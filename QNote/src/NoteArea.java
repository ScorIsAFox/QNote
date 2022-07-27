import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;

public class NoteArea extends JTextArea implements MouseListener{

	private PopMenu menu = new PopMenu();
	protected UndoManager undoManager = new UndoManager();
		
	public NoteArea (int width, int height, NoteContent file, PopMenu menu) {
		this.setSize(width, height);
		this.setEditable(true);
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		this.setFont(new Font("Adobe ºÚÌå Std R",Font.PLAIN,17));
		this.setForeground(Color.white);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setOpaque(false);
		this.setCaretColor(Color.white);

		file.readOperation(this);
		this.getDocument().addUndoableEditListener(undoManager);
		this.addKeyListener(new KeyListener() {
 
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
 
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_Z) {
					if (undoManager.canUndo()) {
						undoManager.undo();
					}
				}
				if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_Y) {
					if (undoManager.canRedo()) {
						undoManager.redo();
					}
				}
			}
 
			@Override
			public void keyTyped(KeyEvent arg0) {
			}

		});
		
		this.menu = menu;
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isMetaDown()) {
            menu.showPopupMenu(e.getComponent(), e.getX(), e.getY());
        }
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
