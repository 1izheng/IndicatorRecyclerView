package com.yjz.indicatorrecyclerview;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;

/**
 * 仿拼拼多多横向滚动recyclerView 带指示器
 * @author yjz
 * created at 2018/12/6 上午10:58
 */

public class MainActivity extends AppCompatActivity {


    RecyclerView horiRecyclerView;
    SeekBar sbarIndicator;
    private HomeHoriTypeAdapter homeHoriTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        horiRecyclerView = findViewById(R.id.hori_recyclerView);
        sbarIndicator = findViewById(R.id.sbar_indicator);

        //设置列表可以水平滑动，两行
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        horiRecyclerView.setLayoutManager(layoutManager);
        homeHoriTypeAdapter = new HomeHoriTypeAdapter(this, new ArrayList<String>());
        homeHoriTypeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(MainActivity.this, "点击位置" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        horiRecyclerView.setAdapter(homeHoriTypeAdapter);
        sbarIndicator.setPadding(0, 0, 0, 0);
        sbarIndicator.setThumbOffset(0);

        horiRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //显示区域的高度。
                int extent = horiRecyclerView.computeHorizontalScrollExtent();
                //整体的高度，注意是整体，包括在显示区域之外的。
                int range = horiRecyclerView.computeHorizontalScrollRange();
                //已经向下滚动的距离，为0时表示已处于顶部。
                int offset = horiRecyclerView.computeHorizontalScrollOffset();
                //此处获取seekbar的getThumb，就是可以滑动的小的滚动游标
                GradientDrawable gradientDrawable = (GradientDrawable) sbarIndicator.getThumb();
                //根据列表的个数，动态设置游标的大小，设置游标的时候，progress进度的颜色设置为和seekbar的颜色设置的一样的，所以就不显示进度了。
                gradientDrawable.setSize(extent / (10), 10);
                //设置可滚动区域
                sbarIndicator.setMax((range - extent));
                if (dx == 0) {
                    sbarIndicator.setProgress(0);
                } else if (dx > 0) {
                    sbarIndicator.setProgress(offset);
                } else if (dx < 0) {
                    sbarIndicator.setProgress(offset);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

        });
    }
}
