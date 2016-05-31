/**
 * Copyright (C) 2016 The example.jLaTeXMath Project
 */
package maximsblog.blogspot.com.jlatexmath.core;

public class CStringFont {

	public String c;
	public int fontId;
	public int boldFontId;

	public CStringFont(String ch, int f) {
		this(ch, f, f);
	}

	public CStringFont(String ch, int f, int bf) {
		c = ch;
		fontId = f;
		boldFontId = bf;
	}

}
