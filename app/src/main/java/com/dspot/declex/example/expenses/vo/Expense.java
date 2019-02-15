package com.dspot.declex.example.expenses.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.dspot.declex.annotation.UseModel;

import java.util.Date;

@UseModel
public class Expense  {

    private String id;

    private double amount;

    private String description;

    private String comment;

    private Date date;

    private static final String EXPENSES_AMOUNT = "amount";
    private static final String EXPENSES_DESCRIPTION = "description";
    private static final String EXPENSES_COMMENT = "comment";
    private static final String EXPENSES_DATE = "date";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
