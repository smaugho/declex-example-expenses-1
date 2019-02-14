package com.dspot.declex.example.expenses.ui.main.newexpenses;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.dspot.declex.example.expenses.auth.ExpensesAuth;
import com.dspot.declex.example.expenses.ui.auth.AuthNavigation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Date;

import api.SingleLiveEvent;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pl.com.dspot.archiannotations.annotation.EViewModel;
import pl.com.dspot.archiannotations.annotation.Observable;

@EBean
@EViewModel
public class NewExpensesViewModel extends ViewModel {

    @Observable
    SingleLiveEvent<Exception> errors;

    @Observable
    SingleLiveEvent<Boolean> dialog;

    @Bean
    ExpensesAuth expensesAuth;

    @Bean
    NewExpensesNavigation newExpensesNavigation;

    @SuppressLint("CheckResult")
    public void createNewExpense(String description, double amount, String comment, Date date) {
        showDialog();
        expensesAuth.currentUser().createNewExpense(description, amount, date, comment)
                .subscribeOn(Schedulers.newThread())
                .subscribe(() -> {
                    newExpensesNavigation.finish();
                    hideDialog();
                }, throwable -> {
                    errors.postValue((Exception) throwable);
                    hideDialog();
                });
    }

    private void showDialog() {
        dialog.postValue(true);
    }

    private void hideDialog() {
        dialog.postValue(false);
    }
}
