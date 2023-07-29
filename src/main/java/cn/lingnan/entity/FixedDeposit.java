package cn.lingnan.entity;

import java.util.Date;

public class FixedDeposit {
    private int id;
    private String cardID;
    private Date startDate;
    private Date endDate;
    private double depositAmount;
    private String depositType;
    private double interestRate;
    private double maturityAmount;


    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcardID() {
        return cardID;
    }

    public void setcardID(String cardID) {
        this.cardID = cardID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getMaturityAmount() {
        return maturityAmount;
    }

    public void setMaturityAmount(double maturityAmount) {
        this.maturityAmount = maturityAmount;
    }

    // ... (same as the previous implementation)

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public FixedDeposit(int id, String cardID, Date startDate, Date endDate, double depositAmount,
                        String depositType, double interestRate, double maturityAmount) {
        this.id = id;
        this.cardID = cardID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.depositAmount = depositAmount;
        this.depositType = depositType;
        this.interestRate = interestRate;
        this.maturityAmount = maturityAmount;
    }

    public FixedDeposit(String cardID, Date startDate, Date endDate, double depositAmount,
                        String depositType, double interestRate, double maturityAmount) {
        this.cardID = cardID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.depositAmount = depositAmount;
        this.depositType = depositType;
        this.interestRate = interestRate;
        this.maturityAmount = maturityAmount;
    }

    @Override
    public String toString() {
        return "FixedDeposit{" +
                "id=" + id +
                ", cardID='" + cardID + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", depositAmount=" + depositAmount +
                ", depositType='" + depositType + '\'' +
                ", interestRate=" + interestRate +
                ", maturityAmount=" + maturityAmount +
                '}';
    }
}
