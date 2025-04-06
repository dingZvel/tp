package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's description in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {
    public static final String MESSAGE_CONSTRAINTS = "Description can take any values, and if the content is blank, "
            + "the description field will be cleared.";

    public final String description;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    public static boolean isValidDescription(String test) {
        return test != null;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Description)) {
            return false;
        }

        Description otherDescription = (Description) other;
        return otherDescription.description.trim().equals(description.trim());
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
