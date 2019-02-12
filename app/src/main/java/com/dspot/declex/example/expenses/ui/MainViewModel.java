package com.dspot.declex.example.expenses.ui;

import android.arch.lifecycle.ViewModel;

import com.dspot.declex.example.expenses.auth.ExpensesAuth;
import com.dspot.declex.example.expenses.auth.ExpensesUser;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import pl.com.dspot.archiannotations.annotation.EViewModel;

@EBean
@EViewModel
public class MainViewModel extends ViewModel {

    @Bean
    ExpensesAuth expensesAuth;

    @Bean
    MainNavigation mainNavigation;

    public void logout() {
        ExpensesUser current = expensesAuth.currentUser();
        if (current != null) {
            current.logout();
            mainNavigation.goToLogin();
        }

    }
}
