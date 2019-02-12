package com.dspot.declex.example.expenses.ui.auth;

import com.dspot.declex.example.expenses.navigation.BaseNavigation;

import org.androidannotations.annotations.EBean;

import static com.dspot.declex.Action.$LoginFragment;
import static com.dspot.declex.Action.$MainActivity;
import static com.dspot.declex.Action.$SignUpFragment;
import static com.dspot.declex.Action.$SplashFragment;

@EBean
public class AuthNavigation extends BaseNavigation {
    public void goToLogin() {
        $LoginFragment();
    }

    public void goToMain() {
        $MainActivity();
        finish();
    }

    public void goToSplash() {
        $SplashFragment();
    }

    public void goToSignUp() {
        $SignUpFragment();
    }
}
