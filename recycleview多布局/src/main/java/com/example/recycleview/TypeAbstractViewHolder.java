package com.example.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/9/8.
 * 抽取基类原则
 * 1、自雷共有的，且实现相同的逻辑，抽取到基类中
 * 2、子类共有，但实现不同的逻辑，以抽象方法的形式定义到基类中。
 */

public abstract class TypeAbstractViewHolder extends RecyclerView.ViewHolder {
    public TypeAbstractViewHolder(View itemView) {
        super(itemView);
    }
    public  abstract void bindHolder(DataModel model);
}
