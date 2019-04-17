package com.example.luyiling.recyclerpractice.card;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.luyiling.recyclerpractice.R;

import java.util.List;

/**
 * Created by luyiling on 2019/4/16
 * <p>
 * TODO:
 *
 * <IMPORTANT></IMPORTANT>
 */
public class CardAdapter extends BaseQuickAdapter<CardData, CardHolder> {

    public CardAdapter(int layoutResId, @Nullable List<CardData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CardHolder helper, final CardData item) {
        helper.setText(R.id.tVitem, item.getId())
                .setImageResource(R.id.iVitem, R.drawable.ic_launcher_foreground)
                .setBackgroundColor(R.id.rLframe, Color.RED);
        CardView cardView = helper.getView(R.id.cVframe);
        cardView.setCardBackgroundColor(item.getBackgroundColor());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"click:"+item.getId());
            }
        });

    }


}
