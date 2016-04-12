package cn.cornflower.com.huan.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/4/5.
 */
public class FragmentTaskAdatpter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList ;
    private List<String> llistTitle;
    public FragmentTaskAdatpter(FragmentManager fm,
                                List<Fragment> fragmentList,
                                List<String> llistTitle ) {
        super(fm);
        this.fragmentList = fragmentList;
        this.llistTitle = llistTitle;

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }


    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return llistTitle.get(position);
    }
}
