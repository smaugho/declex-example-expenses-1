package com.dspot.declex.example.expenses.ui.auth.signup;


import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Toast;

import com.dspot.declex.example.expenses.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import pl.com.dspot.archiannotations.annotation.ViewModel;

@EFragment(R.layout.fragment_sign_up)
public class SignUpFragment extends Fragment {

    @ViewById
    EditText editTextName;

    @ViewById
    EditText editTextEmail;

    @ViewById
    EditText editTextPassword;

    @ViewModel
    SignUpViewModel signUpViewModel;

    @AfterInject
    public void init() {
        signUpViewModel.errors.observe(this, e -> {
            if (e != null)
                Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show();
        });
    }

    @Click(R.id.signUpAction)
    public void signUp(String editTextNameText, String editTextEmailText, String editTextPasswordText) {
        signUpViewModel.signUpWithEmail(editTextNameText
                , editTextEmailText
                , editTextPasswordText);
    }
}
