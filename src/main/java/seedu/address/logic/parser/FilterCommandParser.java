package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Client;
import seedu.address.model.client.predicates.PriorityPredicate;
import seedu.address.model.client.predicates.ProductPreferenceContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    @Override
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PREFERENCE, PREFIX_PRIORITY);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PREFERENCE, PREFIX_PRIORITY);
        if (argMultimap.getSize() != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        boolean isPriorityFilterPresent = isArgumentPresent(argMultimap.getValue(PREFIX_PRIORITY));
        boolean isPreferenceFilterPresent = isArgumentPresent(argMultimap.getValue(PREFIX_PREFERENCE));
        boolean isBothFilterPresent = isPriorityFilterPresent && isPreferenceFilterPresent;
        boolean isNeitherFilterPresent = !isPriorityFilterPresent && !isPreferenceFilterPresent;

        if (isBothFilterPresent || isNeitherFilterPresent) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_ONLY_ONE_FILTER_ALLOWED));
        }

        Predicate<Client> predicate;
        if (isPriorityFilterPresent) {
            predicate =
                    new PriorityPredicate(ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY)));

        } else if (isPreferenceFilterPresent) {
            predicate = new ProductPreferenceContainsKeywordsPredicate(
                    Arrays.asList(argMultimap.getValue(PREFIX_PREFERENCE).get().split("\\s+")));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_ONLY_ONE_FILTER_ALLOWED));
        }

        return new FilterCommand(predicate);

    }

    /**
     * Returns true if argument contains a non-empty value.
     */
    private boolean isArgumentPresent(Optional<String> argument) {
        return argument.map(String::trim).map(s -> !s.isEmpty()).orElse(false);
    }

}
