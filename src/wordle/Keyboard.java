package wordle;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Keyboard extends JPanel {
	private Map<Character, CharLayer> charMap = CharLayer.charLayerMap;
	protected Map<Character, VisibleKey> keyMap;
	
	public Keyboard() {
		createKeyMap();
		setLayout(new GridLayout(3, 1, 2, 2));
		
		JPanel topRow = new JPanel();
		topRow.add(keyMap.get('q').getLabel());
		topRow.add(keyMap.get('w').getLabel());
		topRow.add(keyMap.get('e').getLabel());
		topRow.add(keyMap.get('r').getLabel());
		topRow.add(keyMap.get('t').getLabel());
		topRow.add(keyMap.get('y').getLabel());
		topRow.add(keyMap.get('u').getLabel());
		topRow.add(keyMap.get('i').getLabel());
		topRow.add(keyMap.get('o').getLabel());
		topRow.add(keyMap.get('p').getLabel());
		add(topRow);
		
		JPanel middleRow = new JPanel();
		middleRow.add(keyMap.get('a').getLabel());
		middleRow.add(keyMap.get('s').getLabel());
		middleRow.add(keyMap.get('d').getLabel());
		middleRow.add(keyMap.get('f').getLabel());
		middleRow.add(keyMap.get('g').getLabel());
		middleRow.add(keyMap.get('h').getLabel());
		middleRow.add(keyMap.get('j').getLabel());
		middleRow.add(keyMap.get('k').getLabel());
		middleRow.add(keyMap.get('l').getLabel());
		add(middleRow);
		
		JPanel bottomRow = new JPanel();
		bottomRow.add(keyMap.get('z').getLabel());
		bottomRow.add(keyMap.get('x').getLabel());
		bottomRow.add(keyMap.get('c').getLabel());
		bottomRow.add(keyMap.get('v').getLabel());
		bottomRow.add(keyMap.get('b').getLabel());
		bottomRow.add(keyMap.get('n').getLabel());
		bottomRow.add(keyMap.get('m').getLabel());
		add(bottomRow);
	}
	
	private void createKeyMap() {
		keyMap = new HashMap<>();
		
		JLabel label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		VisibleKey key = new VisibleKey(CharLayer.BLANK, CharLayer.A, label);
		keyMap.put('a', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.B, label);
		keyMap.put('b', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.C, label);
		keyMap.put('c', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.D, label);
		keyMap.put('d', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.E, label);
		keyMap.put('e', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.F, label);
		keyMap.put('f', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.G, label);
		keyMap.put('g', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.H, label);
		keyMap.put('h', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.I, label);
		keyMap.put('i', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.J, label);
		keyMap.put('j', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.K, label);
		keyMap.put('k', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.L, label);
		keyMap.put('l', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.M, label);
		keyMap.put('m', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.N, label);
		keyMap.put('n', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.O, label);
		keyMap.put('o', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.P, label);
		keyMap.put('p', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.Q, label);
		keyMap.put('q', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.R, label);
		keyMap.put('r', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.S, label);
		keyMap.put('s', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.T, label);
		keyMap.put('t', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.U, label);
		keyMap.put('u', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.V, label);
		keyMap.put('v', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.W, label);
		keyMap.put('w', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.X, label);
		keyMap.put('x', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.Y, label);
		keyMap.put('y', key);
		
		label = new JLabel(CharLayer.BLANK.getKeyImage());
		label.setLayout(new BorderLayout());
		key = new VisibleKey(CharLayer.BLANK, CharLayer.Z, label);
		keyMap.put('z', key);
	}
}
