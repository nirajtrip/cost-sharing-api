package com.infoaa.sharing.etracker.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

//import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Cacheable(false)
public class Member {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int memberId;

	@NotEmpty(message = "FirstName cannot be empty")
	private String fullName;


	@Pattern(regexp = "[0-9]{10}", message="Enter valid 10 Digit phone Number")
	private String phoneNumber;

	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Enter a valid email ID")
	private String emailId;

	public Member() {
		// TODO Auto-generated constructor stub
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFirstName(String fullName) {
		this.fullName = fullName;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


}
