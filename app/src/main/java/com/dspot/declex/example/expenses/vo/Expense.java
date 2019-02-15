package com.dspot.declex.example.expenses.vo;

import com.dspot.declex.annotation.UseModel;

import java.util.Date;

import lombok.Data;

@UseModel
public @Data
class Expense {

    public String id;

    private double amount;

    private String description;

    private String comment;

    private Date date;

    private static final String EXPENSES_AMOUNT = "amount";
    private static final String EXPENSES_DESCRIPTION = "description";
    private static final String EXPENSES_COMMENT = "comment";
    private static final String EXPENSES_DATE = "date";

}
