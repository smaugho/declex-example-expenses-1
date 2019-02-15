package com.dspot.declex.example.expenses.ui.main.expensesdetails;


import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.dspot.declex.example.expenses.R;
import com.dspot.declex.example.expenses.ui.main.expenseslist.ExpensesListViewModel;
import com.dspot.declex.example.expenses.vo.Expense;
import com.dspot.declex.example.expenses.vo.Expense_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.Observer;
import pl.com.dspot.archiannotations.annotation.ViewModel;

import static com.dspot.declex.actions.Action.$AlertDialog;
import static com.dspot.declex.actions.Action.$ProgressDialog;
import static com.dspot.declex.actions.Action.$Toast;

@EBinder
@EFragment(R.layout.fragment_expense_details)
public class ExpenseDetailsFragment extends Fragment {

    private static final String ARG_EXPENSE = "arg_expense";

    @ViewModel
    ExpenseDetailsViewModel expenseDetailsViewModel;

    @ViewById(R.id.expenseTitle)
    TextView expenseTitle;

    @ViewById(R.id.expenseAmount)
    TextView expenseAmount;

    @ViewById(R.id.expenseDescription)
    TextView expenseDescription;

    @FragmentArg(ARG_EXPENSE)
    Expense_ expense;

    Dialog dialog;

    @Observer
    void dialog(Boolean isShow) {
        if (isShow) {
            dialog = $ProgressDialog().dialog();
        } else
            dialog.dismiss();
    }

    @AfterViews
    public void setExpense() {
        expenseDetailsViewModel.setExpense(expense);
    }

    @Observer
    void expense(Expense expense) {
        updateUI(expense);
    }

    @Click(R.id.actionDelete)
    public void deleteExpense() {
        $AlertDialog()
                .title(R.string.title_dialog_delete_expense)
                .message(R.string.message_dialog_delete_expense)
                .positiveButton(android.R.string.ok)
                .negativeButton(android.R.string.cancel)
                .dialog();
        if ($AlertDialog.PositiveButtonPressed) {
            expenseDetailsViewModel.deleteExpense(expense.getId());
        }
    }

    private void updateUI(Expense expense) {
        expenseTitle.setText(expense.getDescription());
        expenseAmount.setText(String.valueOf(expense.getAmount()));
        expenseDescription.setText(String.valueOf(expense.getComment()));
    }
}
