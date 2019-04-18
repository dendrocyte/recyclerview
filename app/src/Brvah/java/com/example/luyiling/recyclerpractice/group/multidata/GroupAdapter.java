package com.example.luyiling.recyclerpractice.group.multidata;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.example.luyiling.recyclerpractice.R;

import java.util.List;

/**
 * Created by luyiling on 2019/4/17
 * <p>
 * TODO:
 *
 * <IMPORTANT></IMPORTANT>
 */
public class GroupAdapter extends BaseMultiItemQuickAdapter<GroupMultiData, GroupHolder> { //different parent class

    public GroupAdapter(List<GroupMultiData> data) {
        super(data);
        addItemType(GroupMultiData.HEADER, R.layout.header);
        addItemType(GroupMultiData.ITEM_TXT, R.layout.aditem);
    }

    @Override
    protected void convert(final GroupHolder helper, GroupMultiData item) {
        switch (item.getItemType()){
            case GroupMultiData.HEADER:
                Log.e(TAG, "this is the header");
                helper.setText(R.id.tVheader, "Header! oh yeah");
                break;
            case GroupMultiData.ITEM_TXT:
                helper.setText(R.id.tVitem, item.getId());
                helper.getView(R.id.rLframe).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int data_index=helper.getLayoutPosition()-1;
                        Log.e(TAG,"不規則的frame click! data position:"+ data_index);
                    }
                });
                // 加载网络图片
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
                break;
        }


    }
}
