/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Rushabh
 */
public class LoanData {
    
    private final String bankName;
    private final String userName;

    private final int principleAmount;
    private final int years;
    private final int interest;
    private final int amountToPay;
    private final int emiAmount;
    
    public LoanData(String bankName, String userName, int principleAmount, int years, int interest,
                    int amountToPay, int emiAmount) {
        this.bankName = bankName;
        this.userName = userName;
        this.principleAmount = principleAmount;
        this.years = years;
        this.interest = interest;
        this.amountToPay = amountToPay;
        this.emiAmount = emiAmount;
    }

    public String getBankName(){
        return this.bankName;
    }
    
    public String getUserName(){
        return this.userName;
    }

    public int getAmountToPay() {
        return amountToPay;
    }

    public int getEmiAmount() {
        return emiAmount;
    }

}
