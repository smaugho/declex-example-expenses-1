package com.dspot.declex.example.expenses.ui.main.profile;


import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

import com.dspot.declex.example.expenses.R;
import com.dspot.declex.example.expenses.auth.ExpensesUser;
import com.dspot.declex.example.expenses.ui.auth.splash.SplashViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.Observer;
import pl.com.dspot.archiannotations.annotation.ViewModel;

@EBinder
@EFragment(R.layout.fragment_profile)
public class ProfileFragment extends Fragment {
    @ViewModel
    ProfileViewModel profileViewModel;

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
        profileViewModel.init();
    }

    private void updateUI(ExpensesUser expensesUser) {
        editDisplayName.setText(expensesUser.displayName());
        editEmail.setText(expensesUser.email());
    }
}
