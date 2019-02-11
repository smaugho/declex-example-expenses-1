package com.dspot.declex.example.expenses.ui.auth.login;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dspot.declex.example.expenses.R;
import com.dspot.declex.example.expenses.ui.auth.splash.SplashViewModel;

import org.androidannotations.annotations.EFragment;

import pl.com.dspot.archiannotations.annotation.ViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
@EFragment(R.layout.fragment_login)
public class LoginFragment extends Fragment {

    @ViewModel
    LoginViewModel loginViewModel;

    public LoginFragment() {
    }
}
