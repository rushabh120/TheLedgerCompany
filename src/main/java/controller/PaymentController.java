/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import database.AccountData;
import model.PaymentData;

/**
 *
 * @PaymentController
 * Add paymentData to records to use it while calculating balance
 */
public class PaymentController implements InputDeserializer{

    private AccountData accountData;
    private PaymentData paymentData;

    public PaymentController(ArrayList<String> inputStr) {
        deserializeInputStream(inputStr);
    }

    protected void addPaymentData() {
        accountData.addPaymentData(paymentData);
    }

    @Override
    public void deserializeInputStream(ArrayList<String> inputStream) {
        String bankName = inputStream.get(0);
        String userName = inputStream.get(1);
        int lumpSum = Integer.parseInt(inputStream.get(2));
        int emiMonth = Integer.parseInt(inputStream.get(3));
        accountData = AccountData.findAccountData(bankName, userName);
        paymentData = new PaymentData(lumpSum, emiMonth);
    }
}
