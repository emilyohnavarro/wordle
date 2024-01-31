package wordle;

import javax.swing.JLabel;

public class VisibleTile {
	
	private TileLayer backgroundTile;
	private TileLayer foregroundTile;
	private JLabel label;
	
	public VisibleTile(TileLayer backgroundTile, TileLayer foregroundTile, JLabel label) {
		this.backgroundTile = backgroundTile;
		this.foregroundTile = foregroundTile;
		this.label = label;
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
		label.add(new JLabel(backgroundTile.getImage()));
		label.add(new JLabel(foregroundTile.getImage()));
	}
}
