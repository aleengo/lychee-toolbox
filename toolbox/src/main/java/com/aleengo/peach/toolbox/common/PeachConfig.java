package com.aleengo.peach.toolbox.common;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by CK_ALEENGO on 20/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public final class PeachConfig {

    @Getter @Setter
    private static boolean debug = false;

    private PeachConfig() {
    }
}
