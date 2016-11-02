package com.example.weaver.todolist;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Weaver on 2016/10/30.
 */
public abstract class ViewHolderAdapter extends BaseAdapter {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // create view holder
        ViewHolder vh;
        if (convertView == null) {
            vh = onCreateViewHolder(position, parent);
            vh.view.setTag(vh);
        }
        else{
            vh = (ViewHolder) convertView.getTag();
        }

        // bind view
        onBindViewHolder(position, vh);
        return vh.view;  //return a view here
    }

    public static abstract class ViewHolder {
        public View view;
        public ViewHolder(View view) {
            this.view = view;
        }

    }

    protected abstract ViewHolder onCreateViewHolder(int position, ViewGroup parent);
    protected  abstract void onBindViewHolder(int position, ViewHolder viewHolder);
}
