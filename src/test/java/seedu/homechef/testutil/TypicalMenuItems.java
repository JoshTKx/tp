package seedu.homechef.testutil;

import static seedu.homechef.logic.commands.CommandTestUtil.VALID_MENU_BIRTHDAY_PRICE;
import static seedu.homechef.logic.commands.CommandTestUtil.VALID_MENU_BREAD_PRICE;
import static seedu.homechef.logic.commands.CommandTestUtil.VALID_MENU_CHICKEN_PRICE;
import static seedu.homechef.logic.commands.CommandTestUtil.VALID_MENU_COOKIES_PRICE;
import static seedu.homechef.logic.commands.CommandTestUtil.VALID_MENU_CUPCAKES_PRICE;
import static seedu.homechef.logic.commands.CommandTestUtil.VALID_MENU_DOUGHNUT_PRICE;
import static seedu.homechef.logic.commands.CommandTestUtil.VALID_MENU_MUFFIN_PRICE;
import static seedu.homechef.logic.commands.CommandTestUtil.VALID_MENU_THREETIER_PRICE;
import static seedu.homechef.logic.commands.CommandTestUtil.VALID_MENU_WEDDING_PRICE;

import seedu.homechef.model.common.Food;
import seedu.homechef.model.common.Price;
import seedu.homechef.model.menu.Availability;
import seedu.homechef.model.menu.MenuBook;
import seedu.homechef.model.menu.MenuItem;

/**
 * A utility class containing a list of {@code MenuItem} objects to be used in tests.
 * Names match the Food values used in TypicalOrders and CommandTestUtil so that
 * AddCommand/EditCommand integration tests pass menu validation.
 */
public class TypicalMenuItems {

    // From TypicalOrders.getTypicalOrders() - ALICE through GEORGE
    public static final MenuItem BIRTHDAY = new MenuItem(
            new Food("Birthday Cake"), new Price(VALID_MENU_BIRTHDAY_PRICE), Availability.YES);
    public static final MenuItem BREAD = new MenuItem(
            new Food("Sourdough Bread"), new Price(VALID_MENU_BREAD_PRICE), Availability.YES);
    public static final MenuItem CUPCAKES = new MenuItem(
            new Food("Cupcakes (24pcs)"), new Price(VALID_MENU_CUPCAKES_PRICE), Availability.YES);
    public static final MenuItem THREETIER = new MenuItem(
            new Food("Wedding Cake - 3 Tier"), new Price(VALID_MENU_THREETIER_PRICE), Availability.YES);
    public static final MenuItem COOKIES = new MenuItem(
            new Food("Chocolate Chip Cookies (3pcs)"), new Price(VALID_MENU_COOKIES_PRICE), Availability.YES);
    public static final MenuItem MUFFIN = new MenuItem(
            new Food("Muffin (4pc)"), new Price(VALID_MENU_MUFFIN_PRICE), Availability.YES);
    public static final MenuItem DOUGHNUT = new MenuItem(
            new Food("Doughnut Assortment"), new Price(VALID_MENU_DOUGHNUT_PRICE), Availability.YES);

    // From CommandTestUtil - VALID_FOOD_BOB = "Wedding Cake" (distinct from "Wedding Cake - 3 Tier")
    public static final MenuItem WEDDING = new MenuItem(
            new Food("Wedding Cake"), new Price(VALID_MENU_WEDDING_PRICE), Availability.YES);

    // Extra item used in new integration test cases
    public static final MenuItem CHICKEN_RICE = new MenuItem(
            new Food("Chicken Rice"), new Price(VALID_MENU_CHICKEN_PRICE), Availability.YES);

    private TypicalMenuItems() {
    } // prevents instantiation

    /**
     * Returns a {@code MenuBook} with all the typical menu items.
     */
    public static MenuBook getTypicalMenuBook() {
        MenuBook mb = new MenuBook();
        for (MenuItem item : getTypicalMenuItems()) {
            mb.addMenuItem(item);
        }
        return mb;
    }

    /**
     * Returns an array of all typical menu items.
     */
    public static MenuItem[] getTypicalMenuItems() {
        return new MenuItem[]{BIRTHDAY, BREAD, CUPCAKES, THREETIER, COOKIES, MUFFIN, DOUGHNUT, WEDDING, CHICKEN_RICE};
    }
}
