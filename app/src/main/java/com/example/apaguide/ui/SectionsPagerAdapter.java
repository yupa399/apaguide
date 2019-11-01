package com.example.apaguide.ui;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.apaguide.R;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private int categoryId;    //  Book, Web Page, magazine, Journal

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    public void setCategory(int categoryId){
        this.categoryId = categoryId;
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceholderFragment.newInstance(categoryId, position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;// Show 3 total pages.
    }
}
