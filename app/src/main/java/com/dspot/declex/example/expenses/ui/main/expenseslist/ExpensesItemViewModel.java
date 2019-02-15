package com.dspot.declex.example.expenses.ui.main.expenseslist;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;

import com.dspot.declex.example.expenses.auth.ExpensesAuth;
import com.dspot.declex.example.expenses.auth.ExpensesUser;
import com.dspot.declex.example.expenses.ui.MainNavigation;
import com.dspot.declex.example.expenses.vo.Expense_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Locale;

import api.ItemViewModel;
import api.SingleLiveEvent;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pl.com.dspot.archiannotations.annotation.EViewModel;
import pl.com.dspot.archiannotations.annotation.Observable;

import static java.lang.String.format;

@EBean
@EViewModel
public class ExpensesItemViewModel extends ItemViewModel<Expense_> {

    @Observable
    SingleLiveEvent<Boolean> dialog;

    @Bean
    MainNavigation mainNavigation;

    @Bean
    ExpensesAuth expensesAuth;

    @Observable
    MutableLiveData<Exception> errors;

    String getExpenseTitle() {
        return model.getDescription();
    }

    String getExpenseAmount() {
        return format(Locale.US, "%.02f", model.getAmount());
    }

    void editExpense() {
        //TODO logic here
        //For rapid UI response, you could edit items in the internal list, and call a notify
//        List<Expense_> allExpenses = getModels();
//        setModels(allExpenses);
//        notifyModelsModified();

        //Or you could edit just one specific expense, doing it in the click on remove
        model.setAmount(model.getAmount() + 5);
        notifyModelsModified();

        //Note you should place then a listener to that with
        //setOnItemViewModelsModified("your listener");
        //from outside, so that it should update the list visually, that sadly is not linked (yet)
    }

    @SuppressLint("CheckResult")
    void removeExpense() {
        if (expensesAuth.currentUser() != null) {
            showDialog();
            expensesAuth.currentUser().removeExpense(model.getId())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(() -> {
                        hideDialog();
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

    public void goToDetails() {
        mainNavigation.goToExpenseDetails(model);
    }
}
