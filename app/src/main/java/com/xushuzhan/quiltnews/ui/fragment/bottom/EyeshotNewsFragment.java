package com.xushuzhan.quiltnews.ui.fragment.bottom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xushuzhan.quiltnews.R;

/**
 * Created by xushuzhan on 2016/8/15.
 */
public class EyeshotNewsFragment extends Fragment{
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_eyeshot_news,container,false);
        return view;
    }
}
