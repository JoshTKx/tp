package seedu.homechef.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_FOOD = new Prefix("f/");
    public static final Prefix PREFIX_CUSTOMER = new Prefix("c/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_BANK_PAYMENT = new Prefix("bank/");
    public static final Prefix PREFIX_PAYNOW_PAYMENT = new Prefix("paynow/");
    public static final Prefix PREFIX_CASH_PAYMENT = new Prefix("cash/");
    public static final Prefix PREFIX_PRICE = new Prefix("$/");
    public static final Prefix PREFIX_AVAILABILITY = new Prefix("v/");
    public static final Prefix PREFIX_COMPLETION_STATUS = new Prefix("cs/");
    public static final Prefix PREFIX_PAYMENT_STATUS = new Prefix("ps/");
    public static final Prefix PREFIX_QUANTITY = new Prefix("q/");
}
