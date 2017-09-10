package com.example.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.recycleview.holder.TypeOneViewHolder;
import com.example.recycleview.holder.TypeThreeViewHolder;
import com.example.recycleview.holder.TypeTwoViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 * RecycleView 多布局适配器
 * 1、创建一个类继承RecycleView的adapter
 * 2、定义RecycleView.ViewHolder方便多布局
 * 3、创建三种类型的Item布局以及ViewHolder
 * 4、使用getItemViewType方法
 * 5、在OnCreateViewHolder 和 OnBindViewHolder的创建，数据的判断加载
 */
class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<DataModel> mlist = new ArrayList<>();
    private final LayoutInflater mLayouIntflater;

    public DemoAdapter(Context context, List<DataModel> list) {
        mLayouIntflater = LayoutInflater.from(context);
        mlist.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case DataModel.TYPE_ONE:
                return new TypeOneViewHolder(mLayouIntflater.inflate(R.layout.item_type_one, parent, false));
            case DataModel.TYPE_TWO:
                return new TypeTwoViewHolder(mLayouIntflater.inflate(R.layout.item_type_two, parent, false));
            case DataModel.TYPE_THREE:
                return new TypeThreeViewHolder(mLayouIntflater.inflate(R.layout.item_type_three, parent, false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        switch (getItemViewType(position)) {
//            case DataModel.TYPE_ONE:
//                ImageView avatar1 = holder.itemView.findViewById(R.id.avatar1);
//                TextView name1 = holder.itemView.findViewById(R.id.name1);
//                avatar1.setImageResource(mlist.get(position).avatarColor);
//                name1.setText(mlist.get(position).name);
//                break;
//            case DataModel.TYPE_TWO:
//                TextView name2 = holder.itemView.findViewById(R.id.name2);
//                TextView content2 = holder.itemView.findViewById(R.id.content2);
//                ImageView avatar2 = holder.itemView.findViewById(R.id.avatar2);
//                avatar2.setImageResource(mlist.get(position).avatarColor);
//                content2.setText(mlist.get(position).content);
//                name2.setText(mlist.get(position).name);
//                break;
//            case DataModel.TYPE_THREE:
//                TextView name3 = holder.itemView.findViewById(R.id.name3);
//                TextView content3 = holder.itemView.findViewById(R.id.content3);
//                ImageView avatar3 = holder.itemView.findViewById(R.id.avatar3);
//                ImageView contentImage3 = holder.itemView.findViewById(R.id.contentImage3);
//                avatar3.setImageResource(mlist.get(position).avatarColor);
//                contentImage3.setImageResource(mlist.get(position).contentColor);
//                content3.setText(mlist.get(position).content);
//                name3.setText(mlist.get(position).name);
//                break;
//            default:
//                break;
//        }
        //////////////////////////////////////////////////////////////////////////////////
        ((TypeAbstractViewHolder)holder).bindHolder(mlist.get(position));
    }

    //根据不同的position位置，返回不同的类型
    @Override
    public int getItemViewType(int position) {
        return mlist.get(position).type;
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
