package src;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

class PanelORdenadorContendor extends JPanel {
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
	}
}