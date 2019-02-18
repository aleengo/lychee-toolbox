package com.aleengo.peach.toolbox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;
import lombok.Getter;

/**
 * Created by CK_ALEENGO on 15/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public abstract class ListViewAdapter<E, VH extends ListViewAdapter.ViewHolder> extends BaseAdapter
        implements PeachAdapter {

    /**
     * ViewHolder Class
     */
    public static class ViewHolder {
        private View itemView;
        public ViewHolder(View itemView) {
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = itemView;
        }
    }

    public static final long NO_ITEM_ID = -1;
    private Context mContext;
    /**
     * Elements of the Adapter
     */
    @Getter
    private List<E> items;

    public ListViewAdapter(Context mContext, List<E> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return items != null ?
                items.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return items != null ?
                items.get(position).hashCode() : NO_ITEM_ID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        VH viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(getLayoutResId(), null);
            viewHolder = (VH) onNewViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (VH) convertView.getTag();
        }
        onBindViewHolder(viewHolder, position);
        return convertView;
    }

    @Override
    public void clear() {
        if (items != null && items.size() > 0) {
            items.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * Called by BaseAdapter to display the data at the specified position. This method should
     * update the contents of the {@link ListViewAdapter.ViewHolder#itemView} to reflect
     * the item at the given position.
     * <p>
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    protected abstract void onBindViewHolder(VH holder, int position);

}
