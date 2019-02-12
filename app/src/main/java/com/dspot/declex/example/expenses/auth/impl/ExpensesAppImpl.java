package com.dspot.declex.example.expenses.auth.impl;

import android.content.Context;

import com.dspot.declex.example.expenses.auth.ExpensesApp;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import static org.androidannotations.annotations.EBean.Scope.Singleton;

@EBean(scope = Singleton)
public class ExpensesAppImpl implements ExpensesApp {

    @RootContext
    Context context;

}
