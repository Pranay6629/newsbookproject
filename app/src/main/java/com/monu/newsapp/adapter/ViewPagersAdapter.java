package com.monu.newsapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagersAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mBooksFragments = new ArrayList<>();
    private final List<String> mBooksFragmentTitle = new ArrayList<>();

    public ViewPagersAdapter(FragmentManager fm){
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mBooksFragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mBooksFragmentTitle.get(position);
    }

    @Override
    public int getCount() {
        return mBooksFragments.size();
    }
    public void addBooksFragment(Fragment fragment,String title) {
        mBooksFragments.add(fragment);
        mBooksFragmentTitle.add(title);
    }
}
