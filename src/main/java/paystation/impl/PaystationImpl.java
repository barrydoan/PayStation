package paystation.impl;

import paystation.IllegalCoinException;
import paystation.Paystation;
import paystation.Receipt;

public class PaystationImpl implements Paystation {

    private int insetedSoFar;
    private int timeBought;

    @Override
    public void addPayment(int coinValue) throws IllegalCoinException {
        switch (coinValue) {
            case 5:
                break;
            case 10:
                break;
            case 25:
                break;
            default:
                throw  new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insetedSoFar += coinValue;
        timeBought = insetedSoFar / 5 * 2;
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        System.out.println("Test");
        Receipt r =  new ReceiptImpl(timeBought);
        reset();
        return r;
    }

    private void reset() {
        timeBought = 0;
        insetedSoFar = 0;
    }

    @Override
    public void cancel() {
        reset();
    }
}
