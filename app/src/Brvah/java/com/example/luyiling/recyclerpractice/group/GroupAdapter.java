package com.example.luyiling.recyclerpractice.group;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.luyiling.recyclerpractice.R;

import java.util.List;

/**
 * Created by luyiling on 2019/4/17
 * <p>
 * TODO:
 *
 * <IMPORTANT></IMPORTANT>
 */
public class GroupAdapter extends BaseQuickAdapter<GroupData, GroupHolder> {
    public GroupAdapter(int layoutResId, @Nullable List<GroupData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(GroupHolder helper, GroupData item) {
        helper.setText(R.id.tVitem, item.getId());
        helper.getView(R.id.rLframe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"不規則的frame click!");
            }
        });
        // 加载网络图片
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
    }
}
