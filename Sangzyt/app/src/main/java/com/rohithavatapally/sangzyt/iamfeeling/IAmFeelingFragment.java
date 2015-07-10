package com.rohithavatapally.sangzyt.iamfeeling;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rohithavatapally.sangzyt.MyLibraryFragmentPagerAdapter;
import com.rohithavatapally.sangzyt.R;

/**
 * Created by RohithAvatapally on 7/4/15.
 */
public class IAmFeelingFragment extends Fragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MyLibraryFragmentPagerAdapter mMyLibraryFragmentPagerAdapter;

    public IAmFeelingFragment newInstance(Bundle bundle){
        IAmFeelingFragment iAmFeelingFragment = new IAmFeelingFragment();
        iAmFeelingFragment.setArguments(bundle);
        return iAmFeelingFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_iam_feeling, container, false);

        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mMyLibraryFragmentPagerAdapter = new MyLibraryFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mMyLibraryFragmentPagerAdapter);

        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }
}
