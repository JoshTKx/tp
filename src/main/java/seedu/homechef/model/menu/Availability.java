package seedu.homechef.model.menu;

import static java.util.Objects.requireNonNull;
import static seedu.homechef.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

/**
 * Availability states for a menu item.
 */
public enum Availability {
    YES("Yes"),
    NO("No");

    public static final String MESSAGE_CONSTRAINTS = "Availability must be 'Yes' or 'No'";

    private final String displayValue;

    Availability(String displayValue) {
        assert displayValue != null && !displayValue.isEmpty();
        this.displayValue = displayValue;
    }

    /**
     * Parses user-facing availability text.
     */
    public static Availability fromString(String value) {
        requireNonNull(value);
        checkArgument(isValidAvailability(value), MESSAGE_CONSTRAINTS);
        return Arrays.stream(Availability.values())
                .filter(status -> status.displayValue.equalsIgnoreCase(value))
                .findFirst()
                .get();
    }

    /**
     * Returns true if the specified string is a valid availability value.
     */
    public static boolean isValidAvailability(String test) {
        return Arrays.stream(Availability.values())
                .anyMatch(status -> status.displayValue.equalsIgnoreCase(test));
    }

    public boolean isAvailable() {
        return this == YES;
    }

    @Override
    public String toString() {
        return displayValue;
    }
}



