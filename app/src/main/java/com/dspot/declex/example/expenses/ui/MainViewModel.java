package com.dspot.declex.example.expenses.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.dspot.declex.example.expenses.auth.ExpensesAuth;
import com.dspot.declex.example.expenses.auth.ExpensesUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import pl.com.dspot.archiannotations.annotation.EViewModel;
import pl.com.dspot.archiannotations.annotation.Observable;

@EBean
@EViewModel
public class MainViewModel extends ViewModel {

    @Bean
    ExpensesAuth expensesAuth;

    @Bean
    MainNavigation mainNavigation;

    @Observable
    MutableLiveData<ExpensesUser> expensesUser;

    public void init() {
        expensesUser.postValue(expensesAuth.currentUser());
    }

    public void logout() {
        ExpensesUser current = expensesAuth.currentUser();
        if (current != null) {
            current.logout();
            mainNavigation.goToLogin();
        }
    }
}
