package com.lll.dcdemo.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lll.dcdemo.R;


public class AddSubLayout extends LinearLayout implements View.OnClickListener {
 
 
    private TextView mAddBtn,mSubBtn;
    private TextView mNumText;
    private AddSubListener addSubListener;
 
    public AddSubLayout(Context context) {
        super(context);
        initView();
    }
 
    public AddSubLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
 
    public AddSubLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
 
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AddSubLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }
 
    private void initView(){
        //加载layout布局，第三个参数ViewGroup一定写成this
        View view = View.inflate(getContext(), R.layout.car_add_sub_layout,this);
 
        mAddBtn = view.findViewById(R.id.add);
        mSubBtn = view.findViewById(R.id.sub);
        mNumText = view.findViewById(R.id.text_number);
        mAddBtn.setOnClickListener(this);
        mSubBtn.setOnClickListener(this);
 
    }
 
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
 
        int width = r-l;//getWidth();
        int height = b-t;//getHeight();
 
    }
 
    @Override
    public void onClick(View v) {
        int number = Integer.parseInt(mNumText.getText().toString());
 
        switch (v.getId()){
            case R.id.add:
                number++;
                mNumText.setText(number+"");
                break;
            case R.id.sub:
                if (number==1){
                    Toast.makeText(getContext(),"数量不能小于1",Toast.LENGTH_LONG).show();
                    return;
                }
                number--;
                mNumText.setText(number+"");
                break;
        }
        if (addSubListener!=null){
            addSubListener.addSub(number);
        }
    }
 
    public void setCount(int count) {
        mNumText.setText(count+"");
    }
 
    public void setAddSubListener(AddSubListener addSubListener) {
        this.addSubListener = addSubListener;
    }
 
    public interface AddSubListener{
        void addSub(int count);
    }
}
