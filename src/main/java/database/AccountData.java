/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;
import java.util.HashMap;

import model.LoanData;
import model.PaymentData;

/**
 *
 * @author Rushabh
 */
public class AccountData {

    private final LoanData loanData;
    private final ArrayList<PaymentData> paymentDataList;

    public static ArrayList<AccountData> accountDataList = new ArrayList<>();
    public static HashMap<String, Integer> accountDataMap = new HashMap();
    
    public AccountData(LoanData loanData) {
        this.loanData = loanData;
        paymentDataList = new ArrayList();
    }
    
    public void addPaymentData(PaymentData paymentData) {
        this.paymentDataList.add(paymentData);
    }

    public LoanData getLoanData() {
        return loanData;
    }

    public ArrayList<PaymentData> getPaymentDataList() {
        return paymentDataList;
    }

    public static void addToDataList(AccountData accountData){
        addToDataMap(accountData);
        accountDataList.add(accountData);
    }

    public static AccountData findAccountData(String bankName, String userName) {
        String key = bankName + " " + userName;
        if(!accountDataMap.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        int index = accountDataMap.get(bankName + " " + userName);
        return accountDataList.get(index);
    }

    private static void addToDataMap(AccountData accountData) {
        String key = accountData.loanData.getBankName() + " " +
                        accountData.loanData.getUserName();
        accountDataMap.put(key, accountDataList.size());
    }
}
