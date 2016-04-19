package cn.cornflower.com.huan.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.cornflower.com.huan.R;
import cn.cornflower.com.huan.view.ClearEditText;

public class CommitApplicationActivity extends BaseActivity {

    @InjectView(R.id.tv_phone)
    AppCompatTextView tvPhone;
    @InjectView(R.id.et_amount)
    ClearEditText etAmount;

    @InjectView(R.id.tv_s)
    TextView tvS;
    @InjectView(R.id.tv_m)
    TextView tvM;
    @InjectView(R.id.tv_l)
    TextView tvL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_commit_application);
        ButterKnife.inject(this);

        setToolbar();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.booking_decalaraion));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_send_people, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id ==R.id.action_check_people) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @OnClick(R.id.send)
//    public void onClick() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }

    int s,m,l;
    @OnClick({R.id.iv_s_p, R.id.iv_s_n, R.id.iv_m_p, R.id.iv_m_n, R.id.iv_l_p, R.id.iv_l_n})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_s_p:
                s++ ;
                break;
            case R.id.iv_s_n:
                if(s<=0){
                  s=0;
                }else{
                    s--;
                }

                break;
            case R.id.iv_m_p:
                m++;
                break;
            case R.id.iv_m_n:
                if(m<=0){
                    m=0;
                }else{
                    m--;
                }
                break;
            case R.id.iv_l_p:
                l++;
                break;
            case R.id.iv_l_n:
                if(l<=0){
                    l = 0;
                }else{
                    l--;
                }
                break;

        }
        tvS.setText(String.valueOf(s));
        tvM.setText(String.valueOf(m));
        tvL.setText(String.valueOf(l));
    }

}
