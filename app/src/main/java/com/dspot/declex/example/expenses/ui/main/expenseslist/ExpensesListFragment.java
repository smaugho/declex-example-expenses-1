package com.dspot.declex.example.expenses.ui.main.expenseslist;


import android.support.v4.app.Fragment;

import com.dspot.declex.annotation.Populate;
import com.dspot.declex.example.expenses.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import java.util.List;

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.Observer;
import pl.com.dspot.archiannotations.annotation.ViewModel;

import static com.dspot.declex.actions.Action.$AlertDialog;
import static com.dspot.declex.actions.Action.$Populate;
import static com.dspot.declex.actions.Action.$Toast;

@EBinder
@EFragment(R.layout.fragment_expenses_list)
public class ExpensesListFragment extends Fragment {

    @ViewModel
    ExpensesListViewModel expensesListViewModel;

    @Populate
    List<ExpensesItemViewModel> expensesList;

    @Observer
    void errors(Exception e) {
        $Toast(e.getMessage());
    }

    @Observer
    void expensesItems(List<ExpensesItemViewModel> expensesItems) {
        this.expensesList = expensesItems;
        $Populate(expensesList);
    }

    @Click(R.id.actionEdit)
    void editExpense(ExpensesItemViewModel model) {
        model.editExpense();
    }

    @Click(R.id.actionDelete)
    void removeExpense(ExpensesItemViewModel model) {
        $AlertDialog()
                .title(R.string.title_dialog_delete_expense)
                .message(R.string.message_dialog_delete_expense)
                .positiveButton(android.R.string.ok)
                .negativeButton(android.R.string.cancel)
                .dialog();
        if($AlertDialog.PositiveButtonPressed){
            model.removeExpense();
        }
    }

}
