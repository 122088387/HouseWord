package com.fanziwen.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanziwen.houseword.R;
import com.fanziwen.main.bean.HouseBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王晓赛 or 2016/9/23
 */
public class HouseListAdapter extends BaseAdapter {


    private Context mContext;

    private List<HouseBean.DataBean> list = new ArrayList<>();

    public HouseListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(List<HouseBean.DataBean> list) {
        this.list = list;
    }

    public List<HouseBean.DataBean> getList() {
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.house_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HouseBean.DataBean bean = list.get(position);
        String url = bean.getUrl();
        if (url != null && url.length() > 0) {
            Picasso.with(mContext).load(url).into(holder.house_head);
        } else {
            Picasso.with(mContext).load(R.drawable.default_png).into(holder.house_head);
        }
        holder.house_name.setText(bean.getName());
        holder.house_address.setText("地址：" + bean.getAddress());
        holder.house_describe.setText("描述：" + bean.getIntroduce());
        return convertView;
    }

    class ViewHolder {
        ImageView house_head;
        TextView house_name;
        TextView house_address;
        TextView house_describe;

        public ViewHolder(View view) {
            house_head = (ImageView) view.findViewById(R.id.house_head);
            house_name = (TextView) view.findViewById(R.id.house_name);
            house_address = (TextView) view.findViewById(R.id.house_address);
            house_describe = (TextView) view.findViewById(R.id.house_describe);
        }
    }

}
