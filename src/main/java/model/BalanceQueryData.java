package model;

/**
 * @author Rushabh
 */
public class BalanceQueryData {

    private final int amountPaid;
    private final int emiMonthsLeft;

    public BalanceQueryData(int amountPaid, int emiMonth) {
        this.amountPaid = amountPaid;
        this.emiMonthsLeft = emiMonth;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public int getEmiMonthsLeft() {
        return emiMonthsLeft;
    }

    @Override
    public String toString() {
        return amountPaid + " " + emiMonthsLeft;
    }
}
