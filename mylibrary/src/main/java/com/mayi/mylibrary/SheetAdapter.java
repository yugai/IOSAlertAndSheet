package com.mayi.mylibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * 作者 by yugai 时间 16/9/12.
 * ＊ 邮箱 784787081@qq.com
 */
public class SheetAdapter extends BaseAdapter{
    List<String> datas;
    List<Integer> colors;
    boolean hasTitle;
    Context mContext;

    public SheetAdapter(List<String> datas, List<Integer> colors,boolean hasTitle,Context context) {
        this.datas = datas;
        this.colors=colors;
        this.hasTitle=hasTitle;
        mContext = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) LayoutInflater.from(mContext).inflate(R.layout.sheet_item,null);
        if (position==0&&!hasTitle){
            view.setBackgroundResource(R.drawable.actionsheet_top_selector);
        }
        if (position==getCount()-1){
            view.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
        }
        view.setText(datas.get(position));
        view.setTextColor(colors.get(position));
        return view;
    }
}
