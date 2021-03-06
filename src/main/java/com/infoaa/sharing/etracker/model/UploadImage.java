package com.infoaa.sharing.etracker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;

@Entity
@XmlRootElement
@Table(name="FILE_UPLOAD")
@ApiModel(description="Image upload to the Server.")
public class UploadImage {

	@Id
	private int expenseId;
	private byte[] image;

	public UploadImage() {
		// TODO Auto-generated constructor stub
	}

	public int getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
