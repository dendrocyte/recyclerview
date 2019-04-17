package com.example.luyiling.recyclerpractice.shortcut;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.luyiling.recyclerpractice.R;
import com.example.luyiling.recyclerpractice.card.CardData;
import com.example.luyiling.recyclerpractice.card.CardHolder;

import java.util.List;

/**
 * Created by luyiling on 2019/4/16
 * <p>
 * TODO:
 *
 * <IMPORTANT></IMPORTANT>
 */
public class ShortcutAdapter extends BaseQuickAdapter<ShortcutData, ShortcutHolder>{

    public ShortcutAdapter(int layoutResId, @Nullable List<ShortcutData> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(final ShortcutHolder helper, final ShortcutData item) {
        helper.setText(R.id.tVitem, item.getId())
                .setImageResource(R.id.iVicon, R.drawable.ic_launcher_foreground)
                .setBackgroundColor(R.id.rLframe, Color.RED);

        helper.getView(R.id.rLframe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"click:"+item.getId());
                //get current position:
                Log.e(TAG, "position:"+helper.getLayoutPosition());
            }
        });
    }
}


