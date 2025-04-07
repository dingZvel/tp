package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditClientDescriptor;
import seedu.address.model.client.Client;
import seedu.address.model.client.Frequency;
import seedu.address.model.client.Priority;
import seedu.address.model.client.ProductPreference;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Client.
 */
public class ClientUtil {

    /**
     * Returns an add command string for adding the {@code client}.
     */
    public static String getAddCommand(Client client) {
        return AddCommand.COMMAND_WORD + " " + getClientDetails(client);
    }

    /**
     * Returns the part of command string for the given {@code client}'s details.
     */
    public static String getClientDetails(Client client) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + client.getName().fullName + " ");
        sb.append(PREFIX_PHONE + client.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + client.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + client.getAddress().value + " ");
        client.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        sb.append(PREFIX_PREFERENCE + client.getProductPreference()
                .map(ProductPreference::toString).orElse("") + " ");
        sb.append(PREFIX_FREQUENCY + client.getProductPreference()
                .map(ProductPreference::getFrequency).map(Frequency::toString).orElse("") + " ");
        sb.append(PREFIX_PRIORITY + client.getPriority().map(Priority::toString)
                .map(ClientUtil::priorityStringToInt).orElse("") + " ");

        return sb.toString();
    }

    private static String priorityStringToInt(String priority) {
        switch (priority) {
        case "VIP":
            return "3";
        case "PREMIUM":
            return "2";
        case "STANDARD":
            return "1";
        default:
            return "";
        }
    }

    /**
     * Returns the part of command string for the given {@code EditClientDescriptor}'s details.
     */
    public static String getEditClientDescriptorDetails(EditClientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        sb.append(" ");
        descriptor.getProductPreference().ifPresent(productPreference -> sb.append(PREFIX_PREFERENCE)
                .append(productPreference.toString()).append(" "));
        descriptor.getProductPreference()
                .ifPresent(productPreference -> sb.append(PREFIX_FREQUENCY)
                        .append(productPreference.getFrequency().toString()).append(" "));

        descriptor.getPriority()
                .ifPresent(priority -> sb.append(PREFIX_PRIORITY).append(priorityStringToInt(priority.toString()))
                        .append(" "));
        return sb.toString();
    }
}
