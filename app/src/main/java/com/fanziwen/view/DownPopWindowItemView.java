package com.fanziwen.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanziwen.houseword.R;
import com.fanziwen.main.bean.SelectBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 下拉的控件
 */
public class DownPopWindowItemView extends RelativeLayout {

    private TextView tv;
    //为了筛选时使用
    private int val;

    private String fieldname;

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    private List<SelectBean.ItemsBean.SubtitlesBean> beanList = new ArrayList<>();

    public void setBeanList(List<SelectBean.ItemsBean.SubtitlesBean> beanList) {
        this.beanList = beanList;
    }

    public List<SelectBean.ItemsBean.SubtitlesBean> getBeanList() {
        return beanList;
    }

    public DownPopWindowItemView(Context context) {
        this(context, null);
    }

    public DownPopWindowItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DownPopWindowItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.down_pop_window_item_view, this);
        tv = (TextView) findViewById(R.id.tv_down_pop);
    }

    public void setText(String title) {
        tv.setText(title);
    }
}
