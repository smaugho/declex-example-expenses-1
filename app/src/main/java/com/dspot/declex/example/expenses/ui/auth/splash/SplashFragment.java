package com.dspot.declex.example.expenses.ui.auth.splash;

import android.support.v4.app.Fragment;

import com.dspot.declex.example.expenses.R;
import com.dspot.declex.example.expenses.ui.auth.login.LoginViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import pl.com.dspot.archiannotations.annotation.ViewModel;

@EFragment(R.layout.fragment_splash)
public class SplashFragment extends Fragment {

    @ViewModel
    SplashViewModel splashViewModel;

    @AfterViews
    public void init() {
        splashViewModel.checkCurrentUser();
    }
}
