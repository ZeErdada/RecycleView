package com.leejz.recyclerviewrl;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    //支持下拉刷新的ViewGroup

    private PtrClassicFrameLayout mPtrFrame;
    //List数据
    private List<String> title = new ArrayList<>();
    //RecyclerView自定义Adapter
    private RvAdapter adapter;
    //添加Header和Footer的封装类
    private RecyclerAdapterWithHF mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new RvAdapter(MainActivity.this, title);
        mAdapter = new RecyclerAdapterWithHF(adapter);

        mRecyclerView.setAdapter(mAdapter);
        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_list_view_frame);
        //下拉刷新支持时间
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        //下拉刷新一些设置 详情参考文档
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        //进入Activity就进行自动下拉刷新
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 100);

        //下拉刷新
        mPtrFrame.setPtrHandler(new PtrDefaultHandler() {


            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                title.clear();
                //模拟数据
                for (int i = 0; i <= 5; i++) {
                    title.add("我最帅！！！");
                }
                //模拟联网 延迟更新列表
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                        mPtrFrame.refreshComplete();
                        mPtrFrame.setLoadMoreEnable(true);

                    }
                }, 1000);
            }
        });
        //上拉加载
        mPtrFrame.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                //模拟联网延迟更新数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //模拟数据
                        for (int i = 0; i <= 5; i++) {
                            title.add("没错，你最帅！！！");
                        }
                        mAdapter.notifyDataSetChanged();
                        mPtrFrame.loadMoreComplete(true);
                        Toast.makeText(MainActivity.this, "加载成功！", Toast.LENGTH_SHORT)
                                .show();
                    }
                }, 1000);
            }
        });
    }
}