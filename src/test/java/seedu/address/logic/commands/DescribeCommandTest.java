package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.EditClientDescriptorBuilder;

public class DescribeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_filteredList_success() {
        showClientAtIndex(model, INDEX_FIRST_PERSON);

        Client clientInFilteredList = model.getSortedFilteredClientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Client editedClient = new ClientBuilder(clientInFilteredList).withName(VALID_NAME_BOB)
                .withDescription(VALID_DESCRIPTION).build();
        DescribeCommand describeCommand = new DescribeCommand(INDEX_FIRST_PERSON,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                        .withDescription(VALID_DESCRIPTION).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedClient));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setClient(model.getSortedFilteredClientList().get(0), editedClient);

        assertCommandSuccess(describeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateClientUnfilteredList_failure() {
        Client firstClient = model.getSortedFilteredClientList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder(firstClient).build();
        DescribeCommand describeCommand = new DescribeCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(describeCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicateClientFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_PERSON);

        // edit client in filtered list into a duplicate in address book
        Client clientInList = model.getAddressBook().getClientList().get(INDEX_SECOND_PERSON.getZeroBased());
        DescribeCommand describeCommand = new DescribeCommand(INDEX_FIRST_PERSON,
                new EditClientDescriptorBuilder(clientInList).build());

        assertCommandFailure(describeCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidClientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredClientList().size() + 1);
        EditCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        DescribeCommand describeCommand = new DescribeCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(describeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidClientIndexFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getClientList().size());

        DescribeCommand describeCommand = new DescribeCommand(outOfBoundIndex,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(describeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DescribeCommand standardCommand = new DescribeCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        DescribeCommand.EditClientDescriptor copyDescriptor = new DescribeCommand.EditClientDescriptor(DESC_AMY);
        DescribeCommand commandWithSameValues = new DescribeCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DescribeCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new DescribeCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        DescribeCommand.EditClientDescriptor editClientDescriptor = new DescribeCommand.EditClientDescriptor();
        DescribeCommand describeCommand = new DescribeCommand(index, editClientDescriptor);
        String expected = DescribeCommand.class.getCanonicalName() + "{index=" + index + ", editClientDescriptor="
                + editClientDescriptor + "}";
        assertEquals(expected, describeCommand.toString());
    }
}
