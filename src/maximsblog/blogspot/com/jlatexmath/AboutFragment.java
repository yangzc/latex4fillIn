package maximsblog.blogspot.com.jlatexmath;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LatexView view =  new LatexView(getActivity());
		view.setFormula(ExampleFormula.getFormulaArray()[0]);
		return view;
	}

	public static AboutFragment newInstance() {
		AboutFragment fragment = new AboutFragment();
		return fragment;
	}
}