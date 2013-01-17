package pl.boboli.engineer.test.external.model;

import java.math.BigDecimal;

public class BillingHistoryFact {
	BigDecimal averageIncome;
	BigDecimal averageCost;
	BigDecimal avarageProfit;
	
	
	
	public BillingHistoryFact() {
		super();
	}

	public BillingHistoryFact(BigDecimal averageIncome, BigDecimal averageCost,
			BigDecimal avarageProfit) {
		super();
		this.averageIncome = averageIncome;
		this.averageCost = averageCost;
		this.avarageProfit = avarageProfit;
	}
	
	public BigDecimal getAverageIncome() {
		return averageIncome;
	}
	public void setAverageIncome(BigDecimal averageIncome) {
		this.averageIncome = averageIncome;
	}
	public BigDecimal getAverageCost() {
		return averageCost;
	}
	public void setAverageCost(BigDecimal averageCost) {
		this.averageCost = averageCost;
	}
	public BigDecimal getAvarageProfit() {
		return avarageProfit;
	}
	public void setAvarageProfit(BigDecimal avarageProfit) {
		this.avarageProfit = avarageProfit;
	}
	

}
