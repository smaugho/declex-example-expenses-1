package com.dspot.declex.example.expenses.ui.main.expenseslist;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;

import com.dspot.declex.example.expenses.R;
import com.dspot.declex.example.expenses.auth.ExpensesAuth;
import com.dspot.declex.example.expenses.auth.ExpensesUser;
import com.dspot.declex.example.expenses.ui.MainNavigation;
import com.dspot.declex.example.expenses.ui.dialogedit.DialogExpenseEdit;
import com.dspot.declex.example.expenses.vo.Expense;
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

import static com.dspot.declex.actions.Action.$AlertDialog;
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

    void editExpense(Expense expense) {
        if (expensesAuth.currentUser() != null) {
            showDialog();
            expensesAuth.currentUser().editExpense(expense)
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(() -> {
                        hideDialog();
                    }, throwable -> {
                        hideDialog();
                        errors.postValue((Exception) throwable);
                    });
        }
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
