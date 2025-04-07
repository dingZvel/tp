package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final int totalPurchase;
    private final Optional<ProductPreference> productPreference;
    private final Optional<Description> description;
    private final Optional<Priority> priority;

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Address address,
                  Set<Tag> tags, Optional<ProductPreference> productPreference, Optional<Description> description,
                  Optional<Priority> priority) {
        requireAllNonNull(name, phone, email, address, tags, productPreference, description, priority);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.productPreference = productPreference;
        this.totalPurchase = productPreference
                .map(x -> x.getFrequency().frequency).orElse(0);
        this.description = description;
        this.priority = priority;
    }


    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public int getTotalPurchase() {
        return totalPurchase;
    }

    public Optional<ProductPreference> getProductPreference() {
        return productPreference;
    }

    public Optional<Description> getDescription() {
        return description;
    }

    public Optional<Priority> getPriority() {
        return priority;
    }

    public String getFullName() {
        return name.fullName;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName())
                && otherClient.getPhone().equals(getPhone())
                && otherClient.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both clients have the same identity and data fields.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return name.equals(otherClient.name)
                && phone.equals(otherClient.phone)
                && email.equals(otherClient.email)
                && address.equals(otherClient.address)
                && tags.equals(otherClient.tags)
                && productPreference.equals(otherClient.productPreference)
                && description.equals(otherClient.description)
                && priority.equals(otherClient.priority);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, productPreference, description, priority);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("productPreference", productPreference.map(ProductPreference::toString).orElse(""))
                .add("totalPurchase", totalPurchase)
                .add("description", description.map(Description::toString).orElse(""))
                .add("priority", priority.map(Priority::toString).orElse(""))
                .toString();
    }

}
