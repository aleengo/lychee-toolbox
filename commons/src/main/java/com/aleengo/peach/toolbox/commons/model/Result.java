package com.aleengo.peach.toolbox.commons.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by CK_ALEENGO on 22/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private T value;
    private Throwable error;
}
