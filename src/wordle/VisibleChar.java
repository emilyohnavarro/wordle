package wordle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class VisibleChar {
	
	private CharLayer background;
	private CharLayer foreground;
	private JLabel label;
	
	public VisibleChar(CharLayer background, CharLayer foreground, JLabel label) {
		this.label = label;
		setCharLayers(background, foreground);
	}
	
	public CharLayer getBackground() {
		return background;
	}
	
	public CharLayer getForeground() {
		return foreground;
	}
	
	public void setBackground(CharLayer background) {
		this.background = background;
	}
	
	public void setForeground(CharLayer foreground) {
		this.foreground = foreground;
	}

	public JLabel getLabel() {
		return label;
	}
	
	public abstract ImageIcon getBackgroundImage();
	
	public abstract ImageIcon getForegroundImage();
	
	public void setCharLayers(CharLayer background, CharLayer foreground) {
		setBackground(background);
		setForeground(foreground);
		JLabel label = getLabel();
		label.removeAll();
		label.setIcon(getBackgroundImage());
		label.add(new JLabel(getForegroundImage()));
		label.revalidate();
	}
}
