package com.bawei.taynews_demo.httrack.shoppingMall;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.taynews_demo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 类用途：
 * 作者：ShiZhuangZhuang
 * 时间：2017/4/24 9:12
 */

public class NewsAdapter extends BaseAdapter {
    private Context mContext;
    private List<NewsBean.DataBean> dataScroll;
    private ViewHolder mHolder;

    public NewsAdapter(Context mContext, List<NewsBean.DataBean> dataScroll) {
        this.mContext = mContext;
        this.dataScroll = dataScroll;
    }

    @Override
    public int getCount() {
        return dataScroll.size();
    }

    @Override
    public Object getItem(int position) {
        return dataScroll.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.layout_lists, null);
            mHolder.TITLE = (TextView) convertView.findViewById(R.id.TITLE);
            mHolder.FROMNAME = (TextView) convertView.findViewById(R.id.FROMNAME);
            mHolder.SHOWTIME = (TextView) convertView.findViewById(R.id.SHOWTIME);
            mHolder.IMAGEURL = (ImageView) convertView.findViewById(R.id.IMAGEURL);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisc(true)
                .cacheInMemory(true).build();
        mHolder.TITLE.setText(dataScroll.get(position).getTITLE());
        mHolder.SHOWTIME.setText(dataScroll.get(position).getSHOWTIME());
        mHolder.FROMNAME.setText(dataScroll.get(position).getFROMNAME());
        if (dataScroll.get(position).getIMAGEURL() != null) {
            ImageLoader.getInstance().displayImage(dataScroll.get(position).getIMAGEURL() + "", mHolder.IMAGEURL, options);
        } else {
            mHolder.IMAGEURL.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        TextView TITLE;
        TextView FROMNAME;
        TextView SHOWTIME;
        ImageView IMAGEURL;
    }
}
