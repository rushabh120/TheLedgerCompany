import controller.BalanceController;
import org.junit.jupiter.api.Test;
import controller.InputParser;
import database.AccountData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SystemTest {

    private final String bankName = "TestBankName";
    private final String userName = "TestUserName";

    private final int principleAmount = 2400;
    private final int years = 1;
    private final int interest = 2;

    @Test
    public void testLoanCommand_WhenLoanAllocated_ShouldCreateAccountData() {
        // GIVEN
        ArrayList<String> inputLoan = getInputStreamWithLoanCommand();

        // WHEN
        InputParser inputParser = new InputParser(inputLoan);
        inputParser.commandParser();

        // THEN
        assertNotNull(AccountData.findAccountData(bankName, userName));
    }

    @Test
    public void testPaymentCommand_WhenPaymentDone_ShouldAddPaymentDataToPaymentDataList() {
        // GIVEN
        ArrayList<String> inputStream = new ArrayList();
        ArrayList<String> inputLoan = getInputStreamWithLoanCommand();
        ArrayList<String> inputPayment = getInputStreamWithPaymentCommand();
        inputStream.add(inputLoan.get(0));
        inputStream.add(inputPayment.get(0));

        // WHEN
        InputParser inputParser = new InputParser(inputStream);
        inputParser.commandParser();

        // THEN
        assertEquals(1, AccountData.findAccountData(bankName, userName).
                getPaymentDataList().size());
    }

    @Test
    public void testBalanceCommand_WhenNoPaymentsDone_ShouldGetInitialResult() {
        // GIVEN
        ArrayList<String> inputStream = new ArrayList();
        ArrayList<String> inputLoan = getInputStreamWithLoanCommand();
        ArrayList<String> inputBalanceQ = getInputStreamWithBalanceCommand();
        inputStream.add(inputLoan.get(0));
        inputStream.add(inputBalanceQ.get(0));

        // WHEN
        int expectedPaymentDone = 0;
        int expectedEmiLeft = years * 12;
        InputParser inputParser = new InputParser(inputStream);
        inputParser.commandParser();

        // THEN
        assertEquals(expectedPaymentDone, BalanceController.balanceQueryData.getAmountPaid());
        assertEquals(expectedEmiLeft, BalanceController.balanceQueryData.getEmiMonthsLeft());
    }

    private ArrayList<String> getInputStreamWithPaymentCommand() {
        ArrayList<String> inputStream = new ArrayList();
        inputStream.add("PAYMENT " + bankName + " " + userName + " 1000 5");
        return inputStream;
    }

    private ArrayList<String> getInputStreamWithLoanCommand() {
        ArrayList<String> inputStream = new ArrayList();
        inputStream.add("LOAN " + bankName + " "+ userName + " " + principleAmount
                + " " + years + " " + interest);
        return inputStream;
    }

    private ArrayList<String> getInputStreamWithBalanceCommand() {
        ArrayList<String> inputStream = new ArrayList();
        inputStream.add("BALANCE " + bankName + " " + userName + " 0");
        return inputStream;
    }
}
