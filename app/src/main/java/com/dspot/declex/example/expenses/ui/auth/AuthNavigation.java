package com.dspot.declex.example.expenses.ui.auth;

import com.dspot.declex.example.expenses.navigation.BaseNavigation;

import static com.dspot.declex.Action.$LoginFragment;
import static com.dspot.declex.Action.$SplashFragment;

public class AuthNavigation extends BaseNavigation {
    public void goToLogin() {
        $LoginFragment();
    }

    public void goToMain() {
        //$MainActivity();
    }

    public void goToSplash() {
        $SplashFragment();
    }
}
