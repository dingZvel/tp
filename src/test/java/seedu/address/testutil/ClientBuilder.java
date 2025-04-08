package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Description;
import seedu.address.model.client.Email;
import seedu.address.model.client.Frequency;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.Priority;
import seedu.address.model.client.ProductPreference;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Client objects.
 */
public class ClientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_PRODUCT_PREFERENCE = "shampoo";
    public static final int DEFAULT_TOTAL_PURCHASE = 0;
    public static final String DEFAULT_DESCRIPTION = "Loves shampoo.";
    public static final Priority DEFAULT_PRIORITY = Priority.VIP;

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Optional<ProductPreference> productPreference;
    private int totalPurchase;
    private Optional<Description> description;
    private Optional<Priority> priority;

    /**
     * Creates a {@code ClientBuilder} with the default details.
     */
    public ClientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        totalPurchase = DEFAULT_TOTAL_PURCHASE;
        productPreference = Optional
                .of(new ProductPreference(DEFAULT_PRODUCT_PREFERENCE, new Frequency(DEFAULT_TOTAL_PURCHASE)));
        description = Optional.of(new Description(DEFAULT_DESCRIPTION));
        priority = Optional.of(DEFAULT_PRIORITY);
    }

    /**
     * Initializes the ClientBuilder with the data of {@code clientToCopy}.
     */
    public ClientBuilder(Client clientToCopy) {
        name = clientToCopy.getName();
        phone = clientToCopy.getPhone();
        email = clientToCopy.getEmail();
        address = clientToCopy.getAddress();
        tags = new HashSet<>(clientToCopy.getTags());
        productPreference = clientToCopy.getProductPreference();
        totalPurchase = clientToCopy.getTotalPurchase();
        description = clientToCopy.getDescription();
        priority = clientToCopy.getPriority();
    }

    /**
     * Sets the {@code Name} of the {@code Client} that we are building.
     */
    public ClientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Client} that we are building.
     */
    public ClientBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Client} that we are building.
     */
    public ClientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Client} that we are building.
     */
    public ClientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Client} that we are building.
     */
    public ClientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Product Preference} of the {@code Client} that we are building.
     * Should be called after withTotalPurchase to set the correct product preference frequency.
     */
    public ClientBuilder withProductPreference(String productPreference) {
        this.productPreference = Optional.of(new ProductPreference(productPreference, new Frequency(totalPurchase)));
        return this;
    }

    /**
     * Sets the {@code TotalPurchase} of the {@code Client} that we are building.
     * Should be called before withProductPreference.
     * Unless a frequency of 0 is desired for product preference.
     */
    public ClientBuilder withTotalPurchase(int totalPurchase) {
        this.totalPurchase = totalPurchase;
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Client} that we are building.
     */
    public ClientBuilder withDescription(String description) {
        this.description = Optional.of(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Client} that we are building to be empty.
     */
    public ClientBuilder withEmptyProductPreference() {
        this.productPreference = Optional.empty();
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Client} that we are building to be empty.
     */
    public ClientBuilder withEmptyDescription() {
        this.description = Optional.empty();
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Client} that we are building to be empty.
     */
    public ClientBuilder withEmptyPriority() {
        this.priority = Optional.empty();
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Client} that we are building.
     */
    public ClientBuilder withPriority(int priority) {
        this.priority = Optional.of(Priority.fromInt(priority));
        return this;
    }


    public Client build() {
        return new Client(name, phone, email, address, tags, productPreference, description, priority);
    }

}
