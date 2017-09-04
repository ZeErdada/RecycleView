package com.example.administrator.recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * RecycleView 是Android5.0的新特性，如果想让5.0以下的系统使用，必须关联兼容包
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_add;
    private Button btn_delete;
    private Button btn_list;
    private Button btn_grid;
    private Button btn_flow;
    private RecyclerView recyclerview;
    private ArrayList<String> datas;
    private MyRecylerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_list = (Button) findViewById(R.id.btn_list);
        btn_grid = (Button) findViewById(R.id.btn_grid);
        btn_flow = (Button) findViewById(R.id.btn_flow);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        //初始化数据
        initData();

        //设置RecylerView的适配器
        adapter = new MyRecylerViewAdapter(this, datas);
        recyclerview.setAdapter(adapter);
        //添加分割线
        recyclerview.addItemDecoration(new DividerListItemDecoration(this,DividerListItemDecoration.VERTICAL_LIST));
        //设置默认动画，可以自定义
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        //设置布局管理器
        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter.setOnItemClickListener(new MyRecylerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(MainActivity.this,data,Toast.LENGTH_SHORT).show();
            }
        });
        //设置点击事件
        btn_add.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_grid.setOnClickListener(this);
        btn_flow.setOnClickListener(this);
    }
    private void initData() {
        datas = new ArrayList<>();
        //准备数据集合
        for (int i = 0; i < 100; i++) {
            datas.add("Content_" + i);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add://D.添加数据
                //创建方法添加数据
                adapter.addData(0,"爱我别走");
                break;

            case R.id.btn_delete://D.删除数据
                adapter.removeData(3);
                break;

            case R.id.btn_list://设置List类型效果
                //设置布局管理器
                recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                break;

            case R.id.btn_grid://设置Grid类型效果
                recyclerview.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL,false));
                break;

            case R.id.btn_flow://设置瀑布流类型效果
                // 错列网格布局
                recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager
                        .VERTICAL));
                break;
        }

    }
}
