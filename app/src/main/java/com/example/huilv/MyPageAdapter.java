package com.example.huilv;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPageAdapter extends FragmentPagerAdapter {
    public MyPageAdapter(FragmentManager manager){
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new OneFragment();
        }else if(position==1){
            return new TwoFragment();
        }else{
            return new ThreeFragment();
        }
    }
    @Override
    public CharSequence getPageTitle(int position){
        return "Title"+position;
    }
    public int getCount() {
        return 3;
    }
}
