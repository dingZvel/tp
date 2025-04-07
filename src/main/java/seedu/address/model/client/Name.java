package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Please use only case-sensitive words and one space "
                    + "between each word with length below 40 characters, e.g. “John Doe”";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?=.{1,40}$)[a-zA-Z]+( [a-zA-Z]+)*$";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        String capitalizedName = capitalizeFirstLetters(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = capitalizedName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Converts every first letter of each word in the name to its capital form.
     * e.g. john doe -> John Doe
     *
     * @param name The client's name
     * @return The capitalized client name
     */
    public static String capitalizeFirstLetters(String name) {
        requireNonNull(name);
        if (name.trim().isEmpty()) {
            return "";
        }

        String[] splitNameByWhiteSpace = name.split("\\s+");
        StringBuilder capitalizedNameBuilder = new StringBuilder();
        for (String word : splitNameByWhiteSpace) {
            capitalizedNameBuilder.append(Character.toUpperCase(word.charAt(0)))
                    .append(word, 1, word.length())
                    .append(" ");
        }
        return capitalizedNameBuilder.toString().trim();
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        Name otherName = (Name) other;
        return fullName.trim().equalsIgnoreCase(otherName.fullName.trim());
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
