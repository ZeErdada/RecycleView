package com.example.recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现RecycleView多布局效果
 * 1、搭建环境（添加依赖，添加控件，初始化控件）
 * 2、初始化数据
 * 3、创建适配器
 * 4、设置适配器，以及布局管理器
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    //创建一个集合存放Bean类
    private List<DataModel> list = new ArrayList<>();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);

        //初始化
        initData();
        //创建适配器
        DemoAdapter demoAdapter = new DemoAdapter(this, list);
        recyclerView.setAdapter(demoAdapter);
        //设置布局管理器   参数1：上下文 参数2：规定横着或者竖着 参数3：正着还是倒着
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //listView与GridView效果混排，无论一行两列还是一行一列都是GridLayoutManager
        //做了一个监听器，根据不同类型，显示一行一列还是一行两列
        final GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //拿一下当前item是什么类型
                int type = recyclerView.getAdapter().getItemViewType(position);
                //根据类型，决定GridLayoutManager，一行占用几列
                if (type == DataModel.TYPE_THREE) {
                    return manager.getSpanCount();
                } else {
                    return 1;
                }
            }
        });

    }

    int colors[] = {android.R.color.holo_blue_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light};

    /**
     * Type：  分类型
     * String  name
     * String  content
     * Color
     */
    private void initData() {

        //创建20条Item
        for (int i = 0; i < 20; i++) {
//            int type = (int) (Math.random() * 3 + 1);
            int type;
            if (i<5||(i>15 && i<20) ){
                type=1;
            }else if (i<10 || i>20){
                type=2;
            }else {
                type=3;
            }
            //创建Bean类
            DataModel data = new DataModel();
            //实际开发中，解析服务器拿到的数据
            data.avatarColor = colors[type - 1];
            data.type = type;
            data.name = "Name" + i;
            data.content = "Content" + i;
            data.contentColor = colors[(type + 1) % 3];
            list.add(data);
        }
    }
}
