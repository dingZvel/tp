package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(Name.isValidName("peter  jack")); // contains a continuous space longer than 2
        assertFalse(Name.isValidName(
                "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk")); // longer than 40 characters
        assertFalse(Name.isValidName("12345")); // numbers only
        assertFalse(Name.isValidName("this12345")); //numbers with alphabets

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr")); // long names
    }

    @Test
    public void capitalizeFirstLetters() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.capitalizeFirstLetters(null));

        // invalid name
        assertEquals("", Name.capitalizeFirstLetters(""));
        assertEquals("", Name.capitalizeFirstLetters("   "));
        assertEquals("^", Name.capitalizeFirstLetters("^"));
        assertEquals("Peter*", Name.capitalizeFirstLetters("peter*"));
        assertEquals("Peter John", Name.capitalizeFirstLetters("peter   john"));

        // valid name
        assertEquals("Peter John May", Name.capitalizeFirstLetters("peter john may"));
        assertEquals("Peter John May", Name.capitalizeFirstLetters("Peter john May"));
        assertEquals("12345", Name.capitalizeFirstLetters("12345")); // numbers only
        assertEquals("Peter The 2nd", Name.capitalizeFirstLetters("peter the 2nd")); // alphanumeric characters
        assertEquals("David Roger Jackson Ray Jr 2nd",
                Name.capitalizeFirstLetters("david roger jackson ray jr 2nd")); // long names
    }

    @Test
    public void equals() {
        Name name = new Name("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new Name("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Name("Other Valid Name")));

        //same values with different case -> returns true
        assertTrue(name.equals(new Name("valid name")));
    }
}
