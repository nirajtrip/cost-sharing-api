package com.infoaa.sharing.etracker.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infoaa.sharing.etracker.model.Expense;
import com.infoaa.sharing.etracker.repository.CategoryRepository;
import com.infoaa.sharing.etracker.repository.ExpenseRepository;


@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public void addexpense(Expense expense){
		expenseRepository.save(expense);
	}

	public List<Object[]> getbydate(Date fromDate, Date tillDate) {
		return expenseRepository.getExpenseByDate(fromDate, tillDate);

	}

	public List<Object[]> getbyCategory(String category) {
		int categoryId = categoryRepository.getIdForCategory(category);

		return expenseRepository.getExpenseByCategory(categoryId);

	}
	public List<Object[]> getByCategoryAndDate(Date fromDate, Date tillDate, String category){
		int categoryId = categoryRepository.getIdForCategory(category);
		return expenseRepository.getExpenseByCategoryAndDate(fromDate, tillDate, categoryId);
	}

	public Optional<Expense> getExpenseById(int expenseId){
		return expenseRepository.findById(expenseId);
	}

	public void deleteExpenses(int[] ids){
		for (int i : ids) {
			expenseRepository.deleteById(i);
		}
	}

}
