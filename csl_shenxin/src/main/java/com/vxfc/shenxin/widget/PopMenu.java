package com.vxfc.shenxin.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.util.Util;

public class PopMenu {
    private Object[] itemList;
    private Context context;
    private PopupWindow popupWindow;
    private GridView gridView;
    private PopAdapter popAdapter;
    private int index = 0;
    private SpinnerView title;

    public PopMenu(Context context, Object[] items, SpinnerView title) {
        this.title = title;
        this.context = context;
        this.itemList = items;
        View view = LayoutInflater.from(context).inflate(R.layout.pop_menu, null);
        gridView = (GridView) view.findViewById(R.id.ls_data);
        popAdapter = new PopAdapter();
        gridView.setAdapter(popAdapter);
        gridView.setFocusableInTouchMode(true);
        gridView.setFocusable(true);
        gridView.setSelection(1);

        popupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT
                ,
                LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.popupAnimation);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    //设置菜单项点击监听器
    public void setOnItemClickListener(OnItemClickListener listener) {
        gridView.setOnItemClickListener(listener);
    }

    //下拉式 弹出 pop菜单 parent 右下角
    public void showAsDropDown(View parent) {

        popupWindow.showAsDropDown(parent, 0, -Util.dip2px(context,2f));

        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);
        //刷新状态
        popupWindow.update();
    }

    //隐藏菜单
    public void dismiss() {
        popupWindow.dismiss();
    }

    public void setSelection(int index) {
        this.index = index;
        title.setItemValue(itemList[index].toString());
        popAdapter.notifyDataSetChanged();
    }

    public Object getSelectionItem() {
        return itemList[index];
    }

    public String getSelectionItemText() {
        return itemList[index].toString();
    }

    public int getSelectionIndex() {
        return this.index;
    }

    // 适配器
    private final class PopAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return itemList.length;
        }

        @Override
        public Object getItem(int position) {
            return itemList[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item, null);
            }
            TextView textView = (TextView) convertView;
            if (index == position) {
                convertView.setBackgroundResource(R.drawable.round_spinner_item_s);
                ((TextView) convertView).setTextColor(context.getResources().getColor(android.R.color.white));
            } else {
                convertView.setBackgroundResource(R.drawable.round_spinner_item);
                ((TextView) convertView).setTextColor(context.getResources().getColor(android.R.color.black));
            }
            textView.setText(itemList[position].toString());
            return convertView;
        }
    }
}
