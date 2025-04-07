package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ClientBuilder;

public class ClientTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Client client = new ClientBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> client.getTags().remove(0));
    }

    @Test
    public void isSameClient() {
        // same object -> returns true
        assertTrue(ALICE.isSameClient(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameClient(null));

        // same name, all other attributes different -> returns false
        Client editedAlice = new ClientBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.isSameClient(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ClientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameClient(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Client editedBob = new ClientBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameClient(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Client aliceCopy = new ClientBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different client -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Client editedAlice = new ClientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ClientBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ClientBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Client.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress() + ", tags=" + ALICE.getTags()
                + ", productPreference=" + ALICE.getProductPreference().get()
                + ", totalPurchase=" + ALICE.getTotalPurchase()
                + ", description=" + ALICE.getDescription().get()
                + ", priority=" + ALICE.getPriority().get() + "}";
        assertEquals(expected, ALICE.toString());

        // Test for client without product preference and description
        Client client1 = new ClientBuilder().withEmptyProductPreference().withEmptyDescription().build();
        expected = Client.class.getCanonicalName() + "{name=" + client1.getName() + ", phone=" + client1.getPhone()
                + ", email=" + client1.getEmail() + ", address=" + client1.getAddress() + ", tags=" + client1.getTags()
                + ", productPreference=" + ", totalPurchase=" + client1.getTotalPurchase()
                + ", description="
                + ", priority=" + client1.getPriority().get() + "}";
        assertEquals(expected, client1.toString());

        // Test for client without Priority level
        Client client2 = new ClientBuilder().withEmptyPriority().build();
        expected = Client.class.getCanonicalName() + "{name=" + client2.getName() + ", phone=" + client2.getPhone()
                + ", email=" + client2.getEmail() + ", address=" + client2.getAddress() + ", tags=" + client2.getTags()
                + ", productPreference=" + client2.getProductPreference().get()
                + ", totalPurchase=" + client2.getTotalPurchase()
                + ", description=" + client2.getDescription().get()
                + ", priority=" + "}";
        assertEquals(expected, client2.toString());
    }

    @Test
    void isSameClientSameObjectReturnsTrue() {
        assertTrue(ALICE.isSameClient(ALICE));
    }

    @Test
    void isSameClientNullReturnsFalse() {
        assertFalse(ALICE.isSameClient(null));
    }

    @Test
    void isSameClientSameNameDifferentAttributesReturnsFalse() {
        Client editedAlice = new ClientBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.isSameClient(editedAlice));
    }

    @Test
    void isSameClientDifferentNameSameAttributesReturnsFalse() {
        Client editedAlice = new ClientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameClient(editedAlice));
    }

    @Test
    void isSameClientNameDiffersInCaseReturnsTrue() {
        Client editedBob = new ClientBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameClient(editedBob));
    }
    @Test
    void equalsDifferentProductPreferenceReturnsFalse() {
        Client editedAlice = new ClientBuilder(ALICE).withProductPreference("Conditioner").build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    void equalsDifferentDescriptionReturnsFalse() {
        Client editedAlice = new ClientBuilder(ALICE).withDescription("Loves conditioner.").build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    void equalsDifferentPriorityReturnsFalse() {
        Client editedAlice = new ClientBuilder(ALICE).withPriority(1).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    void equalsSameProductPreferenceReturnsTrue() {
        Client editedAlice = new ClientBuilder(ALICE).withProductPreference("shampoo").build();
        assertTrue(ALICE.equals(editedAlice));
    }

    @Test
    void equalsSameDescriptionReturnsTrue() {
        Client editedAlice = new ClientBuilder(ALICE).withDescription("Loves shampoo.").build();
        assertTrue(ALICE.equals(editedAlice));
    }

    @Test
    void equalsSamePriorityReturnsTrue() {
        Client editedAlice = new ClientBuilder(ALICE).withPriority(3).build();
        assertTrue(ALICE.equals(editedAlice));
    }

    @Test
    void isSameClientDifferentPhoneReturnsFalse() {
        Client editedAlice = new ClientBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.isSameClient(editedAlice));
    }

    @Test
    void isSameClientDifferentEmailReturnsFalse() {
        Client editedAlice = new ClientBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSameClient(editedAlice));
    }

    @Test
    void isSameClientSameNamePhoneEmailAddressReturnsTrue() {
        Client editedAlice = new ClientBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameClient(editedAlice));
    }
}
