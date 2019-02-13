package com.dspot.declex.example.expenses.ui;

import com.dspot.declex.example.expenses.navigation.BaseNavigation;

import org.androidannotations.annotations.EBean;

import static com.dspot.declex.Action.$AuthActivity;
import static com.dspot.declex.Action.$ExpensesListFragment;
import static com.dspot.declex.Action.$ExpensesPerWeekFragment;
import static com.dspot.declex.Action.$NewExpensesActivity;
import static com.dspot.declex.Action.$ProfileActivity;
import static com.dspot.declex.Action.$SignUpFragment;
import static com.dspot.declex.Action.$SplashFragment;

@EBean
public class MainNavigation extends BaseNavigation {

    public void goToExpensesList() {
        $ExpensesListFragment();
    }

    public void goToProfile() {
        $ProfileActivity();
    }

    public void goToSplash() {
        $SplashFragment();
    }

    public void goToSignUp() {
        $SignUpFragment();
    }

    public void goToLogin() {
        $AuthActivity();
        finish();
    }

    public void goToExpensesPerWeek() {
        $ExpensesPerWeekFragment();
    }

    public void goToCreateNewExpenses() {
        $NewExpensesActivity();
    }
}
