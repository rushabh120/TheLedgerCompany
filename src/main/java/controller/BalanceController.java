/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import database.AccountData;
import model.BalanceQueryData;
import model.LoanData;
import model.PaymentData;

/**
 *
 * @BalnaceController
 * Get AccountData using bankName and userName as key
 * Calculate total amount paid so far by traversing paymentDataList
 * Calculate EmiMonths left
 */
public class BalanceController implements InputDeserializer{

    private AccountData accountData;
    public static BalanceQueryData balanceQueryData;

    private int balanceMonth;

    public BalanceController(ArrayList<String> inputStr) {
        deserializeInputStream(inputStr);
    }

    protected void getBalanceStatus() {
        int amountPaid = countLoanAmountPaid(accountData, balanceMonth);
        int emiMonthsLeft = countEMIMonthsLeft(accountData.getLoanData(), amountPaid);
        balanceQueryData = new BalanceQueryData(amountPaid, emiMonthsLeft);
        printBalanceQueryResult();
    }

    private void printBalanceQueryResult() {
        System.out.println(
                        accountData.getLoanData().getBankName() + " " +
                        accountData.getLoanData().getUserName() + " " +
                        balanceQueryData.toString()
        );
    }

    private int countLoanAmountPaid(AccountData accountData, int balanceMonth) {
        int amountPaid = accountData.getLoanData().getEmiAmount() * balanceMonth;
        for (PaymentData paymentData : accountData.getPaymentDataList()) {
            if (paymentData.getEmiMonth() <= balanceMonth) {
                amountPaid += paymentData.getLumpSum();
            }
        }
        return amountPaid;
    }

    private int countEMIMonthsLeft(LoanData loanData, int amountPaid) {
        int amountLeftToPay = loanData.getAmountToPay() - amountPaid;
        return (int) Math.ceil((double)amountLeftToPay / loanData.getEmiAmount());
    }

    @Override
    public void deserializeInputStream(ArrayList<String> inputStream) {
        String bankName = inputStream.get(0);
        String userName = inputStream.get(1);
        accountData = AccountData.findAccountData(bankName, userName);
        balanceMonth = Integer.parseInt(inputStream.get(2));
    }
}
