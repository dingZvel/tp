package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.client.Client;


/**
 * Filters all persons in the address book to display only those that match the given filter condition.
 */
public class FilterCommand extends AbstractFilterCommand {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all persons based on either priority level or "
            + "product preference containing any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: [" + PREFIX_PREFERENCE + "PRODUCT_PREFERENCE] " + "[" +PREFIX_PRIORITY + "PRIORITY_LEVEL]\n"
            + "Example: " + COMMAND_WORD + " pref/shampoo or " + COMMAND_WORD + " priority/1\n";


    public static final String MESSAGE_ONLY_ONE_FILTER_ALLOWED = "Filter command takes exactly one "
            + "filter condition of either product preference or priority and the arguments must not be empty! \n"
            + COMMAND_WORD + " pref/PRODUCT or priority/LEVEL\n"
            + "Example: " + COMMAND_WORD + " pref/coffee or " + COMMAND_WORD + " priority/3";



    public FilterCommand(Predicate<Client> predicate) {
        super(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
