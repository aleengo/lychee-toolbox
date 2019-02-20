package com.aleengo.peach.toolbox.common;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import lombok.Getter;

/**
 * Created by CK_ALEENGO on 20/02/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public final class FragmentConfig {

    @Getter
    private FragmentManager fragmentManager;
    @Getter
    private Fragment fragment;
    @Getter
    private String fragmentTag;
    @Getter
    private @LayoutRes int fragmentContainer;
    @Getter
    private boolean addToBackStack;
    @Getter
    private String backStackName;

    private FragmentConfig(Builder builder) {
        this.fragmentManager = builder.fragmentManager;
        this.fragment = builder.fragment;
        this.fragmentContainer = builder.fragmentContainer;
        this.fragmentTag = builder.fragmentTag;
        this.addToBackStack = builder.addToBackStack;
        this.backStackName = builder.backStackName;
    }

    public static class Builder {
        private FragmentManager fragmentManager;
        private Fragment fragment;
        private String fragmentTag;
        private @LayoutRes int fragmentContainer;
        private boolean addToBackStack;
        private String backStackName;

        public Builder(FragmentManager fragmentManager, Fragment fragment, int fragmentContainer) {
            this.fragmentManager = fragmentManager;
            this.fragment = fragment;
            this.fragmentContainer = fragmentContainer;
            this.fragmentTag = null;
            this.addToBackStack = false;
            this.backStackName = null;
        }

        public Builder fragmentTag(String fragmentTag) {
            this.fragmentTag = fragmentTag;
            return this;
        }

        public Builder addToBackStack(boolean addToBackStack) {
            this.addToBackStack = addToBackStack;
            return this;
        }

        public Builder backStackName(String backStackName) {
            this.backStackName = backStackName;
            return this;
        }

        public FragmentConfig build() {
            return new FragmentConfig(this);
        }
    }
}
