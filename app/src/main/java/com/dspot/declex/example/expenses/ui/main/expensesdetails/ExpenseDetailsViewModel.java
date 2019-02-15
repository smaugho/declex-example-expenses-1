package com.dspot.declex.example.expenses.ui.main.expensesdetails;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;

import com.dspot.declex.example.expenses.auth.ExpensesAuth;
import com.dspot.declex.example.expenses.ui.MainNavigation;
import com.dspot.declex.example.expenses.vo.Expense;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import api.SingleLiveEvent;
import io.reactivex.schedulers.Schedulers;
import pl.com.dspot.archiannotations.annotation.EViewModel;
import pl.com.dspot.archiannotations.annotation.Observable;

@EBean
@EViewModel
public class ExpenseDetailsViewModel extends ViewModel {

    @Bean
    ExpensesAuth expensesAuth;

    @Bean
    MainNavigation mainNavigation;

    @Observable
    SingleLiveEvent<Expense> expense;

    @Observable
    SingleLiveEvent<Exception> errors;

    @Observable
    SingleLiveEvent<Boolean> dialog;

    public void setExpense(Expense expense) {
        this.expense.setValue(expense);
    }

    @SuppressLint("CheckResult")
    public void deleteExpense(String id) {
        if (expensesAuth.currentUser() != null) {
            showDialog();
            expensesAuth.currentUser().removeExpense(id)
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(() -> {
                        hideDialog();
                        mainNavigation.goBack();
                    }, throwable -> {
                        hideDialog();
                        errors.postValue((Exception) throwable);
                    });
        }
    }

    private void showDialog() {
        dialog.postValue(true);
    }

    private void hideDialog() {
        dialog.postValue(false);
    }
}
