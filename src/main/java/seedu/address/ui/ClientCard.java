package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.client.Client;
import seedu.address.model.client.ProductPreference;

/**
 * An UI component that displays information of a {@code Client}.
 */
public class ClientCard extends UiPart<Region> {

    private static final String FXML = "ClientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Client client;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane productPreferenceWithFrequency;
    @FXML
    private FlowPane totalPurchase;
    @FXML
    private FlowPane priority;
    @FXML
    private FlowPane description;

    /**
     * Creates a {@code ClientCode} with the given {@code Client} and index to display.
     */
    public ClientCard(Client client, int displayedIndex) {
        super(FXML);
        this.client = client;
        id.setText(displayedIndex + ". ");
        name.setText(client.getName().fullName);
        phone.setText(client.getPhone().value);
        address.setText(client.getAddress().value);
        email.setText(client.getEmail().value);
        client.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        totalPurchase.getChildren().add(new Label("total purchase: " + String.valueOf(client.getTotalPurchase())));
        if (client.getProductPreference().isPresent() && !client.getProductPreference().get().toString().isEmpty()) {
            productPreferenceWithFrequency.getChildren()
                    .add(new Label(client.getProductPreference()
                            .map(ProductPreference::toString).orElse("") + ": "
                            + client.getProductPreference().get().frequency));
        } else {
            productPreferenceWithFrequency.setVisible(false);
        }

        if (client.getPriority().isPresent() && !client.getPriority().get().toString().isEmpty()) {
            priority.getChildren().add(new Label(client.getPriority()
                    .map(priority -> priority.toString()).orElse("")));
        } else {
            priority.setVisible(false);
        }

        if (client.getDescription().isPresent() && !client.getDescription().get().toString().isEmpty()) {
            description.getChildren().add(new Label(client.getDescription()
                    .map(description -> description.toString()).orElse("")));
        } else {
            description.setVisible(false);
        }
    }
}
