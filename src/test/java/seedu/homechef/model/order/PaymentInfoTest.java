package seedu.homechef.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homechef.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PaymentInfoTest {
    @Test
    public void fromType_cash_success() {
        PaymentInfo info = PaymentInfo.fromType(PaymentType.CASH);
        assertEquals(PaymentType.CASH, info.getType());
        assertEquals("CASH", info.toString());
    }

    @Test
    public void fromType_bank_success() {
        PaymentInfo info = PaymentInfo.fromType(PaymentType.BANK);
        assertEquals(PaymentType.BANK, info.getType());
        assertEquals("BANK", info.toString());
    }

    @Test
    public void fromType_payNow_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> PaymentInfo.fromType(PaymentType.PAYNOW));
    }

    @Test
    public void factories_success() {
        assertEquals(PaymentType.CASH, PaymentInfo.cash().getType());
        assertEquals(PaymentType.PAYNOW, PaymentInfo.payNow("alice@paynow").getType());
        assertEquals("BANK", PaymentInfo.bank().toString());
        assertEquals("BANK (ref: REF123)", PaymentInfo.bank("REF123").toString());
    }

    @Test
    public void fromLegacyFields_payNow_success() {
        PaymentInfo info = PaymentInfo.fromLegacyFields(PaymentType.PAYNOW, "+65 91234567",
                null, null, null, null, null);
        assertEquals(PaymentType.PAYNOW, info.getType());
        assertEquals("PAYNOW (handle: +65 91234567)", info.toString());
    }

    @Test
    public void fromLegacyFields_bankFallbacks_success() {
        assertEquals("REF123", PaymentInfo.fromLegacyFields(
                PaymentType.BANK, null, "DBS", "REF123", null, null, null).getReferenceNumber());
        assertEquals("4321", PaymentInfo.fromLegacyFields(
                PaymentType.BANK, null, null, null, "4321", null, null).getReferenceNumber());
        assertEquals("GrabPay user@grab.com", PaymentInfo.fromLegacyFields(
                PaymentType.BANK, null, null, null, null, "GrabPay", "user@grab.com").getReferenceNumber());
    }

    @Test
    public void fromLegacyFields_failures() {
        assertThrows(IllegalArgumentException.class, () -> PaymentInfo.fromLegacyFields(
                PaymentType.PAYNOW, null, null, null, null, null, null));
    }

    @Test
    public void equals() {
        PaymentInfo cash1 = PaymentInfo.cash();
        PaymentInfo cash2 = PaymentInfo.cash();
        PaymentInfo payNow = PaymentInfo.payNow("alice@paynow");

        assertTrue(cash1.equals(cash1));
        assertTrue(cash1.equals(cash2));
        assertEquals(cash1.hashCode(), cash2.hashCode());
        assertFalse(cash1.equals(payNow));
        assertFalse(cash1.equals(null));
        assertFalse(cash1.equals("CASH"));
    }
}
