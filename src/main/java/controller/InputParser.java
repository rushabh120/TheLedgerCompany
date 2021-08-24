/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @InputParser:
 * Read input file and call controller as per Command (LOAN/ PAYMENT/ BALANCE)
 */
public class InputParser {
    
    private final ArrayList<String> inputStream;
    
    public InputParser(ArrayList<String> fileStream) {
        inputStream = new ArrayList();
        inputStream.addAll(fileStream);
    }
    
    public void commandParser() {
        for(String string : inputStream) {
            String[] inputString = string.split(" ");
            ArrayList<String> inputStr = new ArrayList(Arrays.asList(inputString));

            try {
                Command command = Command.valueOf(inputStr.get(0));
                inputStr.remove(0);

                switch (command) {
                    case LOAN :
                        LoanActionController loanActionController = new LoanActionController(inputStr);
                        loanActionController.allocateLoan();
                        break;
                    case PAYMENT :
                        PaymentController paymentController = new PaymentController(inputStr);
                        paymentController.addPaymentData();
                        break;
                    case BALANCE :
                        BalanceController balanceController = new BalanceController(inputStr);
                        balanceController.getBalanceStatus();
                        break;
                }
            }
            catch(Exception exception) {
                System.out.println("System could not identify the command.");
            }

        }

    }

}

 enum Command {
    LOAN,
    PAYMENT,
    BALANCE;
}