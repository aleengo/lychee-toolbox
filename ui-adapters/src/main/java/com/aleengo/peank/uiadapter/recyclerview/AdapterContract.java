package com.aleengo.peank.uiadapter.recyclerview;


import java.util.Collection;

/**
 * Copyright Aleengo 2019. All rights reserved.
 * Created by bau.cj on 22/06/2019.
 */
public interface AdapterContract<E> {

    void clear();
    E getItem(int position);
    void addItem(E item);
    void addItem(int position, E item);
    void removeItem(int position);
    void updateItem(int position , E item);
    void addItems(Collection<E> collection);

}
