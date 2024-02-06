import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CarDetailerApp extends JFrame {

 // User interface components
 private JComboBox<String> firstServiceComboBox;
 private JComboBox<String> secondServiceComboBox;
 private JComboBox<String> thirdServiceComboBox;
 private JTextField appointmentDateInputField;
 private JTextField appointmentTimeInputField;
 private JTextArea orderSummaryTextArea;

// List to store selected services
private ArrayList<String> selectedServicesList;

// Constructor
public CarDetailerApp() {
initializeUI();
selectedServicesList = new ArrayList<>(); // Initialize the list
}

// Method to initialize the user interface
private void initializeUI() {
setTitle("Car Detailer Application"); // Set window title
setSize(400, 400); // Set window size
setDefaultCloseOperation(EXIT_ON_CLOSE); // Close operation when window is closed

// Create main panel
JPanel panel = new JPanel();
panel.setLayout(new GridLayout(0, 2, 5, 5)); // 2 columns, 5px horizontal and vertical gap

// Add labels and boxes for selecting services
JLabel firstServiceLabel = new JLabel("Select Service 1:");
panel.add(firstServiceLabel);
firstServiceComboBox = new JComboBox<>(new String[]{"Wash", "Wax", "Interior Cleaning"});
panel.add(firstServiceComboBox);

JLabel secondServiceLabel = new JLabel("Select Service 2:");
panel.add(secondServiceLabel);
secondServiceComboBox = new JComboBox<>(new String[]{"None", "Wash", "Wax", "Interior Cleaning"});
panel.add(secondServiceComboBox);

JLabel thirdServiceLabel = new JLabel("Select Service 3:");
panel.add(thirdServiceLabel);
thirdServiceComboBox = new JComboBox<>(new String[]{"None", "Wash", "Wax", "Interior Cleaning"});
panel.add(thirdServiceComboBox);

// Add labels and text fields for appointment
JLabel appointmentDateLabel = new JLabel("Appointment Date:");
panel.add(appointmentDateLabel);
appointmentDateInputField = new JTextField();
panel.add(appointmentDateInputField);

JLabel appointmentTimeLabel = new JLabel("Appointment Time:");
panel.add(appointmentTimeLabel);
appointmentTimeInputField = new JTextField();
panel.add(appointmentTimeInputField);

// Add button to add services
JButton addButton = new JButton("Add to Order");
addButton.addActionListener(new ActionListener() {

public void actionPerformed(ActionEvent e) {
addSelectionToOrder(); // Call method to add selected services to order
}
});
panel.add(addButton);

// Add button to place order
JButton placeOrderButton = new JButton("Place Order");
placeOrderButton.addActionListener(new ActionListener() {

public void actionPerformed(ActionEvent e) {
placeOrder(); // Call method to place the order
}
});
panel.add(placeOrderButton);

// Add text area for displaying order summary
orderSummaryTextArea = new JTextArea();
JScrollPane scrollPane = new JScrollPane(orderSummaryTextArea);
scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
panel.add(scrollPane);

// Add panel to the frame
add(panel);
}

// Method to add selected services to the order
private void addSelectionToOrder() {
// Get selected services from combo boxes
String selectedService1 = (String) firstServiceComboBox.getSelectedItem();
String selectedService2 = (String) secondServiceComboBox.getSelectedItem();
String selectedService3 = (String) thirdServiceComboBox.getSelectedItem();

selectedServicesList.clear(); // Clear previous selections

// Add selected services to the list if not "None"
if (!selectedService1.equals("None")) {
selectedServicesList.add(selectedService1);
}
if (!selectedService2.equals("None")) {
selectedServicesList.add(selectedService2);
}
if (!selectedService3.equals("None")) {
selectedServicesList.add(selectedService3);
}

// Update order summary text area
orderSummaryTextArea.setText(""); // Clear previous content
for (String service : selectedServicesList) {
orderSummaryTextArea.append("- " + service + "\n");
}
}

// Method to place the order
private void placeOrder() {
// Get appointment date and time from input fields
String appointmentDate = appointmentDateInputField.getText();
String appointmentTime = appointmentTimeInputField.getText();

try (FileWriter writer = new FileWriter("order.txt")) {
// Write order summary to a text file
writer.write("Appointment Date: " + appointmentDate + "\n");
writer.write("Appointment Time: " + appointmentTime + "\n");
writer.write("Services:\n");
for (String service : selectedServicesList) {
writer.write("- " + service + "\n");
}
writer.flush();
// Display success message
JOptionPane.showMessageDialog(this, "Order placed successfully! Order summary saved to order_summary.txt");
} catch (IOException e) {
// Display error message if an error occurs
JOptionPane.showMessageDialog(this, "Error occurred while placing order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}
}

// Main method to start the application
public static void main(String[] args) {
SwingUtilities.invokeLater(() -> {
CarDetailerApp app = new CarDetailerApp();
app.setVisible(true);
});
}
}
