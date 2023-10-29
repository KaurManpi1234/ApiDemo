package com.example.apidemo;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SearchRoverFragmentAdapter  extends FragmentPagerAdapter {


    public SearchRoverFragmentAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new SearchRoverFragment();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
