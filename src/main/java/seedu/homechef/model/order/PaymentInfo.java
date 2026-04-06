package seedu.homechef.model.order;

/**
 * Represents payment information for an order.
 */
public interface PaymentInfo {

    String MESSAGE_INVALID_BANK_REFERENCE =
            "Bank payment requires a non-blank payment reference/details value.";
    String MESSAGE_INVALID_PAYNOW_HANDLE =
            "PayNow payment requires a non-blank phone number or handle.";

    /**
     * Returns the payment type.
     */
    PaymentType getType();

    /**
     * Returns the PayNow handle for PayNow payments, or null otherwise.
     */
    default String getHandle() {
        return null;
    }

    /**
     * Returns the bank reference for bank payments, or null otherwise.
     */
    default String getReferenceNumber() {
        return null;
    }

    /**
     * Creates a payment object from a payment type.
     */
    static PaymentInfo fromType(PaymentType type) {
        switch (type) {
        case CASH:
            return cash();
        case PAYNOW:
            throw new IllegalArgumentException("PAYNOW requires a non-blank handle.");
        case BANK:
            return bank();
        default:
            throw new IllegalArgumentException("Unsupported payment type: " + type);
        }
    }

    static PaymentInfo cash() {
        return new CashPayment();
    }

    static PaymentInfo payNow(String handle) {
        return new PayNowPayment(handle);
    }

    static PaymentInfo bank() {
        return new BankPayment();
    }

    static PaymentInfo bank(String reference) {
        return new BankPayment(reference);
    }

    /**
     * Reconstructs a payment object from flattened legacy storage fields.
     */
    static PaymentInfo fromLegacyFields(PaymentType type, String handle, String bankName, String referenceNumber,
                                        String lastFourDigits, String walletProvider, String walletAccountId) {
        switch (type) {
        case CASH:
            return cash();
        case PAYNOW:
            String payNowHandle = firstNonBlank(handle, joinNonBlank(" ", walletProvider, walletAccountId),
                    bankName, referenceNumber);
            if (payNowHandle == null) {
                throw new IllegalArgumentException(MESSAGE_INVALID_PAYNOW_HANDLE);
            }
            return payNow(payNowHandle);
        case BANK:
            String bankReference = firstNonBlank(referenceNumber, lastFourDigits,
                    joinNonBlank(" ", walletProvider, walletAccountId), bankName, handle);
            return bankReference == null ? bank() : bank(bankReference);
        default:
            throw new IllegalArgumentException("Unsupported payment type: " + type);
        }
    }

    private static String firstNonBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.isBlank()) {
                return value;
            }
        }
        return null;
    }

    private static String joinNonBlank(String separator, String... values) {
        StringBuilder builder = new StringBuilder();
        for (String value : values) {
            if (value == null || value.isBlank()) {
                continue;
            }
            if (builder.length() > 0) {
                builder.append(separator);
            }
            builder.append(value);
        }
        return builder.length() == 0 ? null : builder.toString();
    }
}
