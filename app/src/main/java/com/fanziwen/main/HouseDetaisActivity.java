package com.fanziwen.main;

import android.os.Bundle;

import com.fanziwen.base.BaseActivity;
import com.fanziwen.houseword.R;
import com.fanziwen.main.bean.HouseBean;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/21
 */

public class HouseDetaisActivity extends BaseActivity {

    HouseBean.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBar("详情", R.drawable.nav_return, 0);
        dataBean = (HouseBean.DataBean) getIntent().getExtras().getSerializable("bean");

    }
}
