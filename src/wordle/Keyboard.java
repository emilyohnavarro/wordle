package wordle;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Keyboard extends JPanel {
	private Map<Character, KeyLayer> keyMap;
	
	public Keyboard() {
		createKeyMap();
		setLayout(new GridLayout(3, 1, 2, 2));
		JPanel topRow = new JPanel();
		JLabel q = new JLabel(new ImageIcon("res/q-key.png"));
		topRow.add(q);
		JLabel w = new JLabel(new ImageIcon("res/w-key.png"));
		topRow.add(w);
		JLabel e = new JLabel(new ImageIcon("res/e-key.png"));
		topRow.add(e);
		JLabel r = new JLabel(new ImageIcon("res/r-key.png"));
		topRow.add(r);
		JLabel t = new JLabel(new ImageIcon("res/t-key.png"));
		topRow.add(t);
		JLabel y = new JLabel(new ImageIcon("res/y-key.png"));
		topRow.add(y);
		JLabel u = new JLabel(new ImageIcon("res/u-key.png"));
		topRow.add(u);
		JLabel i = new JLabel(new ImageIcon("res/i-key.png"));
		topRow.add(i);
		JLabel o = new JLabel(new ImageIcon("res/o-key.png"));
		topRow.add(o);
		JLabel p = new JLabel(new ImageIcon("res/p-key.png"));
		topRow.add(p);
		add(topRow);
		
		JPanel middleRow = new JPanel();
		JLabel a = new JLabel(new ImageIcon("res/a-key.png"));
		middleRow.add(a);
		JLabel s = new JLabel(new ImageIcon("res/s-key.png"));
		middleRow.add(s);
		JLabel d = new JLabel(new ImageIcon("res/d-key.png"));
		middleRow.add(d);
		JLabel f = new JLabel(new ImageIcon("res/f-key.png"));
		middleRow.add(f);
		JLabel g = new JLabel(new ImageIcon("res/g-key.png"));
		middleRow.add(g);
		JLabel h = new JLabel(new ImageIcon("res/h-key.png"));
		middleRow.add(h);
		JLabel j = new JLabel(new ImageIcon("res/j-key.png"));
		middleRow.add(j);
		JLabel k = new JLabel(new ImageIcon("res/k-key.png"));
		middleRow.add(k);
		JLabel l = new JLabel(new ImageIcon("res/l-key.png"));
		middleRow.add(l);
		add(middleRow);
		
		JPanel bottomRow = new JPanel();
		JLabel z = new JLabel(new ImageIcon("res/z-key.png"));
		bottomRow.add(z);
		JLabel x = new JLabel(new ImageIcon("res/x-key.png"));
		bottomRow.add(x);
		JLabel c = new JLabel(new ImageIcon("res/c-key.png"));
		bottomRow.add(c);
		JLabel v = new JLabel(new ImageIcon("res/v-key.png"));
		bottomRow.add(v);
		JLabel b = new JLabel(new ImageIcon("res/b-key.png"));
		bottomRow.add(b);
		JLabel n = new JLabel(new ImageIcon("res/n-key.png"));
		bottomRow.add(n);
		JLabel m = new JLabel(new ImageIcon("res/m-key.png"));
		bottomRow.add(m);
		add(bottomRow);
	}
	
	private void createKeyMap() {
		keyMap = new HashMap<>();
		keyMap.put('a', KeyLayer.A);
		keyMap.put('b', KeyLayer.B);
		keyMap.put('c', KeyLayer.C);
		keyMap.put('d', KeyLayer.D);
		keyMap.put('e', KeyLayer.E);
		keyMap.put('f', KeyLayer.F);
		keyMap.put('g', KeyLayer.G);
		keyMap.put('h', KeyLayer.H);
		keyMap.put('i', KeyLayer.I);
		keyMap.put('j', KeyLayer.J);
		keyMap.put('k', KeyLayer.K);
		keyMap.put('l', KeyLayer.L);
		keyMap.put('m', KeyLayer.M);
		keyMap.put('n', KeyLayer.N);
		keyMap.put('o', KeyLayer.O);
		keyMap.put('p', KeyLayer.P);
		keyMap.put('q', KeyLayer.Q);
		keyMap.put('r', KeyLayer.R);
		keyMap.put('s', KeyLayer.S);
		keyMap.put('t', KeyLayer.T);
		keyMap.put('u', KeyLayer.U);
		keyMap.put('v', KeyLayer.V);
		keyMap.put('w', KeyLayer.W);
		keyMap.put('x', KeyLayer.X);
		keyMap.put('y', KeyLayer.Y);
		keyMap.put('z', KeyLayer.Z);
	}
}
