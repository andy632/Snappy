package com.loop.anish.loop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.loop.anish.loop.fragment.ChatFragment;
import com.loop.anish.loop.fragment.EmptyFragment;
import com.loop.anish.loop.fragment.StoryFragment;

/**
 * Created by Admin on 02-12-2017.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){

            case 0 :
                return ChatFragment.create();
            case 1 :
                return EmptyFragment.create();
            case 2 :
                return StoryFragment.create();

        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
