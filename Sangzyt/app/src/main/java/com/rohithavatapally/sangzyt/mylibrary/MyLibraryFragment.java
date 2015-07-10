package com.rohithavatapally.sangzyt.mylibrary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rohithavatapally.sangzyt.MyLibraryFragmentPagerAdapter;
import com.rohithavatapally.sangzyt.R;

/**
 * Created by RohithAvatapally on 7/3/15.
 */
public class MyLibraryFragment extends Fragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MyLibraryFragmentPagerAdapter mMyLibraryFragmentPagerAdapter;

    public static MyLibraryFragment newInstance(Bundle bundle){
        MyLibraryFragment myLibraryFragment = new MyLibraryFragment();
        myLibraryFragment.setArguments(bundle);
        return myLibraryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_library, container, false);

        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mMyLibraryFragmentPagerAdapter = new MyLibraryFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mMyLibraryFragmentPagerAdapter);

        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_one){
            Toast.makeText(getActivity(), "Menu One", Toast.LENGTH_LONG).show();
            Log.i("Menu", "Fragment Menu One");
        }

        if (item.getItemId() == R.id.menu_two){
            Toast.makeText(getActivity(), "Menu Two", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
