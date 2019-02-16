package com.dspot.declex.example.expenses.ui.main.newexpenses;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dspot.declex.example.expenses.R;
import com.dspot.declex.example.expenses.ui.auth.signup.SignUpViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Date;

import pl.com.dspot.archiannotations.annotation.EBinder;
import pl.com.dspot.archiannotations.annotation.Observer;
import pl.com.dspot.archiannotations.annotation.ViewModel;

import static com.dspot.declex.actions.Action.$AlertDialog;
import static com.dspot.declex.actions.Action.$ProgressDialog;
import static com.dspot.declex.actions.Action.$Toast;

@EBinder
@EActivity(R.layout.activity_new_expenses)
public class NewExpensesActivity extends AppCompatActivity {

    @ViewById
    Toolbar toolbar;

    @ViewModel
    NewExpensesViewModel newExpensesViewModel;

    @AfterViews
    public void init() {
        setSupportActionBar(toolbar);
    }

    @Observer
    void errors(Exception e) {
        $Toast(e.getMessage());
    }

    Dialog dialog;

    @Observer
    void dialog(Boolean isShow) {
        if (isShow) {
            dialog = $ProgressDialog().dialog();
        } else
            dialog.dismiss();
    }

    @Click(R.id.createAction)
    public void createNewExpense(String editDescriptionText, String editAmountText, String editCommentText) {
        newExpensesViewModel.createNewExpense(editDescriptionText, Double.parseDouble(editAmountText), editCommentText, new Date());
    }

}
