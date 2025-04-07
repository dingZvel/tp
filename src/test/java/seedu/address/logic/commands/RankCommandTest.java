package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_RANKED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.FindCommandTest.preparePredicate;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BENSON;
import static seedu.address.testutil.TypicalClients.CARL;
import static seedu.address.testutil.TypicalClients.DANIEL;
import static seedu.address.testutil.TypicalClients.ELLE;
import static seedu.address.testutil.TypicalClients.FIONA;
import static seedu.address.testutil.TypicalClients.GEORGE;
import static seedu.address.testutil.TypicalClients.getSortedTypicalClients;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.client.predicates.ClientSatisfyAllPredicate;
import seedu.address.model.sortcomparators.TotalPurchaseComparator;


public class RankCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Comparator<Client> firstComparator = (o1, o2) -> 0;
        Comparator<Client> secondComparator = (o1, o2) -> 0;


        RankCommand rankFirstCommand = new RankCommand(firstComparator);
        RankCommand rankSecondCommand = new RankCommand(secondComparator);

        // same object -> returns true
        assertTrue(rankFirstCommand.equals(rankFirstCommand));

        // same values -> returns true
        RankCommand rankFirstCommandCopy = new RankCommand(firstComparator);
        assertTrue(rankFirstCommand.equals(rankFirstCommandCopy));

        // different types -> returns false
        assertFalse(rankFirstCommand.equals(1));

        // null -> returns false
        assertFalse(rankFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(rankFirstCommand.equals(rankSecondCommand));
    }

    @Test
    public void execute_nonFiltered_success() {
        String expectedMessage = String.format(MESSAGE_PERSONS_RANKED, 7);
        Comparator<Client> comparator = new TotalPurchaseComparator();
        RankCommand command = new RankCommand(comparator);
        expectedModel.sortFilteredClientList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getSortedTypicalClients(), model.getSortedFilteredClientList());
    }

    @Test
    public void execute_filtered_success() {
        String expectedMessage = String.format(MESSAGE_PERSONS_RANKED, 2);
        Comparator<Client> comparator = new TotalPurchaseComparator();;
        RankCommand command = new RankCommand(comparator);

        ClientSatisfyAllPredicate predicate = preparePredicate("coffee");
        model.updateFilteredClientList(predicate);
        expectedModel.updateFilteredClientList(predicate);
        expectedModel.sortFilteredClientList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA, CARL), model.getSortedFilteredClientList());

        expectedMessage = String.format(MESSAGE_PERSONS_RANKED, 3);
        predicate = preparePredicate("Kurz Elle Kunz");
        model.updateFilteredClientList(predicate);
        expectedModel.updateFilteredClientList(predicate);
        expectedModel.sortFilteredClientList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA, ELLE, CARL), model.getSortedFilteredClientList());

        expectedMessage = String.format(MESSAGE_PERSONS_RANKED, 3);
        predicate = preparePredicate("coffee book");
        model.updateFilteredClientList(predicate);
        expectedModel.updateFilteredClientList(predicate);
        expectedModel.sortFilteredClientList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA, CARL, DANIEL), model.getSortedFilteredClientList());

        expectedMessage = String.format(MESSAGE_PERSONS_RANKED, 5);
        predicate = preparePredicate("cup friends");
        model.updateFilteredClientList(predicate);
        expectedModel.updateFilteredClientList(predicate);
        expectedModel.sortFilteredClientList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, FIONA, ELLE, DANIEL), model.getSortedFilteredClientList());

        expectedMessage = String.format(MESSAGE_PERSONS_RANKED, 4);
        predicate = preparePredicate("fiona shampoo bag");
        model.updateFilteredClientList(predicate);
        expectedModel.updateFilteredClientList(predicate);
        expectedModel.sortFilteredClientList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, FIONA, GEORGE), model.getSortedFilteredClientList());
    }

    @Test
    public void toStringMethod() {
        Comparator<Client> comparator = new TotalPurchaseComparator();;
        RankCommand rankCommand = new RankCommand(comparator);
        String expected = RankCommand.class.getCanonicalName() + "{comparator=" + comparator + "}";
        assertEquals(expected, rankCommand.toString());
    }

}
