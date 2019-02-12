package com.dspot.declex.example.expenses.ui;

import com.dspot.declex.example.expenses.navigation.BaseNavigation;

import org.androidannotations.annotations.EBean;

import static com.dspot.declex.Action.$AuthActivity;
import static com.dspot.declex.Action.$LoginFragment;
import static com.dspot.declex.Action.$MainActivity;
import static com.dspot.declex.Action.$ProfileFragment;
import static com.dspot.declex.Action.$SignUpFragment;
import static com.dspot.declex.Action.$SplashFragment;

@EBean
public class MainNavigation extends BaseNavigation {

    public void goToExpensesList() {
        $LoginFragment();
    }

    public void goToProfile() {
        $ProfileFragment();
    }

    public void goToSplash() {
        $SplashFragment();
    }

    public void goToSignUp() {
        $SignUpFragment();
    }

    public void goToLogin() {
        $AuthActivity();
        finish();
    }
}
