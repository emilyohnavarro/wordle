package wordle;

import javax.swing.JLabel;

public class VisibleTile extends VisibleChar {
	
	public VisibleTile(CharLayer background, CharLayer foreground, JLabel label) {
		super(background, foreground, label);
	}
	
	public void setCharLayers(CharLayer background, CharLayer foreground) {
		setBackground(background);
		setForeground(foreground);
		JLabel label = getLabel();
		label.removeAll();
		label.setIcon(background.getTileImage());
		label.add(new JLabel(foreground.getTileImage()));
		label.revalidate();
	}
}
