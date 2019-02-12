package com.dspot.declex.example.expenses;

import android.app.Application;

import com.dspot.declex.example.expenses.auth.impl.ExpensesAppImpl;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ExpensesAppImpl.init(this);
    }
}
