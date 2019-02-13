package com.dspot.declex.example.expenses.ui.main.expenseslist;

import android.arch.lifecycle.LifecycleObserver;

import com.dspot.declex.example.expenses.vo.Expense_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;
import java.util.Locale;

import api.ItemViewModel;
import pl.com.dspot.archiannotations.annotation.EViewModel;

import static java.lang.String.format;

@EBean
@EViewModel
public class ExpensesItemViewModel extends ItemViewModel<Expense_> {

    String getExpenseTitle() {
        return model.getDescription();
    }

    String getExpenseAmount() {
        return format(Locale.US, "%.02f", model.getPrice());
    }

    void editExpense() {
        //TODO logic here
        //For rapid UI response, you could edit items in the internal list, and call a notify
//        List<Expense_> allExpenses = getModels();
//        setModels(allExpenses);
//        notifyModelsModified();

        //Or you could edit just one specific expense, doing it in the click on remove
        model.setPrice(model.getPrice() + 5);
        notifyModelsModified();

        //Note you should place then a listener to that with
        //setOnItemViewModelsModified("your listener");
        //from outside, so that it should update the list visually, that sadly is not linked (yet)
    }

    void removeExpense() {
        //doing the same
        this.editExpense();
    }

}
