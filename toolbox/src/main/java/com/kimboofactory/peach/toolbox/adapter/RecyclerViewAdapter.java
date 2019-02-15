package com.kimboofactory.peach.toolbox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import lombok.Getter;

/**
 * Created by CK_ALEENGO on 15/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public abstract class RecyclerViewAdapter<E, VH extends RecyclerViewAdapter.ViewHolder>
        extends RecyclerView.Adapter<VH> implements PeachAdapter {

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
        final View view = LayoutInflater.from(mContext).inflate(getLayoutResId(), parent);
        return (VH) onNewViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public void clear() {
        if (items != null && items.size() > 0) {
            items.clear();
            notifyDataSetChanged();
        }
    }
}
