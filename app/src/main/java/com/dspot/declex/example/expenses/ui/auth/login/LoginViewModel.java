package com.dspot.declex.example.expenses.ui.auth.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.dspot.declex.example.expenses.auth.ExpensesAuth;
import com.dspot.declex.example.expenses.auth.impl.ExpensesAuthImpl;
import com.dspot.declex.example.expenses.ui.auth.AuthNavigation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.com.dspot.archiannotations.annotation.EViewModel;

@EBean
@EViewModel
public class LoginViewModel extends ViewModel {

    @Bean
    ExpensesAuth expensesAuth;

    @Bean
    AuthNavigation authNavigation;

    MutableLiveData<Exception> errors = new MutableLiveData<>();

    public void signIn(String email, String password) {
        expensesAuth
                .signInWithEmailAndPassword(email, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((user, throwable) -> {
                    if (throwable != null)
                        errors.postValue((Exception) throwable);
                    else {
                        authNavigation.goToMain();
                    }
                });
    }
}
