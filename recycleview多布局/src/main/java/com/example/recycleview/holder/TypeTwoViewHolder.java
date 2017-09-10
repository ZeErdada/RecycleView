package com.example.recycleview.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recycleview.DataModel;
import com.example.recycleview.R;
import com.example.recycleview.TypeAbstractViewHolder;

/**
 * Created by Administrator on 2017/9/8.
 */

public class TypeTwoViewHolder extends TypeAbstractViewHolder{
    private TextView tv1,tv2;
    private ImageView imageView;
    public TypeTwoViewHolder(View itemView) {
        super(itemView);
        tv1 = itemView.findViewById(R.id.name2);
        tv2 = itemView.findViewById(R.id.content2);
        imageView = itemView.findViewById(R.id.avatar2);
    }

    @Override
    public void bindHolder(DataModel model) {
        tv1.setText(model.name);
        tv2.setText(model.content);
        imageView.setImageResource(model.avatarColor);
    }
}
