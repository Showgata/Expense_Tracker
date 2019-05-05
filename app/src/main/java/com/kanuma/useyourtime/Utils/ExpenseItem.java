package com.kanuma.useyourtime.Utils;

public class ExpenseItem extends ListItem {

    private Integer totalExpense;

    public Integer getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Integer totalExpense) {
        this.totalExpense = totalExpense;
    }

    @Override
    public int getType() {
        return TYPE_TOTAL;
    }
}
