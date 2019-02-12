package com.dspot.declex.example.expenses.ui.auth.splash;

import android.arch.lifecycle.ViewModel;

import com.dspot.declex.example.expenses.ui.auth.AuthNavigation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import pl.com.dspot.archiannotations.annotation.EViewModel;

@EBean
@EViewModel
public class SplashViewModel extends ViewModel {

    @Bean
    AuthNavigation authNavigation;

    public void checkCurrentUser() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            authNavigation.goToMain();
        } else {
            authNavigation.goToLogin();
        }
    }
}
