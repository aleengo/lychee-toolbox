package com.aleengo.peach.toolbox.commons.mvp;

/**
 * Created by CK_ALEENGO on 01/03/2019.
 * Copyright (c) 2019. All rights reserved.
 */
public interface BaseView {
    //void setPresenter(Presenter presenter);

    /**
     * Attach a Ui controller to this view
     * @param uiCtl Ui controller (Activity, Fragment, ...)
     */
    void attachUiCtl(Object uiCtl);
}
