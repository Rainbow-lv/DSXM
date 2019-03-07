package com.lll.weidu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lll.weidu.adapter.SearchAdapter;
import com.lll.weidu.bean.Result;
import com.lll.weidu.bean.SearchBean;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.exception.ApiException;
import com.lll.weidu.presenter.SearchPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements XRecyclerView.LoadingListener{

    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_shop)
    TextView searchShop;
    @BindView(R.id.search_xrecy)
    XRecyclerView searchXrecy;
    @BindView(R.id.search_none)
    LinearLayout linearLayout;
    private SearchPresenter searchPresenter;
    private SearchAdapter searchAdapter;
    private int page;
    private String trim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        //创建对象
        searchPresenter = new SearchPresenter(new SearchCall());
        searchXrecy.setLayoutManager(new GridLayoutManager(this, 2));
        searchPresenter = new SearchPresenter(new SearchCall());
        searchAdapter = new SearchAdapter();
        searchXrecy.setAdapter(searchAdapter);
        searchXrecy.setLoadingMoreEnabled(true);//加载更多
        searchXrecy.setLoadingListener(this);//点击加载

    }

    @OnClick(R.id.search_shop)
    public void onViewClicked() {
        searchXrecy.refresh();
    }

    @Override
    public void onRefresh() {
        if (searchPresenter.isRunning()){
            searchXrecy.refreshComplete();
            return;
        }
        page=1;
        trim = searchEdit.getText().toString().trim();
       // Log.e("-----",trim);
        searchPresenter.reqeust(true,trim,5);
    }

    @Override
    public void onLoadMore() {
        if (searchPresenter.isRunning()){
            searchXrecy.loadMoreComplete();//隐藏加载圈
            return;
        }
        page++;
        trim = searchEdit.getText().toString().trim();
     //  Log.e("-----",trim);
        searchPresenter.reqeust(false,trim,5);
    }

    private class SearchCall implements DataCall<Result<List<SearchBean>>> {
        @Override
        public void success(Result<List<SearchBean>> result) {
            searchXrecy.loadMoreComplete();
            searchXrecy.refreshComplete();
            searchAdapter.deleteAll();
            if (result.getStatus().equals("0000")) {
                if (result.getResult().size() == 0) {//查询的list长度为0 表示未查询到
                    searchXrecy.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.GONE);
                    searchXrecy.setVisibility(View.VISIBLE);
                    searchAdapter.addList(result.getResult());
                    searchAdapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void fail(ApiException e) {
            searchXrecy.loadMoreComplete();
            searchXrecy.refreshComplete();
            Toast.makeText(getBaseContext(), "没有合适的商品!!!"+e.getCode(), Toast.LENGTH_LONG).show();
        }
    }
}
