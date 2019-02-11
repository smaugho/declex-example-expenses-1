package com.dspot.declex.example.expenses.ui.auth;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dspot.declex.example.expenses.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_auth)
public class AuthActivity extends AppCompatActivity {

    @Bean
    AuthNavigation authNavigation;

    @AfterViews
    public void init() {
        authNavigation.goToSplash();
    }
}
