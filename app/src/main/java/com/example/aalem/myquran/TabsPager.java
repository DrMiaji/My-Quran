package com.example.aalem.myquran;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabsPager extends FragmentStatePagerAdapter {

    String[] titles = {"Chapter", "Juz'","Hizb","Manzil","Bookmarks"};

    public TabsPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ChapterFragment chapterFragment = new ChapterFragment();
                return chapterFragment;
            case 1:
                JuzFragment juzFragment = new JuzFragment();
                return juzFragment;
            case 2:
                HizbFragment hizbFragment = new HizbFragment();
                return hizbFragment;
            case 3:
                ManzilFragment manzilFragment = new ManzilFragment();
                return manzilFragment;
            case 4:
                BookmarksFragment bookmarksFragment = new BookmarksFragment();
                return bookmarksFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
