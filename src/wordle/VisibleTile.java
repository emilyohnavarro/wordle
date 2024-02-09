package wordle;

import java.awt.BorderLayout;

import javax.swing.JLabel;

public class VisibleTile {
	
	private TileLayer backgroundTile;
	private TileLayer foregroundTile;
	private JLabel label;
	
	public VisibleTile(TileLayer backgroundTile, TileLayer foregroundTile, JLabel label) {
		this.label = label;
		setTileLayers(backgroundTile, foregroundTile);
	}
	
	public TileLayer getBackground() {
		return backgroundTile;
	}
	
	public TileLayer getForeground() {
		return foregroundTile;
	}

	public JLabel getLabel() {
		return label;
	}
	
	public void setTileLayers(TileLayer background, TileLayer foreground) {
		backgroundTile = background;
		foregroundTile = foreground;
		label.removeAll();
		label.setIcon(backgroundTile.getImage());
		label.add(new JLabel(foregroundTile.getImage()));
		label.revalidate();
	}
}
