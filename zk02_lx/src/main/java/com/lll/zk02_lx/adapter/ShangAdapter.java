package com.lll.zk02_lx.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lll.zk02_lx.R;
import com.lll.zk02_lx.bean.GouBean;

import java.util.List;

public class ShangAdapter extends BaseQuickAdapter<GouBean.DataBean,BaseViewHolder> {

    //创建接口

    OnGoodItemClickListenerClick onGoodItemClickListenerClick;

    public void setOnGoodItemClickListenerClick(OnGoodItemClickListenerClick onGoodItemClickListenerClick) {
        this.onGoodItemClickListenerClick = onGoodItemClickListenerClick;
    }

    public interface OnGoodItemClickListenerClick{
        public void CallBack();
    }

    public ShangAdapter(int layoutResId, @Nullable List<GouBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final GouBean.DataBean item) {
        helper.setText(R.id.text_name,item.getSellerName());
        //避免焦点抢占
        final CheckBox checkbox_name = helper.getView(R.id.checkbox_name);
        checkbox_name.setOnCheckedChangeListener(null);
         checkbox_name.setChecked(item.getGoodListChecked());

        //子条目
        RecyclerView recyclerView_good = helper.getView(R.id.recycler_list);
        List<GouBean.DataBean.ListBean> goodlist = item.getList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView_good.setLayoutManager(linearLayoutManager);
        final GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.item_goods,goodlist);
        recyclerView_good.setAdapter(goodsAdapter);
        goodsAdapter.notifyDataSetChanged();

        //内部控制外部
        //内部控制外部
        goodsAdapter.setOnGoodsItemClickListener(new GoodsAdapter.OnGoodsItemClickListener() {
            @Override
            public void CallBack() {
                boolean result =true;
                for (int i = 0; i <item.getList().size(); i++) {
                    result = result&item.getList().get(i).getGoodsListChecked();
                }
                checkbox_name.setChecked(result);
                goodsAdapter.notifyDataSetChanged();
                onGoodItemClickListenerClick.CallBack();
            }
        });

        //商家控制内部
        checkbox_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <item.getList().size(); i++) {
                    item.getList().get(i).setGoodsListChecked(checkbox_name.isChecked());
                }
                item.setGoodListChecked(checkbox_name.isChecked());
                notifyDataSetChanged();
                onGoodItemClickListenerClick.CallBack();
            }
        });
}
}

