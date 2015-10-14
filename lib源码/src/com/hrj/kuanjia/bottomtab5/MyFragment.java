package com.hrj.kuanjia.bottomtab5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

/**
 * �����õ�fragment
 * 
 * @author ���ٽ�
 * 
 */
public class MyFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=new View(getActivity());
		v.setLayoutParams(new LayoutParams(0, 0));
		return v;
	}

}
