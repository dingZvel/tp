package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DescribeCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DescribeCommand object
 */
public class DescribeCommandParser implements Parser<DescribeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DescribeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        String[] argList = trimmedArgs.split(" ", 2);
        String id = argList[0];

        if (argList.length > 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DescribeCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(id);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DescribeCommand.MESSAGE_USAGE), pe);
        }

        Optional<String> description;

        if (argList.length == 1) {
            description = Optional.of("");
        } else {
            description = Optional.of(argList[1]);
        }

        EditCommand.EditClientDescriptor editClientDescriptor = new EditCommand.EditClientDescriptor();

        editClientDescriptor.setDescription(ParserUtil.parseDescription(description));

        return new DescribeCommand(index, editClientDescriptor);
    }

}
