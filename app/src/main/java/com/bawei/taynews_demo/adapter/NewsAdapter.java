package com.bawei.taynews_demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * 类用途：
 * 作者：史壮壮
 * 时间：2017/4/10 19:58
 */

public class NewsAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentlist;
    private ArrayList<String> strlist;

    public NewsAdapter(FragmentManager fm, ArrayList<Fragment> fragmentlist, ArrayList<String> strlist) {
        super(fm);
        this.fragmentlist = fragmentlist;
        this.strlist = strlist;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return fragmentlist.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strlist.get(position);
    }
}
