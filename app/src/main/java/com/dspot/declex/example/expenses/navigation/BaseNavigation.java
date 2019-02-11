package com.dspot.declex.example.expenses.navigation;

import android.app.Activity;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class BaseNavigation {

    @RootContext
    protected Activity activity;

    public void goBack() {
        activity.onBackPressed();
    }

    public void finish() {
        activity.finish();
    }
}
