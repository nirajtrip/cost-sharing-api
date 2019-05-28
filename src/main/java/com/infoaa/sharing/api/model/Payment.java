package com.infoaa.sharing.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.infoaa.sharing.api.utils.BigDecimals;
import com.infoaa.sharing.api.utils.Utils;

public class Payment implements Serializable, Comparable<Payment> {

	/**
	 *  Auto generated serialVersionUID
	 */
	private static final long serialVersionUID = -7832458998514579891L;

	public List<BigDecimal> amounts;
    public List<Long> whoPaid;
    public List<Long> forWho;
    public List<Double> weights;
    public String purpose;
    public long datetime;
    public long id;
    public long groupId;
    public boolean transfer;
    public String onlineId;
    // for converting to single currency
    public List<BigDecimal> convertedAmounts;
    public String convertedCurrency;
    public boolean converted = false;
    private String currency = "INR";

    public Payment(String amounts, String forWho, String purpose, String whoPaid, String weights, long datetime,
                   String currency, boolean transfer, long groupId, String onlineId) {
        this.amounts = Utils.splitToBigDecimals(amounts);
        this.whoPaid = Utils.splitToLongs(whoPaid);
        this.forWho = Utils.splitToLongs(forWho);
        this.purpose = purpose;
        this.weights = Utils.splitToDoubles(weights);
        this.currency = "INR";
        this.datetime = datetime;
        this.transfer = transfer;
        this.groupId = groupId;
        this.onlineId = onlineId;
    }

    public BigDecimal getAmount() {
        return BigDecimals.sum(amounts);
    }

    public void setAmount(BigDecimal amount) {
        this.amounts = new ArrayList<BigDecimal>();
        amounts.add(amount);
    }

    public BigDecimal getConvertedAmount() {
        return BigDecimals.sum(convertedAmounts);
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getCurrency() {
        return this.currency;
    }

    @Override
    public String toString() {
        return "a=" + this.amounts + "who=" + this.whoPaid + "for=" + this.forWho + "pur=" + this.purpose + "wei="
                + this.weights + "curr=" + this.currency;
    }

    @Override
    public int compareTo(Payment another) {
        if (this.equals(another))
            return 0;
        if (datetime > another.datetime)
            return -1;
        else
            return 1;
    }
}
