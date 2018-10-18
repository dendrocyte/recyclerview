package com.example.luyiling.recyclerpractice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by luyiling on 2018/10/18
 * <p>
 * TODO: self-definition view
 *
 * <IMPORTANT></IMPORTANT>
 */
public class WrapRecyclerView extends RecyclerView {
    public WrapRecyclerView(@NonNull Context context) {
        super(context);
    }

    public WrapRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**/
    public void addHeader(View view){

    }

    public void addFooter(View view){

    }
}
