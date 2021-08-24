/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * @author Rushabh
 */
public class PaymentData {

    private final int lumpSum;
    private final int emiMonth;

    public PaymentData(int lumpSum, int emiMonth){
        this.lumpSum = lumpSum;
        this.emiMonth = emiMonth;
    }

    public int getLumpSum() {
        return lumpSum;
    }

    public int getEmiMonth() {
        return emiMonth;
    }
}
