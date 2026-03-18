package seedu.homechef.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.homechef.model.Model.PREDICATE_SHOW_ALL_ORDERS;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.homechef.commons.core.index.Index;
import seedu.homechef.commons.util.ToStringBuilder;
import seedu.homechef.logic.Messages;
import seedu.homechef.logic.commands.exceptions.CommandException;
import seedu.homechef.model.Model;
import seedu.homechef.model.order.Address;
import seedu.homechef.model.order.CompletionStatus;
import seedu.homechef.model.order.Customer;
import seedu.homechef.model.order.Date;
import seedu.homechef.model.order.Email;
import seedu.homechef.model.order.Food;
import seedu.homechef.model.order.Order;
import seedu.homechef.model.order.PaymentInfo;
import seedu.homechef.model.order.Phone;
import seedu.homechef.model.tag.DietTag;

/**
 * Marks an order identified using it's displayed index from the HomeChef as In Progress.
 */
public class MarkInProgressCommand extends Command {

    public static final String COMMAND_WORD = "in_progress";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the order identified by the index number used in the displayed order list as in progress.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_IN_PROGRESS_ORDER_SUCCESS = "Marked Order as In Progress: %1$s";

    private final Index targetIndex;

    public MarkInProgressCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToMarkIncomplete = lastShownList.get(targetIndex.getZeroBased());
        Order incompleteOrder = createIncompleteOrder(orderToMarkIncomplete);

        model.setOrder(orderToMarkIncomplete, incompleteOrder);
        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        return new CommandResult(String.format(
                MESSAGE_IN_PROGRESS_ORDER_SUCCESS, Messages.format(orderToMarkIncomplete)));
    }

    /**
     * Creates and returns a {@code Order} with the details of {@code orderToMarkComplete}
     * marking with {@code CompletionStatus} to a value of 1 (complete).
     */
    private static Order createIncompleteOrder(Order orderToMarkIncomplete) {
        assert orderToMarkIncomplete != null;

        Food food = orderToMarkIncomplete.getFood();
        Customer customer = orderToMarkIncomplete.getCustomer();
        Phone phone = orderToMarkIncomplete.getPhone();
        Email email = orderToMarkIncomplete.getEmail();
        Address address = orderToMarkIncomplete.getAddress();
        Date date = orderToMarkIncomplete.getDate();
        CompletionStatus updatedCompletionStatus = new CompletionStatus("In progress");
        Set<DietTag> dietTags = orderToMarkIncomplete.getTags();
        Optional<PaymentInfo> paymentInfo = orderToMarkIncomplete.getPaymentInfo();

        return new Order(food, customer, phone,
                email, address, date, updatedCompletionStatus, dietTags, paymentInfo);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkInProgressCommand)) {
            return false;
        }

        MarkInProgressCommand otherMarkCompleteCommand = (MarkInProgressCommand) other;
        return targetIndex.equals(otherMarkCompleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
