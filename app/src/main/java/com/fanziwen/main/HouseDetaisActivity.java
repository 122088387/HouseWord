package com.fanziwen.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanziwen.base.BaseActivity;
import com.fanziwen.houseword.R;
import com.fanziwen.main.bean.HouseBean;
import com.squareup.picasso.Picasso;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 房屋详情
 */

@ContentView(R.layout.activity_house_details)
public class HouseDetaisActivity extends BaseActivity implements View.OnClickListener {

    HouseBean.DataBean dataBean;

    @ViewInject(R.id.house_details_image)
    private ImageView house_details_image;

    @ViewInject(R.id.house_details_address)
    private TextView house_details_address;

    @ViewInject(R.id.house_details_intrduce)
    private TextView house_details_intrduce;
    @ViewInject(R.id.house_details_type)
    private TextView house_details_type;
    @ViewInject(R.id.house_details_quality)
    private TextView house_details_quality;

    //预定
    @ViewInject(R.id.btn_order)
    private TextView btn_order;
    //路线
    @ViewInject(R.id.btn_route)
    private TextView btn_route;
    //周边
    @ViewInject(R.id.btn_surround)
    private TextView btn_surround;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBean = (HouseBean.DataBean) getIntent().getExtras().getSerializable("bean");
        setActionBar(dataBean.getName(), R.drawable.nav_return, 0);
        btn_order.setOnClickListener(this);
        btn_route.setOnClickListener(this);
        btn_surround.setOnClickListener(this);
        String url = dataBean.getUrl();
        if (url != null && url.length() > 0) {
            Picasso.with(this).load(url).into(house_details_image);
        } else {
            Picasso.with(this).load(R.drawable.default_png).into(house_details_image);
        }
        house_details_address.setText("地址：" + dataBean.getAddress());
        house_details_intrduce.setText("描述：" + dataBean.getIntroduce());
        house_details_type.setText("类型：" + dataBean.getHouseTypeName());
        house_details_quality.setText("性质：" + dataBean.getUseTypeName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_order://预定
                String phone = dataBean.getPhone();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.btn_route://路线指引
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", dataBean);
                openActivty(this, RouteActivity.class, bundle, null);
                break;
            case R.id.btn_surround://周边服务
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("bean", dataBean);
                openActivty(this, SurroundServiceActivity.class, bundle1, null);
                break;
        }
    }
}
