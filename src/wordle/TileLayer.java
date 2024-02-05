package wordle;

import javax.swing.ImageIcon;

public enum TileLayer {
	// maps enum to file name:
	BLANK("blank.gif"),
	GREEN("green.gif"),
	YELLOW("yellow.gif"),
	A("a.png"),
	B("b.png"),
	C("c.png"),
	D("d.png"),
	E("e.png"),
	F("f.png"),
	G("g.png"),
	H("h.png"),
	I("i.png"),
	J("j.png"),
	K("k.png"),
	L("l.png"),
	M("m.png"),
	N("n.png"),
	O("o.png"),
	P("p.png"),
	Q("q.png"),
	R("r.png"),
	S("s.png"),
	T("t.png"),
	U("u.png"),
	V("v.png"),
	W("w.png"),
	X("x.png"),
	Y("y.png"),
	Z("z.png");
	
//	BLANK(' ', "blank.gif"), 
//	A('a', "a.png"),
//	B('b', "b.png"),
//	C('c', "c.png"),
//	D('d', "d.png"),
//	E('e', "e.png"),
//	F('f', "f.png"),
//	G('g', "g.png"),
//	H('h', "h.png"),
//	I('i', "i.png"),
//	J('j', "j.png"),
//	K('k', "k.png"),
//	L('l', "l.png"),
//	M('m', "m.png"),
//	N('n', "n.png"),
//	O('o', "o.png"),
//	P('p', "p.png"),
//	Q('q', "q.png"),
//	R('r', "r.png"),
//	S('s', "s.png"),
//	T('t', "t.png"),
//	U('u', "u.png"),
//	V('v', "v.png"),
//	W('w', "w.png"),
//	X('x', "x.png"),
//	Y('y', "y.png"),
//	Z('z', "z.png");
	
	private ImageIcon img;
//	private char character;

//	TileLayer(char character, String filename) {
	TileLayer(String filename) {
//		this.character = character;
		img = new ImageIcon("res/" + filename);
	}

	public ImageIcon getImage() {
		return img;
	}
	
//	public char getCharacter() {
//		return character;
//	}
}