package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.client.Client;

/**
 * A utility class containing a list of {@code Client} objects to be used in tests.
 */
public class TypicalClients {

    public static final Client ALICE = new ClientBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withTotalPurchase(10)
            .withProductPreference("shampoo")
            .withDescription("Loves shampoo.")
            .withPriority(3)
            .build();
    public static final Client BENSON = new ClientBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withTotalPurchase(9)
            .withProductPreference("cherry shampoo")
            .withDescription("Loves Cherry Shampoo.")
            .withPriority(2)
            .build();
    public static final Client CARL = new ClientBuilder().withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withAddress("wall street")
            .withTotalPurchase(4)
            .withProductPreference("coffee book")
            .withDescription("Loves coffee and reading.")
            .withPriority(1)
            .build();
    public static final Client DANIEL = new ClientBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withTags("friends")
            .withTotalPurchase(2)
            .withProductPreference("recipe book")
            .withDescription("Loves cooking.")
            .withPriority(3)
            .build();
    public static final Client ELLE = new ClientBuilder().withName("Elle Meyer")
            .withPhone("91482224")
            .withEmail("werner@example.com")
            .withAddress("michegan ave")
            .withTotalPurchase(5)
            .withProductPreference("tea cup")
            .withDescription("Loves tea.")
            .withPriority(2)
            .build();
    public static final Client FIONA = new ClientBuilder().withName("Fiona Kunz")
            .withPhone("91482427")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .withTotalPurchase(6)
            .withProductPreference("coffee cup")
            .withDescription("Loves coffee.")
            .withPriority(1)
            .build();
    public static final Client GEORGE = new ClientBuilder().withName("George Best")
            .withPhone("91482442")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .withTotalPurchase(3)
            .withProductPreference("tea bag")
            .withDescription("Loves tea.")
            .withPriority(1)
            .build();

    // Manually added
    public static final Client HOON = new ClientBuilder().withName("Hoon Meier")
            .withPhone("84827424")
            .withEmail("stefan@example.com")
            .withAddress("little india")
            .withTotalPurchase(5)
            .withProductPreference("shampoo")
            .withDescription("Potential client for shampoo.")
            .withPriority(2)
            .build();
    public static final Client IDA = new ClientBuilder().withName("Ida Mueller")
            .withPhone("84872131")
            .withEmail("hans@example.com")
            .withAddress("chicago ave")
            .withTotalPurchase(2)
            .withProductPreference("coffee")
            .withDescription("Potential client for coffee.")
            .withPriority(1)
            .build();

    // Manually added - Client's details found in {@code CommandTestUtil}
    public static final Client AMY = new ClientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    //Bob's product preference and description are empty
    public static final Client BOB = new ClientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalClients() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical clients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Client client : getTypicalClients()) {
            ab.addClient(client);
        }
        return ab;
    }

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    /**
     * Returns a {@code List} with all the typical clients sorted by frequency.
     */
    public static List<Client> getSortedTypicalClients() {
        List<Client> typicalClients = getTypicalClients();
        typicalClients.sort((c1, c2) ->
                Integer.compare(c2.getTotalPurchase(), c1.getTotalPurchase())
        );
        return typicalClients;
    }
}
