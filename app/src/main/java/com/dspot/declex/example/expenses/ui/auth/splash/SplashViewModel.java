package com.dspot.declex.example.expenses.ui.auth.splash;

import android.arch.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.EViewModel;

@EBinder
@EViewModel
public class SplashViewModel extends ViewModel {



    public void checkCurrentUser() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {

        }
    }
}
