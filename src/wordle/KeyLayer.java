package wordle;

import javax.swing.ImageIcon;

public enum KeyLayer {
	// maps enum to file name:
	BLANK("blank-key.gif"),
	GREEN("green-key.gif"),
	YELLOW("yellow-key.gif"),
	A("a-key.png"),
	B("b-key.png"),
	C("c-key.png"),
	D("d-key.png"),
	E("e-key.png"),
	F("f-key.png"),
	G("g-key.png"),
	H("h-key.png"),
	I("i-key.png"),
	J("j-key.png"),
	K("k-key.png"),
	L("l-key.png"),
	M("m-key.png"),
	N("n-key.png"),
	O("o-key.png"),
	P("p-key.png"),
	Q("q-key.png"),
	R("r-key.png"),
	S("s-key.png"),
	T("t-key.png"),
	U("u-key.png"),
	V("v-key.png"),
	W("w-key.png"),
	X("x-key.png"),
	Y("y-key.png"),
	Z("z-key.png");

	private ImageIcon img;

	KeyLayer(String filename) {
		img = new ImageIcon("res/" + filename);
	}

	public ImageIcon getImage() {
		return img;
	}
}