package com.infoaa.sharing.etracker.model;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@XmlRootElement
@Cacheable(false)
@ApiModel(description="All details about the MemberExpense.")
public class MemberExpense {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int expenseId;

    @ApiModelProperty(notes="ExpenseName should have atleast 2 characters")
	private String expenseName;
	private double amount;

	private Date createdDate;
	private String comments;
    @ApiModelProperty(notes="MemberId")
	private int memberId;
    @ApiModelProperty(notes="CategoryId")
	private int categoryId;


	public MemberExpense() {

	}


	public MemberExpense(int expenseId, String expenseName, double amount, Date createdDate, String comments, int memberId) {
		super();
		this.memberId=memberId;
		this.expenseId = expenseId;
		this.expenseName = expenseName;
		this.amount = amount;
		this.createdDate = createdDate;
		this.comments = comments;
	}


	public int getExpenseId() {
		return expenseId;
	}


	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}


	public String getExpenseName() {
		return expenseName;
	}


	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}


}