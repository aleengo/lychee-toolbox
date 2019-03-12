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
public abstract class ListViewAdapter<E, ITEMVIEW extends ItemView<E>> extends BaseAdapter {

    public static final long NO_ITEM_ID = -1;
    public static final int NO_SIZE = 0;

    private Context mContext;
    @Getter private List<E> items;

    public ListViewAdapter(Context mContext, List<E> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : NO_SIZE;
    }

    @Override
    public Object getItem(int position) {
        return items != null ? items.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return items != null ? items.get(position).hashCode() : NO_ITEM_ID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ITEMVIEW itemView;
        if (convertView == null) {
            convertView = onNewItemView();
        }
        itemView = (ITEMVIEW) convertView;
        itemView.bind((E) getItem(position), position);
        return (View) itemView;
    }

    public void clear() {
        if (items != null && items.size() > 0) {
            items.clear();
            notifyDataSetChanged();
        }
    }

    public void updateItems(List<E> newItems) {
        if (newItems != null && newItems.size() > 0) {
            this.items.addAll(newItems);
            notifyDataSetChanged();
        }
    }

    public boolean removeItem(E item) {
        final boolean removed = items.remove(item);
        notifyDataSetChanged();
        return removed;
    }

    public E removeItem(int position) {
        final E item = items.remove(position);
        notifyDataSetChanged();
        return item;
    }

    /**
     * Called when ListView needs a new {@link ItemView} of the given type to represent
     * an item.
     *
     * The new ItemView will be used to display items of the adapter
     * @return A new ItemView that holds and bind View of the given view type.
     */
    protected abstract View onNewItemView();

}
