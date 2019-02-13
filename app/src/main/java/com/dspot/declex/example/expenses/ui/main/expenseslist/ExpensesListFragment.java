package com.dspot.declex.example.expenses.ui.main.expenseslist;


import android.support.v4.app.Fragment;

import com.dspot.declex.example.expenses.R;
import com.dspot.declex.example.expenses.ui.auth.splash.SplashViewModel;

import org.androidannotations.annotations.EFragment;

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.ViewModel;

@EBinder
@EFragment(R.layout.fragment_expenses_list)
public class ExpensesListFragment extends Fragment {
    @ViewModel
    ExpensesListViewModel expensesListViewModel;
}
