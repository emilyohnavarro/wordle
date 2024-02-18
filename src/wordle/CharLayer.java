package wordle;

import java.util.Map;
import static java.util.Map.entry;

import javax.swing.ImageIcon;

public enum CharLayer {
	// maps enum to file names:
	BLANK("blank.gif", "blank-key.gif"),
	GREEN("green.gif", "green-key.gif"),
	YELLOW("yellow.gif", "yellow-key.gif"),
	A("a.png", "a-key.png"),
	B("b.png", "b-key.png"),
	C("c.png", "c-key.png"),
	D("d.png", "d-key.png"),
	E("e.png", "e-key.png"),
	F("f.png", "f-key.png"),
	G("g.png", "g-key.png"),
	H("h.png", "h-key.png"),
	I("i.png", "i-key.png"),
	J("j.png", "j-key.png"),
	K("k.png", "k-key.png"),
	L("l.png", "l-key.png"),
	M("m.png", "m-key.png"),
	N("n.png", "n-key.png"),
	O("o.png", "o-key.png"),
	P("p.png", "p-key.png"),
	Q("q.png", "q-key.png"),
	R("r.png", "r-key.png"),
	S("s.png", "s-key.png"),
	T("t.png", "t-key.png"),
	U("u.png", "u-key.png"),
	V("v.png", "v-key.png"),
	W("w.png", "w-key.png"),
	X("x.png", "x-key.png"),
	Y("y.png", "y-key.png"),
	Z("z.png", "z-key.png");
	
	private ImageIcon tileImg;
	private ImageIcon keyImg;
	
	public static Map<Character, CharLayer> charLayerMap = Map.ofEntries(
			entry(' ', CharLayer.BLANK),
			entry('a', CharLayer.A),
			entry('b', CharLayer.B),
			entry('c', CharLayer.C),
			entry('d', CharLayer.D),
			entry('e', CharLayer.E),
			entry('f', CharLayer.F),
			entry('g', CharLayer.G),
			entry('h', CharLayer.H),
			entry('i', CharLayer.I),
			entry('j', CharLayer.J),
			entry('k', CharLayer.K),
			entry('l', CharLayer.L),
			entry('m', CharLayer.M),
			entry('n', CharLayer.N),
			entry('o', CharLayer.O),
			entry('p', CharLayer.P),
			entry('q', CharLayer.Q),
			entry('r', CharLayer.R),
			entry('s', CharLayer.S),
			entry('t', CharLayer.T),
			entry('u', CharLayer.U),
			entry('v', CharLayer.V),
			entry('w', CharLayer.W),
			entry('x', CharLayer.X),
			entry('y', CharLayer.Y),
			entry('z', CharLayer.Z)
			);

	CharLayer(String tileImgFile, String keyImgFile) {
		tileImg = new ImageIcon("res/" + tileImgFile);
		keyImg = new ImageIcon("res/" + keyImgFile);
	}

	public ImageIcon getTileImage() {
		return tileImg;
	}
	
	public ImageIcon getKeyImage() {
		return keyImg;
	}
}