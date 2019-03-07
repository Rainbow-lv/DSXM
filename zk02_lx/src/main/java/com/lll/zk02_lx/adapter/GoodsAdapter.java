package com.lll.zk02_lx.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lll.zk02_lx.R;
import com.lll.zk02_lx.bean.GouBean;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<GouBean.DataBean.ListBean,BaseViewHolder> {

    //创建接口
    OnGoodsItemClickListener onGoodsItemClickListener;

    public void setOnGoodsItemClickListener(OnGoodsItemClickListener onGoodsItemClickListener) {
        this.onGoodsItemClickListener = onGoodsItemClickListener;
    }
    public interface OnGoodsItemClickListener{
        public void CallBack();
    }

    public GoodsAdapter(int layoutResId, @Nullable List<GouBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final GouBean.DataBean.ListBean item) {
        helper.setText(R.id.text_goodsName,item.getSubhead());
        helper.setText(R.id.text_goodsPrice,"￥："+item.getPrice());
        //避免焦点抢占
        CheckBox checkbox_goods = helper.getView(R.id.checkbox_goods);
        checkbox_goods.setOnCheckedChangeListener(null);
        checkbox_goods.setChecked(item.getGoodsListChecked());

        ImageView imageView = helper.getView(R.id.image_goods);
        String images = item.getImages();
        String[] split = images.split("\\|");
        Glide.with(mContext).load(split[0]).into(imageView);

        checkbox_goods.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setGoodsListChecked(isChecked);
                onGoodsItemClickListener.CallBack();
            }
        });
//        GoodsView goodsView = helper.getView(R.id.goodView);
//
//        goodsView.setOnNumerItemClickListener(new GoodsView.OnNumerItemClickListener() {
//            @Override
//            public void jian(int number) {
//                item.setDefalutNumber(number);
//                onGoodsItemClickListener.CallBack();
//            }
//
//            @Override
//            public void add(int number) {
//                item.setDefalutNumber(number);
//                onGoodsItemClickListener.CallBack();
//            }
//        });
    }
}
