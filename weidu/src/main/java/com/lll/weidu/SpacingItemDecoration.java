package com.lll.weidu;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spacing;

    public SpacingItemDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = spacing / 2;
        outRect.bottom = spacing / 2;
        outRect.left = spacing / 2;
        outRect.right = spacing / 2;
    }
}