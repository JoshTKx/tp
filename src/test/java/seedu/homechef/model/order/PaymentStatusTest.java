package seedu.homechef.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homechef.model.order.PaymentStatus.STATUS_PAID;
import static seedu.homechef.model.order.PaymentStatus.STATUS_UNPAID;

import org.junit.jupiter.api.Test;

public class PaymentStatusTest {

    @Test
    public void constructor_paidAndUnpaid_setsStatusCorrectly() {
        PaymentStatus paidStatus = new PaymentStatus(true);
        PaymentStatus unpaidStatus = new PaymentStatus(false);

        assertTrue(paidStatus.isPaid());
        assertFalse(unpaidStatus.isPaid());
    }

    @Test
    public void toString_paidAndUnpaid_returnsCorrectString() {
        PaymentStatus paidStatus = new PaymentStatus(true);
        PaymentStatus unpaidStatus = new PaymentStatus(false);

        assertEquals(STATUS_PAID, paidStatus.toString());
        assertEquals(STATUS_UNPAID, unpaidStatus.toString());
    }

    @Test
    public void equals() {
        PaymentStatus paid1 = new PaymentStatus(true);
        PaymentStatus paid2 = new PaymentStatus(true);
        PaymentStatus unpaid = new PaymentStatus(false);

        // same object
        assertTrue(paid1.equals(paid1));

        // same value
        assertTrue(paid1.equals(paid2));

        // different value
        assertFalse(paid1.equals(unpaid));

        // null
        assertFalse(paid1.equals(null));

        // different type
        assertFalse(paid1.equals(STATUS_PAID));
    }

}
