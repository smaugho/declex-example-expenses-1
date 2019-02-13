package com.dspot.declex.example.expenses.ui.main.profile;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.dspot.declex.example.expenses.R;
import com.dspot.declex.example.expenses.auth.ExpensesUser;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.Observer;
import pl.com.dspot.archiannotations.annotation.ViewModel;

@EBinder
@EActivity(R.layout.activity_profile)
public class ProfileActivity extends AppCompatActivity {
    @ViewModel
    ProfileViewModel profileViewModel;

    @ViewById
    Toolbar toolbar;

    @Observer
    void expensesUser(ExpensesUser expensesUser) {
        if (expensesUser != null) {
            updateUI(expensesUser);
        }
    }

    @ViewById
    EditText editDisplayName;

    @ViewById
    EditText editEmail;

    @ViewById
    EditText editPassword;

    @AfterViews
    public void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_activity_profile);
        profileViewModel.init();
    }

    private void updateUI(ExpensesUser expensesUser) {
        editDisplayName.setText(expensesUser.displayName());
        editEmail.setText(expensesUser.email());
    }
}
