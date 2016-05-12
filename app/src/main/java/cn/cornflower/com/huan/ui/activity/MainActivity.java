package cn.cornflower.com.huan.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.adapter.GridAadapter;
import cn.cornflower.com.huan.entity.MainGridItem;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.gv)
    GridView gv;
    private GridAadapter gridAdapter;
    private List<MainGridItem> list;
    private long mkeyTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setToolbar();
        initData();
        initListener();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    private void initData() {
        list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            MainGridItem ma = new MainGridItem();
            switch (i){
                case 1:
                    ma.setType(1);
                    ma.setName(getResources().getString(R.string.booking_decalaraion));
                    ma.setResouce(getResources().getDrawable(R.drawable.ic_apply_black_24dp));
                    break;
                case 2:
                    ma.setType(2);
                    ma.setName(getResources().getString(R.string.search));
                    ma.setResouce(getResources().getDrawable(R.drawable.ic_search_black_24dp));
                    break;
                case 3:
                    ma.setType(3);
                    ma.setName(getResources().getString(R.string.task_send));
                    ma.setResouce(getResources().getDrawable(R.drawable.ic_send_black_24dp));
                    break;
                case 4:
                    ma.setType(4);
                    ma.setName(getResources().getString(R.string.task_my));
                    ma.setResouce(getResources().getDrawable(R.drawable.ic_local_taxi_black_24dp));
                    break;
                default:
                    break;
            }
            list.add(ma);
        }
        gridAdapter = new GridAadapter(this, list);
        gv.setAdapter(gridAdapter);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.main));
        setSupportActionBar(toolbar);
    }

    private void initListener() {
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              int type =  list.get(i).getType();
                Intent in =new Intent();

                switch (type) {
                    case 1:
                        in.setClass(MainActivity.this,CommitApplicationActivity.class);
                        break;
                    case 2:
                        in.setClass(MainActivity.this,QueryActivity.class);
                        break;
                    case 3:
                        in.setClass(MainActivity.this,TaskListActivity.class);
                        break;
                    case 4:
                        in.setClass(MainActivity.this,TaskActivity.class);
                        break;
                    default:
                        break;
                }
                startActivity(in);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_my) {
            Intent intent = new Intent(MainActivity.this, MyActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mkeyTime) > 2000) {
                mkeyTime = System.currentTimeMillis();
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
            } else {
                finish();
                System.exit(0);
            }
            return true; }
            return super.onKeyDown(keyCode, event);

    }
}
