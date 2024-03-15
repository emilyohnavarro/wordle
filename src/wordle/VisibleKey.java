package wordle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class VisibleKey extends VisibleChar {
	
	public VisibleKey(CharLayer background, CharLayer foreground, JLabel label) {
		super(background, foreground, label);
	}
	
	public ImageIcon getBackgroundImage() {
		return getBackground().getKeyImage();
	}
	
	public ImageIcon getForegroundImage() {
		return getForeground().getKeyImage();
	}
}
