package com.dspot.declex.example.expenses.vo;

import com.dspot.declex.annotation.UseModel;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Date;

@UseModel
public class Expense {

    private double _amount;

    private String _description;

    private String _comment;

    private Date _date;

    public void setAmount(double _amount) {
        this._amount = _amount;
    }

    public void setDescription(String _description) {
        this._description = _description;
    }

    public void setComment(String _comment) {
        this._comment = _comment;
    }

    public void setDate(Date _date) {
        this._date = _date;
    }

    public String getDescription() {
        return _description;
    }

    public double getAmount() {
        return _amount;
    }

    public Date getDate() {
        return _date;
    }

    public String getComment() {
        return _comment;
    }

    /*public static class Builder {

        private double _amount;

        private String _description;

        private String _comment;

        private Date _date;

        public Builder() {
        }

        public Builder amount(double amount) {
            this._amount = amount;
            return this;
        }

        public Builder description(String description) {
            this._description = description;
            return this;
        }

        public Builder comment(String comment) {
            this._comment = comment;
            return this;
        }

        public Builder date(Date date) {
            this._date = date;
            return this;
        }

        public Expense build() {
            //Here we create the actual bank account object, which is always in a fully initialised state when it's returned.
            Expense expense = new Expense(
                    this._amount,
                    this._description,
                    this._comment,
                    this._date);

            return expense;
        }
    }*/

    private static final String EXPENSES_AMOUNT = "amount";
    private static final String EXPENSES_DESCRIPTION = "description";
    private static final String EXPENSES_COMMENT = "comment";
    private static final String EXPENSES_DATE = "date";

    public static Expense createFromDocumentSnapshot(DocumentSnapshot documentSnapshot) {
        Expense_ expense = new Expense_();
        expense.setAmount(documentSnapshot.getDouble(EXPENSES_AMOUNT));
        expense.setComment(documentSnapshot.getString(EXPENSES_DESCRIPTION));
        expense.setDescription(documentSnapshot.getString(EXPENSES_COMMENT));
        expense.setDate(documentSnapshot.getDate(EXPENSES_DATE));

        return expense;
    }
}
