package cn.cornflower.com.huan.ui.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.adapter.FragmentPeopleAdapter;
import cn.cornflower.com.huan.common.Constants;
import cn.cornflower.com.huan.view.sortlistview.SortModel;

/**
 * Created by Administrator on 2016/4/19.
 */
public class PeolpeFragment extends Fragment {
    @InjectView(R.id.lv_people)
    ListView lvPeople;
    private List<SortModel> list ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        ButterKnife.inject(this, view);
        initData();
        return view;
    }


    private void initData() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            list = bundle.getParcelableArrayList(Constants.PEOPLELIST);
        }else
            list =new ArrayList<>();
        lvPeople.setAdapter(new FragmentPeopleAdapter(
                getActivity(),R.layout.people_item,list));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
