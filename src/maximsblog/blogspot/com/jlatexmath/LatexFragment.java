package maximsblog.blogspot.com.jlatexmath;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import maximsblog.blogspot.com.jlatexmath.core.FillInAtom;

public class LatexFragment extends Fragment {

    private LatexView mLatexView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_latex, null);
        this.mLatexView = (LatexView) view.findViewById(R.id.latexview);
        mLatexView.setFormula(ExampleFormula.getFormulaArray()[0]);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.latex_keyboard_1).setOnClickListener(mClickListener);
        view.findViewById(R.id.latex_keyboard_2).setOnClickListener(mClickListener);
        view.findViewById(R.id.latex_keyboard_3).setOnClickListener(mClickListener);
        view.findViewById(R.id.latex_keyboard_4).setOnClickListener(mClickListener);
        view.findViewById(R.id.latex_keyboard_5).setOnClickListener(mClickListener);
        view.findViewById(R.id.latex_keyboard_6).setOnClickListener(mClickListener);
        view.findViewById(R.id.latex_keyboard_7).setOnClickListener(mClickListener);
        view.findViewById(R.id.latex_keyboard_8).setOnClickListener(mClickListener);
        view.findViewById(R.id.latex_keyboard_9).setOnClickListener(mClickListener);
        view.findViewById(R.id.latex_keyboard_star).setOnClickListener(mClickListener);
        view.findViewById(R.id.latex_keyboard_del).setOnClickListener(mClickListener);
        view.findViewById(R.id.latex_keyboard_w).setOnClickListener(mClickListener);
    }

    public static LatexFragment newInstance() {
        LatexFragment fragment = new LatexFragment();
        return fragment;
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v != null && v instanceof TextView) {
                TextView textView = (TextView) v;
                FillInAtom.FillInBox fillInBox = mLatexView.getFocusFillIn();
                if (fillInBox == null)
                    return;

                String latex = mLatexView.getLatex();
                String index = fillInBox.getIndex();
                String currentText = fillInBox.getText();

                String text = textView.getText().toString();
                if ("删除".equals(text)) {
                    if (TextUtils.isEmpty(currentText))
                        return;

                    mLatexView.setFormula(latex.replace("fillin{" + index +"}{" + currentText + "}",
                            "fillin{" + index +"}{" + currentText.substring(0, currentText.length() -1) + "}"));
//                    fillInBox.setText(currentText.substring(0, currentText.length() -1));
                } else {
//                    fillInBox.setText(currentText + text);
                    mLatexView.setFormula(latex.replace("fillin{" + index +"}{" + currentText + "}",
                            "fillin{" + index +"}{" + currentText + text + "}"));
                }
                mLatexView.relayout();
                mLatexView.postInvalidate();
            }
        }
    };
}