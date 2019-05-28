package com.infoaa.sharing.etracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infoaa.sharing.etracker.model.MemberExpense;

@Repository
public interface MemberExpenseRepository extends CrudRepository<MemberExpense, Integer> {

	@Query("select e.memberId from Member e where e.fullName = :fullName")
	public int getIdForMemberName(@Param("fullName") String fullName);

	@Query("FROM MemberExpense e WHERE e.empId = :memberId")
	public List<MemberExpense> getExpenses(@Param("memberId") int memberId);

	@Query("SELECT e.expenseName, e.amount, e.createdDate, e.comments, e.memberExpenseId, c.category FROM MemberExpense e, Category c WHERE c.categoryId = e.categoryId AND e.memberId = :memberId")
	public List<Object[]> getExpenseAndCategoryDetails(@Param("memberId") int memberId);

	@Query("FROM MemberExpense e WHERE e.memberId = :memberId")
	public List<MemberExpense> getListOfMemberExpenseForMemberId(@Param("memberId")int memberId);

}
