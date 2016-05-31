/**
 * Copyright (C) 2016 The example.jLaTeXMath Project
 */
package maximsblog.blogspot.com.jlatexmath.core;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;

public class FillInAtom extends Atom {

	private String mText;
	private String textStyle;

	public FillInAtom(String text) {
		this.mText = text;
	}

	@Override
	public Box createBox(TeXEnvironment env) {
		if (textStyle == null) {
			String ts = env.getTextStyle();
			if (ts != null) {
				textStyle = ts;
			}
		}
		boolean smallCap = env.getSmallCap();
		CString ch = getString(env.getTeXFont(), env.getStyle(), smallCap);
		Box box = new FillInBox(ch);
		if (smallCap && Character.isLowerCase('0')) {
			// We have a small capital
			box = new ScaleBox(box, 0.8f, 0.8f);
		}
		return box;
	}
	
	private CString getString(TeXFont tf, int style, boolean smallCap) {
		if (textStyle == null) {
			return tf.getDefaultString(mText, style);
		} else {
			return tf.getString(mText, textStyle, style);
		}
	}

	public static class FillInBox extends Box {

		private final CStringFont cf;
		private final float size;
		private CString cString;
		private RectF mRect = new RectF();
		
		public FillInBox(CString c){
			super();
			this.cString = c;
			cf = c.getStringFont();
			size = c.getMetrics().getSize();
			width = c.getWidth();
			height = c.getHeight();
			depth = c.getDepth();
		}

		private float mX, mY;
		
		@Override
		public void draw(Canvas g2, float x, float y) {
			this.mX = x;
			this.mY = y;

			drawDebug(g2, x, y);
			g2.save();
			g2.translate(x, y);
			Typeface font = FontInfo.getFont(cf.fontId);
			if (size != 1) {
				g2.scale(size, size);
			}
			Paint st = AjLatexMath.getPaint();
			st.setTextSize(TeXFormula.PIXELS_PER_POINT);
			st.setTypeface(font);
			st.setAntiAlias(true);
			st.setStrokeWidth(0);

			if (focus) {
				mRect.set(0, -(height) - 0.5f, (int)width, 0.5f);
				st.setStyle(Style.STROKE);
				g2.drawRect(mRect, st);
			}

			st.setStyle(Style.FILL);
			g2.drawText(cf.c, 0.0f, -0.0f, st);
			g2.restore();
		}

		@Override
		public int getLastFontId() {
			return cf.fontId;
		}

		private RectF mRectF = new RectF();

		public RectF getVisibleRect() {
			mRectF.set(mX, mY - height - 0.5f, mX + width, mY + 0.5f);
			Log.v("yangzc", mRectF.toString());
			return mRectF;
		}

		private boolean focus;
		public void setFocus(boolean focus){
			this.focus = focus;
		}
	}
}
