package com.aleengo.peach.toolbox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.aleengo.peach.toolbox.utils.Util;

import java.util.List;

import androidx.annotation.LayoutRes;
import lombok.Getter;

/**
 * Created by CK_ALEENGO on 15/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public abstract class ListViewAdapter<E, VIEW extends ItemView<E>> extends BaseAdapter {

    /**
     * ViewHolder Class
     */
   /* public static class ViewHolder {
        private View itemView;
        public ViewHolder(View itemView) {
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = itemView;
        }
    }*/

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

        VIEW customView = null;
        if (convertView == null) {
            customView = (VIEW) Util.inflateLayout(LayoutInflater.from(mContext), getLayoutResId(), null);
            //viewHolder = onNewViewHolder(convertView);
            //convertView.setTag(viewHolder);
        } //else {
            //viewHolder = (VH) convertView.getTag();
        //}
        customView.bind((E) getItem(position));
        return (View) customView;
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

    protected abstract @LayoutRes int getLayoutResId();

    /**
     * Called when ListView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * onBindViewHolder.
     *
     * @param view The view that can represent the items.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     */
    //protected abstract VH onNewViewHolder(View view);

    /**
     * Called by BaseAdapter to display the items at the specified position. This method should
     * update the contents of the {@link ListViewAdapter.ViewHolder#itemView} to reflect
     * the item at the given position.
     * <p>
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the items set.
     * @param position The position of the item within the adapter's items set.
     */
    //protected abstract void onBindViewHolder(VH holder, int position);

}
