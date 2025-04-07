package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;




public class FilterCommandParserTest {

    private final FilterCommandParser parser = new FilterCommandParser();

    @Test
    void parse_invalidPriorityFilter_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" priority/invalid"));
    }

    @Test
    void parse_invalidPreferenceFilter_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" pref/"));
    }

    @Test
    void parse_validPriorityFilterWithWhitespace_returnsFilterCommand() throws Exception {
        FilterCommand command = parser.parse(" priority/ 1 ");
        String expected = "seedu.address.logic.commands.FilterCommand{predicate=Priority: Optional[STANDARD]}";
        assertEquals(expected, command.toString());
    }

    @Test
    void parse_validPreferenceFilterWithMultipleKeywords_returnsFilterCommand() throws Exception {
        FilterCommand command = parser.parse(" pref/shampoo conditioner");
        String expected = "seedu.address.logic.commands.FilterCommand{predicate=ProductPreference: "
                + "Optional[shampoo, conditioner]}";
    }

    @Test
    void parse_invalidPrefix_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" invalid/1"));
    }
}
