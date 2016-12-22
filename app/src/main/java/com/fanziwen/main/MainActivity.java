package com.fanziwen.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fanziwen.base.BaseActivity;
import com.fanziwen.constant.Const;
import com.fanziwen.houseword.R;
import com.fanziwen.main.adapter.HouseListAdapter;
import com.fanziwen.main.bean.HouseBean;
import com.fanziwen.main.bean.SelectBean;
import com.fanziwen.view.DownPopWindowItemView;
import com.fanziwen.view.ProgressUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    @ViewInject(R.id.house_type)
    DownPopWindowItemView houseType;

    @ViewInject(R.id.house_type1)
    DownPopWindowItemView houseType1;

    List<DownPopWindowItemView> itemViewList = new ArrayList<DownPopWindowItemView>();

    DownPopWindowItemView itemView;

    @ViewInject(R.id.zhe_zhao_ceng)
    private View translucentView;

    //头部下拉的列表
    @ViewInject(R.id.my_list_view)
    private ListView myListView;//类型1、类型2等

    MyAdapter myAdapter;
    private List<SelectBean.ItemsBean.SubtitlesBean> beanList = new ArrayList<>();

    //房屋列表
    @ViewInject(R.id.content_view)
    private ListView content_view;

    HouseListAdapter houseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setActionBar("房源信息", R.drawable.nav_return, 0);

        houseType.setText("性质");
        houseType.setOnClickListener(this);
        houseType1.setText("类型");
        houseType1.setOnClickListener(this);

        translucentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        itemViewList.add(houseType);
        itemViewList.add(houseType1);
        myListView.setOnItemClickListener(this);
        myAdapter = new MyAdapter(-1);
        myListView.setAdapter(myAdapter);

        content_view.setOnItemClickListener(houseDetailListener);
        houseAdapter = new HouseListAdapter(this);
        content_view.setAdapter(houseAdapter);
        getData();
        upLoadData();
    }

    //点击查看详情
    private AdapterView.OnItemClickListener houseDetailListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HouseBean.DataBean dataBean = houseAdapter.getList().get(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("bean",dataBean);
            openActivty(MainActivity.this, HouseDetaisActivity.class, bundle, null);
        }
    };

    /**
     * 获取头部下拉的数据
     */
    private void getData() {
        RequestParams params = new RequestParams(Const.House.URL_HOUSE_USER_APP_GET_HOUSE_FILTRATE);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                SelectBean selectBean = gson.fromJson(result, SelectBean.class);
                for (int i = 0; i < selectBean.getItems().size(); i++) {
                    itemViewList.get(i).setVal(selectBean.getItems().get(i).getSubtitles().get(0).getVal());
                    itemViewList.get(i).setText(selectBean.getItems().get(i).getSubtitles().get(0).getName());
                    itemViewList.get(i).setFieldname(selectBean.getItems().get(i).getFieldname());
                    itemViewList.get(i).setBeanList(selectBean.getItems().get(i).getSubtitles());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                ProgressUtil.close();
            }
        });

    }


    @Override
    public void onClick(View v) {
        itemView = ((DownPopWindowItemView) v);
        myAdapter.setTAG(itemView.getVal());
        beanList = itemView.getBeanList();
        myAdapter.notifyDataSetChanged();
        show();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tv = (TextView) view.findViewById(R.id.tv_list_popup_window_item);
        String msg = tv.getText().toString();
        itemView.setText(msg);
        for (int i = 0; i < beanList.size(); i++) {
            if (beanList.get(i).getName().equals(msg)) {
                itemView.setVal(beanList.get(i).getVal());
            }
        }
        upLoadData();
        dismiss();
    }

    /**
     * 获取房屋列表数据
     */
    private void upLoadData() {
        ProgressUtil.show(this, "加载中...");
        RequestParams params = new RequestParams(Const.House.URL_HOUSE_USER_APP_GET_SELLER_LIST);
        for (int i = 0; i < itemViewList.size(); i++) {
            int val = itemViewList.get(i).getVal();
            params.addParameter(itemViewList.get(i).getFieldname(), val);
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                HouseBean bean = gson.fromJson(result, HouseBean.class);
                houseAdapter.setList(bean.getData());
                houseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                ProgressUtil.close();
            }
        });
    }

    /**
     * 遮罩层和view都显示
     */
    private void show() {
        translucentView.setVisibility(View.VISIBLE);
        myListView.setVisibility(View.VISIBLE);
    }

    /**
     * 遮罩层和view都消失
     */
    private void dismiss() {
        myListView.setVisibility(View.GONE);
        translucentView.setVisibility(View.GONE);
    }


    class MyAdapter extends BaseAdapter {

        private int TAG;

        public MyAdapter(int title) {
            TAG = title;
        }

        public void setTAG(int TAG) {
            this.TAG = TAG;
        }

        @Override
        public int getCount() {
            return beanList.size();
        }

        @Override
        public Object getItem(int position) {
            return beanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_popup_window_item, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv.setText(beanList.get(position).getName());
            for (int i = 0; i < beanList.size(); i++) {
                if (beanList.get(position).getVal() == TAG) {
                    holder.tv.setTextColor(getResources().getColor(R.color.include_title));
                } else {
                    holder.tv.setTextColor(getResources().getColor(R.color.menu_item));
                }
            }
            return convertView;
        }

        class ViewHolder {
            TextView tv;

            ViewHolder(View view) {
                tv = (TextView) view.findViewById(R.id.tv_list_popup_window_item);
            }
        }
    }

}
