package com.dspot.declex.example.expenses.ui.auth.login;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dspot.declex.example.expenses.R;
import com.dspot.declex.example.expenses.ui.auth.AuthNavigation;
import com.dspot.declex.example.expenses.ui.auth.splash.SplashViewModel;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import pl.com.dspot.archiannotations.annotation.ViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
@EFragment(R.layout.fragment_login)
public class LoginFragment extends Fragment {

    @ViewById
    EditText editTextEmail;

    @ViewById
    EditText editTextPassword;

    @Bean
    AuthNavigation authNavigation;

    @ViewModel
    LoginViewModel loginViewModel;

    public LoginFragment() {
    }

    @Click({R.id.loginButton})
    public void signIn() {
        loginViewModel.signIn(editTextEmail.getText().toString(), editTextPassword.getText().toString());
    }

    @Click({R.id.signUpAction})
    public void goToSignUp() {
        authNavigation.goToSignUp();
    }
}
