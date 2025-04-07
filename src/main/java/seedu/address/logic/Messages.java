package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.client.Client;
import seedu.address.model.client.Description;
import seedu.address.model.client.Priority;
import seedu.address.model.client.ProductPreference;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The client index provided is "
            + "positive but out of range";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d clients listed!";
    public static final String MESSAGE_PERSONS_RANKED = "%1$d clients ranked!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_UNKNOWN_COMPARATOR_KEYWORD = "Please provide a valid keyword for entry ranking";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code client} for display to the user.
     */
    public static String format(Client client) {
        final StringBuilder builder = new StringBuilder();
        builder.append(client.getName())
                .append("; Phone: ")
                .append(client.getPhone())
                .append("; Email: ")
                .append(client.getEmail())
                .append("; Address: ")
                .append(client.getAddress())
                .append("; Tags: ");
        client.getTags().forEach(builder::append);
        if (client.getProductPreference().isPresent()) {
            builder.append("; Product Preference: ")
                    .append(client.getProductPreference().map(ProductPreference::toString).get());
        }

        if (client.getDescription().isPresent()) {
            builder.append("; Description: ")
                    .append(client.getDescription().map(Description::toString).get());
        }

        if (client.getPriority().isPresent()) {
            builder.append("; Priority: ")
                    .append(client.getPriority().map(Priority::toString).orElse(""));
        }


        return builder.toString();
    }

}
