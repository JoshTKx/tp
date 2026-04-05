package seedu.homechef.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homechef.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.homechef.model.order.DietTag;

public class DietTagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DietTag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        // EP: empty string tag name
        assertThrows(IllegalArgumentException.class, () -> new DietTag(invalidTagName)); // empty string, boundary value
    }


    @Test
    public void isValidTagName() {
        // EP: null tag name
        assertThrows(NullPointerException.class, () -> DietTag.isValidTagName(null));

        // EP: invalid tag names
        assertFalse(DietTag.isValidTagName(""));
        assertFalse(DietTag.isValidTagName(" "));
        assertFalse(DietTag.isValidTagName("vegan!"));
        assertFalse(DietTag.isValidTagName("-vegan"));

        // EP: valid tag names
        assertTrue(DietTag.isValidTagName("vegan"));
        assertTrue(DietTag.isValidTagName("gluten free"));
        assertTrue(DietTag.isValidTagName("low-carb"));
    }

    @Test
    public void equals() {
        DietTag dietTag = new DietTag("vegan");

        // EP: same values -> returns true
        assertTrue(dietTag.equals(new DietTag("vegan")));

        // EP: same object -> returns true
        assertTrue(dietTag.equals(dietTag));

        // EP: null -> returns false
        assertFalse(dietTag.equals(null));

        // EP: different types -> returns false
        assertFalse(dietTag.equals(1));

        // EP: different values -> returns false
        assertFalse(dietTag.equals(new DietTag("halal")));
    }

    @Test
    public void toStringMethod() {
        DietTag dietTag = new DietTag("vegan");

        // EP: string representation returns stored tag name
        assertEquals("vegan", dietTag.toString());
    }
}
