package com.lewis.lewisframe.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.lewis.lewisframe.R;
import com.lewis.lewisframe.modle.TestModle;
import com.lewis.lewisframe.recyclerview.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        LinearLayout control_LinearLayout = (LinearLayout) findViewById(R.id.control_LinearLayout);
        for (int i = 0; i < control_LinearLayout.getChildCount(); i++) {
            control_LinearLayout.getChildAt(i).setOnClickListener(this);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //使RecyclerView保持固定的大小,这样会提高RecyclerView的性能
        //recyclerView.setHasFixedSize(true);
        initRecyclerView();
        loadItems();
    }

    private void loadItems() {
        List<TestModle> items = new ArrayList<TestModle>() {
        };
        for (int i = 0; i < 5; i++) {
            items.add(new TestModle("第" + (i + 1) + "个item", i % 4 == 0 ? TestModle.TYPE_2 : TestModle.TYPE_1));
        }
        adapter.addData(items);
    }

    private ArrayList<TestModle> loadMore() {
        ArrayList<TestModle> newitems = new ArrayList<TestModle>() {
        };
        for (int i = 0; i < 3; i++) {
            newitems.add(new TestModle("新的第" + (i + 1) + "个item", i % 4 == 0 ? TestModle.TYPE_2 : TestModle.TYPE_1));
        }
        return newitems;
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new RecyclerViewAdapter(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list:
                mLayoutManager = new LinearLayoutManager(this);
                mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(mLayoutManager);
                break;
            case R.id.listHorizontal:
                mLayoutManager = new LinearLayoutManager(this);
                mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(mLayoutManager);
                break;
            case R.id.grid:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
                gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(gridLayoutManager);
                break;
            case R.id.addItem:
                adapter.addData(loadMore(), 2);
                break;
            case R.id.addItemEnd:
                adapter.addData(loadMore());
                break;
            case R.id.refresh:
                adapter.notifyDataSetChanged();
                break;
            default:
        }
    }
}
