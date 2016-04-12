package cn.cornflower.com.huan.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.adapter.StickyListViewAdapter;
import cn.cornflower.com.huan.view.stickylistheaders.ExpandableStickyListHeadersListView;
import cn.cornflower.com.huan.view.stickylistheaders.StickyListHeadersAdapter;
import cn.cornflower.com.huan.view.stickylistheaders.StickyListHeadersListView;

public class QueryActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.stickyListView)
    ExpandableStickyListHeadersListView stickyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        ButterKnife.inject(this);

        setToolbar();
        initData();

    }

    private void initData() {
        StickyListHeadersAdapter adapter = new StickyListViewAdapter(this);
        stickyListView.setAdapter(adapter);
        stickyListView.setOnHeaderClickListener(new StickyListHeadersListView.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(StickyListHeadersListView l, View header, int itemPosition, long headerId, boolean currentlySticky) {
                if(stickyListView.isHeaderCollapsed(headerId)){

                    stickyListView.expand(headerId);
                }else {
                    stickyListView.collapse(headerId);
                }
            }
        });
    }

    private void setToolbar() {
        toolbar.setTitle(getResources().getString(R.string.search));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
