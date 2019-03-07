package com.lll.weidu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lll.weidu.R;

public class JikeLikeView extends RelativeLayout {
    private ImageView likeImage;
    private ImageView shineImage;
    private JikeTextView mJikeTextView;
    private int curCount = 0; // 当前点赞数量
    public JikeLikeView(Context context) {
        super(context);
    }

    public JikeLikeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JikeLikeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    {
        View view = View.inflate(getContext(), R.layout.jike_like_view, null);
        addView(view);
        likeImage = findViewById(R.id.jike_img);
        shineImage = findViewById(R.id.jike_shine);
        mJikeTextView = findViewById(R.id.jike_text);
    }
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        RelativeLayout.LayoutParams params = (LayoutParams) getLayoutParams();
        params.width = LayoutParams.WRAP_CONTENT;
        params.height = LayoutParams.WRAP_CONTENT;
        params.setMargins(20, 20, 20, 20);
    }

    public void setCurCount(int curCount) {
        this.curCount = curCount;
        mJikeTextView.setCurCount(curCount);
    }
    // 点赞, 默认自增1
    public void addLikeCount() {
        mJikeTextView.addCurCount();
        curCount++;
        likeImage.setImageResource(R.drawable.jike_like_selected);
        likeImage.setScaleX(0.7f);
        likeImage.setScaleY(0.7f);
        likeImage.animate().setDuration(300).scaleY(1f).scaleX(1f).setInterpolator(new
                OvershootInterpolator()).start();
        shineImage.setVisibility(VISIBLE);
        shineImage.setImageResource(R.drawable.jike_like_selected_shining);
        shineImage.setScaleY(0.2f);
        shineImage.setScaleX(0.2f);
        shineImage.animate().setDuration(300).scaleX(1f).scaleY(1f).setInterpolator(new
                OvershootInterpolator()).start();
    }
    // 取消点赞, 默认自减1
    public void decentLikeCount() {
        mJikeTextView.decentCurCount();
        curCount--;
        likeImage.setImageResource(R.drawable.jike_like_unselected);
        likeImage.setScaleX(0.7f);
        likeImage.setScaleY(0.7f);
        likeImage.animate().setDuration(300).scaleY(1f).scaleX(1f).setInterpolator(new
                OvershootInterpolator()).start();
        shineImage.animate().setDuration(100).scaleX(0).scaleY(0).start();
    }
}
