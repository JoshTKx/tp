package seedu.homechef.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homechef.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        // EP: null Customer name
        assertThrows(NullPointerException.class, () -> new Customer(null));
    }

    @Test
    public void constructor_invalidCustomer_throwsIllegalArgumentException() {
        String invalidCustomer = "";

        // EP: invalid Customer name
        assertThrows(IllegalArgumentException.class, () -> new Customer(invalidCustomer));
    }

    @Test
    public void isValidCustomer() {
        // EP: null Customer
        assertThrows(NullPointerException.class, () -> Customer.isValidCustomer(null));

        // EP: invalid Customer
        assertFalse(Customer.isValidCustomer("")); // empty string, boundary value
        assertFalse(Customer.isValidCustomer(" ")); // spaces only
        assertFalse(Customer.isValidCustomer("^")); // only non-alphanumeric characters
        assertFalse(Customer.isValidCustomer("peter*")); // contains non-alphanumeric characters

        // EP: valid Customer
        assertTrue(Customer.isValidCustomer("peter jack")); // alphabets only, boundary value
        assertTrue(Customer.isValidCustomer("12345")); // numbers only
        assertTrue(Customer.isValidCustomer("peter the 2nd")); // alphanumeric characters
        assertTrue(Customer.isValidCustomer("D'Souza")); // apostrophe allowed
        assertTrue(Customer.isValidCustomer("D\u2019Souza")); // typographic apostrophe allowed
        assertTrue(Customer.isValidCustomer("Tan Ah-Kow")); // hyphen allowed
        assertTrue(Customer.isValidCustomer("A/B Testing")); // slash allowed
        assertFalse(Customer.isValidCustomer("@Home")); // leading symbol is not allowed
        assertTrue(Customer.isValidCustomer("Jose Alvarez")); // latin characters
        assertTrue(Customer.isValidCustomer("李雷")); // CJK unicode characters
        assertTrue(Customer.isValidCustomer("Li Lei")); // multilingual-style name
        assertTrue(Customer.isValidCustomer("Tan Chee Meng @ Chen Zi Ming")); // at sign allowed
        assertTrue(Customer.isValidCustomer("Anna M.")); // period allowed
        assertTrue(Customer.isValidCustomer("Capital Tan")); // with capital letters
        assertTrue(Customer.isValidCustomer("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void equals() {
        Customer customer = new Customer("Valid Customer");

        // EP: same values -> returns true
        assertTrue(customer.equals(new Customer("Valid Customer")));

        // EP: same object -> returns true
        assertTrue(customer.equals(customer));

        // EP: null -> returns false
        assertFalse(customer.equals(null));

        // EP: different types -> returns false
        assertFalse(customer.equals(5.0f));

        // EP: different values -> returns false
        assertFalse(customer.equals(new Customer("Other Valid Customer")));

        // EP: case differences -> returns true
        Customer lowerCaseCustomer = new Customer("valid customer");
        assertTrue(customer.equals(lowerCaseCustomer));
        assertEquals(customer.hashCode(), lowerCaseCustomer.hashCode());
    }
}
