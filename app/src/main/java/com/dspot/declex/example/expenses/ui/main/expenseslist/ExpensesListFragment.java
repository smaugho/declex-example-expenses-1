package com.dspot.declex.example.expenses.ui.main.expenseslist;


import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dspot.declex.actions.AlertDialogGate;
import com.dspot.declex.annotation.Populate;
import com.dspot.declex.example.expenses.R;
import com.dspot.declex.example.expenses.ui.MainNavigation;
import com.dspot.declex.example.expenses.ui.dialogedit.DialogExpenseEdit;
import com.dspot.declex.example.expenses.vo.Expense;
import com.dspot.declex.example.expenses.vo.Expense_;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import java.util.List;

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.Observer;
import pl.com.dspot.archiannotations.annotation.ViewModel;

import static com.dspot.declex.actions.Action.$AlertDialog;
import static com.dspot.declex.actions.Action.$Populate;
import static com.dspot.declex.actions.Action.$ProgressDialog;
import static com.dspot.declex.actions.Action.$Toast;

@EBinder
@EFragment(R.layout.fragment_expenses_list)
public class ExpensesListFragment extends Fragment {

    @ViewModel
    ExpensesListViewModel expensesListViewModel;

    @Populate
    List<ExpensesItemViewModel> expensesList;

    @Bean
    MainNavigation mainNavigation;

    @Observer
    void errors(Exception e) {
        $Toast(e.getMessage());
    }

    @Observer
    void expensesItems(List<ExpensesItemViewModel> expensesItems) {
        this.expensesList = expensesItems;
        $Populate(expensesList);
    }

    @Click(R.id.newExpensesAction)
    void goToCreateNewExpenses() {
        mainNavigation.goToCreateNewExpenses();
    }

    @Click(R.id.rootParent)
    void goToExpenseDetails(ExpensesItemViewModel model) {
        model.goToDetails();
    }

    @Click(R.id.actionEdit)
    void editExpense(ExpensesItemViewModel model) {
        //model.editExpense();

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_expense_edit_layout, (ViewGroup) getView(), false);

        EditText editDescription = view.findViewById(R.id.editDescription);
        editDescription.setText(model.model.getDescription());

        EditText editAmount = view.findViewById(R.id.editAmount);
        editAmount.setText(String.valueOf(model.model.getAmount()));

        EditText editComment = view.findViewById(R.id.editComment);
        editComment.setText(model.model.getComment());

        Button createAction = view.findViewById(R.id.createAction);

        Dialog tempDialog = $AlertDialog()
                .view(view)
                .dialog();


        createAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Expense expense = new Expense_();
                expense.setId(model.model.getId());
                expense.setDescription(editDescription.getText().toString());
                expense.setComment(editComment.getText().toString());
                expense.setAmount(Double.parseDouble(editAmount.getText().toString()));

                model.editExpense(expense);

                tempDialog.dismiss();
            }
        });


    }

    @Click(R.id.actionDelete)
    void removeExpense(ExpensesItemViewModel model) {
        $AlertDialog()
                .title(R.string.title_dialog_delete_expense)
                .message(R.string.message_dialog_delete_expense)
                .positiveButton(android.R.string.ok)
                .negativeButton(android.R.string.cancel)
                .dialog();
        if ($AlertDialog.PositiveButtonPressed) {
            model.removeExpense();
        }
    }

    Dialog dialog;

    @Observer
    void dialog(Boolean isShow) {
        if (isShow) {
            dialog = $ProgressDialog().dialog();
        } else
            dialog.dismiss();
    }

}
