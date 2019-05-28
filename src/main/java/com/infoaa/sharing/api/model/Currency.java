package com.infoaa.sharing.api.model;

public class Currency {

	public long id;
	public String code;
	public double exchangeRate;
	public String exchangeCode; // 1 exchangeCode = exchangeRate code

	public Currency(String code) {
		this.id = -1; // will be changed later
		this.code = code;
		this.exchangeCode = null;
		this.exchangeRate = 0;
	}

	public Currency(long id, String code, double exchangeRate, String exchangeCode) {
		this.id = id;
		this.code = code;
		this.exchangeRate = exchangeRate;
		this.exchangeCode = exchangeCode;
	}

	public String getSymbol() {
		return "INR";
	}

	public String getName() {
		return "Indian Rupees";
	}
	
	@Override
	public String toString() {
		return code+", rate "+exchangeRate+" to "+exchangeCode;
	}
	
}
