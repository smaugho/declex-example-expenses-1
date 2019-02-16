package com.dspot.declex.example.expenses.ui.dialogedit;

import android.app.Dialog;

import com.dspot.declex.actions.Action;
import com.dspot.declex.example.expenses.R;
import com.dspot.declex.example.expenses.vo.Expense;

import static com.dspot.declex.actions.Action.$AlertDialog;

public class DialogExpenseEdit extends Action.$AlertDialog {

    public DialogExpenseEdit() {

    }

    public static void show(Listened listened) {

    }

    public interface Listened {
        void onExpenseChange(Expense expense);
    }
}
