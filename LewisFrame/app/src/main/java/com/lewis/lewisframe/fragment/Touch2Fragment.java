package com.lewis.lewisframe.fragment;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lewis.lewisframe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Touch2Fragment extends Fragment {

    int color= Color.GRAY;

    @SuppressLint("ValidFragment")
    public Touch2Fragment(int color) {
        this.color = color;
    }

    ViewGroup rootview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = (ViewGroup) inflater.inflate(R.layout.fragment_touch2, container, false);
        rootview.setBackgroundColor(color);
        return rootview;
    }

}
