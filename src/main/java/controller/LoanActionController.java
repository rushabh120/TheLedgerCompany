/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import database.AccountData;
import model.LoanData;

/**
 *
 * @LoanActionController
 * Create LoanData from provided input
 */
public class LoanActionController implements InputDeserializer {

    private LoanData loanData;

    public LoanActionController(ArrayList<String> inputStr) {
        deserializeInputStream(inputStr);
    }

    protected void allocateLoan(){
        AccountData accountData = new AccountData(loanData);
        AccountData.addToDataList(accountData);
    }

    @Override
    public void deserializeInputStream(ArrayList<String> inputStream) {
        String bankName = inputStream.get(0);
        String userName = inputStream.get(1);
        int principleAmount = Integer.parseInt(inputStream.get(2));
        int years = Integer.parseInt(inputStream.get(3));
        int interest = Integer.parseInt(inputStream.get(4));
        createLoanData(bankName, userName, principleAmount, years, interest);
    }

    private void createLoanData(String bankName, String userName, int principleAmount, int years, int interest) {
        int amountToPay = principleAmount + calculateInterestAmount(principleAmount, years, interest);
        int emiAmount = calculateEmiAmount(amountToPay, years);
        loanData = new LoanData(bankName, userName, principleAmount, years, interest, amountToPay, emiAmount);
    }

    private int calculateEmiAmount(int amountToPay, int years) {
        return (int) Math.ceil((double) amountToPay / (12 * years));
    }

    private int calculateInterestAmount(int principleAmount, int years, int interest) {
        return (int) Math.ceil((double) principleAmount * years * interest / 100);
    }
}
