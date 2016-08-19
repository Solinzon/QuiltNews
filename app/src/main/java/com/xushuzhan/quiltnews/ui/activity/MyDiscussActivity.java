package com.xushuzhan.quiltnews.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.presenter.MyDiscussPresenter;
import com.xushuzhan.quiltnews.ui.adapter.MyDiscussAdapter;
import com.xushuzhan.quiltnews.ui.iview.IMyDiscussView;

public class MyDiscussActivity extends AppCompatActivity implements IMyDiscussView {
    EasyRecyclerView easyRecyclerView;
    MyDiscussAdapter myDiscussAdapter;
    MyDiscussPresenter myDiscussPresenter;
    ImageButton ReadMode;
    ImageButton back;
    TextView titleToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_discuss);
        initView();
        initData();
        myDiscussPresenter = new MyDiscussPresenter(myDiscussAdapter);
        myDiscussPresenter.showNewsDiscussList();
    }



    private void initView() {
    easyRecyclerView = (EasyRecyclerView) findViewById(R.id.recycler_view_my_discuss);
        ReadMode = (ImageButton) findViewById(R.id.ib_toobar_read_mode);
        ReadMode.setVisibility(View.INVISIBLE);

        back= (ImageButton) findViewById(R.id.ib_toolbar_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        titleToolbar.setVisibility(View.INVISIBLE);
    }

    private void initData() {
        myDiscussAdapter = new MyDiscussAdapter(MyDiscussActivity.this);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(MyDiscussActivity.this));
        easyRecyclerView.setAdapter(myDiscussAdapter);

        myDiscussAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MyDiscussActivity.this, "抱歉，暂时不能跳转", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(MyDiscussActivity.this, content, Toast.LENGTH_SHORT).show();
    }
}
