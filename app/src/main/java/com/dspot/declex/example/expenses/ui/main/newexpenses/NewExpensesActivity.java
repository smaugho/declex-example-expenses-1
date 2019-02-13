package com.dspot.declex.example.expenses.ui.main.newexpenses;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dspot.declex.example.expenses.R;
import com.dspot.declex.example.expenses.ui.auth.signup.SignUpViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import pl.com.dspot.archiannotations.annotation.ViewModel;

@EActivity(R.layout.activity_new_expenses)
public class NewExpensesActivity extends AppCompatActivity {

    @ViewById
    Toolbar toolbar;

    @ViewModel
    NewExpensesViewModel newExpensesViewModel;

    @AfterViews
    public void init() {
        setSupportActionBar(toolbar);
    }

}
