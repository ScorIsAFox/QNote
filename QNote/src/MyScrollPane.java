import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class MyScrollPane extends JScrollPane{
	private boolean pane_enterable = true; //allow the slider to display when the mouse enters the JScrollPane
	private boolean thumb_enterable = true; //control whether the color of the slider will change
	private boolean pane_enter = true; //a mark for the mouse (true means the mouse is on the JScrollPane)
	private boolean thump_enter = false; //a mark for the mouse (true means the mouse is on the JScrollBar)
	
	private MyScrollBarUI scrollBar_UI = new MyScrollBarUI();
	private MyScrollBar scrollBar = new MyScrollBar();
	
	public MyScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
		this.setViewportView(view);
		this.setVerticalScrollBarPolicy(vsbPolicy);
		this.setHorizontalScrollBarPolicy(hsbPolicy);
		this.setSize(view.getWidth(), view.getHeight());
		this.setLocation(12,35);
		this.setBorder(null);
		this.setOpaque(false);
		this.getViewport().setOpaque(false);
		
		scrollBar.setUnitIncrement(50);
		this.setVerticalScrollBar(scrollBar);
		view.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (!pane_enterable || !thumb_enterable) {return;}
				pane_enter = true;
				thump_enter = false;
				scrollBar.repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!pane_enterable || !thumb_enterable) {return;}
				pane_enter = false;
				scrollBar.repaint();
			}
		});
	}
	
	public void setPaneEnterable(boolean pane_enterable) {
		this.pane_enterable = pane_enterable;
		if (!pane_enterable) {
			pane_enter = true;
			thump_enter = false;
		}
		scrollBar.repaint();
	}
	
	private class MyScrollBarUI extends BasicScrollBarUI {
		@Override
		protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) { //paint the slide way
			Graphics2D g2 = (Graphics2D)g;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0f));
			super.paintTrack((Graphics)g2, c, trackBounds);
		}

		@Override
		protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) { //paint the slide block
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON); // Anti-aliasing
			g2.translate(thumbBounds.x, thumbBounds.y); //Put the brush on the specified coordinates

			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0f));
			if (pane_enter) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
			}
			if (thump_enter) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			}
			g2.setColor(Color.white);
			g2.fillRoundRect(1, 1, thumbBounds.width, thumbBounds.height, 10, 10);
			g2.translate(-thumbBounds.x, -thumbBounds.y); //remove the brush
		}
		
		@Override
		protected JButton createDecreaseButton(int orientation) { //set the downwards button
			JButton button = new JButton();
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorder(null);
			return button;
		}
		
		@Override
		protected JButton createIncreaseButton(int orientation) { //set the upwards button
			JButton button = new JButton();
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorder(null);
			return button;
		}
		
		@Override
		public Dimension getPreferredSize(JComponent c) {
			c.setPreferredSize(new Dimension(8, 0));
			return super.getPreferredSize(c);
		}
	}

	private class MyScrollBar extends JScrollBar{
		public MyScrollBar() {
			this.setBlockIncrement(50);
			this.setOpaque(false);
			this.setUI(scrollBar_UI);
			this.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {}

				@Override
				public void mousePressed(MouseEvent e) {}

				@Override
				public void mouseReleased(MouseEvent e) {}

				@Override
				public void mouseEntered(MouseEvent e) {
					if (!pane_enterable || !thumb_enterable) {return;}
					pane_enter = false;
					thump_enter = true;
					scrollBar.repaint();
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (!pane_enterable || !thumb_enterable) {return;}
					thump_enter = false;
					scrollBar.repaint();
				}
			});
	}	
		}
	
}


