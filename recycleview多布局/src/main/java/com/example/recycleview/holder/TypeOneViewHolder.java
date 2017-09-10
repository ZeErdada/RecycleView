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

public class TypeOneViewHolder extends TypeAbstractViewHolder{
    private ImageView avatar;
    private TextView name;

    public TypeOneViewHolder(View itemView) {
        super(itemView);
        avatar = itemView.findViewById(R.id.avatar1);
        name = itemView.findViewById(R.id.name1);
    }

    @Override
    public void bindHolder(DataModel model) {
        avatar.setImageResource(model.avatarColor);
        name.setText(model.name);
    }
}
