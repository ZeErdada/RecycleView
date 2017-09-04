package com.example.administrator.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/2.
 * 1、继承RecyclerView.adapter
 * 2、写ViewHoder
 * 3、在继承RecylerView.adapter的类泛型定义为这个ViewHolder
 * 4、创建构造方法，得到外界的上下文，数据传到适配器中
 * 5、onCreateViewHolder创建View对象及viewHolder对象
 * 6、在onBindViewHolder中根据条目位置绑定数据
 * 7、
 */

public class MyRecylerViewAdapter extends RecyclerView.Adapter<MyRecylerViewAdapter.MyViewHolder> {
    private  final Context context;
    private ArrayList<String> datas;
    private List<Integer> mHeights = new ArrayList<>();
    //通过构造函数得到外界的上下文数据
    public MyRecylerViewAdapter(Context context, ArrayList<String> datas) {
        this.context = context;
        this.datas = datas;
    }
    //相当于Getview方法，创建View对象及viewHolder对象
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //将一个Xml布局转为view对象
        View itemview = View.inflate(context, R.layout.item_recyclerview, null);
        //返回我们的ViewHolder
        MyViewHolder myViewHolder = new MyViewHolder(itemview);
        return myViewHolder;
    }

    //想当于getview绑定数量不符的代码，数据和view绑定
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //根据item位置得到对应的数据
        String data = datas.get(position);
        //通过holer 对象 给控件设置数据
        holder.tv_title.setText(data);


        // 随机高度, 模拟瀑布效果.
        if (mHeights.size() <= position) {
            mHeights.add((int) (300 + Math.random() * 100));
        }
        //将高度设置给控件
        ViewGroup.LayoutParams layoutParams = holder.iv_icon.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.iv_icon.setLayoutParams(layoutParams);

    }

    //决定RecylerView条目的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //添加Item
    public void addData(int i, String name) {
        datas.add(i,name);
        notifyItemChanged(i);
    }

    public void removeData(int i) {
        //集合中删除数据
        datas.remove(i);
        //删除条目位置
        notifyItemRemoved(i);
        //刷新适配器
        notifyItemChanged(i);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        //控件对象
        private ImageView iv_icon;
        private TextView tv_title;

        public MyViewHolder(View itemView) {
            super(itemView);
          iv_icon = itemView.findViewById(R.id.iv_icon);
          tv_title = itemView.findViewById(R.id.tv_title);
            //设置点击事件比较low的方式
//            iv_icon.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    //获取用户点击Item的位置
//                    Toast.makeText(context,"你好啊",Toast.LENGTH_SHORT).show();
//                }
//            });

            //设置点击事件用接口的方式
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (monItemClickListener!=null){
                        monItemClickListener.onItemClick(view,datas.get(getPosition()));
                    }
                }
            });

        }
    }
    //RecylerView点击事件接口
    interface  OnItemClickListener{
        //抽象方法，点击时回调  View 点击对象 data 点击时数据
        void onItemClick(View view,String data);
    }
    private  OnItemClickListener monItemClickListener;
    //设置RecylerView的某个监听
    public  void  setOnItemClickListener(OnItemClickListener monItemClickListener){
        this.monItemClickListener = monItemClickListener;
    };
}
