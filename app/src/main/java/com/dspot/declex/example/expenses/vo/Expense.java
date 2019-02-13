package com.dspot.declex.example.expenses.vo;

import com.dspot.declex.annotation.UseModel;

@UseModel //This is not necessary, but you could use DecleX Models, there are several of them that are useful, anyway, we'll see later
public class Expense {

    float price;

    String description;

}
