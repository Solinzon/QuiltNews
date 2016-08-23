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
import com.xushuzhan.quiltnews.presenter.MyCollectionPresenter;
import com.xushuzhan.quiltnews.ui.adapter.MyCollectionAdapter;
import com.xushuzhan.quiltnews.ui.iview.IMyCollectionView;

public class MyCollectionActivity extends AppCompatActivity implements IMyCollectionView {
EasyRecyclerView easyRecyclerView;
    MyCollectionAdapter adapter;
    MyCollectionPresenter myCollectionPresenter;
    ImageButton ReadMode;
    ImageButton back;
    TextView titleToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        initView();
        myCollectionPresenter = new MyCollectionPresenter(this,adapter);
        myCollectionPresenter.showNewsCollectionList();
    }

    private void initView() {
        easyRecyclerView = (EasyRecyclerView) findViewById(R.id.recycler_view_my_collection);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyCollectionAdapter(this);
        easyRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MyCollectionActivity.this, "啦啦啦", Toast.LENGTH_SHORT).show();
            }
        });

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
        titleToolbar.setText("我的收藏" +
                "");
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(MyCollectionActivity.this, content, Toast.LENGTH_SHORT).show();
    }
}
