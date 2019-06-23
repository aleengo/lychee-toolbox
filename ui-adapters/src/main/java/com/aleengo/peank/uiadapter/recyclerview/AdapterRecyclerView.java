package com.aleengo.peank.uiadapter.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;

/**
 * Copyright Aleengo 2019. All rights reserved.
 * Created by bau.cj on 22/06/2019.
 */
public abstract class AdapterRecyclerView<E, ITEMVIEW extends ItemView<E>>
        extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>
        implements AdapterContract<E> {

    @Getter
    private Context context;
    private final List<E> items;

    public AdapterRecyclerView(Context context) {
        this.context = context;
        this.items = new LinkedList<>();
    }

    @NonNull
    @Override
    public AdapterRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final ITEMVIEW itemview = onCreateItemView(parent, viewType);
        return new ViewHolder(itemview);
    }

    public abstract ITEMVIEW onCreateItemView(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public E getItem(int position) {
        return items.get(position);
    }

    @Override
    public void clear() {
        final int oldSize = getItemCount();
        items.clear();
        notifyItemRangeChanged(getItemCount(), oldSize);
    }

    @Override
    public void addItem(E item) {
        items.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public void addItem(int position, E item) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    @Override
    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void updateItem(int position, E item) {
        items.set(position, item);
        notifyItemChanged(position);
    }

    @Override
    public void addItems(Collection<E> newItems) {
        final int oldSize = getItemCount();
        items.addAll(newItems);
        notifyItemRangeChanged(oldSize, newItems.size());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Getter
        private View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
