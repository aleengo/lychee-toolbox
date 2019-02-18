package com.aleengo.peach.toolbox.commons.net;

import okhttp3.MediaType;

/**
 * Created by CK_ALEENGO on 16/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public enum Media {
    /**
     * JSON MediaType
     */
    JSON( MediaType.parse("application/json; charset=utf-8") ),

    /**
     * Image MediaType
     */
    IMAGE_PNG( MediaType.parse("image/png") );

    private MediaType mediaType;

    Media(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public MediaType get() {
        return mediaType;
    }
}
