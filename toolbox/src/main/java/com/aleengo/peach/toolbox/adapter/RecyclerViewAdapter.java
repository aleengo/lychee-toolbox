package com.aleengo.peach.toolbox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleengo.peach.toolbox.utils.Util;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import lombok.Getter;

/**
 * Created by CK_ALEENGO on 15/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public abstract class RecyclerViewAdapter<E, VH extends RecyclerViewAdapter.ViewHolder>
        extends RecyclerView.Adapter<VH>  {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    private Context mContext;
    @Getter
    private List<E> items;

    public RecyclerViewAdapter(Context mContext, List<E> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = Util.inflateLayout(LayoutInflater.from(mContext), getLayoutResId(), parent);
        return onNewViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
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

    protected abstract @LayoutRes int getLayoutResId();

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
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
    protected abstract VH onNewViewHolder(View view);
}
