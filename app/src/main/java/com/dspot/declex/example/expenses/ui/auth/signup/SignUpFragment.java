package com.dspot.declex.example.expenses.ui.auth.signup;


import android.support.v4.app.Fragment;

import com.dspot.declex.example.expenses.R;

import org.androidannotations.annotations.EFragment;

import pl.com.dspot.archiannotations.annotation.ViewModel;

@EFragment(R.layout.fragment_sign_up)
public class SignUpFragment extends Fragment {
    @ViewModel
    SignUpViewModel signUpViewModel;
}
