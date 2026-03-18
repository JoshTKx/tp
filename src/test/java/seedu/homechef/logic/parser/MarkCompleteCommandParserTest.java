package seedu.homechef.logic.parser;

import static seedu.homechef.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.homechef.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.homechef.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.homechef.testutil.TypicalIndexes.INDEX_FIRST_ORDER;

import org.junit.jupiter.api.Test;

import seedu.homechef.logic.commands.MarkCompleteCommand;

public class MarkCompleteCommandParserTest {

    private MarkCompleteCommandParser parser = new MarkCompleteCommandParser();

    @Test
    public void parse_validArgs_returnsMarkCompleteCommand() {
        assertParseSuccess(parser, "1", new MarkCompleteCommand(INDEX_FIRST_ORDER));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(
                parser,
                "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCompleteCommand.MESSAGE_USAGE));
    }
}
