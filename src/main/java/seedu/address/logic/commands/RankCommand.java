package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

/**
 * Ranks the current updated list of clients based on a key factor.
 */
public class RankCommand extends Command {

    public static final String COMMAND_WORD = "rank";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Ranks all clients based on "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORD]\n"
            + "Example: " + COMMAND_WORD + " total";

    private final Comparator<Client> comparator;

    public RankCommand(Comparator<Client> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredClientList(comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_RANKED, model.getSortedFilteredClientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RankCommand)) {
            return false;
        }

        RankCommand otherRankCommand = (RankCommand) other;
        return comparator.equals(otherRankCommand.comparator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("comparator", comparator)
                .toString();
    }
}
