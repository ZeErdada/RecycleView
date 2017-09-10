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

public class TypeThreeViewHolder extends TypeAbstractViewHolder {
    private ImageView iv1, iv2;
    private TextView tv1, tv2;

    public TypeThreeViewHolder(View itemView) {
        super(itemView);
        tv1 = itemView.findViewById(R.id.name3);
        tv2 = itemView.findViewById(R.id.content3);
        iv1 = itemView.findViewById(R.id.avatar3);
        iv2 = itemView.findViewById(R.id.contentImage3);
    }

    @Override
    public void bindHolder(DataModel model) {
        tv1.setText(model.name);
        tv2.setText(model.content);
        iv1.setImageResource(model.avatarColor);
        iv2.setImageResource(model.contentColor);
    }
}
