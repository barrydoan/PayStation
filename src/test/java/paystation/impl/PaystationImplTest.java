package paystation.impl;

import com.sun.org.apache.bcel.internal.generic.FADD;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import paystation.IllegalCoinException;
import paystation.Paystation;
import paystation.Receipt;

import static org.junit.jupiter.api.Assertions.*;

class PaystationImplTest {

    Paystation ps;

    @BeforeEach
    void setUp() {
        ps = new PaystationImpl();
    }

    @Test
    public void shouldDisplay2MinFor5Cents() throws IllegalCoinException {
        ps.addPayment(5);
        assertEquals(2, ps.readDisplay());
    }

    @Test
    public void shouldDisplay10MinFor25Cents() throws IllegalCoinException {
        ps.addPayment(25);
        assertEquals(10, ps.readDisplay());
    }

    @Test()
    public void shouldRejectIlligalCoin() throws IllegalCoinException {
        assertThrows(IllegalCoinException.class, () -> {
            ps.addPayment(17);
        });
    }

    @Test
    public void shouldDisplay14MinFor10And25Cents() throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals(14, ps.readDisplay());
    }

    @Test
    public void shouldReturnCorrectReceiptWhenBuy() throws IllegalCoinException {
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(25);
        Receipt receipt;
        receipt = ps.buy();
        assertNotNull(receipt, "Receipt reference cannot be null");
        assertEquals(16, receipt.value());
    }

    @Test
    public void shouldReturnReceiptWhenBuy100c() throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);

        Receipt receipt;
        receipt = ps.buy();
        assertEquals(40, receipt.value());
    }

    @Test
    public void shouldClearAfterBuy() throws IllegalCoinException {
        ps.addPayment(25);
        ps.buy();
        // verify that the display reads 0
        assertEquals(0, ps.readDisplay());
        // verify that a following by scenario behaves properly
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals(14, ps.readDisplay());
        Receipt r = ps.buy();
        assertEquals(14, r.value());
        // check the display should be cleared
        assertEquals(0, ps.readDisplay());
    }

    @Test
    public void shouldClearAfterCancel() throws IllegalCoinException {
        ps.addPayment(10);
        ps.cancel();
        assertEquals(0, ps.readDisplay());
        // test after cancel should work
        ps.addPayment(25);
        assertEquals(10, ps.readDisplay());
    }
}