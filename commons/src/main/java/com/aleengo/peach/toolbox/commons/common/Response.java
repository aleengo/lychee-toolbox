package com.aleengo.peach.toolbox.commons.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by CK_ALEENGO on 19/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response<E> {
    private List<E> value;
    private Throwable error;
}
