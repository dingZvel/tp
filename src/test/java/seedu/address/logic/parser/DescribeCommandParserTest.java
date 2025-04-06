package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DescribeCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.testutil.EditClientDescriptorBuilder;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DescribeCommandParserTest {

    private DescribeCommandParser parser = new DescribeCommandParser();

    @Test
    public void parse_validArgs_returnsDescribeCommand() {
        String userInput = "1" + PREAMBLE_WHITESPACE + VALID_DESCRIPTION;

        EditCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION).build();
        DescribeCommand expectedCommand = new DescribeCommand(INDEX_FIRST_PERSON, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validArgsWithEmptyDescription_returnsDescribeCommand() {
        String userInput = "1";

        EditCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withDescription("").build();
        DescribeCommand expectedCommand = new DescribeCommand(INDEX_FIRST_PERSON, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a saf", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DescribeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "b", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DescribeCommand.MESSAGE_USAGE));
    }
}
