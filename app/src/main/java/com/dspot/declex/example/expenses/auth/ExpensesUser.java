package com.dspot.declex.example.expenses.auth;

import com.dspot.declex.example.expenses.auth.impl.ExpensesUserImpl;
import com.dspot.declex.example.expenses.vo.User;

import org.androidannotations.annotations.ImplementedBy;

@ImplementedBy(ExpensesUserImpl.class)
public interface ExpensesUser extends User {

}
