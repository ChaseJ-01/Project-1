package com.project.main;

public class ExpenseDAOFactory {
	private static ExpenseDAO dao;

    private ExpenseDAOFactory(){}
    
    public static ExpenseDAO getExpenseDao(){
        if(dao==null)
            dao = new ExpenseDAOImpl();
        return dao;
    }
}
