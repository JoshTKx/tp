package seedu.homechef.model.order;

/**
 * Represents a cash payment.
 */
public final class CashPayment implements PaymentInfo {

    @Override
    public PaymentType getType() {
        return PaymentType.CASH;
    }

    @Override
    public String toString() {
        return "CASH";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof CashPayment;
    }

    @Override
    public int hashCode() {
        return PaymentType.CASH.hashCode();
    }
}
