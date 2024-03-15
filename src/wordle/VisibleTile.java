package wordle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class VisibleTile extends VisibleChar {
	
	public VisibleTile(CharLayer background, CharLayer foreground, JLabel label) {
		super(background, foreground, label);
	}
	
	public ImageIcon getBackgroundImage() {
		return getBackground().getTileImage();
	}
	
	public ImageIcon getForegroundImage() {
		return getForeground().getTileImage();
	}
}
