package com.dspot.declex.example.expenses.ui.main.expenseslist;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;

import com.dspot.declex.example.expenses.auth.ExpensesAuth;
import com.dspot.declex.example.expenses.vo.Expense;
import com.dspot.declex.example.expenses.vo.Expense_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.LinkedList;
import java.util.List;

import api.ItemViewModelList;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pl.com.dspot.archiannotations.annotation.EViewModel;
import pl.com.dspot.archiannotations.annotation.Observable;
import pl.com.dspot.archiannotations.annotation.ViewModel;

@EBean
@EViewModel
public class ExpensesListViewModel extends android.arch.lifecycle.ViewModel implements LifecycleObserver {

    @ViewModel
    ExpensesItemViewModel itemViewModel; //Note I decided to have the ItemViewModel here to don't expose the Model (Expense) to the View (ExpensesListFragment),
    //but you could put this one on the View, and here have a MutableLiveData<List<Expense_>> expenses;, and then observer that list on the View
    //And create the ItemViewModelList there in the view.
    @Bean
    ExpensesAuth expensesAuth;
    @Observable
    MutableLiveData<List<ExpensesItemViewModel>> expensesItems;

    @AfterInject
    void initializeDependencies() {
        //Any initialization, here I set a listener
        itemViewModel.setOnItemViewModelsModified((expenses) -> expensesItems.setValue(expensesItems.getValue()));
    }


    @SuppressLint("CheckResult")
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        //I added this like an observer, just for you to know that it works ;)
    void updateDependencies() {

        if (expensesAuth.currentUser() != null) {
            expensesAuth.currentUser().expenses()
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Consumer<List<Expense>>() {
                        @Override
                        public void accept(List<Expense> expense) throws Exception {
                            expensesItems.postValue(new ItemViewModelList<>(itemViewModel, expense));

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    });
        }

        /*//I'll create here some mocked data
        List<Expense_> myExpenses = new LinkedList<>();

        Expense_ expense = new Expense_();
        expense.setDescription("This is a small expense");
        expense.setAmount(32.4f);
        myExpenses.add(expense);

        expense = new Expense_();
        expense.setDescription("This is a medium expense");
        expense.setAmount(150.56f);
        myExpenses.add(expense);

        expense = new Expense_();
        expense.setDescription("This is a big expense");
        expense.setAmount(1500.56f);
        myExpenses.add(expense);

        expense = new Expense_();
        expense.setDescription("And this is the last one");
        expense.setAmount(1240.56f);
        myExpenses.add(expense);


        expensesItems.setValue(new ItemViewModelList<>(itemViewModel, myExpenses));*/

    }

}
