package seedu.homechef.logic.parser;

import static seedu.homechef.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.homechef.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.homechef.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.homechef.testutil.TypicalIndexes.INDEX_FIRST_ORDER;

import org.junit.jupiter.api.Test;

import seedu.homechef.logic.commands.DeleteMenuCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteMenuCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteMenuCommandParser, and therefore we test only one of them.
 * The path variation for those two cases occur inside ParserUtil, and therefore should be
 * covered by ParserUtilTest.
 */
public class DeleteMenuCommandParserTest {

    private final DeleteMenuCommandParser parser = new DeleteMenuCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteMenuCommand() {
        // EP: valid one-based index
        assertParseSuccess(parser, "1", new DeleteMenuCommand(INDEX_FIRST_ORDER));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // EP: non-numeric index
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMenuCommand.MESSAGE_USAGE));
    }
}
