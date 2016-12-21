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
import com.fanziwen.main.bean.SubtitlesBean;
import com.fanziwen.utils.SPUtils;
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

    //头部下拉的列表
    @ViewInject(R.id.my_list_view)
    private ListView myListView;//类型1、类型2等

    private List<SubtitlesBean> beanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getData();
    }

    private void initView() {
        ProgressUtil.show(this, "加载中...");
        setActionBar("信息", R.drawable.nav_return, 0);
        houseType.setText("性质");
        //设置筛选数据
        houseType.setBeanList(beanList);
        houseType.setOnClickListener(this);
        houseType1.setText("类型");
        houseType1.setOnClickListener(this);
        itemViewList.add(houseType);
        itemViewList.add(houseType1);
        myListView.setOnItemClickListener(this);
    }

    private void getData() {
        RequestParams params = new RequestParams(Const.WuYe.URL_JOP_PER);
        for (int i = 0; i < itemViewList.size(); i++) {
            int val = itemViewList.get(i).getVal();
            if (i == 0) {
//                params.addParameter(itemsBeanList.get(i).getFieldname(), val);
            } else if (i == 1) {
//                    params.addParameter(itemsBeanList.get(i).getFieldname(), "");
            }
        }
        params.addParameter("appId", 110);
        params.addParameter("userId", SPUtils.get(this, Const.SPDate.ID, -1));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
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
        itemView.setBeanList(itemView.getBeanList());
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    class MyAdapter extends BaseAdapter {

        private String TAG;

        public MyAdapter(String title) {
            TAG = title;
        }

        @Override
        public int getCount() {
            return subtitles.size();
        }

        @Override
        public Object getItem(int position) {
            return subtitles.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_popup_window_item, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv.setText(subtitles.get(position).getName());
            for (int i = 0; i < subtitles.size(); i++) {
                if (subtitles.get(position).getName().equals(TAG)) {
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
