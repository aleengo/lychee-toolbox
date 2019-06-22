package com.aleengo.peach.box.commons.strategy;

import com.aleengo.peach.box.commons.model.RawJSON;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by CK_ALEENGO on 22/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public class ListPairTypeAdapter extends TypeAdapter<RawJSON> {

    @Override
    public void write(JsonWriter out, RawJSON value) throws IOException {

    }

    @Override
    public RawJSON read(JsonReader in) throws IOException {
        final RawJSON pair = new RawJSON();

        JsonToken token = in.peek();

        if(token.equals(JsonToken.BEGIN_OBJECT)) {
            in.beginObject();
            while (true) {
                token = in.peek();
                if (token.equals(JsonToken.END_OBJECT)) {
                    break;
                }
                System.out.println(" name: " + in.nextName() +  " [value: " + in.nextString() + "]");
               /* pair.setName(in.nextName());
                pair.setValue(in.nextString());*/
            }
            in.endObject();
        }
        return pair;
    }
}
