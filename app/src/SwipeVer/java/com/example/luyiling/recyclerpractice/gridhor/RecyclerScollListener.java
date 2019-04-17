package com.example.luyiling.recyclerpractice.gridhor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by luyiling on 2019/3/20
 * <p>
 * TODO:
 *
 * <IMPORTANT></IMPORTANT>
 */
public class RecyclerScollListener extends GestureDetector.SimpleOnGestureListener {
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        return super.onScroll(e1, e2, distanceX, distanceY);
    }
}
