package cn.Travels_App.utils;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class CommonViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragments;
    private final String[] tabTitles;

    public CommonViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] tabTitles) {
        super(fm);
        this.mFragments = fragments;
        this.tabTitles = tabTitles;
    }

    public CommonViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> tabTitles) {
        super(fm);
        this.mFragments = fragments;
        this.tabTitles = new String[tabTitles.size()];
        for (int i = 0; i < tabTitles.size(); i++) {
            this.tabTitles[i] = tabTitles.get(i);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
