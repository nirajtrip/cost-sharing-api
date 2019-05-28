package com.infoaa.sharing.etracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infoaa.sharing.etracker.model.MemberExpense;
import com.infoaa.sharing.etracker.repository.MemberExpenseRepository;

@Service
public class MemberExpenseService {

	@Autowired
	private MemberExpenseRepository memberExpenseRepository;

	public void saveMemberExpense(MemberExpense expense){
		memberExpenseRepository.save(expense);
	}

	public int getMemberId(String fullName){
		return memberExpenseRepository.getIdForMemberName(fullName);
	}

	public List<MemberExpense> getExpenses(int memberId){
		return memberExpenseRepository.getExpenses(memberId);
	}

	public List<Object[]> getExpenseAndCategoryDetails(int memberId){
		return memberExpenseRepository.getExpenseAndCategoryDetails(memberId);
	}

	public void deleteExpenses(int[] ids){
		for (int i : ids) {
			memberExpenseRepository.deleteById(i);
		}
	}

	public void deleteMemberExpense(String memberName){
		int memberId = getMemberId(memberName);
		List<MemberExpense> expenses = memberExpenseRepository.getListOfMemberExpenseForMemberId(memberId);
		for (MemberExpense memberExpense : expenses) {
			memberExpenseRepository.delete(memberExpense);
		}
	}
}
