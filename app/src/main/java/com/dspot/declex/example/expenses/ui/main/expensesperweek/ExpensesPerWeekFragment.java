package com.dspot.declex.example.expenses.ui.main.expensesperweek;


import android.support.v4.app.Fragment;

import com.dspot.declex.example.expenses.R;

import org.androidannotations.annotations.EFragment;

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.ViewModel;

@EBinder
@EFragment(R.layout.fragment_expenses_per_week)
public class ExpensesPerWeekFragment extends Fragment {

    @ViewModel
    ExpensesPerWeekViewModel expensesPerWeekViewModel;
}
