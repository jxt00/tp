package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTITY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AB3Model;
import seedu.address.model.AB3ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListAB3CommandTest {

    private AB3Model model;
    private AB3Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new AB3ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new AB3ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAB3Command(), model, ListAB3Command.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_ENTITY);
        assertCommandSuccess(new ListAB3Command(), model, ListAB3Command.MESSAGE_SUCCESS, expectedModel);
    }
}
