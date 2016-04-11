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
    public FragmentTaskAdatpter(FragmentManager fm,List<Fragment> fragmentList ) {
        super(fm);
        this.fragmentList = fragmentList;

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }


    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
