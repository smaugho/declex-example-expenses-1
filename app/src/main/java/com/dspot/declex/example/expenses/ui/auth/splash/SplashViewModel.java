package com.dspot.declex.example.expenses.ui.auth.splash;

import android.arch.lifecycle.ViewModel;

import com.dspot.declex.example.expenses.auth.ExpensesAuth;
import com.dspot.declex.example.expenses.auth.ExpensesUser;
import com.dspot.declex.example.expenses.ui.auth.AuthNavigation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import pl.com.dspot.archiannotations.annotation.EViewModel;

@EBean
@EViewModel
public class SplashViewModel extends ViewModel {

    @Bean
    AuthNavigation authNavigation;

    @Bean
    ExpensesAuth expensesAuth;

    public void checkCurrentUser() {
        ExpensesUser currentUser = expensesAuth.currentUser();
        if (currentUser != null) {
            authNavigation.goToMain();
        } else {
            authNavigation.goToLogin();
        }
    }
}
