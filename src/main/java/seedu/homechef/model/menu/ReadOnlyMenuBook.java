package seedu.homechef.model.menu;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a MenuBook.
 */
public interface ReadOnlyMenuBook {

    /**
     * Returns an unmodifiable view of the menu items list.
     * This list will not contain any duplicate menu items.
     */
    ObservableList<MenuItem> getMenuItemList();

    /**
     * Returns the menu item whose name exactly matches {@code foodName} (case-insensitive),
     * or empty if no item matches.
     */
    Optional<MenuItem> findExact(String foodName);

    /**
     * Returns all menu items whose name contains {@code foodName} as a substring (case-insensitive).
     */
    List<MenuItem> findBySubstring(String foodName);
}
