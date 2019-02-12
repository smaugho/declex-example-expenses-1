package com.dspot.declex.example.expenses.auth.impl;

import android.content.Context;

import com.dspot.declex.example.expenses.auth.ExpensesApp;

public class ExpensesAppImpl implements ExpensesApp {

    Context context;

    private static ExpensesApp instance = null;

    public ExpensesAppImpl(Context context) {
        this.context = context;
    }

    public static void init(Context context) {
        instance = new ExpensesAppImpl(context);
    }

    public static ExpensesApp getInstance() {
        return instance;
    }
}
