package com.dspot.declex.example.expenses.ui.main.expensesdetails;

import android.arch.lifecycle.ViewModel;

import org.androidannotations.annotations.EBean;

import api.SingleLiveEvent;
import pl.com.dspot.archiannotations.annotation.EViewModel;
import pl.com.dspot.archiannotations.annotation.Observable;

@EBean
@EViewModel
public class ExpenseDetailsViewModel extends ViewModel {

    @Observable
    SingleLiveEvent<Exception> errors;

    @Observable
    SingleLiveEvent<Boolean> dialog;
}
