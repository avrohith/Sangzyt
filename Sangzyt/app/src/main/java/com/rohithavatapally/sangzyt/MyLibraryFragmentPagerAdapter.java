package com.rohithavatapally.sangzyt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rohithavatapally.sangzyt.mylibrary.SongsFragment;

/**
 * Created by RohithAvatapally on 6/27/15.
 */
public class MyLibraryFragmentPagerAdapter extends FragmentPagerAdapter {

    public MyLibraryFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new SongsFragment();
            case 1: return new TabTwoFragment();
            case 2: return new TabThreeFragment();
            default: return new SongsFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Tab 1";
            case 1: return "Tab 2";
            case 2: return "Tab 3";
            default: return "Tab 1";
        }
    }
}
