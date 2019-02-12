package com.dspot.declex.example.expenses.ui.auth;

import android.support.v7.app.AppCompatActivity;

import com.dspot.declex.example.expenses.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;

@Fullscreen
@EActivity(R.layout.activity_auth)
public class AuthActivity extends AppCompatActivity {

    @Bean
    AuthNavigation authNavigation;

    @AfterViews
    public void init() {
        authNavigation.goToSplash();
    }
}
