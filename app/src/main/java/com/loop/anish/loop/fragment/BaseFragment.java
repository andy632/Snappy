package com.loop.anish.loop.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Admin on 02-12-2017.
 */

public abstract class BaseFragment extends Fragment {

    private View mRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRoot = inflater.inflate(getLayoutResID(),container , false);
        inOnCreateView(mRoot, container , savedInstanceState);
        return mRoot;
    }

    @LayoutRes
    public abstract int getLayoutResID();

    public abstract void inOnCreateView(View root , @Nullable ViewGroup container, @Nullable Bundle savedInstance);
}
