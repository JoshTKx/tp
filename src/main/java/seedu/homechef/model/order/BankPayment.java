package seedu.homechef.model.order;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents a bank transfer payment.
 */
public final class BankPayment implements PaymentInfo {
    public static final String MESSAGE_INVALID_REFERENCE =
            "Bank payment requires 1-100 characters and at least one letter or digit.";

    private static final int MAX_REFERENCE_LENGTH = 100;
    private static final Pattern HAS_ALPHANUMERIC = Pattern.compile(".*[A-Za-z0-9].*");
    private final String reference;

    /**
     * Creates a bank transfer payment with the specified reference.
     *
     * @param reference Bank transfer reference.
     */
    public BankPayment(String reference) {
        requireNonNull(reference);
        String trimmedReference = reference.trim();
        if (trimmedReference.isEmpty()
                || trimmedReference.length() > MAX_REFERENCE_LENGTH
                || !HAS_ALPHANUMERIC.matcher(trimmedReference).matches()) {
            throw new IllegalArgumentException(MESSAGE_INVALID_REFERENCE);
        }
        this.reference = reference;
    }

    @Override
    public String getReference() {
        return reference;
    }

    @Override
    public String toString() {
        return "Bank: " + reference;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof BankPayment)) {
            return false;
        }
        BankPayment otherPayment = (BankPayment) other;
        return reference.equals(otherPayment.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(BankPayment.class, reference);
    }
}
