package com.dspot.declex.example.expenses.ui.main.newexpenses;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.dspot.declex.example.expenses.auth.ExpensesAuth;
import com.dspot.declex.example.expenses.ui.auth.AuthNavigation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Date;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pl.com.dspot.archiannotations.annotation.EViewModel;
import pl.com.dspot.archiannotations.annotation.Observable;

@EBean
@EViewModel
public class NewExpensesViewModel extends ViewModel {

    @Observable
    MutableLiveData<Exception> errors;

    @Bean
    ExpensesAuth expensesAuth;

    @Bean
    NewExpensesNavigation newExpensesNavigation;

    public void createNewExpense(String description, double amount, String comment, Date date) {
        expensesAuth.currentUser().createNewExpense(description, amount, date, comment)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        newExpensesNavigation.finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        errors.postValue((Exception) throwable);
                    }
                });
    }
}
