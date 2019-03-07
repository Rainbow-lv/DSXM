package com.lll.yuekao_moni.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.yuekao_moni.R;
import com.lll.yuekao_moni.app.MyApp;
import com.lll.yuekao_moni.bean.Goods;
import com.lll.yuekao_moni.bean.ShopCar;
import com.lll.yuekao_moni.view.AddSubLayout;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends BaseExpandableListAdapter {
    List<ShopCar> mlist = new ArrayList<>();
    private TotalPriceListener totalPriceListener;

    public void AddAll(List<ShopCar> data1) {
        if (data1 != null){
            mlist.addAll(data1);
        }
    }

    public interface TotalPriceListener {
        void totalPrice(double totalPrice);
    }

    public void setTotalPriceListener(TotalPriceListener totalPriceListener) {
        this.totalPriceListener = totalPriceListener;
    }

    @Override
    public int getGroupCount() {
        return mlist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mlist.get(groupPosition).getList().size();
    }

    //获取父条目的位置
    @Override
    public Object getGroup(int groupPosition) {
        return mlist.get(groupPosition);
    }

    //获取子条目的位置
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mlist.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //一级列表
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder gh = new GroupHolder();
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.group_list, null);
            gh.checkBox = convertView.findViewById(R.id.checkBox);
            convertView.setTag(gh);
        } else {
            gh = (GroupHolder) convertView.getTag();
        }
        //绑定数据
        final ShopCar shopCar = mlist.get(groupPosition);
        gh.checkBox.setText(shopCar.getSellerName());
        gh.checkBox.setChecked(shopCar.isCheck());
        gh.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //数据更新
                shopCar.setCheck(isChecked);
                //得到商品信息
                List<Goods> goodsList = mlist.get(groupPosition).getList();
                for (int i = 0; i < goodsList.size(); i++) {
                    goodsList.get(i).setSelected(isChecked ? 1 : 0);//商铺选中则商品必须选中
                }
                notifyDataSetChanged();
                //计算价格
                jisuanprice();
            }
        });
        return convertView;
    }


    //二级列表
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder ch = new ChildHolder();
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(),R.layout.child_list,null);
            ch.check = convertView.findViewById(R.id.car_check);
            ch.image = convertView.findViewById(R.id.sim_image);
            ch.text = convertView.findViewById(R.id.text_con);
            ch.price = convertView.findViewById(R.id.text_price);
            ch.addsub = convertView.findViewById(R.id.add_sub_layout);
            convertView.setTag(ch);
        }else {
            ch = (ChildHolder) convertView.getTag();
        }
        //绑定数据
        final Goods goods = mlist.get(groupPosition).getList().get(childPosition);
        ch.text.setText(goods.getTitle());
        ch.price.setText("￥"+goods.getPrice());
        //点击选中价格
        ch.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                goods.setSelected(isChecked?1:0);
                jisuanprice();//计算价格
            }
        });
        if (goods.getSelected() == 0){
            ch.check.setChecked(false);
        }else{
            ch.check.setChecked(true);
        }
//        String images = goods.getImages();
//        String[] split = images.split("!");
//        if (split.length>0){
//            String replace = split[0].replace("https", "http");
//            if (MyApp.getcontext() != null){
//                Glide.with(MyApp.getcontext()).load(replace).into(ch.image);
//            }
//
//        }
        String imageurl = "https" + goods.getImages().split("https")[1];
        imageurl = imageurl.substring(0, imageurl.lastIndexOf(".jpg") + ".jpg".length());
        if (MyApp.getcontext() != null){
            Glide.with(MyApp.getcontext()).load(imageurl).into(ch.image);//加载图片
        }

//        String images = mlist.get(i).getCommodityPic();//得到图片集
//        String[] split = images.split(",");//得到一个图片
//        if (split.length > 0) {
//            //将https成http  进行联网显示
//            replace = split[0].replace("https", "http");
//            Glide.with(MyApp.getContext()).load(replace).into(myHolder.go_comment_Iamge);//设置图片
//        }
        //设置商品数量
        ch.addsub.setCount(goods.getNum());
        ch.addsub.setAddSubListener(new AddSubLayout.AddSubListener() {
            @Override
            public void addSub(int count) {
                goods.setNum(count);
                jisuanprice();//计算价格
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void checkAll(boolean isChecked) {
        for (int i = 0; i < mlist.size(); i++) {//循环的商家
            ShopCar shopCar = mlist.get(i);
            shopCar.setCheck(isChecked);
            for (int j = 0; j < shopCar.getList().size(); j++) {
                Goods goods = shopCar.getList().get(j);
                goods.setSelected(isChecked?1:0);
            }
        }
        notifyDataSetChanged();
        jisuanprice();//计算价格
    }

    class GroupHolder {
        public CheckBox checkBox;
    }

    class ChildHolder {
        public CheckBox check;
        public TextView text;
        public TextView price;
        public ImageView image;
        public AddSubLayout addsub;
    }

    //计算价格
    private void jisuanprice() {
        double totalprice=0;
        for (int i=0;i<mlist.size();i++){
            ShopCar shopCar = mlist.get(i);
            for (int j = 0; j < shopCar.getList().size(); j++) {
                Goods goods = shopCar.getList().get(j);
                if (goods.getSelected() == 1){
                    //如果选中
                    totalprice = totalprice + goods.getNum()*goods.getPrice();
                }
            }
            if (totalPriceListener != null){
                totalPriceListener.totalPrice(totalprice);
            }
        }
    }

}

