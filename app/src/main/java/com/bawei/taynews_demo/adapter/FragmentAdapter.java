package com.bawei.taynews_demo.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.taynews_demo.R;
import com.bawei.taynews_demo.bean.NewsBean;
import com.bawei.taynews_demo.utils.ImageXutils;

import java.util.List;


/**
 * 类用途：
 * 作者：史壮壮
 * 时间：2017/4/10 20:28
 */

public class FragmentAdapter extends BaseAdapter {
    private Context context;
    private List<NewsBean.ResultBean.DataBean> dataList;
    private ViewHolder mHolder;

    public FragmentAdapter(Context context, List<NewsBean.ResultBean.DataBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.layout_list, null);
            mHolder.title = (TextView) convertView.findViewById(R.id.list_title);
            mHolder.author_name = (TextView) convertView.findViewById(R.id.list_author_name);
            mHolder.thumbnail_pic_s = (ImageView) convertView.findViewById(R.id.list_pic_s);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.title.setText(dataList.get(position).getTitle());
        mHolder.author_name.setText(dataList.get(position).getAuthor_name());
        ImageXutils.getimage(mHolder.thumbnail_pic_s,dataList.get(position).getThumbnail_pic_s());

        return convertView;
    }

    class ViewHolder {
        ImageView thumbnail_pic_s;
        TextView title;
        TextView author_name;
    }
}
