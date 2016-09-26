package com.nishitadutta.auction.Utils;

import android.content.Context;
import android.widget.Toast;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

/**
 * Created by Nishita on 26-09-2016.
 */
@EBean(scope = EBean.Scope.Singleton)
public class ToastManager {

    @RootContext
    Context appCtx;

    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void show(CharSequence text) {
        Toast.makeText(appCtx, text, Toast.LENGTH_LONG).show();
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void show(int textResId) {
        Toast.makeText(appCtx, textResId, Toast.LENGTH_LONG).show();
    }

}
