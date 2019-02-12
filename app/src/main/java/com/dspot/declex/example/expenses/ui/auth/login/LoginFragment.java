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

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.Observer;
import pl.com.dspot.archiannotations.annotation.ViewModel;

import static com.dspot.declex.actions.Action.$Toast;

/**
 * A placeholder fragment containing a simple view.
 */
@EBinder
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

    @Observer
    void errors(Exception e) {
        $Toast(e.getMessage());
    }

    public LoginFragment() {
    }

    @Click({R.id.loginButton})
    public void signIn(String editTextEmailText, String editTextPasswordText) {
        loginViewModel.signIn(editTextEmailText, editTextPasswordText);
    }

    @Click({R.id.signUpAction})
    public void goToSignUp() {
        authNavigation.goToSignUp();
    }
}
