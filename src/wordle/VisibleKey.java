package wordle;

import javax.swing.JLabel;

public class VisibleKey extends VisibleChar {
	
	public VisibleKey(CharLayer background, CharLayer foreground, JLabel label) {
		super(background, foreground, label);
	}
	
	public void setCharLayers(CharLayer background, CharLayer foreground) {
		setBackground(background);
		setForeground(foreground);
		JLabel label = getLabel();
		label.removeAll();
		label.setIcon(background.getKeyImage());
		label.add(new JLabel(foreground.getKeyImage()));
		label.revalidate();
	}
}
