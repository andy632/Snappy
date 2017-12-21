package com.loop.anish.loop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.loop.anish.loop.R;

/**
 * Created by Admin on 02-12-2017.
 */

public class StoryFragment extends BaseFragment {

    public static StoryFragment create(){
        return new StoryFragment();
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_story;
    }

    @Override
    public void inOnCreateView(View root, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {

    }
}
