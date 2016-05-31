package maximsblog.blogspot.com.jlatexmath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import maximsblog.blogspot.com.jlatexmath.core.AjLatexMath;
import maximsblog.blogspot.com.jlatexmath.core.FillInAtom;
import maximsblog.blogspot.com.jlatexmath.core.Insets;
import maximsblog.blogspot.com.jlatexmath.core.TeXConstants;
import maximsblog.blogspot.com.jlatexmath.core.TeXFormula;
import maximsblog.blogspot.com.jlatexmath.core.TeXIcon;

/**
 * Created by yangzc on 16/5/31.
 */
public class LatexView extends View {

    private TeXFormula mTexFormula;
    private TeXIcon mTexIcon;
    private TeXFormula.TeXIconBuilder mBuilder;

    public LatexView(Context context) {
        super(context);
        init();
    }

    public LatexView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mTexFormula = new TeXFormula();
        mBuilder = mTexFormula.new TeXIconBuilder()
                .setStyle(TeXConstants.STYLE_DISPLAY)
                .setSize(30);
    }

    public void setFormula(String latex){
        mTexFormula.setLaTeX(latex);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mBuilder != null) {
            mBuilder.setWidth(TeXConstants.UNIT_PIXEL, getWidth(), TeXConstants.ALIGN_LEFT);
            mBuilder.setIsMaxWidth(true);
            mBuilder.setInterLineSpacing(TeXConstants.UNIT_PIXEL,
                    AjLatexMath.getLeading(30));
        }
        mTexIcon = mBuilder.build();
        mTexIcon.setInsets(new Insets(5, 5, 5, 5));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        if (mTexIcon != null) {
            mTexIcon.paintIcon(canvas, 0, 0);
        }
    }

    private FillInAtom.FillInBox mFocusFillIn = null;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mTexIcon == null && mTexIcon.getSize() != 0)
            return false;

        float x = event.getX() / mTexIcon.getSize();
        float y = event.getY() / mTexIcon.getSize();

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            {
                Log.v("yangzc", "x: " + x + ", y: " + y);
                FillInAtom.FillInBox fillInBox = mTexIcon.getBox().getFillInBox(x, y);
                if (mFocusFillIn != null) {
                    mFocusFillIn.setFocus(false);
                }
                if (fillInBox != null) {
                    fillInBox.setFocus(true);
                    mFocusFillIn = fillInBox;
                }
                postInvalidate();
                break;
            }
        }
        return true;
    }
}
