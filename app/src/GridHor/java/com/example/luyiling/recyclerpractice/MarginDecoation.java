package com.example.luyiling.recyclerpractice;

import android.graphics.Rect;
import android.support.annotation.IntRange;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by luyiling on 2018/10/18
 * <p>
 * TODO:
 *
 * <IMPORTANT></IMPORTANT>
 */
public class MarginDecoation extends RecyclerView.ItemDecoration {
    int margin;

    public MarginDecoation(@IntRange(from=0) int margin) {
        super();
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = margin;
        }
        outRect.left = margin;
        outRect.right = margin;
        outRect.bottom = margin;
    }
}
